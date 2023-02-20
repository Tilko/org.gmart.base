package org.gmart.base.data.source.temporalState;

public abstract class StableTemporalState<T> implements TemporalState<T>{

	@Override
	public boolean isStable() {
		return true;
	}
}
