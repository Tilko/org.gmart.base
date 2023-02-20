package org.gmart.base.data.transform.toPrimitive;

import javax.annotation.processing.Generated;
import java.util.function.Function;

/** for signature disambigufication */
@Generated("org.gmart.functional.javaPrimitives.Gen")
public interface ToByte<T> extends Function<T, Byte> {
	public static <T> ToByte<T> of(Function<T, Byte> f){
		return (ToByte<T>)f;
	}
}
