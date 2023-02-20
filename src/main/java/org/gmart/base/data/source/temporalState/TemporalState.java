package org.gmart.base.data.source.temporalState;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.gmart.base.data.structure.dim1.ListBasedBuilder;
import org.gmart.base.data.structure.tuple.Pair;


public interface TemporalState<T> extends Temporal {
	
	T getState();
	public static abstract class StableTemporalState<T> implements TemporalState<T> {
		@Override
		public void evolve() {}
		@Override
		public boolean isStable() {
			return true;
		}
	}
	public static <X,Y> TemporalState<Y> intoSourceFrom(TemporalState<X> state, Function<X, Y> mapping) {
		return new StableTemporalState<Y>() {
			@Override
			public Y getState() {
				return mapping.apply(state.getState());
			}
		};
	}
	public static TemporalState<Boolean> alwaysTrue = new StableTemporalState<>() {
		@Override
		public Boolean getState() {
			return true;
		}
	};
	public static TemporalState<Boolean> alwaysFalse = new StableTemporalState<>() {
		@Override
		public Boolean getState() {
			return false;
		}
	};
	default Supplier<T> intoSupplier() {
		return new Supplier<>() {
			@Override
			public T get() {
				return getState();
			}
		};
	}
	default <Y> Supplier<Y> intoSupplier(Function<? super T, Y> mapping) {
		return new Supplier<>() {
			@Override
			public Y get() {
				return mapping.apply(TemporalState.this.getState());
			}
		};
//		return new TemporalState<Y>() {
//			@Override
//			public void evolve() {/*no evolution, it's a dependency, a source that will evolve elsewhere*/}
//			@Override
//			public boolean isStable() {
//				return true;//state.isStable();
//			}
//			@Override
//			public Y getState() {
//				return mapping.apply(TemporalState.this.getState());
//			}
//		};
	}
	public static <X,Y> TemporalState<Y> from(TemporalState<X> state, Function<X, Y> mapping) {
		return new TemporalState<Y>() {
			@Override
			public Y getState() {
				return mapping.apply(state.getState());
			}
			@Override
			public void evolve() {
				state.evolve();
			}
			@Override
			public boolean isStable() {
				return state.isStable();
			}
		};
	}
	
	public static <T> TemporalState<T> of(T initState, Iterator<T> stateIterator){
		return new TemporalState_IteratorImpl<>(initState, stateIterator);
	}
	public static <T> TemporalState<T> of(Iterator<T> stateIterator){
		return new TemporalState_IteratorImpl<>(stateIterator);
	}
	public static class TemporalState_IteratorImpl<T> implements TemporalState<T> {
		Iterator<? extends T> stateIterator;
		T currentState;
		public TemporalState_IteratorImpl(T initState, Iterator<? extends T> stateIterator){
			this.stateIterator = stateIterator;
			this.currentState = initState;
		}
		public TemporalState_IteratorImpl(Iterator<T> stateIterator_initStateWithFirstElementIfAny){
			this.stateIterator = stateIterator_initStateWithFirstElementIfAny;
			evolve();
		}
		@Override
		public void evolve() {
			if(stateIterator.hasNext())
				currentState = stateIterator.next();
		}
		@Override
		public boolean isStable() {
			return !stateIterator.hasNext();
		}
		@Override
		public T getState() {
			return currentState;
		}
	}
	
	public static abstract class AbstractTemporalState<T> implements TemporalState<T> {
		long iterationNumber;
		long currentIteration;
		public long getIterationNumber() {
			return iterationNumber;
		}
		public long getCurrentIteration() {
			return currentIteration;
		}
		public AbstractTemporalState(long iterationNumber){
			this.iterationNumber = iterationNumber;
			currentIteration = 0;
		}
		@Override
		public void evolve() {
			currentIteration++;
		}
		boolean isInternallyStable = false;
		@Override
		public boolean isStable() {
			if(!isInternallyStable)
				isInternallyStable = currentIteration == iterationNumber;
			return isInternallyStable;
		}
		
	}
	public static class DependentTemporalState<T> implements TemporalState<T> {
		T current;
		public void setState(T state) {
			this.current = state;
		}
		public DependentTemporalState(){}
		@Override
		public T getState() {
			return current;
		}
		@Override
		public void evolve() {}
		@Override
		public boolean isStable() {
			return true;
		} 
	}
	
	
	
