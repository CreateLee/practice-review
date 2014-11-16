package com.weblee.dataDownloasLink;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Author: weblee
 * @Email: likaiweb@163.com
 * @Blog: http://www.cnblogs.com/lkzf/
 * @Time: 2014年11月8日下午1:24:52
 * 
 *************        function description ***************
 *
 ****************************************************
 */

public class Downloads {

    public static void main(String[] args) {
	// TODO Auto-generated method stub
	String url = "http://www1.ncdc.noaa.gov/pub/data/noaa/";
	String dir = "F:" + File.separatorChar
		+ url.split("/")[url.split("/").length - 1] + ".html";

	HttpURLConnection httpUrl = null;
	BufferedInputStream bis = null;
	FileOutputStream fos = null;

	try {
	    // 得到 httpurl 连接
	    httpUrl = (HttpURLConnection) new URL(url).openConnection();
	    // 连接指定的网络资源
	    httpUrl.connect();
	    // 获取网络输入流
	    bis = new BufferedInputStream(httpUrl.getInputStream());
	    //
	    fos = new FileOutputStream(new File(dir));
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	int temp;
	// 保存文件
	try {
	    while ((temp = bis.read()) != -1) {
		fos.write((char) temp);
	    }
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} finally {
	    try {
		fos.close();
		bis.close();
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }

	    httpUrl.disconnect();
	}

    }
}
