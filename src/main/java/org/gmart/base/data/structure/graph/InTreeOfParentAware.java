package org.gmart.base.data.structure.graph;

import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.gmart.base.Streams;

/**
 * the 2 following commented impl of "commonParent" demonstrate that InTreeOfParentAware is not necessary
 * (ParentAware is enough) but it improves the code quality (also in client code) at almost no cost
 * (just the trivial "ThisAs" implementation).
 * To conclude, the first method is also commented because it's implemented by the InTreeOfParentAware::getCommonParentWith (default method).
 */
//	public static <T extends InTreeOfParentAware<T>> T commonParent(InTreeOfParentAware<T> child0, InTreeOfParentAware<T> child1){ ////commonParent(T child0, T child1) { //possible but more indirect.
//		return child0.getAncestorsStream_thisIncluded()
//				.filter(child1.getAncestorsStream_thisIncluded().collect(Collectors.toSet())::contains).findFirst().orElse(null);
//	}
//	public static <T extends ParentAware<T>> T commonParent2(T child0, T child1) {  ////commonParent2(InTreeOfParentAware<T> child0, InTreeOfParentAware<T> child1)  // not possible this time
//		return Streams.of(AncestorIterators.from_included(child1))
//				.filter(Streams.of(AncestorIterators.from_included(child0)).collect(Collectors.toSet())::contains).findFirst().orElse(null);
////		return findFirstAncestorVerifying(child1, Streams.of(AncestorIterators.fromFirstIncluded(child0))
////				                                         .collect(Collectors.toSet())::contains);
//	}

public interface InTreeOfParentAware<P extends InTreeOfParentAware<P>> extends ParentAware<P>, ThisAs<P> {
	
	default Stream<P> getAncestorsStream_thisIncluded() {
		return Streams.of(AncestorIterators.from_included(getThis()));
	}
	default Stream<P> getAncestorsStream_thisIncluded(Predicate<P> stopStreamingCondition) {
		return Streams.of(AncestorIterators.from_included(getThis(), stopStreamingCondition));
	}
	default P getCommonParentWith(P other) {
		return getAncestorsStream_thisIncluded()
				.filter(other.getAncestorsStream_thisIncluded().collect(Collectors.toSet())::contains).findFirst().orElse(null);
				//TreeSearch.commonParent(this, other);
	}
}