	public static class TemporalStateConstant<T> extends AbstractTemporalState<T> {
		T constant;
		public TemporalStateConstant(long iterationNumber, T constant){
			super(iterationNumber);
			this.constant = constant;
		}
		@Override
		public T getState() {
			return constant;
		} 
	}
	public interface TemporalStateWithStateListener<T extends Temporal> extends TemporalState<T> {
		void setStateChangeListener(Consumer<T> stateChangeListener);
	}
	public abstract static class AbstractSequence<T extends Temporal> implements Temporal {
		T current;
		
		public AbstractSequence() {}
//		protected boolean setupNextChildCondition() {
//			return current.isStable() && currentIndex < tss.size()-1;
//		}
		Consumer<T> stateChangeListener;
		public void setStateChangeListener(Consumer<T> stateChangeListener) {
			this.stateChangeListener = stateChangeListener;
		}
		protected void afterHavingSetupNextChildHook() {
			if(stateChangeListener != null) {
				stateChangeListener.accept(current);
			}
		}
	}
	
	public abstract static class AbstractSuppliedSequence<T extends Temporal> extends AbstractSequence<T> implements Temporal {
		Supplier<T> tss;
		
		public AbstractSuppliedSequence(Supplier<T> tss) {
			this.tss = tss;
			this.current = tss.get();
		}
//		protected boolean setupNextChildCondition() {
//			return current.isStable() && currentIndex < tss.size()-1;
//		}
		@Override
		public void evolve() {
			current.evolve();
			if(current.isStable()) {
				current = tss.get();
				afterHavingSetupNextChildHook();
			}
		}
	}
	public abstract static class AbstractTemporalStateSequence<T extends Temporal> extends AbstractSequence<T> implements Temporal {
		List<T> tss;
		int currentIndex;
		
		public AbstractTemporalStateSequence(List<T> tss) {
			assert tss.size() > 0;
			this.tss = tss;
			this.current = tss.get(0);
			this.currentIndex = 0;
		}
//		protected boolean setupNextChildCondition() {
//			return current.isStable() && currentIndex < tss.size()-1;
//		}
		@Override
		public void evolve() {
			current.evolve();
			if(current.isStable() && currentIndex < tss.size()-1) {
				current = tss.get(++currentIndex);
				afterHavingSetupNextChildHook();
			}
		}
	}

	public abstract static class TemporalStateSequenceImpl<T extends Temporal> extends AbstractTemporalStateSequence<T> {
		public TemporalStateSequenceImpl(List<T> tss) {
			super(tss);
		}
		@Override
		public boolean isStable() {
			//System.out.println("current.isStable():" + current.isStable() + " currentIndex == tss.size()-1:"  + (currentIndex == tss.size()-1));
			return current.isStable() && currentIndex == tss.size()-1;
		}
	}

	public static class SuppliedSequenceImpl<T extends Temporal> extends AbstractSuppliedSequence<T> {
		public SuppliedSequenceImpl(Supplier<T> tss) {
			super(tss);
		}
		@Override
		public boolean isStable() {
			return false;
		}
	}
	
//	public abstract static class TemporalStateSequence_circular<T extends Temporal> extends AbstractTemporalStateSequence<T> {
//	StableAfterDurationTemporal timer;
//	public TemporalStateSequence_circular(long iterationNumberDuration, List<T> tss) {
//		super(tss);
//		timer = StableAfterDurationTemporal.of(iterationNumberDuration);
//	}
//	protected boolean setupNextChildCondition() {
//		return current.isStable();
//	}
//	@Override
//	public boolean isStable() {
//		return timer.isStable();
//	}
//}
//	public static class TemporalStateSequenceWithChangeListenerImpl_circular<T extends Temporal> extends TemporalStateSequence_circular<T> {
//		public TemporalStateSequenceWithChangeListenerImpl_circular(long iterationNumberDuration, List<T> tss) {
//			super(iterationNumberDuration, tss);
//		}
//		Consumer<T> stateChangeListener;
//		public void setStateChangeListener(Consumer<T> stateChangeListener) {
//			this.stateChangeListener = stateChangeListener;
//		}
//		protected void afterHavingSetupNextChildHook() {
//			if(stateChangeListener != null) {
//				stateChangeListener.accept(current);
//			}
//		}
//	}
//	public static class TemporalSequence_circular<T extends Temporal> extends TemporalStateSequenceWithChangeListenerImpl_circular<T> implements TemporalStateWithStateListener<T> {
//		public TemporalSequence_circular(long iterationNumberDuration, List<T> tss) {
//			super(iterationNumberDuration, tss);
//		}
//		@Override
//		public T getState() {
//			return current;
//		}
//		public static  <T extends Temporal> TemporalStateWithStateListener<T> of(long iterationNumberDuration, List<T> sequenceElem){
//			return new TemporalSequence_circular<T>(iterationNumberDuration, sequenceElem);
//		}
//	}
	public static class SuppliedSequence<T extends Temporal> extends SuppliedSequenceImpl<T> implements TemporalStateWithStateListener<T> {
//		public static class Builder<T extends Temporal> extends FromListBuilder<T, TemporalState<T>>{
//			public TemporalState<T> build() {
//				return new TemporalSequence<T>(tss);
//			}
//		}
		public SuppliedSequence(Supplier<T> tss) {
			super(tss);
		}
		@Override
		public T getState() {
			return current;
		}
		
