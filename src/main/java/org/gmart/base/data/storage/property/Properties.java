package org.gmart.base.data.storage.property;

import java.util.function.Supplier;


/**
 * If you want to change default => Properties.withDefault(myOtherPropertyThatSupplyThatDefault); ...
 */
public class Properties {
	public static <V> DefaultedProperty<V> withDefault(V initValue_also_defaultValue) {
		return new AbstractDefaultedProperty<V>() {
			@Override
			public V getDefaultValue() {
				return initValue_also_defaultValue;
			}	
		};
	}
	public static <V> DefaultedProperty<V> withDefault(Supplier<V> defaultValueSupplier){
		return new AbstractDefaultedProperty<V>() {
			@Override
			public V getDefaultValue() {
				return defaultValueSupplier.get();
			}	
		};
	}
}
