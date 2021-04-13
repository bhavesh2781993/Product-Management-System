package com.bz.pms.document;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Document("Products")
@Data
public class Product {

	@Field("_id")
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
