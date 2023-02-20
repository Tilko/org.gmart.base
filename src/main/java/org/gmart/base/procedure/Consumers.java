package org.gmart.base.procedure;

import java.util.function.Consumer;
import java.util.function.Function;

public class Consumers<T> {
	public static <T> Consumer<T> of(Class<T> cl, Consumer<T> consumer) {
		return consumer;
	}

	@SafeVarargs
	public static <T> Consumer<T> concat(Consumer<T> ... consumers){
		return arg -> {
			for(Consumer<T> consumer : consumers) {
				consumer.accept(arg);
			}
		};
	}
	
	public static <I,O> Consumer<I> of(Function<I,O> map, Consumer<O> consumer) {
		return i -> consumer.accept(map.apply(i));
	}
}
