package com.louisapps.action;


public class AllCategoriesItems {
	
	private Integer imageNumber;
	private String imageText;
	
	public AllCategoriesItems(Integer nImage, String nAction) {
		this.imageNumber = nImage;
		this.imageText = nAction;
	}
	public Integer getImageNumber(){
		return imageNumber;
	}
	public void setImageNumber (Integer imageNumber){
		this.imageNumber = imageNumber;
	}
	
	public String getImageText(){
		return imageText;
	}
	public void setImageText (String imageText){
		this.imageText = imageText;
	}

}
