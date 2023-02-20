package org.gmart.base.data.transform.memoization;

import java.util.function.Function;
import java.util.function.Supplier;

public abstract class AbstractMemo_SupplierImpl<X,Y> extends AbstractMemo<X,Y> implements Function<X,Y> {
	Supplier<Y> supplier;
	public AbstractMemo_SupplierImpl(Supplier<Y> supplier) {
		super();
		this.supplier = supplier;
	}

	@Override
	public Y applyNoMemo(X x) {
		return supplier.get();
	}
}