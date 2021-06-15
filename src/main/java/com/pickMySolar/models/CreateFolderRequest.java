package com.pickMySolar.models;

public class CreateFolderRequest 
{
	private String name;
	private String mimeType;
	
	public CreateFolderRequest(String name,String mimetype)
	{
		this.name=name;
		this.mimeType=mimetype;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

}
