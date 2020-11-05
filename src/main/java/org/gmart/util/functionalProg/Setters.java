/*******************************************************************************
 * Copyright 2020 Grégoire Martinetti
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package org.gmart.util.functionalProg;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.gmart.util.functionalProg.math.integrator.IntegratorInt_post;

public class Setters {
	
	public static abstract class F<E,Y> {
		protected abstract Y apply_internal(E input);
	}
	
	
	
	public static interface BaseInterface<E,Y> {
		
	}
	public static interface FC<E,Y> extends BaseInterface<E,Y>, Consumer<E> {
//		default void forEach(Stream<E> inputs) {
//			inputs.forEachOrdered(e->this.accept(e));
//		}
	}
	public static interface FS<E,Y> extends BaseInterface<E,Y>, Supplier<Y> {
		
	}
	public static interface FCS<E,Y> extends BaseInterface<E,Y>, Consumer<E>, Supplier<Y>, Runnable {
	}
	
	
	public abstract static class FImpl<E,Y> extends F<E,Y> {
		public FImpl(Function<E,Y> internalFunction) {
			this.internalFunction = internalFunction;
		}
		protected final Function<E,Y> internalFunction;
		protected Y apply_internal(E input) {
			return internalFunction.apply(input);
		}
	}
	
	
	public static abstract class AbstractFCImpl<E,Y> extends FImpl<E,Y> implements FC<E,Y> {
		public AbstractFCImpl(Function<E,Y> internalFunction) {
			super(internalFunction);
		}
		
		protected abstract void putOutput(Y output);
		@Override
		public void accept(E input) {
			Y y = apply_internal(input);
			putOutput(y);
		}
	}
	public static class FCImpl<E,Y> extends AbstractFCImpl<E,Y> implements FC<E,Y>{
		public FCImpl(Function<E,Y> internalFunction, Consumer<Y> outputConsumer) {
			super(internalFunction);
			this.outputConsumer = outputConsumer;
		}
		
		protected final Consumer<Y> outputConsumer;
		protected void putOutput(Y output) {
			outputConsumer.accept(output);
		}
	}
//	public static <E,Y> FC<E,Y> make(Function<E,Y> internalFunction, Consumer<Y> outputConsumer) {
//		return new FCImpl<>(internalFunction, outputConsumer);
//	}
	public static <E,Y> Consumer<E> make(Function<E,Y> internalFunction, Consumer<Y> outputConsumer) {
		return e -> outputConsumer.accept(internalFunction.apply(e));
	}
//	public static <Z,A> Consumer<Stream<A>> make_streamIn(Function<A,Z> internalFunction, Consumer<Stream<Z>> outputConsumer) {
//		return new FCImpl<>(CartesianProduct.streamMap(internalFunction), outputConsumer);
//	}
	public static <Z,A> FC<Stream<A>,Stream<Z>> make_streamIn(Function<A,Z> internalFunction, Consumer<Stream<Z>> outputConsumer) {
		return e -> outputConsumer.accept(e.map(internalFunction));//new FCImpl<>(CartesianProduct.streamMap(internalFunction), outputConsumer);
	}
//	public static <I,Z,A> FC<Stream<RezWithIn<I,A>>, Stream<RezWithIn<I,Z>>> make_streamIn2(Function<A,Z> internalFunction, Consumer<Stream<RezWithIn<I,Z>>> outputConsumer) {
//		return e -> outputConsumer.accept(e.map(RezWithIn.mapAdaptor(internalFunction)));//new FCImpl<>(CartesianProduct.streamMap2(internalFunction), outputConsumer);
//	}
//	public static <E,Y> FC<E,Y> make(Function<E,Y> internalFunction, Consumer<Y> outputConsumer) {
//		return new FCImpl<>(internalFunction, outputConsumer);
//	}
	public static <E,Y> void exec(Stream<E> inputs, Function<E,Y> internalFunction, Consumer<Y> outputConsumer) {
		inputs.forEach(make(internalFunction, outputConsumer));
	}
	public static <E,Y> void exec(Stream<E> inputs, FC<E,Y> f) {
		inputs.forEach(f);
	}
	
	public static class FCImpl2<E,Y> extends AbstractFCImpl<E,Y> implements FC<E,Y>{
		public FCImpl2(Function<E,Y> internalFunction, Function<E,Consumer<Y>> inputToOutputConsumer) {
			super(internalFunction);
			this.inputToOutputConsumer = inputToOutputConsumer;
		}
		Function<E,Consumer<Y>> inputToOutputConsumer;
		
		@Override
		public void accept(E input) {
			Consumer<Y> outputConsumer = inputToOutputConsumer.apply(input);
			Y y = apply_internal(input);
			outputConsumer.accept(y);
		}
		
		@Override
		protected void putOutput(Y output) {assert false;} //neverCalled used
	}
	public static <E,Y> FC<E,Y> fromInput(Function<E,Y> internalFunction, Function<E, Consumer<Y>> inputToOutputConsumer) {
		return new FCImpl2<>(internalFunction, inputToOutputConsumer);
	}
	
	
	public static abstract class AbstractFSImpl<E,Y> extends FImpl<E,Y> implements FS<E,Y> {
		public AbstractFSImpl(Function<E,Y> internalFunction) {
			super(internalFunction);
		}
		
		protected abstract E getInput();
		@Override
		public Y get() {
			return apply_internal(getInput());
		}
	}
	public static class FSImpl<E,Y> extends AbstractFSImpl<E,Y> implements FS<E,Y> {
		public FSImpl(Function<E,Y> internalFunction, Supplier<E> inputSupplier) {
			super(internalFunction);
			this.inputSupplier = inputSupplier;
		}
		
		protected final Supplier<E> inputSupplier;
		@Override
		protected E getInput() {
			return inputSupplier.get();
		}
	}
	public static <E,Y> FS<E,Y> make(Function<E,Y> internalFunction, Supplier<E> inputSupplier) {
		return new FSImpl<>(internalFunction, inputSupplier);
	}
	
	
	public static abstract class AbstractFCSImpl<E,Y> extends FImpl<E,Y> implements FCS<E,Y> {
		public AbstractFCSImpl(Function<E,Y> internalFunction) {
			super(internalFunction);
		}
		
		protected abstract void putOutput(Y output);
		@Override
		public void accept(E input) {
			Y y = apply_internal(input);
			putOutput(y);
		}
		
		protected abstract E getInput();
		@Override
		public Y get() {
			return apply_internal(getInput());
		}
		
		@Override
		public void run() {
			accept(getInput());
		}
	}
	public static class FCSImpl<E,Y> extends AbstractFCSImpl<E,Y> implements FCS<E,Y> {
		public FCSImpl(Function<E,Y> internalFunction, Consumer<Y> outputConsumer, Supplier<E> inputSupplier) {
			super(internalFunction);
			this.outputConsumer = outputConsumer;
			this.inputSupplier = inputSupplier;
		}
		
		protected final Consumer<Y> outputConsumer;
		protected void putOutput(Y output) {
			outputConsumer.accept(output);
		}
		
		protected final Supplier<E> inputSupplier;
		@Override
		protected E getInput() {
			return inputSupplier.get();
		}
	}
	public static <E,Y> FCS<E,Y> make(Function<E,Y> internalFunction, Consumer<Y> outputConsumer, Supplier<E> inputSupplier) {
		return new FCSImpl<>(internalFunction, outputConsumer, inputSupplier);
	}
	
	public static class M<E,Y> {
		private final Function<E,Y> internalFunction;
		public M(Function<E,Y> internalFunction){
			this.internalFunction = internalFunction;
		}
		public FC<E,Y> make(Consumer<Y> outputConsumer) {
			return new FCImpl<>(internalFunction, outputConsumer);
		}
		public FS<E,Y> make(Supplier<E> inputSupplier) {
			return Setters.make(internalFunction, inputSupplier);
		}
		public FCS<E,Y> make(Consumer<Y> outputConsumer, Supplier<E> inputSupplier) {
			return Setters.make(internalFunction, outputConsumer, inputSupplier);
		}
	}
	public static <E,Y> M<E,Y> make(Function<E,Y> internalFunction){
		return new M<>(internalFunction);
	}
	
	public static void main(String[] s) {
		
//		FC<Integer, Integer> f = make(new IntegratorInt_post(), y -> System.out.println(y));
//		Stream.of(1,2,3,4,5,6,7,8,9,10).forEach(e->f.accept(e));
		exec(IntStream.range(1, 11).boxed(), new IntegratorInt_post(), y -> System.out.println(y));
	}
	
//	public <E,Y> A<E,Y> make(FactoryFunctObj<E,Y> functObjFactory, Consumer<Y> outputConsumer) {
//		return null;
//	}
	
	
	
}
