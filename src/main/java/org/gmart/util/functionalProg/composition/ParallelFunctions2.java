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
package org.gmart.util.functionalProg.composition;

import java.util.function.Function;

import org.javatuples.homogeneous.Bi;

public class ParallelFunctions2<X,Y> implements Function<X,Bi<Y>> {

	private final Function<X,Y> f0;
	private final Function<X,Y> f1;
	
	ParallelFunctions2(Function<X,Y> f0, Function<X,Y> f1){
		this.f0 = f0;
		this.f1 = f1;
	}
//	ParallelFunctions2(Function<X,Y> f0, Function<X,Y> f1, boolean swap){
//		if(swap) {
//			this.f0 = f1;
//			this.f1 = f0;
//		} else {
//			this.f0 = f0;
//			this.f1 = f1;
//		}		
//	}
	@Override
	public Bi<Y> apply(X input) {
		return new Bi<>(f0.apply(input), f1.apply(input));
	}
}
