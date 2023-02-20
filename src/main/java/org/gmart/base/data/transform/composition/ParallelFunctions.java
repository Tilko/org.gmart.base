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
package org.gmart.base.data.transform.composition;

import java.util.ArrayList;
import java.util.function.Function;

import org.gmart.base.data.structure.tuple.homogeneous.Bi;

public class ParallelFunctions {
	public static <X,Y> Function<X,Bi<Y>> of(Function<X,Y> f0, Function<X,Y> f1){
		return new ParallelFunctions2<>(f0, f1);
	}
	public static <X,Y> Function<X,Bi<Y>> of(Function<X,Y> f0, Function<X,Y> f1, boolean swap){
		return swap ? new ParallelFunctions2<>(f1, f0) : new ParallelFunctions2<>(f0, f1);
	}
	public static <X,Y> Function<X,Bi<Y>> of(Bi<Function<X,Y>> functions){
		return new ParallelFunctions2<>(functions.getValue0(), functions.getValue1());
	}
	
	@SafeVarargs
	public static <X,Y> Function<X,ArrayList<Y>> of(Function<X,Y> ... functions){
		return new ParallelFunctionsN<X,Y>(functions);
	}
}