		public static <T extends Temporal> TemporalStateWithStateListener<T> of(Supplier<T> suppliedSequence){
			return new SuppliedSequence<>(suppliedSequence);
		}
		
	}
	public static class TemporalSequence<T extends Temporal> extends TemporalStateSequenceImpl<T> implements TemporalStateWithStateListener<T> {
//		public static class Builder<T extends Temporal> extends FromListBuilder<T, TemporalState<T>>{
//			public TemporalState<T> build() {
//				return new TemporalSequence<T>(tss);
//			}
//		}
		public TemporalSequence(List<T> tss) {
			super(tss);
		}
		@Override
		public T getState() {
			return current;
		}
		@SafeVarargs
		public static <T extends Temporal> TemporalSequence<T> of(T ... sequenceElem){
			return new TemporalSequence<>(Arrays.asList(sequenceElem));
		}
		public static <T extends Temporal> TemporalSequence<T> of(List<T> sequenceElem){
			return new TemporalSequence<>(sequenceElem);
		}
		
	}
	
	public static abstract class TemporalStateSequenceBuilder<T, ConcreteBuilder> extends ListBasedBuilder<TemporalState<? extends T>, TemporalState<T>, ConcreteBuilder>{}
	
	public static class TemporalStateSequence<T> extends TemporalStateSequenceImpl<TemporalState<? extends T>> implements TemporalState<T> {
		public static class Builder<T> extends TemporalStateSequenceBuilder<T, Builder<T>> {
			public Builder(){
				super();
			}
			public TemporalStateSequence<T> build() {
				return new TemporalStateSequence<T>(elements);
			}
			@Override
			public Builder<T> getThis() {
				return this;
			}
		}
		public static <T> TemporalStateSequence<T> of(List<TemporalState<? extends T>> elements){
			return new TemporalStateSequence<>(elements);
		}
		@SafeVarargs
		public static <T> TemporalStateSequence<T> of(TemporalState<? extends T> ... elements){
			return new TemporalStateSequence<>(Arrays.asList(elements));
		}
		public TemporalStateSequence(List<TemporalState<? extends T>> tss){
			super(tss);
		}
		@Override
		public T getState() {
			return current.getState();
		}
	}
	
	
	public static class ChainedTemporalStateSequence<T> extends TemporalStateSequenceImpl<TemporalState<? extends T>> implements TemporalState<T> {
		public static class Builder<T> extends TemporalStateSequenceBuilder<T, Builder<T>> {
			BinaryOperator<T> completeStateCalculator;
			public Builder(BinaryOperator<T> completeStateCalculator){
				super();
				this.completeStateCalculator = completeStateCalculator;
			}
			public ChainedTemporalStateSequence<T> build() {
				return new ChainedTemporalStateSequence<>(completeStateCalculator, elements);
			}
			@Override
			public Builder<T> getThis() {
				return this;
			}
		}
		public ChainedTemporalStateSequence(BinaryOperator<T> completeStateCalculator, List<TemporalState<? extends T>> tss){
			super(tss);
			this.completeStateCalculator = completeStateCalculator;
			this.initState = null;
			this.completeState = null;
		}

		T initState;
		T completeState;
		BinaryOperator<T> completeStateCalculator;
		
