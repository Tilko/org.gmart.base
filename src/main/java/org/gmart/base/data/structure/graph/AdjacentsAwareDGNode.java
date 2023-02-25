package org.gmart.base.data.structure.graph;

/** 
 * Directed graph node aware of its adjacents node (source and target nodes)
 * */
public interface AdjacentsAwareDGNode<A extends AdjacentsAwareDGNode<A>> extends TargetsAwareDGNode<A>, SourcesAwareDGNode<A>{
	
	
	public interface AdjacentsAwareDGNode2<T extends TargetsAwareDGNode<T>, S extends SourcesAwareDGNode<S>> extends TargetsAwareDGNode<T>, SourcesAwareDGNode<S>{
		
		
	}
}