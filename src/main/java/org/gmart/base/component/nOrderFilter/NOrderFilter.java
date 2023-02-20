package org.gmart.base.component.nOrderFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import org.gmart.base.data.structure.dim1.CircularArrayList;
import org.gmart.base.procedure.TriConsumer;

public class NOrderFilter {
	public static <X,Y> List<Y> processCircular(List<X> dataCircle, int order, Function<List<X>, Y> combinatory){
		CircularArrayList<X> dataCircle0 = new CircularArrayList<>(dataCircle);
		ArrayList<Y> rez = new ArrayList<>();
		for(int i = 0; i < dataCircle.size(); i++) {
			rez.add(combinatory.apply(dataCircle0.subListCircular(i, i + order)));
		}
		return rez;
	}
	public static <X,Y> void processNotCircular(List<X> dataLine, int order, Consumer<List<X>> user){
		if(dataLine.size() < order) 
			return;
		int lastp1 = dataLine.size() - order + 1;
		for(int i = 0; i < lastp1; i++) {
			user.accept(dataLine.subList(i, i + order));			
		}
	}
	public static <X,Y> List<Y> processNotCircular_list(List<X> signal, int order, Function<List<X>, Y> combinatory){
		ArrayList<Y> rez = new ArrayList<>();
		processNotCircular(signal, order, xs -> rez.add(combinatory.apply(xs)));
		return rez;
	}
	
	public static <X,Y> List<Y> processNotCircular_list(List<X> signal, BiFunction<X,X, Y> combinatory){
		ArrayList<Y> rez = new ArrayList<>();
		processNotCircular(signal, (x0,x1) -> rez.add(combinatory.apply(x0,x1)));
		return rez;
	}
	public static <X,Y> void processNotCircular(List<X> signal, BiConsumer<X,X> user){
		if(signal.size() < 2) 
			return;
		int lastp1 = signal.size() - 1;
		for(int i = 0; i < lastp1; i++) {
			user.accept(signal.get(i), signal.get(i+1));			
		}
	}
	
	
	
	public static <X,Y> void processNotCircular(List<X> signal, TriConsumer<X,X,X> user){
		if(signal.size() < 3) 
			return;
		int lastp1 = signal.size() - 2;
		for(int i = 0; i < lastp1; i++) {
			user.accept(signal.get(i), signal.get(i+1), signal.get(i+2));			
		}
	}
}
