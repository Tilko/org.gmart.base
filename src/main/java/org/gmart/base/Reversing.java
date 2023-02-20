package org.gmart.base;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.lang3.ArrayUtils;
import org.gmart.base.data.structure.dim1.Arrays2;

import com.google.common.collect.Lists;

public class Reversing {
	public static void main(String[] args) {
//		String[] array = From.a(List.of("a")).toArray_workIfSameTypesOfElements();
//		array.getClass().getC.getComponentType()
//		System.out.println(array[0]);
		String[] l = of(new String[] {"e"});
		System.out.println(l[0]);
	}
	public static <E> E[] of(E[] from) {
		return Lists.reverse(Arrays.asList(from)).toArray(Arrays2.ofZeroing(from));
	}
	public static <E> List<E> view(List<E> list) {
		return Lists.reverse(list);
	}
	public static <E> Stream<E> of(Stream<E> stream) {
		return view(FromStream.toAList(stream)).stream();
	}
	public static <E> void appliedTo(E[]list) {
		ArrayUtils.reverse(list);
	}
	
	public static class From {
		boolean viewOfInput;
		boolean unmodifiableOutput;
		
		public static <T> FromList<T> a(List<T> list) {
			return new FromList<>(list);
		}
		
		public static class FromList<T> {
			List<T> list;
			public FromList(List<T> list) {
				this.list = list;
			}
			boolean viewOfInput;
			boolean unmodifiableOutput;
			public ToArray<T> toArray(Class<T> klass){
				return new ToArray<T>(klass);
			}
			public T[] toArray2(Class<T> klass){
				return new ToArray<T>(klass).from(list);
			}
		}
	}
	public static class ToArray<T> {
		Class<T> klass;
		public ToArray(Class<T> klass) {
		this.klass = klass;
		}
		boolean viewOfInput;
		boolean unmodifiableOutput;
		boolean keepingOrder;
		
		@SuppressWarnings("unchecked")
		public T[] from(List<T> list){
			return list.toArray((T[])Array.newInstance(klass, list.size()));
		}
	}
	public static class ListToArray<T> {
		Class<T> klass;
		public ListToArray(List<T> list, Class<T> klass) {
		this.klass = klass;
		}
		boolean viewOfInput;
		boolean unmodifiableOutput;
		boolean keepingOrder;
		
		@SuppressWarnings("unchecked")
		public T[] from(List<T> list){
			return list.toArray((T[])Array.newInstance(klass, list.size()));
		}
	}
	
	
	public static class FromList {
		boolean viewOfInput;
		boolean unmodifiableOutput;
		
		
		
		
	}
	public static class ToList {
		boolean viewOfInput;
		boolean unmodifiableOutput;
		boolean keepingOrder;
		
		
		
	}
}
