package com.louisapps.action;

import com.backendless.BackendlessUser;

public class Users extends BackendlessUser
{
	  private String email;
	  private String password;
	  private String login;
	  private String companyName;
	  private String photo;
	  
  public String getEmail()
  {
    return email;
  }

  public void setEmail( String email )
  {
    this.email = email;
  }

  public String getPassword()
  {
    return password;
  }

  public String getLogin()
  {
    return login;
  }

  public void setLogin( String login )
  {
    this.login = login;
  }
  
  public String getCompanyName()
  {
    return companyName;
  }

  public void setCompanyName( String companyName )
  {
    this.companyName = companyName;
  }
  
  public String getPhoto()
  {
    return photo;
  }

  public void setPhoto( String photo )
  {
    this.photo = photo;
  }
}

