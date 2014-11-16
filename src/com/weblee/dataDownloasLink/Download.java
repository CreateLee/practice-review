package com.weblee.dataDownloasLink;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

public class Download {
	public static void main(String[] args) {	
		String regionUrl = "F:" + File.separator + "source" + File.separator
				+ "java" + File.separator + "sun" + File.separator
				+ "reflect" + File.separator + "resource.url";
		String destinationPath = "F:" + File.separator + "source" + File.separator
				+ "java" + File.separator + "sun" + File.separator + "reflect" + File.separator;
		// TODO Auto-generated method stub
		downloadFiles(regionUrl, destinationPath);
	}

	public static void downloadFiles(String filename, String destinationPath) {
		// TODO Auto-generated method stub
		File file = new File(filename);
		//
		RandomAccessFile randomAccessFile = null;

		try {
			randomAccessFile = new RandomAccessFile(file, "rw");
			//
			String tempString = null;
			while (true) {
				tempString = randomAccessFile.readLine();
				if (tempString == null) {
					break;
				}

				downloadCore(tempString, destinationPath);

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

	public static void downloadCore(String destinationurl,
			String destinationPath) {
		// 生成文件名
		String fileName = destinationurl.split("/")[destinationurl.split("/").length - 1];
		// 生成文件路径及文件名
		fileName = destinationPath + fileName;

		HttpURLConnection httpUrl = null;
		BufferedInputStream bis = null;
		FileOutputStream fos = null;

		try {
			// 得到 httpurl 连接
			httpUrl = (HttpURLConnection) new URL(destinationurl)
					.openConnection();
			// 连接指定的网络资源
			httpUrl.connect();
			// 获取网络输入流
			bis = new BufferedInputStream(httpUrl.getInputStream());
			//
			fos = new FileOutputStream(fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 设置代理服务器
		/*
		 * System.getProperties().put("proxySet", "true");
		 * System.getProperties().put("proxyHost", "10.154.134.110");
		 * System.getProperties().put("proxyPort", "8080");
		 */

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
	
	public static void downloadCore(String destinationurl,
			String destinationPath, boolean isDir) {
		// 生成文件名
		String fileName = destinationurl.split("/")[destinationurl.split("/").length - 1];
		
		/*
		 * 
		 */
		if (!fileName.contains(".")) {
			new File(destinationPath + fileName).mkdir();
			// downloadCore(destinationurl, destinationPath + fileName + File.separator, false);
		} 
		
		// 生成文件路径及文件名
		fileName = destinationPath + fileName;

		HttpURLConnection httpUrl = null;
		BufferedInputStream bis = null;
		FileOutputStream fos = null;

		try {
			// 得到 httpurl 连接
			httpUrl = (HttpURLConnection) new URL(destinationurl)
					.openConnection();
			// 连接指定的网络资源
			httpUrl.connect();
			// 获取网络输入流
			bis = new BufferedInputStream(httpUrl.getInputStream());
			//
			fos = new FileOutputStream(fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 设置代理服务器
		/*
		 * System.getProperties().put("proxySet", "true");
		 * System.getProperties().put("proxyHost", "10.154.134.110");
		 * System.getProperties().put("proxyPort", "8080");
		 */

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
