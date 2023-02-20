package org.gmart.base.data.transform.number.doubleFuncts;

import java.util.function.DoubleFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;

import org.gmart.base.data.source.IteratorX;
import org.gmart.base.data.source.number.Ramp;

public interface Double2Y<Y> extends DoubleFunction<Y> {
	default IteratorX<Y> rampIterator(double step, double finalValue, double initValue) {
		return Ramp.of(step, finalValue, initValue).map(this);
	}
	default IteratorX<Y> rampUnit(double step, boolean lastValueIncluded) {
		return Ramp.ofUnit(step, lastValueIncluded).map(this);
	}
	default IteratorX<Y> ramp2(double step, double totalVariation, double initValue) {
		return rampIterator(step, initValue + totalVariation, initValue);
	}
	default IteratorX<Y> rampUnit(double step) {
		return rampIterator(step, 1, 0);
	}
	default Double2Y<Y> compose(DoubleUnaryOperator innerFunction) {
		return x -> this.apply(innerFunction.applyAsDouble(x));
	}
	default Double2Y<Y> compose_mult(double factor) {
		return x -> this.apply(factor*x);
	}
	default Double2Y<Y> compose_div(double divisor) {
		return compose_mult(1/divisor);
	}
	default <Y2> Double2Y<Y2> then(Function<? super Y, ? extends Y2> mappingFunction){
		return t -> mappingFunction.apply(this.apply(t));
	}

	default DoubleFunct thenD(Function<? super Y, Double> mappingFunction){
		return t -> mappingFunction.apply(this.apply(t));
	}
	public static <Y> Double2Y<Y> saturateAt(double saturationValue, Double2Y<? extends Y> saturatedFunction) {
		return t -> {
			if(t > saturationValue)
				return saturatedFunction.apply(saturationValue);
			else return saturatedFunction.apply(t);
		};
		
	}
	
}