package org.gmart.base.data.transform.toPrimitive;

import java.io.IOException;
import java.util.List;

import org.gmart.devtools.coding.java.gen.templating.JavaGenerators;
import org.gmart.lang.java.JavaPrimitives;


class Gen {
public static void main(String[] args) throws IOException {

		List.of(JavaPrimitives.primitives).stream().forEach(primitive -> {
			primitive.getNameBoxed();

			JavaGenerators.generateJavaFileFromST("To" + primitive.getNameBoxed(), """
					import java.util.function.Function;
					
					/** for signature disambigufication */
					@Generated("$common.generatorFQName$")
					public interface $common.typeNameOfFile$<T> extends Function<T, $type.nameBoxed$> {
						public static <T> To$type.nameBoxed$<T> of(Function<T, $type.nameBoxed$> f){
							return (To$type.nameBoxed$<T>)f;
						}
					}
					""", st -> {
				st.add("type", primitive);
				//st.add("fileTypeName", primitive.getNameBoxed());
			});

			/// System.out.println(hello.render());
		});
	}
}
