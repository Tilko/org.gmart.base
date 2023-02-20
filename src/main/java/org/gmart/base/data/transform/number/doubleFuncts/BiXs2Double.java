package org.gmart.base.data.transform.number.doubleFuncts;


public interface BiXs2Double<X0, X1> extends BiFunctionX<X0, X1, Double> {

	default DoubleFunct compose(Double2Y<? extends X0> f0, Double2Y<? extends X1> f1) {
		return x -> this.apply(f0.apply(x), f1.apply(x));
	}
	
}
