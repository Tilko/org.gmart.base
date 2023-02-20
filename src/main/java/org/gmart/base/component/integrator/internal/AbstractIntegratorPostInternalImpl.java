package org.gmart.base.component.integrator.internal;

public abstract class AbstractIntegratorPostInternalImpl<In,Out> extends AbstractIntegratorInternalImpl<In,Out> implements IntegratorPostInternal<In,Out> {

	AbstractIntegratorPostInternalImpl(Out initialAccumulation) {
		super(initialAccumulation);
	}

}