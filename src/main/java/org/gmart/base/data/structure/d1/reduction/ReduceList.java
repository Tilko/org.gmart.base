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
package org.gmart.base.data.structure.d1.reduction;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public class ReduceList {
	public static <X,Y> List<Y> reduceAdjacentPairs(List<X> in, BiFunction<X,X,Y> constructor) {
		List<Y> rez = new ArrayList<>();
		for(int i = 0; i < in.size(); i += 2) {
			X main = in.get(i);
			X sub = i+1 != in.size() ? in.get(i+1) : null;
			rez.add(constructor.apply(main, sub));
		}
		return rez;
	}
	
}
