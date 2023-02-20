package org.gmart.base.component.automaton.finite;

import java.util.function.BiConsumer;


/** 
 * an FSM evolves with an event (E) by getting the optional current state node (N) active transition (T), 
 * if any activated transition: call of "activityReaction(old state node and activated transition), and then, of course, change of current state node  
 * */
public class Fsm<N extends FsmNode<E, T>, E, T extends Pointer<N>> {
	N current;
	public N getCurrentStateNode() {
		return current;
	}
	BiConsumer<N, T> activityReaction;
	public Fsm(N current) {
		super();
		this.current = current;
		this.activityReaction = null;
	}
	public Fsm(N current, BiConsumer<N, T> activityReaction) {
		super();
		this.current = current;
		this.activityReaction = activityReaction;
	}

	public void evolve(E event) {
		current.getActiveTransition(event).ifPresent(tr -> {
			if(activityReaction != null)
				activityReaction.accept(current, tr);
			current = tr.target();
		});

	}
}
