package org.gmart.base.component.generator.internal;

public interface GeneratorPostInternal<T> extends GeneratorInternal<T> {
	T getAccumulation();
	
	@Override
	default T get() {
		T previousAccumulation = this.getAccumulation();
		setAccumulation(this.calculateNewAccumulation(previousAccumulation));
		return previousAccumulation;
	}
}
