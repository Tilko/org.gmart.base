package org.gmart.base.data.transform.memoization;

import java.util.function.Function;

public class Memo_RefAndEqualsTest<X,Y> extends AbstractMemo_FunctionImpl<X,Y> {
	public Memo_RefAndEqualsTest(Function<X, Y> function) {
		super(function);
	}
	@Override
	protected boolean equalityWithPreviousCheck(X x) {
		return x == prevX || x.equals(prevX);
	}
}