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
package org.gmart.base.component.integrator.number;

import java.util.function.BinaryOperator;

import org.gmart.base.component.integrator.Integrator;

public class DIntegrators {

	public final static BinaryOperator<Double> dAdder = (accu, toAccu) -> accu + toAccu;

	public static <N> Integrator<Double, Double> post(){
		return post(0.);//new IntegratorDouble_post();
	}
	
	public static <N> Integrator<Double, Double> pre(){
		return pre(0.);
	}
	public static <N> Integrator<Double, Double> post(double initValue){
		return Integrator.post(initValue, dAdder);
	}
	
	public static <N> Integrator<Double, Double> pre(double initValue){
		return Integrator.pre(initValue, dAdder);
	}
	
}
