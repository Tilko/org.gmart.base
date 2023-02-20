package org.gmart.base;

import java.util.Collection;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;
import java.util.stream.StreamSupport;

public class Streams<T> {
	
	public static <T> Stream<T> OfNullable(Collection<T> l){
		if(l == null)
			return Stream.empty();
		return l.stream();
	}

	public static <T> Stream<T> of(Iterator<T> iterator){
			return StreamSupport.stream(
	                Spliterators.spliteratorUnknownSize(
	                		iterator,
	                		Spliterator.ORDERED)
	                , false);
		}
		public static <T> Stream<T> of(Iterator<T> iterator, int estimateSize){
			return StreamSupport.stream(
	                Spliterators.spliterator(
	                		iterator, estimateSize, 
	                		Spliterator.ORDERED)
	                , false);
		}
		@SafeVarargs
		public static <T> Stream<T> concat(Stream<? extends T> ... streams){
			return Stream.of(streams).flatMap(i -> i);
		}

		public static <T> Stream<T> ofSame(int cardinal, T same) {
			return IntStream.range(0, cardinal).mapToObj(i->same);
		}

		public static <T> Stream<T> ofSame(int cardinal, Supplier<T> same) {
			return IntStream.range(0, cardinal).mapToObj(i->same.get());
		}

		public static Stream concatUS(Object ... objs) {
			if(objs.length == 0)
				return Stream.empty();
			Stream rez = Streams.of(objs[0]);
			for(int i = 1; i < objs.length; i++) {
				rez = Stream.concat(rez, Streams.of(objs[i]));
			}
			return rez;
		}

		public static Stream of(Object elemOrStream){
			if(elemOrStream instanceof Stream stream) {
				return stream;
			} else {
				return Stream.of(elemOrStream);
			}
		}

		public static <E> Stream<E> of(Consumer<Builder<E>> consumer) {
			Builder<E> builder = Stream.builder();
			consumer.accept(builder);
			return builder.build();
		}

}
