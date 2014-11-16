package com.weblee.dataDownloasLink.top;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Author: weblee
 * @Email: likaiweb@163.com
 * @Blog: http://www.cnblogs.com/lkzf/
 * @Time: 2014��11��8������1:44:39
 * 
 *************        function description ***************
 *
 ****************************************************
 */

public class TopAnalysis {
    public static void anlysisSubFile(String url, String path) {
	// TODO Auto-generated method stub
		
		File topFile = new File(path);
		RandomAccessFile randomAccessFile = null;
		File urls = new File(path+".txt");
		RandomAccessFile randomAccessFile2 = null;

		try {
		    randomAccessFile = new RandomAccessFile(topFile, "rw");
		    randomAccessFile2 = new RandomAccessFile(urls, "rw");
		    //
		    String tempString = null;
		    while (true) {
			tempString = randomAccessFile.readLine();
			if (tempString == null) {
			    break;
			}

			// process
			if (tempString.length()>104) {
		  		  if ("compressed".equals(tempString.substring(38, 48))) {
		  		    String infoStr = tempString.substring(84, 104);
		  		    
		  		  randomAccessFile2.writeBytes(url+infoStr+(char)Character.LINE_SEPARATOR);
		  		    System.out.println(infoStr);
				}
		  		  
		    		
				}
			
			

		    }

		} catch (FileNotFoundException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		} catch (IOException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		} finally {
		    try {
			randomAccessFile.close();
			randomAccessFile2.close();
		    } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    }
		}

    }

    public static void main(String[] args) {
	// TODO Auto-generated method stub
	String path = "F:\\noaa\\";
	String url = "http://www1.ncdc.noaa.gov/pub/data/noaa/";
	File topFile = new File(path+"noaa.txt");
	RandomAccessFile randomAccessFile = null;

	try {
	    randomAccessFile = new RandomAccessFile(topFile, "rw");
	    //
	    String tempString = null;
	    while (true) {
		tempString = randomAccessFile.readLine();
		if (tempString == null) {
		    break;
		}

		// process
		String infoStr = tempString.substring(80, 84);
		File subDir = new File(path + infoStr);
		subDir.mkdir();
		//
		down(url + infoStr + "/", subDir.getAbsolutePath() + File.separatorChar + infoStr);
		 System.out.println(subDir.getAbsolutePath() + File.separatorChar + infoStr);
		anlysisSubFile(url + infoStr + "/", subDir.getAbsolutePath() + File.separatorChar + infoStr);

		 System.out.println(subDir.getAbsolutePath() + File.separatorChar + infoStr);

	    }

	} catch (FileNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} finally {
	    try {
		randomAccessFile.close();
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}

    }

    public static void down(String url, String dir) {
	// TODO Auto-generated method stub
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
