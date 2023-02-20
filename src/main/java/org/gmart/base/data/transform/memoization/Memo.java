package org.gmart.base.data.transform.memoization;

import java.util.function.Consumer;
import java.util.function.Function;

public interface Memo<X, Y> extends Function<X,Y> {
	
	Y applyNoMemo(X x);
	Y lastValue();
	default void ifLastValue(Consumer<Y> lastValueUser) {
		Y lastValue = lastValue();
		if(lastValue != null)
			lastValueUser.accept(lastValue);
	}
}
