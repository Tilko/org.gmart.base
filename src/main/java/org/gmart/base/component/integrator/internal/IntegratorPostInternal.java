package org.gmart.base.component.integrator.internal;

public interface IntegratorPostInternal<In, Out> extends IntegratorInternal<In, Out> {
	Out getAccumulation();
	
	@Override
	default Out apply(In t) {
		Out previousAccumulation = this.getAccumulation();
		setAccumulation(this.calculateNewAccumulation(previousAccumulation, t));
		return previousAccumulation;
	}
}
