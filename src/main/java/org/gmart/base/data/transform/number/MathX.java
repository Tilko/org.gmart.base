package org.gmart.base.data.transform.number;

import org.gmart.base.data.structure.tuple.homogeneous.Bi;

public class MathX {
	
	public static double ensureUnitRange(double value) {
		return ensureRange(0, 1, value);
	}
	public static double ensureRange(double min, double max, double value) {
		return Math.min(max, Math.max(min, value));
	}
	/**
	 * @return value0: quotient (euclidean), value1: remainder, as: num = quotient*denom + remainder
	 */
	public static Bi<Double> euclDiv(double num, double denom){
		double remainder = num%denom;
		return Bi.of((num-remainder)/denom, remainder);
	}
	
	public static void main(String[] args) {
		double pi = Math.PI;		
		double divisor = pi;
		double angle = -2*divisor - 0.1;
		double remainder = angle%divisor;
		System.out.println("angle/pi:" + (angle/divisor));
		System.out.println("angle%pi:" + remainder);
		System.out.println("angle - remainder:" + (angle - remainder));
		System.out.println("(angle/pi) + angle%pi:" + ((angle/pi) + angle%pi));
		
		Bi<Double> euclDiv = euclDiv(-2*divisor - 0.1, divisor);
		System.out.println("euclDiv.quotient:" + euclDiv.getValue0());
		System.out.println("euclDiv.remainder:" + euclDiv.getValue1());
		System.out.println("input - reconstruction:" + (angle - (euclDiv.getValue0()*divisor + euclDiv.getValue1())));
		euclDiv = euclDiv(2*divisor + 0.1, divisor);
		System.out.println("euclDiv.quotient:" + euclDiv.getValue0());
		System.out.println("euclDiv.remainder:" + euclDiv.getValue1());
	}
}
