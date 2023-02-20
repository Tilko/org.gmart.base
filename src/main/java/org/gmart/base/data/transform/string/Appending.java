package org.gmart.base.data.transform.string;

import org.apache.commons.lang3.StringUtils;

public class Appending {

	public static void appendTickList(StringBuilder sb, int indentOffset, String tick, String ... strings) {
		for(String str : strings) {
			sb.append(StringUtils.repeat("    ", indentOffset));
			sb.append(tick);
			sb.append(str);
		}
	}

}
