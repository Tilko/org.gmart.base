package org.gmart.base.data.source;

import java.util.function.Function;
import java.util.function.Supplier;

public class Suppliers {
	
	public static <I,O> Supplier<O> of(Supplier<I> s0, Function<I,O> map) {
		return ()-> map.apply(s0.get());
	}
}
