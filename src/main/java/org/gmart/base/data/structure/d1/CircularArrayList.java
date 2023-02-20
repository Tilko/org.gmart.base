/*******************************************************************************
 * Copyright 2020 Gr�goire Martinetti
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
package org.gmart.base.data.structure.d1;

import java.util.ArrayList;
import java.util.List;

public class CircularArrayList<E> extends ArrayList<E>
{
    private static final long serialVersionUID = 1L;
    public CircularArrayList() {
    	super();
	}
    public CircularArrayList(List<E> list) {
    	super(list);
	}

	public E get(int index)
    {
//        if (index == -1) {
//            index = size()-1;
//        } else if (index == size()) {
//            index = 0;
//        }
        int size = size();
		int modulo = index%size;
		if(modulo < 0) {
			modulo = size + modulo;
		}
		return super.get(modulo);
    }
    public CircularArrayList<E> subListCircular(int beg, int endExcluded){
    	return new CircularArrayList<>(this.subList(beg, endExcluded));
    }
}