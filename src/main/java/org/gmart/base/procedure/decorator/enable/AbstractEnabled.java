package org.gmart.base.procedure.decorator.enable;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AbstractEnabled implements OnOffSwitch{
	boolean isEnabled = true;

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	/**
	 * disabled by default
	 */
	public AbstractEnabled() {}
}