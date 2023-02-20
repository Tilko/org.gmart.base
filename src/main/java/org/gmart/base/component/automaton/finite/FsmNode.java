package org.gmart.base.component.automaton.finite;

import java.util.Optional;

/**
 * E: event type
 * T: transition type
 * @author marti
 *
 * @param <E>
 * @param <T>
 */
public interface FsmNode<E,T> {
	Optional<T> getActiveTransition(E event);
	
}
