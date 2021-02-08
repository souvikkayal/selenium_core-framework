package com.tt.libs;

import com.tt.ui.Browser;

public class BasePage {

	protected Browser browser;
	public BasePage() {
		
	}
	public BasePage(Browser browser) {
		setBrowser(browser);
	}
	public void setBrowser(Browser browser) {
		this.browser = browser;
	}
}
