package org.gmart.util.functionalProg.enabled;


public class EnabledRunnable extends AbstractEnabled implements Runnable {
	Runnable runnable;
	public EnabledRunnable(Runnable runnable) {
		super();
		this.runnable = runnable;
	}
	public EnabledRunnable(Runnable runnable, boolean isEnabled) {
		super(isEnabled);
		this.runnable = runnable;
	}
	@Override
	public void run() {
		if(isEnabled)
			runnable.run();
	}
	public static EnabledRunnable of(Runnable runnable) {
		return new EnabledRunnable(runnable);
	}
}