package com.louisapps.action;


import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.persistence.BackendlessDataQuery;

public class ActionCreation
{
  private String category;
  private java.util.Date created;
  private String login;
  private String logo;
  private String nameAction;
  private String action_start;
  private String objectId;
  private String action_end;
  private String adress;
  private String descrip;
  private String nameCompany;
  private String ownerId;
  private java.util.Date updated;
  private Integer numberImage;

  public String getCategory()
  {
    return category;
  }

  public void setCategory( String category )
  {
    this.category = category;
  }

  public java.util.Date getCreated()
  {
    return created;
  }

  public String getLogin()
  {
    return login;
  }

  public void setLogin( String login )
  {
    this.login = login;
  }
  
  public String getLogo()
  {
    return logo;
  }

  public void setLogo( String logo )
  {
    this.logo = logo;
  }

  public String getNameAction()
  {
    return nameAction;
  }

  public void setNameAction( String nameAction )
  {
    this.nameAction = nameAction;
  }

  public String getAction_start()
  {
    return action_start;
  }

  public void setAction_start( String action_start )
  {
    this.action_start = action_start;
  }

  public String getObjectId()
  {
    return objectId;
  }

  public String getAdress()
  {
    return adress;
  }

  public void setAdress( String adress )
  {
    this.adress = adress;
  }
  
  public String getAction_end()
  {
    return action_end;
  }

  public void setAction_end( String action_end )
  {
    this.action_end = action_end;
  }

  public String getDescrip()
  {
    return descrip;
  }

  public void setDescrip( String descrip )
  {
    this.descrip = descrip;
  }

  public String getNameCompany()
  {
    return nameCompany;
  }

  public void setNameCompany( String nameCompany )
  {
    this.nameCompany = nameCompany;
  }

  public String getOwnerId()
  {
    return ownerId;
  }

  public java.util.Date getUpdated()
  {
    return updated;
  }

  public Integer getNumberImage()
  {
    return numberImage;
  }

  public void setNumberImage( Integer numberImage )
  {
    this.numberImage = numberImage;
  }

                                                    
  public ActionCreation save()
  {
    return Backendless.Data.of( ActionCreation.class ).save( this );
  }

  public Future<ActionCreation> saveAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<ActionCreation> future = new Future<ActionCreation>();
      Backendless.Data.of( ActionCreation.class ).save( this, future );

      return future;
    }
  }

  public void saveAsync( AsyncCallback<ActionCreation> callback )
  {
    Backendless.Data.of( ActionCreation.class ).save( this, callback );
  }

  public Long remove()
  {
    return Backendless.Data.of( ActionCreation.class ).remove( this );
  }

  public Future<Long> removeAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Long> future = new Future<Long>();
      Backendless.Data.of( ActionCreation.class ).remove( this, future );

      return future;
    }
  }

  public void removeAsync( AsyncCallback<Long> callback )
  {
    Backendless.Data.of( ActionCreation.class ).remove( this, callback );
  }

  public static ActionCreation findById( String id )
  {
    return Backendless.Data.of( ActionCreation.class ).findById( id );
  }

  public static Future<ActionCreation> findByIdAsync( String id )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<ActionCreation> future = new Future<ActionCreation>();
      Backendless.Data.of( ActionCreation.class ).findById( id, future );

      return future;
    }
  }

  public static void findByIdAsync( String id, AsyncCallback<ActionCreation> callback )
  {
    Backendless.Data.of( ActionCreation.class ).findById( id, callback );
  }

  public static ActionCreation findFirst()
  {
    return Backendless.Data.of( ActionCreation.class ).findFirst();
  }

  public static Future<ActionCreation> findFirstAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<ActionCreation> future = new Future<ActionCreation>();
      Backendless.Data.of( ActionCreation.class ).findFirst( future );

      return future;
    }
  }

  public static void findFirstAsync( AsyncCallback<ActionCreation> callback )
  {
    Backendless.Data.of( ActionCreation.class ).findFirst( callback );
  }

  public static ActionCreation findLast()
  {
    return Backendless.Data.of( ActionCreation.class ).findLast();
  }

  public static Future<ActionCreation> findLastAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<ActionCreation> future = new Future<ActionCreation>();
      Backendless.Data.of( ActionCreation.class ).findLast( future );

      return future;
    }
  }

  public static void findLastAsync( AsyncCallback<ActionCreation> callback )
  {
    Backendless.Data.of( ActionCreation.class ).findLast( callback );
  }

  public static BackendlessCollection<ActionCreation> find( BackendlessDataQuery query )
  {
    return Backendless.Data.of( ActionCreation.class ).find( query );
  }

  public static Future<BackendlessCollection<ActionCreation>> findAsync( BackendlessDataQuery query )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<BackendlessCollection<ActionCreation>> future = new Future<BackendlessCollection<ActionCreation>>();
      Backendless.Data.of( ActionCreation.class ).find( query, future );

      return future;
    }
  }

  public static void findAsync( BackendlessDataQuery query, AsyncCallback<BackendlessCollection<ActionCreation>> callback )
  {
    Backendless.Data.of( ActionCreation.class ).find( query, callback );
  }
}