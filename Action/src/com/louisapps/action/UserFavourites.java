package com.louisapps.action;


public class UserFavourites {
	
	private ActionCreation favouriteAction;
	private String objectId;
	private String ownerId;
	
	public ActionCreation getFavouriteAction()
	{
	    return favouriteAction;
	}

	public void setFavouriteAction( ActionCreation favouriteAction )
	{		
	    this.favouriteAction = favouriteAction;
	}
	
	public String getObjectId()
	{
	    return objectId;
	}

	public void setObjectId( String objectId )
	{		
	    this.objectId = objectId;
	}
	
	public String getOwnerId()
	  {
	    return ownerId;
	  }

}
