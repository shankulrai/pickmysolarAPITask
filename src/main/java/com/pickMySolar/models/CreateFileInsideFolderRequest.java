package com.pickMySolar.models;

import java.util.List;

public class CreateFileInsideFolderRequest 
{
	String name;
	List<String> parents;
	
	public CreateFileInsideFolderRequest(String name,List<String> parents)
	{
		this.name=name;
		this.parents=parents;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getparents() {
		return parents;
	}

	public void setparents(List<String> parents) {
		this.parents = parents;
	}


	
	

}
