package org.gmart.base.component.automaton.finite;

import java.util.function.Predicate;

public interface Activable<E> {
	Predicate<E> activityCondition();
}
