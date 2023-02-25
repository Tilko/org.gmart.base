package org.gmart.base.data.structure.graph.building;

import java.util.HashSet;
import java.util.function.Function;
import java.util.stream.Stream;

public class CycleDetector<T, Identity> {
	Function<T, Identity> idGetter;
	Function<T, Stream<T>> childrenGetter;
	public CycleDetector(Function<T, Identity> idGetter, Function<T, Stream<T>> childrenGetter) {
		super();
		this.idGetter = idGetter;
		this.childrenGetter = childrenGetter;
		this.identities = new HashSet<>();
	}
	HashSet<Identity> identities;
	public Identity isCyclic(T composite) {
		Identity id = idGetter.apply(composite);
		if(identities.contains(id))
			return id;
		identities.add(id);			
		Identity inCircle = childrenGetter.apply(composite)
				.map(e-> isCyclic(e)).filter(id0->id0 != null).findFirst().orElse(null);
		if(inCircle != null)
			return inCircle;
		identities.remove(id);
		return null;
	}
}