package org.gmart.base.data.structure.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Caution: Implementer must ensure that it is effectively a tree (not a cyclic graph, else "leaveStream()" result in infinite loop and stack overflow)
 * @author marti
 *
 * @param <C>
 */
public interface TargetsAwareDTreeNode<C extends TargetsAwareDTreeNode<C>> extends ThisAs<C> { //ThisAs for visitTreeBreadthFirst
	Stream<C> getTreeNodeTargetsStream();
	
	/**
	 * Caller must ensure that it is really a tree the accessible part of the graph from this node (successors)
	 * @return
	 */
	default Stream<C> leaveStream() {
		return getTreeNodeTargetsStream().flatMap(TargetsAwareDTreeNode::leaveStream);
	}
	/** 
	 * "sink node" or a "leaf node" (in case of directed tree)
	 * */
	public interface Leaf<C extends TargetsAwareDTreeNode<C>> extends TargetsAwareDTreeNode<C> {
		default Stream<C> getTreeNodeTargetsStream(){
			return Stream.empty();
		}
	}
	
	
	/** BFS for trees (not for acyclic graphs) */
	default void visitTreeBreadthFirst(Consumer<C> nodeVisitor_nullable, Consumer<Integer> depthListener_nullable) {
		Queue<C> queue = new LinkedList<C>();

		queue.add(getThis());
		int depth = 0;
		while (!queue.isEmpty()) {
			if (depthListener_nullable != null)
				depthListener_nullable.accept(depth);
			int initialQueueSize = queue.size();
			// necessary to visit all nodes at the same depth:
			// (do not rewrite "i < size()" because size() changes in the "for")
			for (int i = 0; i < initialQueueSize; i++) {
				C node = queue.remove();
				if (nodeVisitor_nullable != null)
					nodeVisitor_nullable.accept(node);
				node.getTreeNodeTargetsStream().forEach(queue::add);
			}
			depth++;
		}
	}
	
	
	////////////////://///////	
	/////////TESTING://///////
	////////////////://///////
	
	
	public class BFS_Test {
		public static void main(String[] args) {
			visitTreeBreadthFirstTest();
		}

		static void visitTreeBreadthFirstTest() {
			/*
			 * Example tree: 1 
			 *             / | \ 
			 *             2 3 4 
			 *           / \ |
			 *           5 6 7
			 */
			class TreeNode implements TargetsAwareDTreeNode<TreeNode> {
				int val;
				List<TreeNode> children;
				TreeNode(int val) {
					this.val = val;
					children = new ArrayList<>();
				}
				@Override
				public Stream<TreeNode> getTreeNodeTargetsStream() {
					return children.stream();
				}
				@Override
				public TreeNode getThis() {
					return this;
				}
			}
			TreeNode root = new TreeNode(1);
			root.children.add(new TreeNode(2));
			root.children.add(new TreeNode(3));
			root.children.add(new TreeNode(4));
			root.children.get(0).children.add(new TreeNode(5));
			root.children.get(0).children.add(new TreeNode(6));
			root.children.get(2).children.add(new TreeNode(7));

			root.visitTreeBreadthFirst(n -> System.out.print(n.val + " "), i -> System.out.println("at depth: " + i + "->"));
		}
	}
}