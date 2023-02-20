package org.gmart.base;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FromStream {

	/** 
	 * for a much faster impl than "Stream::toList"
	 * */
	@SuppressWarnings("unchecked")
	public static <T> List<T> toAList(Stream<T> stream){
		//stream.toList();
		return (List<T>) Arrays.asList(stream.toArray());
	}
	public static <T> Function<Stream<T>,T[]> toArrayMapper(Class<T> cl){
		return s -> toArray(s, cl);
	}
	@SuppressWarnings("unchecked")
	public static <T> T[] toArray(Stream<T> stream, Class<T> cl) {
		return stream.toArray(n->(T[])Array.newInstance(cl, n));
	}
	
	public static <T,K> HashMap<K,T> toMap(Stream<T> stream, Function<T,K> keyMapper){
		return stream.collect(Collectors.toMap(keyMapper, v->v, (a,b)->b, HashMap::new));
	}
	public static <T,K, M extends Map<K,T>> M toMap(Stream<T> stream, Function<T,K> keyMapper, Supplier<M> mapFactory){
		return stream.collect(Collectors.toMap(keyMapper, v->v, (a,b)->b, mapFactory));
	}
	public static <T,K,V, M extends Map<K,V>> M toMap(Stream<T> stream, Function<T,K> keyMapper, Function<T,V> valueMapper, Supplier<M> mapFactory){
		return stream.collect(Collectors.toMap(keyMapper, valueMapper, (a,b)->b, mapFactory));
	}
}
