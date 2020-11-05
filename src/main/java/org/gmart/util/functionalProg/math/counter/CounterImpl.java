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
package org.gmart.util.functionalProg.math.counter;


public class CounterImpl implements Counter {
	protected int count;
	public CounterImpl() {}
	public CounterImpl(int initValue) {
		this.count = initValue;
	}
	
	public void inc() {
		count++;
	}
	@Override
	public void add(int toAdd) {
		count += toAdd;
	}
	@Override
	public int get() {
		return count;
	}

}
