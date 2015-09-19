
package com.rect.dymanic;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.content.res.AssetManager;

/**
 * @author Rect
 *
 */
public class FileUtils 
{

	/**
	 * 从Asset文件夹中复制文件到File
	 * @param context
	 * @param name
	 * @param file
	 */
	public static void AssetToFile(Context context, String name, File file) 
	{
		
		AssetManager assetManager = context.getAssets();

		InputStream is;
		try 
		{
			is = assetManager.open(name);
			java.io.ByteArrayOutputStream bout = new java.io.ByteArrayOutputStream();

			byte[] bufferByte = new byte[1024];
			int l = -1;
			while ((l = is.read(bufferByte)) > -1) {
				bout.write(bufferByte, 0, l);
			}
			byte[] rBytes = bout.toByteArray();
			bout.close();
			is.close();

			if (!file.exists()) {
				file.createNewFile();
			}

			DataOutputStream dos = new DataOutputStream(new FileOutputStream(file));
			dos.write(rBytes);
			dos.flush();
			dos.close();

		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (OutOfMemoryError e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//---------------------------------------------------------------------------------
	/**
	 * 从URL中下载文件到File
	 * @param name
	 * @param file
	 */
	public static void URLToFile(String strURL, File file)
	{
		InputStream is = null;
		
		try {
			is = __GetInputStreamFromURL(strURL);
			
			java.io.ByteArrayOutputStream bout = new java.io.ByteArrayOutputStream();

			byte[] bufferByte = new byte[1024];
			int l = -1;
			while ((l = is.read(bufferByte)) > -1) {
				bout.write(bufferByte, 0, l);
			}
			byte[] rBytes = bout.toByteArray();
			bout.close();
			is.close();

			if (!file.exists()) {
				file.createNewFile();
			}

			DataOutputStream dos = new DataOutputStream(new FileOutputStream(
					file));
			dos.write(rBytes);
			dos.flush();
			dos.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OutOfMemoryError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//---------------------------------------------------------------------------------
	/**  
     * 根据URL得到输入流  
     * @param urlStr  
     * @return  
     */  
    private static  InputStream __GetInputStreamFromURL(String urlStr) 
    {  
        HttpURLConnection urlConn = null;  
        InputStream inputStream = null;  
        try 
        {  
        	URL url = new URL(urlStr);  
            urlConn = (HttpURLConnection)url.openConnection();  
            inputStream = urlConn.getInputStream();  
              
        } 
        catch (MalformedURLException e) 
        {  
            e.printStackTrace();  
        } catch (IOException e) 
        {  
            e.printStackTrace();  
        }  
          
        return inputStream;  
    }
}





















