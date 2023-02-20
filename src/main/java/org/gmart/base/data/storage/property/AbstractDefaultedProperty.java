package org.gmart.base.data.storage.property;

public abstract class AbstractDefaultedProperty<V> implements DefaultedProperty<V> {
	protected V value;
	public V get() {
		return value != null ? value : getDefaultValue();
	}
	public void accept(V value) {
		this.value = value;
	}
	@Override
	public void restoreDefault() {
		this.value = getDefaultValue();
	}
}
