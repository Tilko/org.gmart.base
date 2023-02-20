package org.gmart.base.component.generator.internal;

public interface GeneratorPreInternal<T> extends GeneratorInternal<T> {
	@Override
	default T get() {
		T newAccumulation = this.calculateNewAccumulation(getAccumulation());
		setAccumulation(newAccumulation);
		return newAccumulation;
	}
}
