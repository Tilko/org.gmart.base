package org.gmart.base.data.source;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * "X" as "extendable"
 */
public interface SupplierX<O> extends Supplier<O> {
	public static <T> SupplierX<T> of(Supplier<T> supplier) {
		return new SupplierX<T>() {
			@Override public T get() {return supplier.get();}
		};
	}
	default <O2> SupplierX<O2> to(Function<O, O2> adaptor) {
		return  ()-> adaptor.apply(get());
	}
	
	default Runnable supply(Consumer<O> supplied) {
		return ()->supplied.accept(get());
	}
}
