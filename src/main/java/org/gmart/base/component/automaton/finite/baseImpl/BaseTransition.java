package org.gmart.base.component.automaton.finite.baseImpl;

import java.util.function.Predicate;

import org.gmart.base.component.automaton.finite.TransitionImpl;

class BaseTransition<E> extends TransitionImpl<BaseNode<E>, E> {
	public BaseTransition(BaseNode<E> target, Predicate<E> activityCondition) {
		super(target, activityCondition);
	}
}