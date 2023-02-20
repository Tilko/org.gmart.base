package org.gmart.base.component.automaton.finite.baseImpl;

import java.util.function.BiConsumer;

import org.gmart.base.component.automaton.finite.Fsm;

public class BaseFsm<E> extends Fsm<BaseNode<E>, E, BaseTransition<E>> {
	public BaseFsm(BaseNode<E> current, BiConsumer<BaseNode<E>, BaseTransition<E>> activityReaction) {
		super(current, activityReaction);
	}

	public BaseFsm(BaseNode<E> current) {
		super(current);
	}
}