package org.gmart.base.data.storage.property.immutable.lazyinit;

import java.util.function.Function;

public class LazyInitLate1Arg<T, X> {//implements Function<X, T> {
	T builtObject;
	private Function<X,T> builder;
	public Function<X,T> getBuilder() {
		return builder;
	}

	public LazyInitLate1Arg(Function<X,T> builder) {
		super();
		this.builder = builder;
	}
	public boolean hasBeenUsed() {
		return builtObject != null;
	}
	public T get_initWithArgAtFirstCall(X x) {
		if(builtObject == null)
			builtObject = builder.apply(x);
		return builtObject;
	}
	public static <X,T> LazyInitLate1Arg<T, X> of(Function<X, T> builder){
		return new LazyInitLate1Arg<>(builder);
	}
//	public static <T, A, X,Y> LazySupplier2<X,Y,T> lazy(A a, Function<A,E> f){
//		return new LazySupplier2<X,Y,T>(()->f.apply(a));
//	}
//	public static <E, A, B> LazySupplier2<E> lazy(A a, B b, BiFunction<A,B,E> f){
//		return new LazySupplier2<E>(()->f.apply(a,b));
//	}
	
}