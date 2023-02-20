package org.gmart.base.data.transform.toPrimitive;

import javax.annotation.processing.Generated;
import java.util.function.Function;

/** for signature disambigufication */
@Generated("org.gmart.functional.javaPrimitives.Gen")
public interface ToBoolean<T> extends Function<T, Boolean> {
	public static <T> ToBoolean<T> of(Function<T, Boolean> f){
		return (ToBoolean<T>)f;
	}
}
