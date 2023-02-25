package org.gmart.base.data.structure.graph;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


public interface Composite<T> {
	Stream<T> getChildrenStream();
	
	default Optional<T> getFirstChild() {
		return getChildrenStream().findFirst();
	}
	
	
	
	public class ListImpl<T> implements Composite<T> {
		private List<T> children;
		
		public List<T> getChildren() {
			return children;
		}
		public void setChildren(List<T> children) {
			this.children = children;
		}
		public Stream<T> getChildrenStream() {
			return children.stream();
		}
		public ListImpl() {
			super();
		}
		public ListImpl(List<T> children) {
			super();
			this.children = children;
		}
		
		public static class Buidler<T> {
			List<T> children;
			public void add(T child) {
				children.add(child);
			}
			public ListImpl<T> build(){
				return new ListImpl<>(children);
			}
		}
	}
}
