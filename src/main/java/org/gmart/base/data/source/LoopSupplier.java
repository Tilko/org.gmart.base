package org.gmart.base.data.source;

import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.gmart.base.data.structure.d1.CircularArrayList;

public class LoopSupplier<E> implements Supplier<E> {
	Iterator<E> iterator;
	Supplier<Stream<E>> streamSupplier;
	public LoopSupplier(Supplier<Stream<E>> streamSupplier) {
		super();
		this.streamSupplier = streamSupplier;
		renewIterator();
	}
	protected void renewIterator() {
		iterator = streamSupplier.get().iterator();		
	}
	@Override
	public E get() {
		if(iterator.hasNext())
			return iterator.next();
		else {
			renewIterator();
			if(iterator.hasNext())
				return iterator.next();
			else return null;
		}
	}
	//CircularArrayList<E> circularList;
	//this.circularList = list.collect(Collectors.toCollection(CircularArrayList::new));
	public LoopSupplier(List<E> list) {
		super();
		//this.circularList = new CircularArrayList<E>(list);
	}
	public LoopSupplier(CircularArrayList<E> list) {
		super();
		//this.circularList = list;
	}
}
