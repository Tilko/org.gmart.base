package org.gmart.util.functionalProg.enabled;

public class AbstractEnabled{
	boolean isEnabled = true;

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public AbstractEnabled(boolean isEnabled) {
		super();
		this.isEnabled = isEnabled;
	}

	public AbstractEnabled() {
		// TODO Auto-generated constructor stub
	}
}