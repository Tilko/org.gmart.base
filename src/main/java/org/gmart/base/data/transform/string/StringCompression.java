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
package org.gmart.base.data.transform.string;

public class StringCompression {
	
	
	public static int[] getExtremeIdenticalSegmentLen(String s0, String s1) {
		int s0Len = s0.length();
		int s1Len = s1.length();
		String minText;
		String maxText;
		int minLen;
		int maxLen;
		boolean rightBiggerThanLeft = s0Len < s1Len;
		if(rightBiggerThanLeft) {
			minText = s0;
			maxText = s1;
			minLen = s0Len;
			maxLen = s1Len;
		} else {
			minText = s1;
			maxText = s0;
			minLen = s1Len;
			maxLen = s0Len;
		}
		
		int leftIdenticalLen = 0;
		for(; leftIdenticalLen < minLen; leftIdenticalLen++) {
			char charAt = minText.charAt(leftIdenticalLen);
			char charAt2 = maxText.charAt(leftIdenticalLen);
			if(charAt != charAt2) break;
		}
		int minLen_m1 = minLen - 1;
		int maxLen_m1 = maxLen - 1;
		int rightIdenticalLen = 0;
		for(; rightIdenticalLen < minLen - leftIdenticalLen; rightIdenticalLen++) {
			char charAt = minText.charAt(minLen_m1 - rightIdenticalLen);
			char charAt2 = maxText.charAt(maxLen_m1 - rightIdenticalLen);
			if(charAt != charAt2) break;
		}
		return new int[] {leftIdenticalLen, rightIdenticalLen, rightBiggerThanLeft ? 0 : 1};
	}
	
	
	
}
