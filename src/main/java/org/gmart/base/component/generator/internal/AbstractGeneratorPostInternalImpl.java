package org.gmart.base.component.generator.internal;

public abstract class AbstractGeneratorPostInternalImpl<T> extends AbstractGeneratorInternalImpl<T> implements GeneratorPostInternal<T> {

	AbstractGeneratorPostInternalImpl(T initialAccumulation) {
		super(initialAccumulation);
	}

}