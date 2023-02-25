package org.gmart.base.data.structure.graph;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class AncestorIterators {
	public static <P extends ParentAware<P>> Iterator<P> from_included(P startingNode_included) {
		return new AncestorIterator_firstIncluded<P>(startingNode_included);
	}
	public static <P extends ParentAware<P>> Iterator<P> from_excluded(P startingNode_excluded) {
		return new AncestorIterator_firstExcluded<P>(startingNode_excluded);
	}
	public static <P extends ParentAware<P>> Iterator<P> from_included(P startingNode_included, Predicate<P> breakCondition) {
		return new AncestorIterator_firstIncludedWithBreakCondition<P>(startingNode_included, breakCondition);
	}
	public static <P extends ParentAware<P>> Iterator<P> from_excluded(P startingNode_excluded, Predicate<P> breakCondition) {
		return new AncestorIterator_firstIncludedWithBreakCondition<P>(startingNode_excluded, breakCondition);
	}
	private static class AncestorIterator_firstExcluded<P extends ParentAware<P>> extends AncestorIterator_firstIncluded<P> {
		public AncestorIterator_firstExcluded(P startingNode_included) {
			super(startingNode_included == null ? null : startingNode_included.getParent());
		}
    }
	private static class AncestorIterator_firstIncluded<P extends ParentAware<P>> implements Iterator<P> {
	   
//	    public static <P extends ParentAware<P>> AncestorIterator_firstIncluded<P> of(P startingNode_included) {
//			return new AncestorIterator_firstIncluded<P>(startingNode_included);
//		}

		protected P currentNode;

	    protected AncestorIterator_firstIncluded(P startingNode_included) {
	        this.currentNode = startingNode_included;
	    }
	
		@Override
	    public boolean hasNext() {
	        return currentNode != null;
	    }
	    
	    @Override
	    public P next() {
	        if (!hasNext()) {
	            throw new NoSuchElementException();
	        }
	        P mem = currentNode;
	        currentNode = currentNode.getParent();
	        return mem;
	    }
	    
	    
	}
	private static class AncestorIterator_firstIncludedWithBreakCondition<P extends ParentAware<P>> extends AncestorIterator_firstIncluded<P> {
	    Predicate<P> breakCondition;
		public AncestorIterator_firstIncludedWithBreakCondition(P startingNode_included, Predicate<P> breakCondition) {
			super(startingNode_included);
			this.breakCondition = breakCondition;
		}
		@Override
	    public boolean hasNext() {
			return super.hasNext() && breakCondition.test(currentNode);
	    }
	}
  
}