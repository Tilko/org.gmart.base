package org.gmart.base.data.transform.number.doubleFuncts;

public interface BiDoubleOp extends BiDoubleFunct<Double> {
	default DoubleFunct compose(DoubleFunct f0, DoubleFunct f1) {
		return x -> this.apply(f0.apply(x), f1.apply(x));
	}
}