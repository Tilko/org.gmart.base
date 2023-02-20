package org.gmart.base.data.storage.property.optional;

import java.util.function.Supplier;

public class OptionalProps {
	public static <T> OptPropertyImpl<T> of(){
    	return new OptPropertyImpl<T>();
    }
    public static <T> OptPropertyImpl<T> of(T initValue){
    	return new OptPropertyImpl<T>(initValue);
    }
    public static <T> SuppliedOptional<T> of(Supplier<T> supplier) {
		return new SuppliedOptional<T>(supplier);
	}
}
