package org.gmart.base.data.transform.number.doubleFuncts;



public interface BiDoubleFunct<Y> extends BiFunctionX<Double, Double, Y> {
	default Double2Y<Y> compose(DoubleFunct f0, DoubleFunct f1) {
		return x -> this.apply(f0.apply(x), f1.apply(x));
	}
}
