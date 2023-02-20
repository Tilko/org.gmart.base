package org.gmart.base.component.integrator.internal;

import org.gmart.base.component.integrator.Integrator;

public class IntegratorImpl<In,Out> implements Integrator<In,Out>{
	private final IntegratorInternal<In,Out> internal;
	
	public IntegratorImpl(IntegratorInternal<In,Out> internal) {
		super();
		this.internal = internal;
	}

	public Out apply(In t) {
		return internal.apply(t);
	}
	public static class PreIntegratorImpl<In,Out> extends IntegratorImpl<Out,In> {

		public PreIntegratorImpl(IntegratorPreInternal<Out,In> internal) {
			super(internal);
		}
		
	}
	public static class PostIntegratorImpl<In,Out> extends IntegratorImpl<In,Out> {

		public PostIntegratorImpl(IntegratorPostInternal<In,Out> internal) {
			super(internal);
		}
		
	}
}