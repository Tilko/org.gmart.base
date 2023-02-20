package org.gmart.base.data.source.temporalState;

import org.gmart.base.data.source.temporalState.TemporalState.AbstractTemporalState;

public class StableAfterDurationTemporal extends AbstractTemporalState<Boolean> {
	public static StableAfterDurationTemporal of(long iterationNumber) {
		return new StableAfterDurationTemporal(iterationNumber);
	}
	private StableAfterDurationTemporal(long iterationNumber){
		super(iterationNumber);
	}
	@Override
	public Boolean getState() {
		return isStable();
	}
}