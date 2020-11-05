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
package org.gmart.util.functionalProg.adaptation;

public class AdaptArrays {
	public static Integer[] toBoxed(int[] array) {
		int length = array.length;
		Integer[] rez = new Integer[length];
		for(int i = 0; i < length; i++)
			rez[i] = array[i];
		return rez;
	}
	public static Double[] toBoxed(double[] array) {
		int length = array.length;
		Double[] rez = new Double[length];
		for(int i = 0; i < length; i++)
			rez[i] = array[i];
		return rez;
	}
	public static Float[] toBoxed(float[] array) {
		int length = array.length;
		Float[] rez = new Float[length];
		for(int i = 0; i < length; i++)
			rez[i] = array[i];
		return rez;
	}
	public static Boolean[] toBoxed(boolean[] array) {
		int length = array.length;
		Boolean[] rez = new Boolean[length];
		for(int i = 0; i < length; i++)
			rez[i] = array[i];
		return rez;
	}
	public static Short[] toBoxed(short[] array) {
		int length = array.length;
		Short[] rez = new Short[length];
		for(int i = 0; i < length; i++)
			rez[i] = array[i];
		return rez;
	}
	
	
	
	public static int[] toUnboxed(Integer[] array) {
		int length = array.length;
		int[] rez = new int[length];
		for(int i = 0; i < length; i++)
			rez[i] = array[i];
		return rez;
	}
	public static double[] toUnboxed(Double[] array) {
		int length = array.length;
		double[] rez = new double[length];
		for(int i = 0; i < length; i++)
			rez[i] = array[i];
		return rez;
	}
	public static float[] toUnboxed(Float[] array) {
		int length = array.length;
		float[] rez = new float[length];
		for(int i = 0; i < length; i++)
			rez[i] = array[i];
		return rez;
	}
	public static boolean[] toUnboxed(Boolean[] array) {
		int length = array.length;
		boolean[] rez = new boolean[length];
		for(int i = 0; i < length; i++)
			rez[i] = array[i];
		return rez;
	}
	public static short[] toUnboxed(Short[] array) {
		int length = array.length;
		short[] rez = new short[length];
		for(int i = 0; i < length; i++)
			rez[i] = array[i];
		return rez;
	}
}
