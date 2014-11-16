package com.weblee.dataDownloasLink.netDisk;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @Author: weblee
 * @Email: likaiweb@163.com
 * @Blog: http://www.cnblogs.com/lkzf/
 * @Time: 2014��11��9������4:30:25
 * 
 *************        function description ***************
 *
 ****************************************************
 */

public class GetLinks {

    public static void offLine() throws IOException {
	File dir = new File("F:\\noaa\\");
	// ��ȡ��Ŀ¼�µ��ļ�
	File[] files = dir.listFiles();

	// netDisk path
	String path = "/apps/weblee/climateData/";
	// accessToken
	String accessToken = "23.d50bfe61fafcfe1877784472f9a6f416.2592000.1418103395.3928183261-4580296";

	for (File file : files) {
	    // ��Ӧ�ļ��н��з�������
	    if (file.isDirectory()) {
		String name = file.getName();
		// create Ŀ¼
		Offline.mkdir(path + name, accessToken);

		File urlFile = new File(file.getAbsoluteFile() + File.separator
			+ name + ".txt");
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
			// offLine download
			Offline.addTask(tempString, path + name, accessToken);
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
