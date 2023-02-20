package org.gmart.base.functionFromAcyclicGraphCodeGen;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public class AbstractComponent<T, E0> {
	
	public static void main(String[] args) {
		new AbstractComponent.Factory<>().build();
	}
	T state;
	
	interface T_ro<T> {
		
	}
	T_ro<T> state_ro;
	Supplier<E0> x;
	
	public Supplier<String> getOutStringExample(){
		return () -> state.toString() + x.get().toString();
	}
	
	public <O> Supplier<O> getOut(BiFunction<T_ro<T>, Supplier<E0>, O> outBuilder){
		return () -> outBuilder.apply(state_ro, x);
	}
	
	
	AbstractComponent(Supplier<E0> x) {
		this.x = x;
	}
	AbstractComponent(T initialState, Supplier<E0> x) {
		this.state = initialState;
		this.x = x;
	}
	
	public static class Factory<T, E0> {
		Supplier<E0> x;
		T initialState;
		public Factory<T, E0> withInitialState(T initialState) {
			this.initialState = initialState;
			return this;
		}
		
		public Factory<T, E0> with(Supplier<E0> x) {
			this.x = x;
			return this;
		}
		public Factory<T, E0> with(E0 x) {
			this.x = ()->x;
			return this;
		}
		public AbstractComponent<T, E0> build(){
			if(x == null) {
				
			}
			return new AbstractComponent<>(x);
		}
	}
}
