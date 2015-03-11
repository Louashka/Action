package com.louisapps.action;

public class CategoriesItems {
	
	private Integer imageNumber;
	private String imageTextCompany;
	private String imageTextDate;
	private String imageTextName;
	private String imageLogo;
	
	public CategoriesItems(Integer nImage, String nTextCompany, String nTextDate, String nTextName, String nLogo) {
		this.imageNumber = nImage;
		this.imageTextCompany = nTextCompany;
		this.imageTextDate = nTextDate;
		this.imageTextName = nTextName;
		this.imageLogo = nLogo;
	}
	public Integer getImageNumber(){
		return imageNumber;
	}
	public void setImageNumber (Integer imageNumber){
		this.imageNumber = imageNumber;
	}
	
	public String getImageTextCompany(){
		return imageTextCompany;
	}
	public void setImageTextCompany (String imageTextCompany){
		this.imageTextCompany = imageTextCompany;
	}
	
	public String getImageTextDate(){
		return imageTextDate;
	}
	public void setImageTextDate (String imageTextDate){
		this.imageTextDate = imageTextDate;
	}
	
	public String getImageTextName(){
		return imageTextName;
	}
	public void setImageTextName (String imageTextName){
		this.imageTextName = imageTextName;
	}
	
	public String getImageLogo(){
		return imageLogo;
	}
	public void setImageLogo (String imageLogo){
		this.imageLogo = imageLogo;
	}

}
