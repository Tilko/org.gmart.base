package org.gmart.util.functionalProg.enabled;

import java.util.function.Consumer;

public class EnabledConsumer<T> extends AbstractEnabled implements Consumer<T> {
	Consumer<T> consumer;
	public EnabledConsumer(Consumer<T> consumer) {
		super();
		this.consumer = consumer;
	}
	public EnabledConsumer(Consumer<T> consumer, boolean isEnabled) {
		super(isEnabled);
		this.consumer = consumer;
	}
	@Override
	public void accept(T t) {
		if(isEnabled)
			consumer.accept(t);
	}
	public static <T> EnabledConsumer<T> of(Consumer<T> consumer) {
		return new EnabledConsumer<>(consumer);
	}
}