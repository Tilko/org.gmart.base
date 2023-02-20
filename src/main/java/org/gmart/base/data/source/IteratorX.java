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
package org.gmart.base.data.source;

import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

import org.gmart.base.Streams;

public interface IteratorX<T> extends Iterator<T> {
	public static <T> IteratorX<T> of(Iterator<T> iterator) {
		return new IteratorX<T>() {
			@Override public boolean hasNext() {return iterator.hasNext();}
			@Override public T next() {return iterator.next();}
		};
	}
	default <Y> IteratorX<Y> to(Function<? super T, ? extends Y> mappingFunction){
		IteratorX<T> src = this;
		return new IteratorX<>() {
			@Override
			public boolean hasNext() {
				return src.hasNext();
			}
			@Override
			public Y next() {
				return mappingFunction.apply(src.next());
			}
		};
	}
	default Stream<T> toStream(){
		return Streams.of(this);
	}
	default void supplyOne(Consumer<T> consumer){
		if(this.hasNext())
			consumer.accept(this.next());
	}
	default Runnable supplyOneRunnable(Consumer<T> consumer){
		return ()->this.supplyOne(consumer);
	}
	default void supplyAll(Consumer<T> consumer){
		while(this.hasNext())
			consumer.accept(this.next());
	}
	default Runnable supplyAllRunnable(Consumer<T> consumer){
		return ()->this.supplyAll(consumer);
	}
}