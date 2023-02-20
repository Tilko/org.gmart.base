package org.gmart.base.data.transform.toPrimitive;

import javax.annotation.processing.Generated;
import java.util.function.Function;
import java.util.stream.Stream;

/** for signature disambigufication */
@Generated("org.gmart.functional.javaPrimitives.Gen")
public interface ToDouble<T> extends Function<T, Double> {
	public static <T> ToDouble<T> of(Function<T, Double> f){
		return (ToDouble<T>)f;
	}
	
}
