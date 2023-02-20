package org.gmart.base.data.storage.property;

import java.util.function.Consumer;
import java.util.function.Supplier;

public interface Property<T> extends Supplier<T>, Consumer<T> {

}
