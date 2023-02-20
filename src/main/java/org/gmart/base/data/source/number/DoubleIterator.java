package org.gmart.base.data.source.number;

import java.util.function.DoubleFunction;
import java.util.function.Function;

import org.gmart.base.data.source.IteratorX;

public interface DoubleIterator extends IteratorX<Double> {
	
	public static DoubleIterator sinus(double initPhase, double deltaAngle, double totalPhaseVariation, double amplitude){
		return Ramp.of(deltaAngle, initPhase + totalPhaseVariation, initPhase).mapDouble(phase -> amplitude*Math.sin(phase));
	}
	public static Ramp ramp(double finalValue, double step, double initValue){
		return Ramp.of(step, finalValue, initValue);
	}

	default <Y> IteratorX<Y> map(DoubleFunction<Y> mappingFunction){
		IteratorX<Double> src = this;
		return new IteratorX<>() {
			@Override
			public boolean hasNext() {
				return src.hasNext();
			}
			@Override
			public Y next() {
				return mappingFunction.apply(src.next());
			}
		};
	}
	
	default DoubleIterator add(double offset) {
		return new AbstractDoubleIterator(this) {
			@Override
			public Double next() {
				return src.next() + offset;
			}
		};
	}
	default DoubleIterator mult(double coeff) {
		return new AbstractDoubleIterator(this) {
			@Override
			public Double next() {
				return coeff*src.next();
			}
		};
	}
	default DoubleIterator mapDouble(DoubleFunction<Double> mappingFunction){
		return new AbstractDoubleIterator(this) {
			@Override
			public Double next() {
				return mappingFunction.apply(src.next());
			}
		};
	}
	default <Y> IteratorX<Y> mapD(Function<Double, Y> mappingFunction){
		return this.to(mappingFunction);

	}
	public static abstract class AbstractDoubleIterator implements DoubleIterator {
		DoubleIterator src;
		public AbstractDoubleIterator(DoubleIterator src) {
			super();
			this.src = src;
		}
		@Override
		public boolean hasNext() {
			return src.hasNext();
		}
	}
}
