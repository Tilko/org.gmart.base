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

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;


public class ParallelFunctionsN<X,Y> implements Function<X, List<Y>> {
	Function<X,Y>[] functions;
	@SafeVarargs
	public ParallelFunctionsN(Function<X,Y> ... functions){
		this.functions = functions;
	}
	@Override
	public List<Y> apply(X t) {
		ArrayList<Y> list = new ArrayList<Y>(functions.length);
		for(Function<X,Y>  f : functions) {
			list.add(f.apply(t));
		}
		return list;
	}
	
	
//	public static <E> E[] toArray(Class<E> cl, List<E> list) {
//		int size = list.size();
//		E[] es = (E[]) Array.newInstance(cl, size);
//		for(int i = 0; i < size; i++) 
//			es[i] = list.get(i);
//		return es;
//	}
}
