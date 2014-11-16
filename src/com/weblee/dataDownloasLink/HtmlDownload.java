package com.weblee.dataDownloasLink;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

public class HtmlDownload {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String regionUrl = "." + File.separator + "url";
		String destinationPath = "." + File.separator + "dir" + File.separator;

		// Download.downloadFiles(regionUrl, destinationPath);
		// analysisSun();
		downloadFiles("F:" + File.separator + "source" + File.separator
				+ "java" + File.separator + "sun" + File.separator + "sun", "F:" + File.separator + "source" + File.separator
				+ "java" + File.separator + "sun" + File.separator);
	}
	
	public static void analysisSun() {
		String filename = "." + File.separator + "dir" + File.separator + "sun"; 
		String destinationPath = "F:" + File.separator + "source" + File.separator
				+ "java" + File.separator + "sun" + File.separator + "sun";
		File file = new File(filename);
		File urlFile = new File(destinationPath);
		
		// output
		OutputStream outputStream = null;

		//
		RandomAccessFile randomAccessFile = null;

		try {
			randomAccessFile = new RandomAccessFile(file, "rw");
			outputStream = new FileOutputStream(urlFile);
			//
			String tempString = null;
			String url = null;
			String temp = null;
			String uri = null;
			while (true) {
				tempString = randomAccessFile.readLine();
				if (tempString == null) {
					break;
				}

				if (tempString.contains("folder")) {
					url = tempString.split("</td><td>")[1];
					url = url.split("</a></td><td")[0];
					url = url.split(">")[1];
					url = "http://www.docjar.com/src/api/sun/" + url;
					
					//url = url.replace("\"", "");
					
					//
					
					
					//uri = url.split("/")[url.split("/").length-1];
					//url = "http://www.docjar.com" + url;
					System.out.println(url);
					if (!url.equals(temp)) {
						outputStream.write(url.getBytes());
						outputStream.write((char) Character.LINE_SEPARATOR);
					}
					// outputStream.write(url.getBytes());
					
				}
				
				temp = url;

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
				outputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	}
	
	public static void analysisSun(String filepath) {
		File file = new File(filepath);
		File urlFile = new File(filepath + ".urls");
		
		// output
		OutputStream outputStream = null;

		//
		RandomAccessFile randomAccessFile = null;

		try {
			randomAccessFile = new RandomAccessFile(file, "rw");
			outputStream = new FileOutputStream(urlFile);
			//
			String tempString = null;
			String url = null;
			String temp = null;
			String uri = null;
			while (true) {
				tempString = randomAccessFile.readLine();
				if (tempString == null) {
					break;
				}

				if (tempString.contains(".java")) {
					url = tempString.split("</td><td>")[1];
					url = url.split("</a></td><td")[0];
					url = url.split(">")[1];
					
					System.out.println(filepath);
					//url = url.replace("\"", "");
					
					//
					
					
					//uri = url.split("/")[url.split("/").length-1];
					url = "http://www.docjar.com/src/api/sun/" + filepath.split("\\\\")[filepath.split("\\\\").length-1]+ "/" + url;
					//System.out.println(url);
					if (!url.equals(temp)) {
						outputStream.write(url.getBytes());
						outputStream.write((char) Character.LINE_SEPARATOR);
					}
					// outputStream.write(url.getBytes());
					
				}
				
				temp = url;

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
				outputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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

				new File(destinationPath + tempString.split("/")[tempString.split("/").length - 1]).mkdir();
				downloadCore(tempString, destinationPath + tempString.split("/")[tempString.split("/").length - 1]+File.separator);

				analysisSun(destinationPath + tempString.split("/")[tempString.split("/").length - 1]+File.separator+tempString.split("/")[tempString.split("/").length - 1]);
				/*
				 * 
				 */
				Download.downloadFiles(destinationPath + tempString.split("/")[tempString.split("/").length - 1]+File.separator+tempString.split("/")[tempString.split("/").length - 1]+".urls", destinationPath + tempString.split("/")[tempString.split("/").length - 1]+File.separator);
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

}
