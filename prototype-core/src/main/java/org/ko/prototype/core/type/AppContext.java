package org.ko.prototype.core.type;

public enum AppContext {

	Api("prototype-api"),
	Admin("prototype-admin");
	
	private final String id;
	
	AppContext(String id){
		this.id = id;
	}

	public String getId() {
		return id;
	}
	
}

