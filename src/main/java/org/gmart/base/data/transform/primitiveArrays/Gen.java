package org.gmart.base.data.transform.primitiveArrays;

import java.io.IOException;
import java.util.List;

import org.gmart.lang.java.JavaPrimitives;
import org.gmart.stjavagen.StForSingleJava;

class Gen {
	public static void main(String[] args) throws IOException {
		StForSingleJava.generateJavaFileFromSTGFile(st -> {
			st.add("type", List.of(JavaPrimitives.primitives));
		});
	}
}