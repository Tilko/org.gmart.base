package org.gmart.base.data.transform.number.doubleFuncts;

import java.util.function.DoubleFunction;
import java.util.function.Function;

import org.gmart.base.data.source.number.DoubleIterator;
import org.gmart.base.data.source.number.Ramp;

public interface DoubleFunct extends Double2Y<Double> {// UnaryOperator<Double> {
	//DoubleUnaryOperator
//	default <Y> Double2Y<Y> then(Function<Double, ? extends Y> mappingFunction){
//		return x -> mappingFunction.apply(this.apply(x));
//	}
	public static DoubleFunct rampUnitFunction(double finalValue, double delay) {
		return rampUnitFunction(finalValue).composeD(t-> t - delay);
	}
	public static DoubleFunct rampFunction(double finalValue, double delay, double rampDuration) {
		return rampFunction(finalValue, rampDuration).composeD(t-> t - delay);
	}
	public static RampBuilder rampBuilder() {
		return new RampBuilder();
	}
	public static class RampBuilder {
		double duration = 1;
		double delay = 0;
		double finalValue = 1;
		public RampBuilder withDuration(double duration) {
			this.duration = duration;
			return this;
		}
		public RampBuilder withDelay(double delay) {
			this.delay = delay;
			return this;
		}
		public RampBuilder withFinalValue(double finalValue) {
			this.finalValue = finalValue;
			return this;
		}
		public DoubleFunct build() {
			return rampFunction(finalValue, delay, duration);
		}
	}
	public static DoubleFunct rampFunction(double finalValue, double rampDuration) {
		double speed = finalValue/rampDuration;
		return t -> {
			if(t < 0) {
				return 0.0;
			} else if(t > rampDuration) {
				return finalValue;
			} else return t*speed;
		};
	}
	public static DoubleFunct rampUnitFunction(double finalValue) {
		return t -> {
			if(t < 0) {
				return 0.0;
			} else if(t > 1) {
				return finalValue;
			} else return t*finalValue;
		};
	}
	
	
	default DoubleIterator rampIterator(double step, double finalValue, double initValue) {
		return Ramp.of(step, finalValue, initValue).mapDouble(this);
	}
	default DoubleIterator rampIterator(double step, double finalValue) {
		return Ramp.of(step, finalValue).mapDouble(this);
	}
	default DoubleFunct composeD(Function<Double, Double> mappingFunction){
		return t -> this.apply(mappingFunction.apply(t));
	}
//	default DoubleFunct then(DoubleFunct next) {
//		return x-> next.apply(this.apply(x));
//	}
//	default DoubleFunct then(UnaryOperator<Double> next) {
//		return x-> next.apply(this.apply(x));
//	}
	
	default DoubleFunct sin() {
		return this.thenD(Math::sin);
	}
	default DoubleFunct cos() {
		return this.thenD(Math::cos);
	}
	default DoubleFunct sin(double amplitude) {
		return this.thenD(phase -> amplitude*Math.sin(phase));
	}
	default DoubleFunct cos(double amplitude) {
		return this.thenD(phase -> amplitude*Math.cos(phase));
	}
	default <Y2> Double2Y<Y2> then(DoubleFunction<? extends Y2> mappingFunction){
		return t -> mappingFunction.apply(this.apply(t));
	}

	default DoubleFunct then(DoubleFunct mappingFunction){
		return t -> mappingFunction.apply(this.apply(t));
	}
	default DoubleFunct add(DoubleFunct toSuperpose) {
		return t -> this.apply(t) + toSuperpose.apply(t);
	}
	default DoubleFunct add(double offset) {
		return this.thenD(x -> x + offset);
	}
	default DoubleFunct mult(double coeff) {
		return this.thenD(x -> x*coeff);
	}
	default DoubleFunct square() {
		return this.thenD(x -> x*x);
	}
	default DoubleFunct pow(double exponent) {
		return this.thenD(x -> Math.pow(x, exponent));
	}
	
//	default DoubleFunct then(DoubleUnaryOperator next) {
//		return x-> next.applyAsDouble(this.apply(x));
//	}
}
