package org.gmart.base.data.transform.memoization;

import java.util.function.Function;
import java.util.function.Supplier;


public class Memos {	
	public static <X, Y> Memo<X, Y> withRefAndEqualsTest(Function<X, Y> function)  {
		return new Memo_RefAndEqualsTest<>(function);
	}
	public static <X, Y> Memo<X, Y> withRefAndEqualsTest(Supplier<Y> supplier)  {
		return new AbstractMemo_SupplierImpl<>(supplier) {
			@Override
			protected boolean equalityWithPreviousCheck(X x) {
				return x == prevX || x.equals(prevX);
			}
		};
	}
}
