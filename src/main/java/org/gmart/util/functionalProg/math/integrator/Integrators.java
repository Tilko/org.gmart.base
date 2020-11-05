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

import java.util.function.Function;

public class Integrators {
	public static <N> Function<N, Double> post(Function<N,Double> andThen_ed){
		return andThen_ed.andThen(new IntegratorDouble_post());
	}
	public static <N> Function<N, Integer> post_int(Function<N,Integer> andThen_ed){
		return andThen_ed.andThen(new IntegratorInt_post());
	}
	public static <N> Function<N, Double> pre(Function<N,Double> andThen_ed){
		return andThen_ed.andThen(new IntegratorDouble_pre());
	}
	public static <N> Function<N, Integer> pre_int(Function<N,Integer> andThen_ed){
		return andThen_ed.andThen(new IntegratorInt_pre());
	}
	
	public static <N> Function<N, Double> post(Function<N,Double> andThen_ed, double initVal){
		return andThen_ed.andThen(new IntegratorDouble_post(initVal));
	}
	public static <N> Function<N, Integer> post_int(Function<N,Integer> andThen_ed, int initVal){
		return andThen_ed.andThen(new IntegratorInt_post(initVal));
	}
	public static <N> Function<N, Double> pre(Function<N,Double> andThen_ed, double initVal){
		return andThen_ed.andThen(new IntegratorDouble_pre(initVal));
	}
	public static <N> Function<N, Integer> pre_int(Function<N,Integer> andThen_ed, int initVal){
		return andThen_ed.andThen(new IntegratorInt_pre(initVal));
	}
}
