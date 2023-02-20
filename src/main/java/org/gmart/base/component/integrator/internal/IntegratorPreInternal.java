package org.gmart.base.component.integrator.internal;

public interface IntegratorPreInternal<In, Out> extends IntegratorInternal<In, Out> {
	@Override
	default Out apply(In toAccumulate) {
		Out newAccumulation = this.calculateNewAccumulation(getAccumulation(), toAccumulate);
		setAccumulation(newAccumulation);
		return newAccumulation;
	}
}
