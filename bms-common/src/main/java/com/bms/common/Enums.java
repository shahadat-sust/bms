package com.bms.common;


public class Enums {

	public enum ImageType {
		THUMB("thumb", 100),
		CROP("crop", -1),
		R300("r300", 300),
		R600("r600", 600);
		
		String name;
		int size;
		
		private ImageType(String name, int size) { 
			this.name = name; 
			this.size = size;
		}
		
		public String getName() {
			return this.name;
		}
		
		public int getSize() {
			return this.size;
		}
	}
	
}
