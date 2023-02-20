package org.gmart.base.data.transform.memoization;

import java.util.function.BiPredicate;
import java.util.function.Function;

public class Memo_CustomTest<X,Y> extends AbstractMemo_FunctionImpl<X,Y> {
	public Memo_CustomTest(Function<X, Y> function, BiPredicate<X,X> equalityCheck) {
		super(function);
		this.equalityCheck = equalityCheck;
	}
	BiPredicate<X,X> equalityCheck;
	@Override
	protected boolean equalityWithPreviousCheck(X x) {
		return equalityCheck.test(prevX, x);
	}
	
	@Override
	public Y applyNoMemo(X x) {
		return function.apply(x);
	}
}