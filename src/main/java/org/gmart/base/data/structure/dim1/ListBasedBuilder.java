package org.gmart.base.data.structure.dim1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import interfaces.nodes.ThisSupplier;

public abstract class ListBasedBuilder<T, R, ConcreteBuilder> implements ThisSupplier<ConcreteBuilder>{
	protected List<T> elements = new ArrayList<>();
	public ListBasedBuilder() {
		elements = new ArrayList<>();
	}
	public ListBasedBuilder(List<T> tss) {
		this.elements = tss;
	}
	public ConcreteBuilder add(T ts2){
		elements.add(ts2);
		return getThis();
	}
	@SuppressWarnings("unchecked")
	public ConcreteBuilder add(T ... ts2){
		elements.addAll(Arrays.asList(ts2));
		return getThis();
	}
	public ConcreteBuilder add(Stream<T> elems){
		elements.addAll(elems.toList());
		return getThis();
	}
	public ConcreteBuilder add(List<T> elems){
		elements.addAll(elems);
		return getThis();
	}
	public abstract R build();
}