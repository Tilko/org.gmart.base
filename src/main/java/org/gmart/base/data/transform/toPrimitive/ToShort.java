package org.gmart.base.data.transform.toPrimitive;

import javax.annotation.processing.Generated;
import java.util.function.Function;

/** for signature disambigufication */
@Generated("org.gmart.functional.javaPrimitives.Gen")
public interface ToShort<T> extends Function<T, Short> {
	public static <T> ToShort<T> of(Function<T, Short> f){
		return (ToShort<T>)f;
	}
}
