package org.gmart.base.component.automaton.finite;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NodeImpl<E,T extends Activable<E>> implements FsmNode<E,T>{
	List<T> transitions;
	public NodeImpl(List<T> transitions) {
		super();
		this.transitions = transitions;
	}
	public NodeImpl() {
		super();
		transitions = new ArrayList<>();
	}
	public NodeImpl(T transition) {
		this();
		transitions.add(transition);
	}
	
	List<T> getTransitions() {
		return transitions;
	}
	public void addTransition(T transition) {
		transitions.add(transition);
	}
	public Optional<T> getActiveTransition(E event) {
		return getTransitions().stream().filter(tr -> tr.activityCondition().test(event)).findFirst();			
	}
}