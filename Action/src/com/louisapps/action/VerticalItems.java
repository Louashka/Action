package com.louisapps.action;

import java.util.ArrayList;

public class VerticalItems {
	
	private String categName;
	private ArrayList<CategoriesItems> categoriesItems;
	
	public VerticalItems (String categName, ArrayList<CategoriesItems> categoriesItems){
		this.categName = categName;
		this.categoriesItems = categoriesItems;
	}
	
	public String getCategName(){
		return categName;
	}
	public void setCategName (String categName){
		this.categName = categName;
	}
	
	public ArrayList<CategoriesItems> getCategoriesItems(){
		return categoriesItems;
	}
	public void setCategoriesItems (ArrayList<CategoriesItems> categoriesItems){
		this.categoriesItems = categoriesItems;
	}

}
