package com.louisapps.action;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends Activity {
	
	private static final String DB_NAME = "mydb";
	  private static final int DB_VERSION = 1;
	  private static final String DB_TABLE = "mytab";
	  
	  public static final String COLUMN_ID = "_id";
	  public static final String COLUMN_OB_ID = "objectId";
	  
	  private static final String DB_CREATE = 
	    "create table " + DB_TABLE + "(" +
	      COLUMN_ID + " integer primary key autoincrement, " +
	      COLUMN_OB_ID + " objectId" +
	    ");";
	  
	  private final Context mCtx;
	  
	  
	  private DBHelper mDBHelper;
	  private SQLiteDatabase mDB;
	  
	  public DataBase(Context ctx) {
	    mCtx = ctx;
	  }
	  
	  // ������� �����������
	  public void open() {
	    mDBHelper = new DBHelper(mCtx, DB_NAME, null, DB_VERSION);
	    mDB = mDBHelper.getWritableDatabase();
	  }
	  
	  // ������� �����������
	  public void close() {
	    if (mDBHelper!=null) mDBHelper.close();
	  }
	  
	  // �������� ��� ������ �� ������� DB_TABLE
	  public Cursor getAllData() {
	    return mDB.query(DB_TABLE, null, null, null, null, null, null);
	  }
	  
	  // �������� ������ � DB_TABLE
	  public void addRec(String objectId) {
	    ContentValues cv = new ContentValues();
	    cv.put(COLUMN_OB_ID, objectId);
	    mDB.insert(DB_TABLE, null, cv);
	  }
	  
	  // ������� ������ �� DB_TABLE
	  public void delRec(long id) {
	    mDB.delete(DB_TABLE, COLUMN_ID + " = " + id, null);
	  }
	  
	  // ����� �� �������� � ���������� ��
	  private class DBHelper extends SQLiteOpenHelper {

	    public DBHelper(Context context, String name, CursorFactory factory,
	        int version) {
	      super(context, name, factory, version);
	    }

	    // ������� � ��������� ��
	    @Override
	    public void onCreate(SQLiteDatabase db) {
	      db.execSQL(DB_CREATE);
	      
	      ContentValues cv = new ContentValues();
	      for (int i = 1; i < 5; i++) {
	        cv.put(COLUMN_OB_ID, "sometext " + i);
	        db.insert(DB_TABLE, null, cv);
	      }
	    }

	    @Override
	    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    }
	  }

}