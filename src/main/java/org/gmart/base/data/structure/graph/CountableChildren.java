package org.gmart.base.data.structure.graph;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@FunctionalInterface
public interface CountableChildren {
	int getChildrenCount();
	
	
	
	public static int getChildrenCount(Stream<?> s) {
		return (int) s.count();
	}
	public static int getChildrenCount(Collection<?> s) {
		return s.size();
	}
	public static int getChildrenCount(Object[] s) {
		return s.length;
	}
	
//	public static String getImplCode(String childrenAttributeAccess) {
//		return "public int getChildrenCount(){ return ReadableChildrenCount.getChildrenCount(" + childrenAttributeAccess + ");}";
//	}
	
//	public static String getImplCode(String children_ReadAccessCode) {
//		return "public int getChildrenCount(){ return " + children_ReadAccessCode + ".size();}";
//	}
	
	public static String makeImplCode(String children_ReadAccessCode) {
		return "return " + children_ReadAccessCode + ".size();";
	}
	
	
	public class GenListImpl<C> extends Composite.ListImpl<C> implements CountableComposite<C> {
//		public List<T> getChildren() {
//			return children;
//		}
		@Override
		public int getChildrenCount() {
			return getChildren().size();
		}
		public GenListImpl() {
			super();
		}
		public GenListImpl(List<C> children) {
			super(children);
		}
		
		public static class Buidler<T> {
			protected List<T> children;
			public void add(T child) {
				children.add(child);
			}
			public CountableChildren.GenListImpl<T> build(){
				return new CountableChildren.GenListImpl<>(children);
			}
		}
	}
}
