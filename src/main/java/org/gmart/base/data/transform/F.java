package org.gmart.base.data.transform;

import java.util.function.Function;

public class F {
	/**
	 * to obtain a function from a lambda expression
	 */
	public static <X,Y> Function<X,Y> of(Function<X, Y> f){
		return f;
	}
}
