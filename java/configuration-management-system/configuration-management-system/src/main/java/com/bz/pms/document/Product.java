package com.bz.pms.document;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document("Products")
@Data
public class Product {

	@Id
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
