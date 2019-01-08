package com.davidpinilla.controllers.util.paginator;

public class PageItem {
	private int pageNumber;
	private boolean current;
	public PageItem(int pageNumber, boolean current) {
		this.pageNumber = pageNumber;
		this.current = current;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public boolean isCurrent() {
		return current;
	}
}
