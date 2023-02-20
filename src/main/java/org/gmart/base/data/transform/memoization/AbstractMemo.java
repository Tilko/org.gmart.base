package org.gmart.base.data.transform.memoization;

public abstract class AbstractMemo<X,Y> implements Memo<X, Y> {
	boolean xHasBeenSet;
	X prevX;
	Y prevY;
	
	protected abstract boolean equalityWithPreviousCheck(X x);
	public abstract Y applyNoMemo(X x);
	@Override
	public Y apply(X x) {
		if(xHasBeenSet && equalityWithPreviousCheck(x))
			return prevY;
		
		this.prevY = applyNoMemo(x);
		this.prevX = x;
		xHasBeenSet = true;
		return prevY;
	}

	@Override
	public Y lastValue() {
		return prevY;
	}
}