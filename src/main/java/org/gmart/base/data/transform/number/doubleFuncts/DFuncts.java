package org.gmart.base.data.transform.number.doubleFuncts;


public class DFuncts {
	
	public static final DoubleFunct id = x->x;
	public static final DoubleFunct neg = x->-x;
	public static final DoubleFunct square = x->x*x;
	public static final DoubleFunct inv = x->1/x;
	public static final DoubleFunct sin = Math::sin;
	public static final DoubleFunct cos = Math::cos;
	public static final DoubleFunct tan = Math::tan;
	public static final DoubleFunct exp = Math::exp;
	public static final DoubleFunct log = Math::log;
	public static final DoubleFunct log10 = Math::log10;
	public static final DoubleFunct abs = Math::abs;
	public static final DoubleFunct signum = Math::signum;
	
	public static final DoubleFunct sinBetween0And1 = x -> 0.5*(1 + Math.sin(x));
	public static final DoubleFunct cosBetween0And1 = x -> 0.5*(1 + Math.cos(x));
	
	public static DoubleFunct affine(double coeff, double offset) {
		return x -> coeff*x + offset;
	}
	
	public static DoubleFunct add2(double offset) {
		return x -> x + offset;
	}
	public static final BiDoubleOp add = (x,y) -> x + y;
	public static final BiDoubleOp sub = (x,y) -> x - y;
	public static final BiDoubleOp mult = (x,y) -> x*y;
	public static final BiDoubleOp div = (x,y) -> x/y;
	public static final BiDoubleOp mod = (x,y) -> x%y;

	public static final BiDoubleOp pow = Math::pow;
	public static final BiDoubleOp min = Math::min;
	public static final BiDoubleOp max = Math::max;
	
	public static DoubleFunct add2(DoubleFunct f0, DoubleFunct f1) {
//		add.compose(id, square);
		return x -> add.apply(f0.apply(x), f1.apply(x));
	}
	
	public static DoubleFunct add(double offset) {
		return x -> x + offset;
	}
	public static DoubleFunct mult(double coeff) {
		return x -> x*coeff;
	}
	public static DoubleFunct pow(double exponent) {
		return x -> Math.pow(x, exponent);
	}
	
	public static <Y> Double2Y<Y> then(Double2Y<Y> f) {
		return f;
	} 
}
