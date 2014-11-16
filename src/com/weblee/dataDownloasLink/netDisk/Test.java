package com.weblee.dataDownloasLink.netDisk;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @Author: weblee
 * @Email: likaiweb@163.com
 * @Blog: http://www.cnblogs.com/lkzf/
 * @Time: 2014年11月9日下午4:30:02
 * 
 *************        function description ***************
 *
 ****************************************************
 */

public class Test {
    public static void main(String[] args) {
	File dir = new File("F:\\noaa\\");
	// 获取该目录下的文件
	File[] files = dir.listFiles();

	for (File file : files) {
	    // 对应文件夹进行分析遍历
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
