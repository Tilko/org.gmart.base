package org.gmart.base.data.source.temporalState;
//package org.gmart.util.functionalProg.iterators.temporalState;
//
//
//public class Step implements TemporalState<Boolean> {
//	private boolean initState;
//	
//	public boolean getInitState() {
//		return initState;
//	}
//
//	public Boolean getState() {
//		return isStable() ? !initState : initState;
//	}
//
//	private long counter = 0;
//	private long finalCountExcluded;
//	public Step(boolean initState, long finalCountExcluded) {
//		super();
//		this.initState = initState;
//		this.finalCountExcluded = finalCountExcluded;
//	}
//
//	public Step(long finalCountExcluded) {
//		super();
//		this.initState = false;
//		this.finalCountExcluded = finalCountExcluded;
//	}
//
//	@Override
//	public void evolve() {
//		if(counter < finalCountExcluded)
//			counter++;
//	}
//
//	@Override
//	public boolean isStable() {
//		return counter == finalCountExcluded;
//	}
//	
//	public static Step of(long count) {
//		return new Step(count);
//	}
//}
