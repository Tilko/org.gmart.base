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
package org.gmart.util.functionalProg.properties;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

public interface IOptional<T> {
	public void ifPresent(Consumer<? super T> action);
    public void ifPresentOrElse(Consumer<? super T> action, Runnable emptyAction);
    public IOptional<T> filter(Predicate<? super T> predicate);
    public <U> IOptional<U> map(Function<? super T, ? extends U> mapper);
    public <U> IOptional<U> flatMap(Function<? super T, ? extends OptProperty<? extends U>> mapper);
    public IOptional<T> or(Supplier<? extends OptProperty<? extends T>> supplier);
    public Stream<T> stream();
    public T orElse(T other);
    public T orElseThrow();
    public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X;
}
