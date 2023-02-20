/*******************************************************************************
 * Copyright 2020 Grégoire Martinetti
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package org.gmart.base.data.storage.property.optional;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;


public interface OptionalProp<V> extends IOptional<V> {
	V getNullableValue();
	
	default Optional<V> getOptional() {
		return Optional.ofNullable(getNullableValue());
	}
//	default V get() {
//		return getOptional().get();
//	}
//	
	default boolean isPresent() {
		return getNullableValue() != null;
	}

	default boolean isEmpty() {
		return getNullableValue() == null;
	}

	default void ifPresent(Consumer<? super V> action) {
		getOptional().ifPresent(action);
	}

	default void ifPresentOrElse(Consumer<? super V> action, Runnable emptyAction) {
		getOptional().ifPresentOrElse(action, emptyAction);
	}

	default Optional<V> filter(Predicate<? super V> predicate) {
		return getOptional().filter(predicate);
	}

	default <U> Optional<U> map(Function<? super V, ? extends U> mapper) {
		return getOptional().map(mapper);
	}

	default <U> Optional<U> flatMap(Function<? super V, ? extends Optional<? extends U>> mapper) {
		return getOptional().flatMap(mapper);
	}

	default Optional<V> or(Supplier<? extends Optional<? extends V>> supplier) {
		return getOptional().or(supplier);
	}

	default Stream<V> stream() {
		return getOptional().stream();
	}

	default V orElse(V other) {
		return getOptional().orElse(other);
	}

	default V orElseGet(Supplier<? extends V> supplier) {
		return getOptional().orElseGet(supplier);
	}

	default V orElseThrow() {
		return getOptional().orElseThrow();
	}

	default <X extends Throwable> V orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
		return getOptional().orElseThrow(exceptionSupplier);
	}

//	default boolean equals(Object obj) {
//		return getOptional().equals(obj);
//	}
//
//	default int hashCode() {
//		return getOptional().hashCode();
//	}
//
//	default String toString() {
//		return getOptional().toString();
//	}
}
