package com.weblee.dataDownloasLink.netDisk;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @Author: weblee
 * @Email: likaiweb@163.com
 * @Blog: http://www.cnblogs.com/lkzf/
 * @Time: 2014��11��9������4:30:02
 * 
 *************        function description ***************
 *
 ****************************************************
 */

public class Test {
    public static void main(String[] args) {
	File dir = new File("F:\\noaa\\");
	// ��ȡ��Ŀ¼�µ��ļ�
	File[] files = dir.listFiles();

	for (File file : files) {
	    // ��Ӧ�ļ��н��з�������
	    if (file.isDirectory()) {
		String name = file.getName();

		//System.out.println(file.getAbsoluteFile() + File.separator + name + ".txt");
		File urlFile = new File(file.getAbsoluteFile() + File.separator + name + ".txt");
		RandomAccessFile randomAccessFile = null;
		try {
		    randomAccessFile = new RandomAccessFile(urlFile, "r");
		    //
		    String tempString = null;
		    while (true) {
			tempString = randomAccessFile.readLine();
			if (tempString == null) {
			    break;
			}

			// process
			System.out.println(tempString);
			
			//	

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
	}
    }
}
