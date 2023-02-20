package org.gmart.base.component.generator.internal;

public abstract class AbstractGeneratorPreInternalImpl<T> extends AbstractGeneratorInternalImpl<T> implements GeneratorPreInternal<T> {

	AbstractGeneratorPreInternalImpl(T initialAccumulation) {
		super(initialAccumulation);
	}

}