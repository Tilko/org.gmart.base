package org.gmart.base.data.structure.graph;

public interface Decorator<T> {
	T getDecorated();

	
	
	public class Impl<T> implements Decorator<T> {
		protected T child;

		public T getDecorated() {
			return child;
		}

		public Impl(T child) {
			super();
			this.child = child;
		}
	}
}
