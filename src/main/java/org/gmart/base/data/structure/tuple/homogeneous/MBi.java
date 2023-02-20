package org.gmart.base.data.structure.tuple.homogeneous;

public interface MBi<T> extends IBi<T> {
	void setT0(T arg);
	void setT1(T arg);
	
	default void set(IBi<T> arg) {
		setT0(arg.getValue0());
		setT0(arg.getValue1());
	}
}
