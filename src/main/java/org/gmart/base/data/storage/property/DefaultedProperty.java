package org.gmart.base.data.storage.property;

public interface DefaultedProperty<T> extends Property<T> {
	/**
	 * set the value of this property to default value
	 */
	void restoreDefault();
	T getDefaultValue();
}
