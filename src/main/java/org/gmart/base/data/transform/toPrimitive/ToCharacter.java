package org.gmart.base.data.transform.toPrimitive;

import javax.annotation.processing.Generated;
import java.util.function.Function;

/** for signature disambigufication */
@Generated("org.gmart.functional.javaPrimitives.Gen")
public interface ToCharacter<T> extends Function<T, Character> {
	public static <T> ToCharacter<T> of(Function<T, Character> f){
		return (ToCharacter<T>)f;
	}
}
