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

public interface IOptional<V> {
	boolean isPresent();
	boolean isEmpty();
	V orElseGet(Supplier<? extends V> supplier);
	public void ifPresent(Consumer<? super V> action);
    public void ifPresentOrElse(Consumer<? super V> action, Runnable emptyAction);
    public Optional<V> filter(Predicate<? super V> predicate);
    public <U> Optional<U> map(Function<? super V, ? extends U> mapper);
    public <U> Optional<U> flatMap(Function<? super V, ? extends Optional<? extends U>> mapper);
    public Optional<V> or(Supplier<? extends Optional<? extends V>> supplier);
    public Stream<V> stream();
    public V orElse(V other);
    public V orElseThrow();
    public <X extends Throwable> V orElseThrow(Supplier<? extends X> exceptionSupplier) throws X;
}
