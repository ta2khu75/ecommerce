package com.ta2khu75.enviroment;

public class FileEnviroment {
	private FileEnviroment() {
		throw new IllegalStateException("Enviroment class");
	}
	public static final String UPLOAD_PRODUCT = "upload/product/";
	public static final String UPLOAD_BRAND = "upload/brand/";
	public static final String UPLOAD_PRODUCT_IMAGE = "upload/product/image/";
	public static final String UPLOAD_CATEGORY= "upload/category/";
}
