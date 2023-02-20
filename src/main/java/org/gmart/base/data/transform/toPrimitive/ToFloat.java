package org.gmart.base.data.transform.toPrimitive;

import javax.annotation.processing.Generated;
import java.util.function.Function;

/** for signature disambigufication */
@Generated("org.gmart.functional.javaPrimitives.Gen")
public interface ToFloat<T> extends Function<T, Float> {
	public static <T> ToFloat<T> of(Function<T, Float> f){
		return (ToFloat<T>)f;
	}
}
