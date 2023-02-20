package org.gmart.base.data.transform.number.doubleFuncts;

import java.util.function.BiFunction;

public interface BiFunctionX<X0,X1,Y> extends BiFunction<X0,X1,Y> {
	default Double2Y<Y> compose(Double2Y<? extends X0> f0, Double2Y<? extends X1> f1) {
		return x -> this.apply(f0.apply(x), f1.apply(x));
	}
}