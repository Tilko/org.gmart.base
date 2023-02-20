package org.gmart.base.component.integrator.number;

import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

import org.gmart.base.component.integrator.Integrator;

public class IntIntegrators {

	public final static BinaryOperator<Integer> iAdder = (accu, toAccu) -> accu + toAccu;
	
	public static <N> Integrator<Integer, Integer> post(int initValue){
		return Integrator.post(initValue, iAdder);
	}
	public static <N> Integrator<Integer, Integer> pre(int initValue){
		return Integrator.pre(initValue, iAdder);
	}
	public static <N> Integrator<Integer, Integer> post(){
		return post(0);//new IntegratorDouble_post();
	}
	public static <N> Integrator<Integer, Integer> pre(){
		return pre(0);
	}
}
