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
package org.gmart.util.functionalProg;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class LazySupplier<T> implements Supplier<T> {
	T builtObject;
	final private Supplier<T> builder;
	public LazySupplier(Supplier<T> builder) {
		super();
		this.builder = builder;
	}
	public boolean hasBeenUsed() {
		return builtObject != null;
	}
	@Override
	public T get() {
		if(builtObject == null)
			builtObject = builder.get();
		return builtObject;
	}
	
	public static <E> LazySupplier<E> lazy(Supplier<E> builder){
		return new LazySupplier<E>(builder);
	}
	public static <E, A> LazySupplier<E> lazy(A a, Function<A,E> f){
		return new LazySupplier<E>(()->f.apply(a));
	}
	public static <E, A, B> LazySupplier<E> lazy(A a, B b, BiFunction<A,B,E> f){
		return new LazySupplier<E>(()->f.apply(a,b));
	}
}
