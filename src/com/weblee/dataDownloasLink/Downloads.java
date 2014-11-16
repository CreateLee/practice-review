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
 * @Time: 2014��11��8������1:24:52
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
	    // �õ� httpurl ����
	    httpUrl = (HttpURLConnection) new URL(url).openConnection();
	    // ����ָ����������Դ
	    httpUrl.connect();
	    // ��ȡ����������
	    bis = new BufferedInputStream(httpUrl.getInputStream());
	    //
	    fos = new FileOutputStream(new File(dir));
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	int temp;
	// �����ļ�
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
