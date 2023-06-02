package org.edupoll.model.dto.response;

//현재페이지를 나타내주는 DTO
public class PageItem {
	long value;
	boolean active;
	
	
	public long getValue() {
		return value;
	}
	public void setValue(long value) {
		this.value = value;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public PageItem(long value, boolean active) {
		super();
		this.value = value;
		this.active = active;
	}
	
	

	
}
