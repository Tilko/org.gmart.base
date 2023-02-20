package org.gmart.base.data.transform.number.doubleFuncts;

import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;

public interface UnitD2Y<Y> extends Double2Y<Y> {
	default UnitD2Y<Y> reverse(){
		return x -> this.apply(1 - x);		
	}
	default Double2Y<Y> compose(DoubleUnaryOperator innerFunction) {
		return x -> this.apply(innerFunction.applyAsDouble(x));
	}
//	default <Z> UnitD2Y<Z> then(Function<? super Y, Z> nextFunction) {
//		return x -> nextFunction.apply(this.apply(x));
//	}
	default <Y2> UnitD2Y<Y2> then(Function<? super Y, ? extends Y2> mappingFunction){
		return x -> mappingFunction.apply(this.apply(x));
	}
	
	
	default UnitD2Y<Y> saturated(double saturationValue){
		return saturateAt(saturationValue, this);
	}
	
	public static <Y> UnitD2Y<Y> saturateAt(double saturationValue, UnitD2Y<? extends Y> saturatedFunction) {
		return t -> {
			if(t > saturationValue)
				return saturatedFunction.apply(saturationValue);
			else return saturatedFunction.apply(t);
		};
		
	}
	default UnitD2Y<Y> delayed(double delay) {
		return t -> apply(t-delay);	
	}
	
	
}
