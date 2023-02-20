package org.gmart.base.component.automaton.finite.baseImpl;

import java.util.function.Predicate;

import org.gmart.base.component.automaton.finite.NodeImpl;

public class BaseNode<E> extends NodeImpl<E, BaseTransition<E>> {
	public BaseNode() {
		super();
	}
	public BaseNode(BaseNode<E> targetOfFirstTransition, Predicate<E> activityConditionOfFirstTransition) {
		super();
		addTransition(targetOfFirstTransition, activityConditionOfFirstTransition);
	}
	public void addTransition(BaseNode<E> targetOfFirstTransition, Predicate<E> activityConditionOfFirstTransition) {
		addTransition(new BaseTransition<>(targetOfFirstTransition, activityConditionOfFirstTransition));
	}
}



