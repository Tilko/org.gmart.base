package org.gmart.base.data.transform.primitiveArrays;

import java.io.IOException;
import java.util.List;

import org.gmart.devtools.coding.java.gen.templating.JavaGenerators;
import org.gmart.lang.java.JavaPrimitives;

class Gen {
	public static void main(String[] args) throws IOException {
		JavaGenerators.generateJavaFileFromSTGFile(st -> {
			st.add("type", List.of(JavaPrimitives.primitives));
		});
	}
}