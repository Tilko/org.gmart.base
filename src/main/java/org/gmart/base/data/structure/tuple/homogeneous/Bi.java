package org.gmart.base.data.structure.tuple.homogeneous;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.gmart.base.FromStream;
import org.gmart.base.data.structure.tuple.Pair;

public class Bi<T> extends Pair<T,T> implements IBi<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8165935734858816483L;

	public Bi(T value0, T value1) {
		super(value0, value1);
	}
	public static <T> Bi<T> of(T value0, T value1){
		return new Bi<>(value0, value1);
	}
	public static <T> Bi<T> of(T value0, T value1, boolean swap){
		return swap ? new Bi<>(value1, value0) : new Bi<>(value0, value1);
	}
	
	public static <T> Bi<T> of(Supplier<T> value0, Supplier<T> value1){
		return of(value0.get(), value1.get());
	}
	public static <T> Bi<T> of(Supplier<T> value0, Supplier<T> value1, boolean swap){
		return of(value0.get(), value1.get(), swap);
	}
	public static <T> Bi<T> of(T value0, T value1, BiPredicate<T,T> swapCondition){
		return of(value0, value1, swapCondition.test(value0, value1));
	}
	
	public <Y> Bi<Y> map(Function<T,Y> mapping) {
		return map(mapping, mapping);
	}
	public <Y> Bi<Y> map(Function<T,Y> mapping0, Function<T,Y> mapping1) {
		return Bi.of(mapping0.apply(this.getValue0()), mapping1.apply(this.getValue1()));
	}
	public static <T> HashMap<T, T> toMap(List<Bi<T>> oldNewPairs) {
		HashMap<T, T> hashMap = new HashMap<T,T>();
		oldNewPairs.forEach(v -> hashMap.put(v.getValue0(), v.getValue1()));
		return hashMap;
	}
	
	public Stream<T> stream(){
		return Stream.of(getValue0(), getValue1());
	}

	public static <X> Bi<X> fromArray(final X[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        if (array.length != 2) {
            throw new IllegalArgumentException("Array must have exactly 2 elements in order to create a Pair. Size is " + array.length);
        }
        return new Bi<X>(array[0],array[1]);
    }
	public static List<Bi<String>> parse(String string) {
		//Stream.of(string.split("\\R")).forEach(s->System.out.println(s));
		return Stream.of(string.split("\\R")).map(pair -> Bi.fromArray(pair.split("¤{3}"))).toList();
	}
	public static <T> Bi<Stream<T>> transpose(Stream<Bi<T>> tupleStream){
		return transpose(tupleStream.toList());
	}
	public static <T> Bi<Stream<T>> transpose(Bi<T>[] pairs) {
		return Bi.transpose(Arrays.asList(pairs));
	}
	public static <T> Bi<T[]> transpose(List<Bi<T>> pairs, Class<T> cl) {
		return Bi.transpose(pairs).map(FromStream.toArrayMapper(cl));
	}
	public static <T> Bi<Stream<T>> transpose(List<Bi<T>> pairs) {
		return Bi.of(pairs.stream().map(Bi::getValue0), pairs.stream().map(Bi::getValue1));
	}
 }
