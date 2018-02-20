package com.jaycee.appguide;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		boolean mFirst = isFirstEnter(MainActivity.this,MainActivity.this.getClass().getName()); 
        if(mFirst) 
//          mHandler.sendEmptyMessageDelayed(SWITCH_GUIDACTIVITY,5000); 
        	mHandler.sendEmptyMessage(SWITCH_GUIDACTIVITY); 
//      else 
//          mHandler.sendEmptyMessageDelayed(SWITCH_MAINACTIVITY,5000); 
//        	mHandler.sendEmptyMessage(SWITCH_MAINACTIVITY); 

		
	}
	
	//**************************************************************** 
    // 判断应用是否初次加载，读取SharedPreferences中的guide_activity字段 
    //**************************************************************** 
    private static final String SHAREDPREFERENCES_NAME = "my_pref"; 
    private static final String KEY_GUIDE_ACTIVITY = "guide_activity"; 
    @SuppressLint("WorldReadableFiles")
	@SuppressWarnings("deprecation")
	private boolean isFirstEnter(Context context,String className){ 
        if(context==null || className==null||"".equalsIgnoreCase(className))return false; 
        String mResultStr = context.getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_WORLD_READABLE) 
                 .getString(KEY_GUIDE_ACTIVITY, "");//取得所有类名 如 com.my.MainActivity 
        if(mResultStr.equalsIgnoreCase("false")) 
            return false; 
        else 
            return true; 
    } 
    
    //************************************************* 
    // Handler:跳转至不同页面 
    //************************************************* 
    private final static int SWITCH_MAINACTIVITY = 1000; 
    private final static int SWITCH_GUIDACTIVITY = 1001; 
    @SuppressLint("HandlerLeak")
	public Handler mHandler = new Handler(){ 
        public void handleMessage(Message msg) { 
            switch(msg.what){ 
            case SWITCH_MAINACTIVITY: 
                Intent mIntent = new Intent(); 
                mIntent.setClass(MainActivity.this, MainActivity.class); 
                MainActivity.this.startActivity(mIntent); 
                MainActivity.this.finish(); 
                break; 
            case SWITCH_GUIDACTIVITY: 
                mIntent = new Intent(); 
                mIntent.setClass(MainActivity.this, GuideActivity.class); 
                MainActivity.this.startActivity(mIntent); 
                MainActivity.this.finish(); 
                break; 
            } 
            super.handleMessage(msg); 
        } 
    }; 


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
