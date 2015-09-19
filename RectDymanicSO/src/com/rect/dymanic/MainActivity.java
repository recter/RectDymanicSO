package com.rect.dymanic;

import java.io.File;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author Rect
 *
 */
public class MainActivity extends Activity {
	//---------------------------------------------------------------------------------
	// 静态加载.so
	//	static {
	//		System.loadLibrary("rectdymanic");
	//	}

	private TextView m_TextNum;
	private Button m_Button2Asset;
	private Button m_Button2URL;
	private Boolean m_bLoadAssetsSuccess;
	private Boolean m_bLoadHTTPSuccess;
	//---------------------------------------------------------------------------------
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		if (android.os.Build.VERSION.SDK_INT > 9)
		{
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
		
		m_bLoadAssetsSuccess = false;
		m_bLoadHTTPSuccess = false;
		
		m_TextNum = (TextView) findViewById(R.id.hello);
		MyButtonListener linsener = new MyButtonListener();
		
		m_Button2Asset = (Button) findViewById(R.id.button_first);
		m_Button2Asset.setOnClickListener((OnClickListener) linsener);
		
		m_Button2URL = (Button) findViewById(R.id.button_two);
		m_Button2URL.setOnClickListener((OnClickListener) linsener);
		
	}
	//---------------------------------------------------------------------------------
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	//---------------------------------------------------------------------------------
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	//---------------------------------------------------------------------------------
	private void __LoadFormAssets() 
	{
		
		if(m_bLoadAssetsSuccess)
		{
			return;
		}
		
		File dir = getDir("lib", Context.MODE_PRIVATE);
		File soFile = new File(dir, "librectdymanicassets.so");
		FileUtils.AssetToFile(this, "librectdymanicassets.so", soFile);

		try 
		{
			System.load(soFile.getAbsolutePath());
			m_bLoadAssetsSuccess = true;
		} 
		catch (Exception e) 
		{
		}
		
	}
	//---------------------------------------------------------------------------------
	private void __LoadFormURL()
	{
		 
		if(m_bLoadHTTPSuccess)
		{
			return;
		}
		
		File dir = getDir("lib", Context.MODE_PRIVATE);
		File soFile = new File(dir, "librectdymanichttp.so");
		// ！Warnning！
		// 这个URL上的so文件只保存30天，若这个实效了？
		// 0.请到 assets文件夹取 librectdymanichttp.so
		// 1.自行上传到随便一个服务器
		// 2.修改一下URL为你的URL即可
		FileUtils.URLToFile( "http://box.zjuqsc.com/-66469911", soFile);
		try 
		{
			System.load(soFile.getAbsolutePath());
			m_bLoadHTTPSuccess = true;
		} 
		catch (Exception e) 
		{
		}
		
	}
	//---------------------------------------------------------------------------------
	class MyButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			
			String str = "no Message";
			switch(v.getId())
			{
			
			case R.id.button_first:
				__LoadFormAssets();
				str = "Form Assets so:" + CallDymanicFuncFormAssets(1, 2);
				break;
				
			case R.id.button_two:
				__LoadFormURL();
				str = "Form URL so:" + CallDymanicFuncFormHTTP(1, 2);
				break;
				
			default:
					break;
			}
			
			m_TextNum.setText(m_TextNum.getText() + "\n" + str.toString());
		}

	}
	//---------------------------------------------------------------------------------
	public static native String CallDymanicFuncFormAssets(int x, int y);
	public static native String CallDymanicFuncFormHTTP(int x, int y);
	//---------------------------------------------------------------------------------
}
