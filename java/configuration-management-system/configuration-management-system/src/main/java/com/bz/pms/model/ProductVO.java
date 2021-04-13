package com.bz.pms.model;

import java.util.List;

import lombok.Data;

@Data
public class ProductVO {

	private String id;
	
	private String productName;
	private String companyName;
	private List<Content> contents;

	@Data
	public static class Content {
		private String name;
		private String amount;
		private String measure;
	}

}
