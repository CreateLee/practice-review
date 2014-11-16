package com.weblee.dataDownloasLink.netDisk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Author: weblee
 * @Email: likaiweb@163.com
 * @Blog: http://www.cnblogs.com/lkzf/
 * @Time: 2014年11月9日下午2:08:44
 * 
 *************        function description ***************
 *
 ****************************************************
 */

public class Offline {

    public static void main(String[] args) throws IOException {
	// GetLinks.offLine();
	// netDisk path
	String path = "/apps/weblee/climateData/";
	// accessToken
	String accessToken = "23.d50bfe61fafcfe1877784472f9a6f416.2592000.1418103395.3928183261-4580296";

	String sourceUrl = "http://www1.ncdc.noaa.gov/pub/data/noaa/2014/010010-99999-2014.gz";
	System.out.println(addTask(sourceUrl, path, accessToken));
    }

    /*
     * 功能 查询离线下载任务ID列表及任务信息。
     */
    public static String listTask(String accessToken) throws IOException {
	StringBuffer https = new StringBuffer(
		"https://pcs.baidu.com/rest/2.0/pcs/services/cloud_dl");

	// 拼接https
	https.append("?method=list_task&access_token=");
	https.append(accessToken);

	// return requireSubmit(https.toString());
	return sendPost(https.toString());
    }

    /*
     * 
     * 功能 为当前用户创建一个目录。
     */
    public static String mkdir(String path, String accessToken)
	    throws IOException {
	StringBuffer https = new StringBuffer(
		"https://pcs.baidu.com/rest/2.0/pcs/file");

	// 拼接https
	https.append("?method=mkdir&access_token=");
	https.append(accessToken);
	https.append("&path=");
	https.append(path);

	// return requireSubmit(https.toString());
	return sendPost(https.toString());
    }

    /*
     * 功能 添加离线下载任务，实现单个文件离线下载。
     */
    public static String addTask(String sourceUrl, String savePath,
	    String accessToken) throws IOException {
	StringBuffer https = new StringBuffer(
		"https://pcs.baidu.com/rest/2.0/pcs/services/cloud_dl");

	// 拼接https
	https.append("?method=add_task&access_token=");
	https.append(accessToken);
	https.append("&save_path=");
	https.append(savePath);
	https.append("&source_url=");
	https.append(sourceUrl);

	// System.out.println(listTask(accessToken));

	// return requireSubmit(https.toString());
	return sendPost(https.toString());
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url) {
	PrintWriter out = null;
	BufferedReader in = null;
	StringBuffer result = new StringBuffer();
	try {
	    URL realUrl = new URL(url);
	    // 打开和URL之间的连接
	    URLConnection conn = realUrl.openConnection();
	    // 设置通用的请求属性
	    conn.setRequestProperty("accept", "*/*");
	    conn.setRequestProperty("connection", "Keep-Alive");
	    conn.setRequestProperty("user-agent",
		    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
	    // 发送POST请求必须设置如下两行
	    conn.setDoOutput(true);
	    conn.setDoInput(true);

	    // 获取URLConnection对象对应的输出流
	    out = new PrintWriter(conn.getOutputStream());

	    // flush输出流的缓冲
	    out.flush();
	    // 定义BufferedReader输入流来读取URL的响应
	    in = new BufferedReader(
		    new InputStreamReader(conn.getInputStream()));

	    String line;
	    while ((line = in.readLine()) != null) {
		result.append(line + Character.LINE_SEPARATOR);
	    }
	} catch (Exception e) {
	    System.out.println("发送 POST 请求出现异常！" + e);
	    System.out.println("reTesting ………………………………………………" + url);
	    //sendPost(url);
	    e.printStackTrace();
	}
	// 使用finally块来关闭输出流、输入流
	finally {
	    try {
		if (out != null) {
		    out.close();
		}
		if (in != null) {
		    in.close();
		}
	    } catch (IOException ex) {
		ex.printStackTrace();
	    }
	}

	System.out.println();
	System.out
		.println("*******************successful***************************");
	System.out.println();

	return result.toString();
    }

    public static String requireSubmit(String https) throws IOException {
	// URL url = new URL("http://localhost:9000/wbsc/spi/visitorvisit.do");
	URL url = new URL(https);
	URLConnection connection = url.openConnection();
	/**
	 * 然后把连接设为输出模式。URLConnection通常作为输入来使用，比如下载一个Web页。
	 * 通过把URLConnection设为输出，你可以把数据向你个Web页传送。下面是如何做：
	 */

	connection.setDoOutput(true);
	/**
	 * 最后，为了得到OutputStream，简单起见，把它约束在Writer并且放入POST信息中，例如： ...
	 */
	OutputStreamWriter out = new OutputStreamWriter(
		connection.getOutputStream(), "UTF-8");

	// 一旦发送成功，用以下方法就可以得到服务器的回应：
	StringBuffer result = new StringBuffer();

	InputStream l_urlStream;
	l_urlStream = connection.getInputStream();

	BufferedReader l_reader = new BufferedReader(new InputStreamReader(
		l_urlStream));

	String tmpLine;

	while ((tmpLine = l_reader.readLine()) != null) {
	    result.append(tmpLine + Character.LINE_SEPARATOR);
	}

	System.out.println();
	System.out
		.println("*******************successful***************************");
	System.out.println();

	return result.toString();
    }
}
