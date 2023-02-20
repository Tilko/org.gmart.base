package org.gmart.base.component.generator.internal;

import org.gmart.base.component.generator.Generator;

public class GeneratorImpl<T> implements Generator<T>{
	private final GeneratorInternal<T> internal;
	
	public GeneratorImpl(GeneratorInternal<T> internal) {
		super();
		this.internal = internal;
	}

	public T get() {
		return internal.get();
	}
	public static class PreGeneratorImpl<T> extends GeneratorImpl<T> {

		public PreGeneratorImpl(GeneratorPreInternal<T> internal) {
			super(internal);
		}
	}
	public static class PostGeneratorImpl<T> extends GeneratorImpl<T> {

		public PostGeneratorImpl(GeneratorPostInternal<T> internal) {
			super(internal);
		}

	}
}