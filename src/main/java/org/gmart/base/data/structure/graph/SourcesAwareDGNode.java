package org.gmart.base.data.structure.graph;

import java.util.stream.Stream;

/** 
 * Directed graph node aware of its
 * */
public interface SourcesAwareDGNode<S extends SourcesAwareDGNode<S>> {
	Stream<S> getSourcesStream();
	

	/** 
	 * also called "root node(s)"
	 * */
	public interface EntryNode<S extends SourcesAwareDGNode<S>> extends SourcesAwareDGNode<S> {
		@Override
		default Stream<S> getSourcesStream(){
			return Stream.empty();
		}
	}
}