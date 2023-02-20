package org.gmart.base;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.gmart.base.data.structure.dim1.Arrays2;

public class Joining {

	@SuppressWarnings("unchecked")
	public static <T> Stream<T> join(Stream<T> inputStream, Supplier<T> toInsert) {
		Object[] in = inputStream.toArray();
		inputStream.iterator();
		return Stream.of(join((T[])in, toInsert));
	}

	public static <T> T[] join(T[] input, Supplier<T> toInsert) {
		int size = input.length;
		int outLen = 2*size - 1;
		T[] out = Arrays2.ofZeroing(input, outLen);
		int inLastIndex = size-1;
		for(int i = 0, j = 0; j < inLastIndex; i += 2, j++) {
			out[i] = input[j];
			out[i+1] = toInsert.get();
		}
		out[outLen-1] = input[inLastIndex];
		return out;
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
	
	//	@SuppressWarnings("unchecked")
	//	public static <T> ArrayList<T> insertBetween(List<T> input, Supplier<T> toInsert) {
	//		int size = input.size();
	//		int outLen = 2*size - 1;
	//		ArrayList<T> out = new ArrayList<>(outLen);
	//		int inLastIndex = size-1;
	//		for(int i = 0, j = 0; j < inLastIndex; i += 2, j++) {
	//			out.add(null)
	//			out[i] = in[j];
	//			out[i+1] = toInsert.get();
	//		}
	//		out[outLen-1] = in[inLastIndex];
	//		return Stream.of((T[])out);
	//	}
	
}