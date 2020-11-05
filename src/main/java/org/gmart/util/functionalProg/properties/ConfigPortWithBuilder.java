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

public class ConfigPortWithBuilder<T,E,B> extends OptProperty<B>{

	final public OptProperty<T> input = newProperty();

	E e;
	Function<E,B> builder;
	public ConfigPortWithBuilder(Function<E,B> builder) {
		super();
		this.builder = builder;
	}
	
	public static <T,E,B> ConfigPortWithBuilder<T,E,B> newPort(Function<E,B> builder){
		return new ConfigPortWithBuilder<T,E,B>(builder);
	}

	public void init(Function<T,E> dataBuilder) {
		input.ifPresent(input -> e = dataBuilder.apply(input));
	}
	
	protected B getValue() {
		if(e != null)
			return builder.apply(e);
    	return null;
    }
	@SafeVarargs
	public static <T,E> void loadInput(Function<T,E> dataBuilder, ConfigPortWithBuilder<T,E,?> ... ports) {
		for(ConfigPortWithBuilder<T,E,?> port : ports) {
			port.init(dataBuilder);
		}
	}
}
