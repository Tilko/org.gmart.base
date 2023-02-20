package org.gmart.base.data.transform.toPrimitive;

import javax.annotation.processing.Generated;
import java.util.function.Function;

/** for signature disambigufication */
@Generated("org.gmart.functional.javaPrimitives.Gen")
public interface ToLong<T> extends Function<T, Long> {
	public static <T> ToLong<T> of(Function<T, Long> f){
		return (ToLong<T>)f;
	}
}
