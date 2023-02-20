package org.gmart.base.data.transform.primitiveArrays;

import javax.annotation.processing.Generated;

@Generated("org.gmart.base.adaptation.Gen")
public class PrimitivesArrays {
	public static Character[] toBoxed(char[] array) {
		int length = array.length;
		Character[] rez = new Character[length];
		for(int i = 0; i < length; i++)
			rez[i] = array[i];
		return rez;
	}
	public static char[] toUnboxed(Character[] array) {
		int length = array.length;
		char[] rez = new char[length];
		for(int i = 0; i < length; i++)
			rez[i] = array[i];
		return rez;
	}
	public static Byte[] toBoxed(byte[] array) {
		int length = array.length;
		Byte[] rez = new Byte[length];
		for(int i = 0; i < length; i++)
			rez[i] = array[i];
		return rez;
	}
	public static byte[] toUnboxed(Byte[] array) {
		int length = array.length;
		byte[] rez = new byte[length];
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
	public static short[] toUnboxed(Short[] array) {
		int length = array.length;
		short[] rez = new short[length];
		for(int i = 0; i < length; i++)
			rez[i] = array[i];
		return rez;
	}
	public static Integer[] toBoxed(int[] array) {
		int length = array.length;
		Integer[] rez = new Integer[length];
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
	public static Long[] toBoxed(long[] array) {
		int length = array.length;
		Long[] rez = new Long[length];
		for(int i = 0; i < length; i++)
			rez[i] = array[i];
		return rez;
	}
	public static long[] toUnboxed(Long[] array) {
		int length = array.length;
		long[] rez = new long[length];
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
	public static float[] toUnboxed(Float[] array) {
		int length = array.length;
		float[] rez = new float[length];
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
	public static double[] toUnboxed(Double[] array) {
		int length = array.length;
		double[] rez = new double[length];
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
	public static boolean[] toUnboxed(Boolean[] array) {
		int length = array.length;
		boolean[] rez = new boolean[length];
		for(int i = 0; i < length; i++)
			rez[i] = array[i];
		return rez;
	}
}