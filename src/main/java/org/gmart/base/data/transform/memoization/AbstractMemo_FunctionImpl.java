package org.gmart.base.data.transform.memoization;

import java.util.function.Function;

public abstract class AbstractMemo_FunctionImpl<X,Y> extends AbstractMemo<X,Y> implements Function<X,Y> {
	Function<X,Y> function;
	public AbstractMemo_FunctionImpl(Function<X, Y> function) {
		super();
		this.function = function;
	}
	@Override
	public Y applyNoMemo(X x) {
		return function.apply(x);
	}
}