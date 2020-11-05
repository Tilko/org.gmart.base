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

public class LazySupplier2<X,Y,T> implements BiFunction<X, Y, T> {
	T builtObject;
	private BiFunction<X,Y,T> builder;
	public BiFunction<X,Y,T> getBuilder() {
		return builder;
	}
	public void setBuilder(BiFunction<X,Y,T> builder) {
		this.builder = builder;
	}
	public LazySupplier2(BiFunction<X,Y,T> builder) {
		super();
		this.builder = builder;
	}
	public boolean hasBeenUsed() {
		return builtObject != null;
	}
	@Override
	public T apply(X x, Y y) {
		if(builtObject == null)
			builtObject = builder.apply(x,y);
		return builtObject;
	}
	public static <X,Y,T> LazySupplier2<X,Y,T> lazy(BiFunction<X,Y,T> builder){
		return new LazySupplier2<X,Y,T>(builder);
	}
//	public static <T, A, X,Y> LazySupplier2<X,Y,T> lazy(A a, Function<A,E> f){
//		return new LazySupplier2<X,Y,T>(()->f.apply(a));
//	}
//	public static <E, A, B> LazySupplier2<E> lazy(A a, B b, BiFunction<A,B,E> f){
//		return new LazySupplier2<E>(()->f.apply(a,b));
//	}
	
}
