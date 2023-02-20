package org.gmart.base.data.structure.dim1;

import java.lang.reflect.Array;

public class Arrays2 {
	public static <T> T[] ofZeroing(T[] from) {
		return ofZeroing(from, from.length);
	}
	@SuppressWarnings("unchecked")
	public static <T> T[] ofZeroing(T[] from, int withLen) {
		return (T[])Array.newInstance(from.getClass().getComponentType(), withLen);
	}
}
