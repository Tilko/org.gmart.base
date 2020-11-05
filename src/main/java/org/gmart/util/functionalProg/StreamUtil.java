/*******************************************************************************
 * Copyright 2020 Grégoire Martinetti
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package org.gmart.util.functionalProg;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;

import org.javatuples.Pair;


public class StreamUtil {
	public static <T> boolean checkUnicity(Stream<T> stream){
		return stream.allMatch(new HashSet<>()::add);
	}
	public static <T,K, M extends Map<K,T>> M toMap(Stream<T> stream, Function<T,K> keyMapper, Supplier<M> mapFactory){
		return stream.collect(Collectors.toMap(keyMapper, v->v, (a,b)->b, mapFactory));
	}
	public static <T,K,V, M extends Map<K,V>> M toMap(Stream<T> stream, Function<T,K> keyMapper, Function<T,V> valueMapper, Supplier<M> mapFactory){
		return stream.collect(Collectors.toMap(keyMapper, valueMapper, (a,b)->b, mapFactory));
	}
	public static <T> Stream<T> filter(T[] methods, Predicate<T> filtering) {
		return Stream.of(methods).filter(filtering);
	}
//	public static <T> Optional<T> getUnique(Stream<T> stream){
//		
//	}
	
	public static <E0,E1> Stream<Pair<E0,E1>> f2(Consumer<BiConsumer<E0,E1>> consumer) {
		Builder<Pair<E0,E1>> builder = Stream.builder();
		consumer.accept((e0, e1)->{
			builder.accept(Pair.with(e0, e1));
		});
		return builder.build();
	}
	public static <E0> Stream<E0> f22(Consumer<Consumer<E0>> consumer) {
		Builder<E0> builder = Stream.builder();
		consumer.accept(e0 -> builder.accept(e0));
		return builder.build();
	}
	
	
	public static <E> int forEach(Stream<E> stream, BiConsumer<Integer, E> consumer) {
		return forEach(stream,0, consumer);
	}
	public static <E> int forEach(Stream<E> stream, int fromIndex, BiConsumer<Integer, E> consumer) {
		//long count = stream.get().count();
		//AtomicLong i = new AtomicLong(0);
		AtomicInteger i = new AtomicInteger(fromIndex);
		stream.forEach(e ->{
			consumer.accept(i.getAndIncrement(), e);
		});
		return i.get();
	}
	
	public static <E> void join(Stream<E> elems, Runnable between, Consumer<E> consumer) {
		AtomicBoolean isFirst = new AtomicBoolean(true);
		elems.forEach(elem -> {
			if(isFirst.get()) {
				isFirst.set(false);
				consumer.accept(elem);
			} else {
				between.run();
				consumer.accept(elem);
			}
		});
	}
	
	/**
	 * Caller must ensure that "as.size() <= bs.size()"
	 */
	public static <A,B,C> Stream<C> zip(List<A> as, List<B> bs, BiFunction<A,B,C> toCont){
		assert as.size() <= bs.size();
		int size = as.size();
		Builder<C> builder = Stream.builder();
		for(int i = 0; i < size; i++) {
			builder.add(toCont.apply(as.get(i), bs.get(i)));
		}
		return builder.build();
	}
	
	public static boolean isPlural(Stream<?> stream) {
		return stream.limit(2).count() == 2;
	}
	public static <T> Stream<T> streamOfNullable(Collection<T> l){
		if(l == null)
			return Stream.empty();
		return l.stream();
	}
	
	public static void repeat(int n, Runnable r) {
		for(int i = 0; i < n; i++) r.run();
	}
	public static <T> T repeat(int n, T seed, Function<T,T> nextGetter) {
		for(int i = 0; i < n; i++) {
			seed = nextGetter.apply(seed);
		}
		return seed;
	}
	public static <T> Stream<T> flatter(int n, Stream<T> seed, Function<T,Stream<T>> nextGetter) {
		for(int i = 0; i < n; i++) {
			seed = seed.flatMap(nextGetter);
		}
		return seed;
	}
	public static <T> Stream<T> flatter(int n, T seed, Function<T,Stream<T>> nextGetter, Function<T,T> intermediate) {
		Stream<T> stream = Stream.of(seed);
		for(int i = 0; i < n; i++) {
			stream = stream.map(intermediate).flatMap(nextGetter);
		}
		return stream;
	}
}
