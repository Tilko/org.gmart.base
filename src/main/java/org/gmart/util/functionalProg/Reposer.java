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

import java.util.function.BiConsumer;
import java.util.function.Function;

public class Reposer<T, E> {
	private final BiConsumer<T,E> setter;
	private final Function<T,E> getter;
	public BiConsumer<T, E> getSetter() {
		return setter;
	}
	public Function<T, E> getGetter() {
		return getter;
	}
	public Reposer(BiConsumer<T, E> T_set_E, Function<T, E> T_get_E) {
		super();
		this.setter = T_set_E;
		this.getter = T_get_E;
	}
	public E getFrom(T t) {
		return getter.apply(t);
	}
	public void setTo(T t, E e) {
		setter.accept(t, e);
	}
}
