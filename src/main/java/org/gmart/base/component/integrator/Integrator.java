package org.gmart.base.component.integrator;

import java.util.function.BiFunction;
import java.util.function.Function;

import org.gmart.base.component.integrator.internal.IntegratorImpl.PostIntegratorImpl;
import org.gmart.base.component.integrator.internal.IntegratorImpl.PreIntegratorImpl;
import org.gmart.base.component.integrator.internal.IntegratorInternal;

public interface Integrator<In,Out> extends Function<In,Out> {
	/**
	 * left operand of accumulator is the accumulation (calculated from all previous call)
	 */
	public static <In,Out> Integrator<In,Out> pre(Out initialAccumulation, BiFunction<Out, In, Out> accumulator){
		return new PreIntegratorImpl<Out,In>(IntegratorInternal.pre(initialAccumulation, accumulator));
	}
	/**
	 * left operand of accumulator is the accumulation (calculated from all previous call)
	 */
	public static <In,Out> Integrator<In,Out> post(Out initialAccumulation, BiFunction<Out, In, Out> accumulator) {
		return new PostIntegratorImpl<In,Out>(IntegratorInternal.post(initialAccumulation, accumulator));
	}
	
}
