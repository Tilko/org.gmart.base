package org.gmart.base.debug;

public class RuntimeUtils {
	public static void printStack() {
		try {int a = 1/0;}catch(Exception e) {e.printStackTrace();};
	}
}
