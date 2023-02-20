package org.gmart.base.data.source.number;

public abstract class Ramp implements DoubleIterator {
	double current;
	double finalValue;
	double step;
	public Ramp(double step, double finalValue, double initValue){
		this.current = initValue;
		this.finalValue = finalValue;
		this.step = step;
	}
	public Ramp(double step, double finalValue){
		this(step, finalValue, 0);
	}
	public void setCurrentValue(double currentValue) {
		this.current = currentValue;
	}
	@Override
	public Double next() {
		double c = current;
		if(hasNext())
			this.current += step;
		return c;
	}
	
	public static class ToLastValueIncluded extends Ramp {
		public ToLastValueIncluded(double step, double finalValue, double initValue) {
			super(step, finalValue, initValue);
			this.finalValue = finalValue + 0.00000000000001;
		}
		public ToLastValueIncluded(double step, double finalValue) {
			super(step, finalValue);
			this.finalValue = finalValue + 0.00000000000001;
		}
		@Override
		public boolean hasNext() {
			return current <= finalValue;
		}
	}
	public static class ToLastValueExcluded extends Ramp {
		public ToLastValueExcluded(double step, double finalValue, double initValue) {
			super(step, finalValue, initValue);
		}
		public ToLastValueExcluded(double step, double finalValue) {
			super(step, finalValue);
		}
		@Override
		public boolean hasNext() {
			return current < finalValue;
		}
	}
	public static Ramp of(double step, double finalValue, double initValue, boolean lastValueIncluded) {
		return lastValueIncluded ? new ToLastValueIncluded(step, finalValue, initValue) : new ToLastValueExcluded(step, finalValue, initValue);
	}
	public static Ramp of(double step, double finalValue, double initValue) {
		return of(step, finalValue, initValue, false);
	}
	public static Ramp of(double step, double finalValue) {
		return of(step, finalValue, 0, false);
	}
	public static Unit ofUnit(double step) {
		return ofUnit(step, false);
	}
	public static Unit ofUnit(double step, boolean lastValueIncluded) {
		return lastValueIncluded ? new Unit.ToLastValueIncluded(step) : new Unit.ToLastValueExcluded(step);
	}
	public static abstract class Unit extends Ramp {
		public Unit(double step) {
			super(step, 1, 0);
		}
		public static class ToLastValueIncluded extends Unit {
			public ToLastValueIncluded(double step) {
				super(step);
				this.finalValue = finalValue + 0.00000000000001;
			}
			@Override
			public boolean hasNext() {
				return current <= finalValue;
			}
		}
		public static class ToLastValueExcluded extends Unit {
			public ToLastValueExcluded(double step) {
				super(step);
			}
			@Override
			public boolean hasNext() {
				return current < finalValue;
			}
		}
	}
	public static Ramp unit(double step) {
		return ofUnit(step);
	}
	public static Unit time0Only() {
		return new Unit(0) {
			@Override
			public Double next() {
				notDone = false;
				return 0.;
			}
			boolean notDone = true;
			@Override
			public boolean hasNext() {
				return notDone;
			}
		};
	}
//	public static void main(String[] args) {
//		Ramp.unit(0.2).forEachRemaining(x -> System.out.println(x));
//	}
}
