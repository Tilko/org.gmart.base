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
package org.gmart.base.data.transform.number;

import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

/**  
 * base number operation only done with incrementation */
public class Just4Fun_NumberProcessor {
	public static void log(Object obj) {
		System.out.println(obj);
	}
	public static void main(String[] args) {
		for(int i = 0; i < 11; i++) {
			log(Integer2.of(0).power(Integer2.of(i)));
		}
	}

	private static class Integer2 {
		public static final Integer2 zero = new Integer2(0);
		public static final Integer2 one  = new Integer2(1);
		public static Integer2 of(int value) {
			return new Integer2(value);
		}
		public String toString() {
			return "" + value;
		}
		int value;
		public Integer2(int value) {
			this.value = value;
		}
		public int intValue() {
			return value;
		}
		public Integer2 increment() {
			return new Integer2(value + 1);
		}
		public Integer2 add(Integer2 addend) {
			return new NumberProcessorByRepeator_Unary(this, addend).calculate(Integer2::increment);   
		}
		public Integer2 multiply(Integer2 multiplicand) {
			return new NumberProcessorByRepeator_Binary(zero, this, multiplicand).calculate(Integer2::add); 
		}
		public Integer2 power(Integer2 exponent) {
			return new NumberProcessorByRepeator_Binary(one, this, exponent).calculate(Integer2::multiply); 
		}
//		public Integer2 power2(Integer2 exponent2) {
//			return new NumberProcessorByRepeator_Binary(this, this, exponent2).calculate(Integer2::power);
//		}
//		public Integer2 power22(Integer2 seed, Integer2 exponent22) {
//			return new NumberProcessorByRepeator_Binary(seed, this, exponent22).calculate(Integer2::power);
//		}
	}
	
	private static abstract class AbstractNumberProcessorByRepeator {
		protected Integer2 seed;
		private final Integer2 numberOfTime;
		public AbstractNumberProcessorByRepeator(Integer2 seedInit, Integer2 numberOfTime){
			this.numberOfTime = numberOfTime;
			this.seed = seedInit;
		}
		protected void execRepetitionsOf(Runnable runnable){
			repeat(numberOfTime, runnable);
		}
	}
	private static class NumberProcessorByRepeator_Unary extends AbstractNumberProcessorByRepeator {
		public NumberProcessorByRepeator_Unary(Integer2 seedInit, Integer2 numberOfTime){
			super(seedInit, numberOfTime);
		}
		public Integer2 calculate(UnaryOperator<Integer2> operator) {
			execRepetitionsOf(() -> seed = operator.apply(seed));
			return seed;
		}
	}
	private static class NumberProcessorByRepeator_Binary extends AbstractNumberProcessorByRepeator {
		private final Integer2 instance;
		public NumberProcessorByRepeator_Binary(Integer2 seedInit, Integer2 instance, Integer2 numberOfTime){
			super(seedInit, numberOfTime);
			this.instance = instance;
		}
		public Integer2 calculate(BinaryOperator<Integer2> operator) {
			execRepetitionsOf(() -> seed = operator.apply(seed, instance));
			return seed;
		}
	}
	static void repeat(Integer2 numberOfTime, Runnable process) {
		for(int i = 0; i < numberOfTime.intValue(); i++) {
			process.run();
		}
	}
}
