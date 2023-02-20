package org.gmart.base.component.automaton.finite;

import java.util.function.Predicate;


public class TransitionImpl<N, E> implements Transition<N, E> {
	N target; 
	Predicate<E> activityCondition;
	public TransitionImpl(N target, Predicate<E> activityCondition) {
		super();
		this.target = target;
		this.activityCondition = activityCondition;
	}
	@Override
	public Predicate<E> activityCondition() {
		return activityCondition;
	}
	@Override
	public N target() {
		return target;
	}

	
}