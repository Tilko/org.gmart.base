package org.gmart.base.data.transform.string;

import java.util.function.Function;

public interface String2Y<Y> extends Function<String, Y> {
	
	default String2Y<Y> composeStr(Function<? super String,? extends String> first) {
		return str -> this.apply(first.apply(str));
	}

	default <Y2> String2Y<Y2> then(Function<? super Y, ? extends Y2> second){
		return t -> second.apply(this.apply(t));
	}
	default StrFunct thenStr(Function<? super Y, String> mappingFunction){
		return t -> mappingFunction.apply(this.apply(t));
	}
	
	default String2Y<Y> pre(String prefix) {
		return str -> this.apply(prefix + str);
	}
	default String2Y<Y> post(String suffix) {
		return str -> this.apply(str + suffix);
	}
	default String2Y<Y> surround(String prefix, String suffix) {
		return str -> this.apply(prefix + str + suffix);
	}
}