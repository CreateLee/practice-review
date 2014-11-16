package com.weblee.dataDownloasLink.top;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @Author: weblee
 * @Email: likaiweb@163.com
 * @Blog: http://www.cnblogs.com/lkzf/
 * @Time: 2014年11月8日下午2:36:33

 *************  function description  ***************
 *
 ****************************************************
 */

public class Test {
 public static void main(String[] args) {
  // TODO Auto-generated method stub
  	String path = "F:\\noaa\\1901\\1901";
  	
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
  		if (tempString.length()>49) {
  		  if ("compressed".equals(tempString.substring(38, 48))) {
  		    String infoStr = tempString.substring(84, 104);
  		  randomAccessFile2.writeBytes(infoStr+(char)Character.LINE_SEPARATOR);
  		  
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
}
