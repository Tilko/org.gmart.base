package org.gmart.base.component.integrator.internal;

import java.util.function.BiFunction;
import java.util.function.Function;

public interface IntegratorInternal<In,Out> extends Function<In, Out> {
	Out calculateNewAccumulation(Out currentAccumulation, In toAccumulate);

	void setAccumulation(Out accumulation);
	Out getAccumulation();
	
	public static <In,Out> IntegratorPreInternal<In,Out> pre(Out initialAccumulation, BiFunction<Out, In, Out> accumulator){
		return new AbstractIntegratorPreInternalImpl<In,Out>(initialAccumulation) {
			@Override
			public Out calculateNewAccumulation(Out currentAccumulation, In toAccumulate) {
				return accumulator.apply(currentAccumulation, toAccumulate);
			}
		};
	}
	public static <In,Out> IntegratorPostInternal<In,Out> post(Out initialAccumulation, BiFunction<Out,In,Out> accumulator){
		return new AbstractIntegratorPostInternalImpl<In,Out>(initialAccumulation) {
			@Override
			public Out calculateNewAccumulation(Out currentAccumulation, In toAccumulate) {
				return accumulator.apply(currentAccumulation, toAccumulate);
			}
		};
	}
}
