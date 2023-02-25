package org.gmart.base.data.structure.graph;

import java.util.stream.Stream;

/** 
 * Directed graph node aware of its
 * */
public interface TargetsAwareDGNode<T extends TargetsAwareDGNode<T>> {
	Stream<T> getTargetsStream();
	

	/** 
	 * "sink node" or a "leaf node" (in case of directed tree)
	 * */
	public interface TerminalNode<T extends TargetsAwareDGNode<T>> extends TargetsAwareDGNode<T> {
		default Stream<T> getTargetsStream(){
			return Stream.empty();
		}
	}
}