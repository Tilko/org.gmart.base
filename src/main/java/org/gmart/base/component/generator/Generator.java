package org.gmart.base.component.generator;

import java.util.Iterator;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import org.gmart.base.component.generator.internal.GeneratorImpl.PostGeneratorImpl;
import org.gmart.base.component.generator.internal.GeneratorImpl.PreGeneratorImpl;
import org.gmart.base.component.generator.internal.GeneratorInternal;

import com.google.common.base.Optional;

public interface Generator<T> extends Supplier<T> {
	public static <T> Generator<T> pre(T initialAccumulation, UnaryOperator<T> accumulator){
		return new PreGeneratorImpl<T>(GeneratorInternal.pre(initialAccumulation, accumulator));
	}
	public static <T> Generator<T> post(T initialAccumulation, UnaryOperator<T> accumulator) {
		return new PostGeneratorImpl<T>(GeneratorInternal.post(initialAccumulation, accumulator));
	}
	default Optional<T> repeat(int n) {
		return Optional.fromNullable(repeatNullable(n));
	}
	/** return null if "n" = 0 */
	default T repeatNullable(int n) {
		T t = null;
		for(int i = 0; i < n; i++) {
			t = this.get();
		}
		return t;
	}
	default Iterator<T> toIterator(int numberOfIteration){
		return new Iterator<>() {
			int i = 0;
			@Override
			public boolean hasNext() {
				return i <= numberOfIteration;
			}
			@Override
			public T next() {
				i++;
				return Generator.this.get();
			}
		};
	}
}
