package org.gmart.base.component.integrator.internal;

import lombok.Getter;
import lombok.Setter;

public abstract class AbstractIntegratorInternalImpl<In,Out> implements IntegratorInternal<In,Out> {

	@Setter @Getter
	Out accumulation;
	
	public AbstractIntegratorInternalImpl(Out initialAccumulation) {
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