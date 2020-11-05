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
package org.gmart.util.functionalProg.interfaces;

import java.util.function.Function;

//import util.proc.BiContainer;

@FunctionalInterface
public interface ThrowingFunction<T,R, E extends Exception> {
	R apply(T e) throws E;

	static <T,R, E extends Exception> Function<T,R> wrapper(ThrowingFunction<T,R, E> f, AtomicExceptionRef<E> exception) throws E {
		return kv -> {
			try {
				return f.apply(kv);
			} catch (Exception rawEx) {
				if(!exception.hasBeenInitialized)
					try {
						exception.set((E)rawEx);
					} catch (Exception e1) {
						exception.rawException = rawEx;
					}
			}
			return null;
		};
	}
//	static <T,R, E extends Exception> Function<T,BiContainer<R, AtomicExceptionRef<E>>> wrapper(ThrowingFunction<T,R, E> f) throws E {
//		return kv -> {
//			try {
//				return new BiContainer<>(f.apply(kv), null);
//			} catch (Exception rawEx) {
//				AtomicExceptionRef<E> exceptionContainer = new AtomicExceptionRef<E>();
//				try {
//					exceptionContainer.set((E)rawEx);
//				} catch (Exception e1) {
//					exceptionContainer.rawException = rawEx;
//				}
//				return new BiContainer<>(null, exceptionContainer);
//			}
//		};
//	}
//	static <T,R, E extends Exception> Function<T,R> wrapper(ThrowingFunction<T,R, E> f, Supplier<E> exceptionBuilder) throws E {
//		return input -> {
//			try {
//				f.apply(input);
//			} catch(Exception e) {
//				try {
//					throw exceptionBuilder.get();
//				} catch (E e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//			}
//		};
//	}
}
