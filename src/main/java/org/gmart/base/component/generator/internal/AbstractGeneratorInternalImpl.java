package org.gmart.base.component.generator.internal;

import lombok.Getter;
import lombok.Setter;

public abstract class AbstractGeneratorInternalImpl<T> implements GeneratorInternal<T> {

	@Setter @Getter
	T accumulation;
	
	public AbstractGeneratorInternalImpl(T initialAccumulation) {
		this.accumulation = initialAccumulation;
	}

//		@Override
//		public T apply(T t) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//		@Override
//		public T calculateNewAccumulation(T toAccumulate) {
//			// TODO Auto-generated method stub
//			return null;
//		}
}