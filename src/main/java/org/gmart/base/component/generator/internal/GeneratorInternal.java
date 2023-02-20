package org.gmart.base.component.generator.internal;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public interface GeneratorInternal<T> extends Supplier<T> {
	T calculateNewAccumulation(T currentAccumulation);

	void setAccumulation(T accumulation);
	T getAccumulation();
	
	public static <T> GeneratorPreInternal<T> pre(T initialAccumulation, UnaryOperator<T> accumulator){
		return new AbstractGeneratorPreInternalImpl<T>(initialAccumulation) {
			@Override
			public T calculateNewAccumulation(T currentAccumulation) {
				return accumulator.apply(currentAccumulation);
			}
		};
	}
	public static <T> GeneratorPostInternal<T> post(T initialAccumulation, UnaryOperator<T> accumulator){
		return new AbstractGeneratorPostInternalImpl<T>(initialAccumulation) {
			@Override
			public T calculateNewAccumulation(T currentAccumulation) {
				return accumulator.apply(currentAccumulation);
			}
		};
	}
}
