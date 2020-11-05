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

import java.util.function.Function;

public class ConfigPort<T,E> extends OptProperty<E> {
	
	final public OptProperty<T> input = newProperty();
	
	
//	Function<T,E> dataBuilder;
//  public static <T,E> ConfigPort<T,E> newPort(Function<T,E> builder){
//	return new ConfigPort<T,E>(builder);
//}
//	public ConfigPort() Function<T,E> builder) {
//		super();
//		this.dataBuilder = builder;
//	}
	
	public ConfigPort() {
		super();
	}
	
	public static <T,E> ConfigPort<T,E> newPort(){
		return new ConfigPort<T,E>();
	}

	public void init(Function<T,E> dataBuilder) {
		input.ifPresent(input -> value = dataBuilder.apply(input));
	}
	
}
