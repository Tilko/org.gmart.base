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
package org.gmart.base;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;

import org.gmart.base.data.transform.TriFunction;

public class StreamUtils {
	public static <T> boolean checkUnicity(Stream<T> stream){
		return stream.allMatch(new HashSet<>()::add);
	}
	
	public static boolean isPlural(Stream<?> stream) {
		return stream.limit(2).count() == 2;
	}
	

	public static <E> int forEach(Stream<? extends E> stream, BiConsumer<Integer, ? super E> consumer) {
		return forEach(stream,0, consumer);
	}
	public static <E> int forEach(Stream<? extends E> stream, int fromIndex, BiConsumer<Integer, ? super E> consumer) {
		AtomicInteger i = new AtomicInteger(fromIndex);
		stream.forEach(e ->consumer.accept(i.getAndIncrement(), e));
		return i.get();
	}
	public static <E, Y>  Stream<Y> map(Stream<? extends E> stream, BiFunction<Integer, ? super E, ? extends Y> mapping) {
		return map(stream,0, mapping);
	}
	public static <E, Y> Stream<Y> map(Stream<E> stream, int fromIndex, BiFunction<Integer, ? super E, ? extends Y> mapping) {
		AtomicInteger i = new AtomicInteger(fromIndex);
		return stream.map(e -> mapping.apply(i.getAndIncrement(), e));
	}
	
	
	public static <E, P, Y> Stream<Y> zipWithProp(List<E> list, Function<? super E, ? extends P> otherStreamMapping, BiFunction<? super E, ? super P, ? extends Y> zipping) {
		return zip(list, list.stream().map(otherStreamMapping).toList(), zipping);
	}
	public static <E, P, Y> Stream<Y> zipWithProp(List<E> list, Function<? super E, ? extends P> otherStreamMapping, TriFunction<? super E, ? super P, Integer, ? extends Y> zipping) {
		return zip(list, list.stream().map(otherStreamMapping).toList(), zipping);
	}
	/**
	 * output stream length == minSize(input lists)
	 */
	public static <A,B,C> Stream<C> zip(List<A> as, List<B> bs, BiFunction<? super A, ? super B, ? extends C> toCont){
		return zipInternal(as, bs, Math.min(as.size(), bs.size()), toCont);
	}
	private static <A,B,C> Stream<C> zipInternal(List<A> as, List<B> bs, int size, BiFunction<? super A, ? super B, ? extends C> toCont){
		Builder<C> builder = Stream.builder();
		for(int i = 0; i < size; i++) {
			builder.add(toCont.apply(as.get(i), bs.get(i)));
		}
		return builder.build();
	}
	/**
	 * output stream length == minSize(input lists)
	 */
	public static <A,B,C> Stream<C> zip(List<A> as, List<B> bs, TriFunction<? super A, ? super B, Integer, ? extends C> toCont){
		return zipInternal(as, bs, Math.min(as.size(), bs.size()), toCont);
	}
	private static <A,B,C> Stream<C> zipInternal(List<A> as, List<B> bs, int size, TriFunction<? super A, ? super B, Integer, ? extends C> toCont){
		Builder<C> builder = Stream.builder();
		for(int i = 0; i < size; i++) {
			builder.add(toCont.apply(as.get(i), bs.get(i), i));
		}
		return builder.build();
	}
	
	

	
	public static <T> ArrayList<T> insertInMiddle(Stream<T> stream, T elem) {
		ArrayList<T> result = stream.collect(Collectors.toCollection(ArrayList::new));
	    result.add(result.size()/2, elem);
	    return result;
	}
	public static <T> ArrayList<T> insertInStream(Stream<T> stream, int index, T elem) {
		ArrayList<T> result = stream.collect(Collectors.toCollection(ArrayList::new));
	    result.add(index, elem);
	    return result;
	}
	
	
}
