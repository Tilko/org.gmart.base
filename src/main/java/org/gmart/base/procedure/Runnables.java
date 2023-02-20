package org.gmart.base.procedure;

public class Runnables {

	public static void runNTimes(int n, Runnable r) {
		for(int i = 0; i < n; i++) r.run();
	}
	public static Runnable of(int n, Runnable r) {
		return () -> runNTimes(n, r);
	}
	
}
