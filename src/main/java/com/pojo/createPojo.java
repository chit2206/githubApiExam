package com.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class createPojo {
	
	private String name;
	private String description;
	private String homepage;
	//private String private;
	public String getname() {
		return name;
		}

		@JsonProperty("name")
		public void setname(String name) {
		this.name = name;
		}
		public String getdescription() {
			return description;
			}

			@JsonProperty("description")
			public void setdescription(String description) {
			this.description = description;
			}
			public String gethomepage() {
				return homepage;
				}

				@JsonProperty("homepage")
				public void sethomepage(String homepage) {
				this.homepage = homepage;
				}
	

}
