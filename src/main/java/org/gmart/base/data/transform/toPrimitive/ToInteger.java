package org.gmart.base.data.transform.toPrimitive;

import javax.annotation.processing.Generated;
import java.util.function.Function;

/** for signature disambigufication */
@Generated("org.gmart.functional.javaPrimitives.Gen")
public interface ToInteger<T> extends Function<T, Integer> {
	public static <T> ToInteger<T> of(Function<T, Integer> f){
		return (ToInteger<T>)f;
	}
}