		private T makeCompleteState() {
			return initState == null ? 
					current.getState() :
					completeStateCalculator.apply(initState, current.getState());
		}
		
		@Override
		public void evolve() {
			if(current.isStable()) {
				if(currentIndex < tss.size()-1) {
					currentIndex++;
					initState = this.getState();
					current = tss.get(currentIndex);
					completeState = initState;
				}
			} else {
				current.evolve();
				completeState = makeCompleteState();
			}
		}
		@Override
		public T getState() {
			if(completeState == null)
				return current.getState(); 
			return completeState;
		}
//		public TemporalStateSequence2(BinaryOperator<T> completeStateCalculator){
//		super();
//		this.completeStateCalculator = completeStateCalculator;
//	}
//	public TemporalStateSequence2(TemporalState<? extends T> ts1, BinaryOperator<T> completeStateCalculator){
//		super(ts1);
//		this.completeStateCalculator = completeStateCalculator;
//	}
//	public TemporalStateSequence2(TemporalState<? extends T> ts1, TemporalState<? extends T> ts2, BinaryOperator<T> completeStateCalculator){
//		super(ts1, ts2);
//		this.completeStateCalculator = completeStateCalculator;
//	}
	}
	
	
	
	
	public static abstract class AbstractTemporalBiState<T1, T2, T> implements TemporalState<T> {
		TemporalState<T1> ts1;
		TemporalState<T2> ts2;
		public AbstractTemporalBiState(TemporalState<T1> ts1, TemporalState<T2> ts2) {
			super();
			this.ts1 = ts1;
			this.ts2 = ts2;
		}
		@Override
		public void evolve() {
			ts1.evolve();
			ts2.evolve();
		}
		@Override
		public boolean isStable() {
			return ts1.isStable() && ts2.isStable();
		}
	}
	default <T2> TemporalState<Pair<T,T2>> bi(TemporalState<T2> ts2){
		return new AbstractTemporalBiState<T,T2, Pair<T, T2>>(this, ts2) {
			@Override
			public Pair<T, T2> getState() {
				return Pair.with(ts1.getState(), ts2.getState());
			}
		};
	}
	default <T2> TemporalState<Pair<T,T2>> bi(Iterator<T2> it2){
		return this.bi(TemporalState.of(it2));
	}

	
//	public interface TemporalDouble extends TemporalState<Double> {
//		default TemporalState<BiDouble> bi(TemporalDouble ts2){
//			return new AbstractTemporalBiState<Double, Double, BiDouble>(this, ts2) {
//				@Override
//				public BiDouble getState() {
//					return BiDouble.of(ts1.getState(), ts2.getState());
//				}
//			};
//		}
//	}
//	public static TemporalDouble of(DoubleIterator stateIterator){
//		return new TemporalDoubleImpl(stateIterator);
//	}
//	public class TemporalDoubleImpl extends TemporalState_IteratorImpl<Double> implements TemporalDouble {
//		public TemporalDoubleImpl(Iterator<Double> stateIterator_initStateWithFirstElementIfAny) {
//			super(stateIterator_initStateWithFirstElementIfAny);
//		}
//		public TemporalDoubleImpl(Double initState, Iterator<Double> stateIterator) {
//			super(initState, stateIterator);
//		}
//	}


//default TemporalState<T> then(TemporalState<? extends T> ts2){
//	return new TemporalStateSequence<>(this, ts2);
//}
//default TemporalState<T> then(Iterator<T> ts2){
//	System.out.println("hhhhh");
//	return new TemporalStateSequence<>(this, TemporalState.of(ts2));
//}

//default TemporalState<T> then(TemporalState<T> ts2, BinaryOperator<T> completeStateCalculator){
//	return new TemporalStateSequence2<>(this, ts2, completeStateCalculator);
//}
//default TemporalState<T> then(Iterator<T> ts2, BinaryOperator<T> completeStateCalculator){
//	return new TemporalStateSequence2<>(this, TemporalState.of(ts2), completeStateCalculator);
//}
//default TemporalState<T> then(BinaryOperator<T> completeStateCalculator){
//	return new TemporalStateSequence2<>(this, completeStateCalculator);
//}
//public static <T> TemporalState<T> chainedSequence(BinaryOperator<T> completeStateCalculator){
//	return new TemporalStateSequence2<>(completeStateCalculator); 
//}

}
