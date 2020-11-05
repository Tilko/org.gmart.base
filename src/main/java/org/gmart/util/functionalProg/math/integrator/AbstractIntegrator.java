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
package org.gmart.util.functionalProg.math.integrator;

import java.util.function.UnaryOperator;

public abstract class AbstractIntegrator<N> implements UnaryOperator<N>{
	AbstractIntegrator(){
		defaultInit();
	}
	AbstractIntegrator(N initValue){
		this.a = initValue;
	}
	N a;
	protected abstract void add(N input);
	protected abstract void defaultInit();
}
