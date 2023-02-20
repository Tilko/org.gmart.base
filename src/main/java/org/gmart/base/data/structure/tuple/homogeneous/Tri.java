package org.gmart.base.data.structure.tuple.homogeneous;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.gmart.base.FromStream;
import org.gmart.base.data.structure.tuple.Triplet;


public class Tri<T> extends Triplet<T,T,T> implements ITri<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8165935734858816483L;

	public Tri(T value0, T value1, T value2) {
		super(value0, value1, value2);
	}
	public static <T> Tri<T> of(T value0, T value1, T value2){
		return new Tri<>(value0, value1, value2);
	}
	
	public static <T> Tri<T> of(Supplier<T> value0, Supplier<T> value1, Supplier<T> value2){
		return of(value0.get(), value1.get(), value2.get());
	}

	public <Y> Tri<Y> map(Function<T,Y> mapping) {
		return map(mapping, mapping, mapping);
	}
	public <Y> Tri<Y> map(Function<T,Y> mapping0, Function<T,Y> mapping1, Function<T,Y> mapping2) {
		return Tri.of(mapping0.apply(this.getValue0()), mapping1.apply(this.getValue1()), mapping2.apply(this.getValue2()));
	}
	public Tri<T> change(Function<T, ? extends T> mapping0, Function<T, ? extends T> mapping1, Function<T, ? extends T> mapping2) {
		return Tri.of(mapping0 == null ? this.getValue0() : mapping0.apply(this.getValue0())
				    , mapping1 == null ? this.getValue1() : mapping1.apply(this.getValue1())
				    , mapping2 == null ? this.getValue2() : mapping2.apply(this.getValue2()));
	}
	public Stream<T> stream(){
		return Stream.of(getValue0(), getValue1(), getValue2());
	}
	public static <T> Tri<Stream<T>> transpose3(Stream<Tri<T>> streamOfTuple){
		List<Tri<T>> list = FromStream.toAList(streamOfTuple);//.toList();
		return Tri.of(list.stream().map(Tri::getValue0), list.stream().map(Tri::getValue1), list.stream().map(Tri::getValue2));
	}
 }