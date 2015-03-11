package com.louisapps.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.async.callback.BackendlessCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.local.UserTokenStorageFactory;
import com.louisapps.action.fragments.AllCategories;

public class MainActivity extends ActionBarActivity {
	 
    private DrawerLayout myDrawerLayout;
    private ListView myDrawerList;
    private ActionBarDrawerToggle myDrawerToggle;
    private LinearLayout mDrawer;
 
    // navigation drawer title
    private CharSequence myDrawerTitle;
    // used to store app title
    private CharSequence myTitle;
    private SimpleAdapter mAdapter;
    private List<HashMap<String,String>> mList;
    final int DIALOG_CHOOSE_CATEGORY = 1;
 
    private String[] viewsNames;
    ArrayList<String> values = new ArrayList<String>();
    private String[] categNamesInFilter;
    final private String CATEGORY = "category";
    final private String ICON = "icon";
    private FragmentTransaction fTrans;
    private String userToken;
    private String userId;
    private String userLogin;
    private TextView userName;
    public boolean checkedValues[] = new boolean[23];
    public boolean defaultValues[] = new boolean[23];
    SharedPreferences sharedPref;
    SharedPreferences sharedListV;
    SharedPreferences sharedValues;
    SharedPreferences.Editor listEditor;
    SharedPreferences.Editor valuesEditor;
    String categories[] = new String[]{
    		"Авто", "Аптеки и оптика", "Аудио/Видео", "Банки", "Вещи для дома", "Детские товары",
    		"Зоомагазины", "Кафе и Рестораны", "Книжные магазины", "Компьютеры и оргтехника",
            "Косметика и парфюмерия", "Мебель", "Обувь", "Одежда", "Садоводство", 
            "Салоны красоты", "Спорттовары",  "Сувениры", "Сумки и аксессуары", 
            "Текстиль", "Телефоны и аксессуары", "Туризм", "Ювелирные изделия"
    };
    
    int[] imCateg = new int[]{
    		R.drawable.ic_catalog,
    		R.drawable.ic_action_important,
    		R.drawable.ic_action_ic_myactions,
    		R.drawable.ic_action_new,
    		R.drawable.ic_action_logout
    };
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String appVersion = "v1";
        String YOUR_APP_ID = "149BBA0B-F8B9-CD77-FF47-C9C80D8F8500";
        String YOUR_SECRET_KEY = "3AFEEA72-40D3-4F4D-FF31-E4208FC91D00";
        Backendless.initApp( this, YOUR_APP_ID, YOUR_SECRET_KEY, appVersion );
        
        rebuildFragment();
        userName = (TextView) findViewById (R.id.userName);
        userToken = UserTokenStorageFactory.instance().getStorage().get();
        if( userToken != null && !userToken.equals( "" ) ){
        	userId = Backendless.UserService.loggedInUser();
        	Defaults.getProperty (userId, new BackendlessCallback<BackendlessCollection<Users>>() {

				@Override
				public void handleResponse(BackendlessCollection<Users> arg) {
					for (Users task: arg.getCurrentPage()){
						userLogin = task.getLogin();			        	
					}				
					userName.setText(userLogin);
				} 
				
				@Override
	    	    public void handleFault( BackendlessFault backendlessFault )
	    	    {
	    	        Toast.makeText( MainActivity.this, backendlessFault.getMessage(), Toast.LENGTH_LONG ).show();
	    	    }
	    		
        	});
        }
        	
        myTitle =  getTitle();	
        myDrawerTitle = getResources().getString(R.string.menu);

        // load slide menu items             
        viewsNames = getResources().getStringArray(R.array.screen_array);
        if( userToken != null && !userToken.equals( "" ) ){
        	viewsNames[4] = "Выйти";
        } else {
        	viewsNames[4] = "Войти";
        }
        myDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        myDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawer = ( LinearLayout) findViewById(R.id.drawer);
            
        mList = new ArrayList<HashMap<String,String>>();
        for(int i = 0; i < imCateg.length; i++){
          HashMap<String, String> hm = new HashMap<String,String>();
          hm.put(CATEGORY, viewsNames[i]);
          hm.put(ICON, Integer.toString(imCateg[i]) );
          mList.add(hm);
        }
        
     // Keys used in Hashmap
        String[] from = { ICON,CATEGORY };
        
       // Ids of views in listview_layout
        int[] to = { R.id.ic , R.id.label };
        
       // Instantiating an adapter to store each items
        // R.layout.drawer_layout defines the layout of each item
        mAdapter = new SimpleAdapter(this, mList, R.layout.drawer_list_item, from, to);
 
        myDrawerList.setAdapter(mAdapter);
 
