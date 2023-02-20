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
package org.gmart.base.data.transform;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicExceptionRef<E extends Exception> extends AtomicReference<E>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8608442956522873149L;
	public Exception rawException;
	boolean hasBeenInitialized = false;
	void setAsInitialized() {
		hasBeenInitialized = true;
	}
	public void throwIfAny() throws E, Exception {
		if(rawException != null)
			throw rawException;
		else {
			E e = this.get();
			if(e != null) throw e;
		}
	}
	public void throwIfAny2() throws E {
		if(rawException != null)
			throw new RuntimeException(rawException);
		else {
			E e = this.get();
			if(e != null) throw e;
		}
	}
}

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
	
