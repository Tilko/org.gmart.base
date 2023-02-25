package org.gmart.base.data.structure.graph;

public interface ParentAware<P> {// extends PotentialComposite<N> {
	void setParent(P parent); 
	P getParent();
//	default Stream<N> getPotentialChildren() {
//		return Stream.empty();
//	}
	public abstract static class AbstractImpl<P> implements ParentAware<P> {
		P parent;
		@Override
		public void setParent(P parent) {
			this.parent = parent;
		}
		@Override
		public P getParent() {
			return parent;
		}
	}
}