package com.example.serialutil;

import com.skyworth.splicing.SerialPortUtil;
import com.skyworth.splicing.SerialPortUtil.OnDataReceiveListener;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
/*
 * @author guoxiao
 * 
 */
public class SerialActivity extends Activity {

	SerialPortUtil mSerialPortUtil;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_world);
        //单模式
        mSerialPortUtil = SerialPortUtil.getInstance();
        mSerialPortUtil.setOnDataReceiveListener(new OnDataReceiveListener() {
			
			@Override
			public void onDataReceive(byte[] buffer, int size) {
				// TODO Auto-generated method stub
				Log.d("[gx]", " DataReceive:" + new String(buffer,0,size));
			}
		});
    }

    public void onWrite(View view){
    	mSerialPortUtil.sendCmds("<open,1,0,3,0,0,0,0,0,0,1910,1070>");
    }
    
    public void onWrite3(View view){
    	byte[] send = new byte[1];
    	send[0] = 3;
    	mSerialPortUtil.sendByte(send);
    }
    
    public void onWrite5(View view){
    	byte[] send = new byte[1];
    	send[0] = 5;
    	mSerialPortUtil.sendByte(send);
    	
    	try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	
    	send[0] = 3;
    	mSerialPortUtil.sendByte(send);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.hello_world, menu);
        return true;
    }
    
}