        // enabling action bar app icon and behaving it as toggle button
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);        
 
        myDrawerToggle = new ActionBarDrawerToggle(this, myDrawerLayout,
            R.drawable.ic_drawer, //nav menu toggle icon
            R.string.app_name, // nav drawer open - description for accessibility
            R.string.app_name // nav drawer close - description for accessibility
        ){
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(myTitle);
                // calling onPrepareOptionsMenu() to show action bar icons
                invalidateOptionsMenu();
            }
 
            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(myDrawerTitle);
                // calling onPrepareOptionsMenu() to hide action bar icons
                invalidateOptionsMenu();
            }
        };
        myDrawerLayout.setDrawerListener(myDrawerToggle);
 
        if (savedInstanceState == null) {
            // on first time display view for first nav item
            displayView(0);;

        }
 
        myDrawerList.setOnItemClickListener(new DrawerItemClickListener());   
        
    }
    
    protected void onDestroy() {
        super.onDestroy();
      }

      protected void onPause() {
        super.onPause();
      }

      protected void onRestart() {
        super.onRestart();
      }

      protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
      }

      protected void onResume() {
        super.onResume();
      }

      protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
      }

      protected void onStart() {
        super.onStart();
      }

      protected void onStop() {
        super.onStop();
      }
 
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(
            AdapterView<?> parent, View view, int position, long id
        ) {
            // display view for selected nav drawer item
            displayView(position);
        }               
    }
 
    private void displayView(int position) {
        // update the main content by replacing fragments
        Fragment fragment = null;
        switch (position) {
        case 0:
            fragment = new AllCategories();
            break;
        case 1:
        	if( userToken != null && !userToken.equals( "" ) ){
            	Intent myIntent1 = new Intent(this, Subscription.class);
                this.startActivity(myIntent1);
                break;
        	}
        	else {
            	Intent myIntent1 = new Intent(this, LogIn.class);
                this.startActivity(myIntent1);
                break;
        	}
        case 2:
        	if( userToken != null && !userToken.equals( "" ) ){
            	Intent myIntent1 = new Intent(this, PrivateOffice.class);
                this.startActivity(myIntent1);
                break;
        	}
        	else {
            	Intent myIntent1 = new Intent(this, LogIn.class);
                this.startActivity(myIntent1);
                break;
        	}
        case 3:
        	if( userToken != null && !userToken.equals( "" ) ){
            	Intent myIntent1 = new Intent(this, Create_action.class);
                this.startActivity(myIntent1);
                break;
        	}
        	else {
            	Intent myIntent1 = new Intent(this, LogIn.class);
                this.startActivity(myIntent1);
                break;
        	}
        case 4:
        	if( userToken != null && !userToken.equals( "" ) ){
        		Backendless.UserService.logout( new AsyncCallback<Void>() 
         			   {
         			     public void handleResponse( Void response )
         			     {
         			    	 startActivity( new Intent(MainActivity.this, MainActivity.class) );
    	    		    	 Toast.makeText(MainActivity.this, "Вы успешно вышли из системы", Toast.LENGTH_LONG).show();
         			     }
         			 
         			     public void handleFault( BackendlessFault fault )
         			     {
         			    	 Toast.makeText(MainActivity.this, fault.getMessage(), Toast.LENGTH_LONG).show();
         			     }
         			   });
         	   break;
        	} else {
        		Intent myIntent1 = new Intent(this, LogIn.class);
                this.startActivity(myIntent1);
                break;
        	}
        default:
            break;
        }
 
        if (fragment != null) {
            android.app.FragmentManager fragmentManager = getFragmentManager();
            fTrans = fragmentManager.beginTransaction();
            fTrans.replace(R.id.content_frame, fragment);
            fTrans.addToBackStack(null);
            fTrans.commit();

            // update selected item and title, then close the drawer
            myDrawerList.setItemChecked(position, true);
            myDrawerList.setSelection(position);
            setTitle(viewsNames[position]);
            myDrawerLayout.closeDrawer(mDrawer);
        } else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }
    }
 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
 
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // toggle nav drawer on selecting action bar app icon/title
        if (myDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action bar actions click
        switch (item.getItemId()) {
        case R.id.categor_refresh:
        	rebuildFragment();
        	sharedListV = this.getSharedPreferences("actionArrays", 0);;
            sharedValues = this.getSharedPreferences("valuesArrays", 0);
            listEditor = sharedListV.edit();
            listEditor.clear();
            listEditor.commit();
            valuesEditor = sharedValues.edit();
            valuesEditor.clear();
            valuesEditor.commit();
        	Fragment fragment = new AllCategories();
        	android.app.FragmentManager fragmentManager = getFragmentManager();
            fTrans = fragmentManager.beginTransaction();
            fTrans.replace(R.id.content_frame, fragment);
            fTrans.addToBackStack(null);
            fTrans.commit();
        	return true;
        case R.id.categor_filter:
        	AlertDialog dialog = onCreateDialog(DIALOG_CHOOSE_CATEGORY);
            dialog.show();
        	return true;
		default:
            return super.onOptionsItemSelected(item);
        }
    }
 
    /**
     * Called when invalidateOptionsMenu() is triggered
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // if navigation drawer is opened, hide the action items
        boolean drawerOpen = myDrawerLayout.isDrawerOpen(mDrawer);  
        menu.findItem(R.id.categor_refresh).setVisible(!drawerOpen);
        menu.findItem(R.id.categor_filter).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }
 
    @Override
    public void setTitle(CharSequence title) {
        myTitle = title;
        getActionBar().setTitle(myTitle);
    }
 
    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        myDrawerToggle.syncState();
    }
 
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        myDrawerToggle.onConfigurationChanged(newConfig);
    }
    
	protected AlertDialog onCreateDialog(int id) {
		    	
    	if (id == DIALOG_CHOOSE_CATEGORY) {
    		AlertDialog.Builder adb = new AlertDialog.Builder(this);
	        adb.setTitle(R.string.filter); 
	        adb.setMultiChoiceItems(categNamesInFilter, defaultValues, new DialogInterface.OnMultiChoiceClickListener() {
                public void onClick(DialogInterface dialog, int whichButton,
                        boolean isChecked) {
                	if (isChecked){
                		SharedPreferences.Editor editor = sharedPref.edit();
                		editor.putBoolean(categNamesInFilter[whichButton], true);
                		editor.commit();
                		defaultValues[whichButton] = true;
                		for (int i = 0; i < checkedValues.length; i++){
                			if (categories[i].equals(categNamesInFilter[whichButton]))
                				checkedValues[i] = true;
                		}
                	} else {
                		SharedPreferences.Editor editor = sharedPref.edit();
                		editor.putBoolean(categNamesInFilter[whichButton], false);
                		editor.commit();
                		defaultValues[whichButton] = false;
                		for (int i = 0; i < checkedValues.length; i++){
                			if (categories[i].equals(categNamesInFilter[whichButton]))
                				checkedValues[i] = false;
                		}
                	}
                }
	        });
	        adb.setCancelable(true);
	        adb.setPositiveButton(R.string.ok, myClickListener);
	        adb.setNegativeButton(R.string.cancel, myClickListener);
	        return adb.create();
    	} else {
    		return null;
    	}
    }	    
    OnClickListener myClickListener = new OnClickListener() {
    	
        public void onClick(DialogInterface dialog, int which) {
          switch (which) {
          // положительная кнопка
          case Dialog.BUTTON_POSITIVE:      	
        	AllCategories frag = (AllCategories) getFragmentManager().findFragmentById(R.id.content_frame);
      		frag.hideItems(checkedValues);
        	dialog.dismiss();
            break;
          // негаитвная кнопка
          case Dialog.BUTTON_NEGATIVE:
        	dialog.dismiss();
            break;
          }
        }
      };
      
      public void rebuildFragment() {
    	  values.clear();
    	  
    	  sharedPref = ((GlobalVariable)this.getApplicationContext()).getSharedPref();
          for (int i = 0; i < checkedValues.length; i++)
          	checkedValues[i] = sharedPref.getBoolean(categories[i], true);
          
          Defaults.getTasks(new BackendlessCallback<BackendlessCollection<ActionCreation>>() {

  			@Override
  			public void handleResponse(
  					BackendlessCollection<ActionCreation> taskBackendlessCollection) {
  				for( ActionCreation task : taskBackendlessCollection.getCurrentPage()) {					
  					values.add(task.getCategory());
  				}
  				HashSet<String> hs = new HashSet<String>();
  				hs.addAll(values);
  				values.clear();
  				values.addAll(hs);
  				Collections.sort(values);
  				categNamesInFilter = values.toArray(new String[values.size()]);
  				for (int i = 0; i < categories.length; i++) {
  					if (!Arrays.asList(categNamesInFilter).contains(categories[i]))
  						checkedValues[i] = false;						
  				}
  				for (int i = 0; i < categNamesInFilter.length; i++)
  					defaultValues[i] = sharedPref.getBoolean(categNamesInFilter[i], true);
  			}
  			 
  			@Override
      	    public void handleFault( BackendlessFault backendlessFault )
      	    {
      	        Toast.makeText( MainActivity.this, backendlessFault.getMessage(), Toast.LENGTH_LONG ).show();
      	    }
          });
    	  
      }
            
}