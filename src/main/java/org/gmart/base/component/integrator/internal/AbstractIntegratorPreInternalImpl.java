package org.gmart.base.component.integrator.internal;

public abstract class AbstractIntegratorPreInternalImpl<In,Out> extends AbstractIntegratorInternalImpl<In,Out> implements IntegratorPreInternal<In,Out> {

	AbstractIntegratorPreInternalImpl(Out initialAccumulation) {
		super(initialAccumulation);
	}

}