package org.gmart.base.data.transform.string;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public interface StrFunct extends UnaryOperator<String> {
	
	default StrFunct composeStr(Function<? super String,? extends String> first) {
		return str -> this.apply(first.apply(str));
	}
	default StrFunct withPre(String prefix) {
		return this.composeStr(pre(prefix));
	}
	public static StrFunct pre(String prefix) {
		return str -> prefix + str;
	}
	
}