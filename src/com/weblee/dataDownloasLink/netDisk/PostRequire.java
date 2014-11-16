package com.weblee.dataDownloasLink.netDisk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Author: weblee
 * @Email: likaiweb@163.com
 * @Blog: http://www.cnblogs.com/lkzf/
 * @Time: 2014年11月9日下午1:52:19
 * 
 *************        function description ***************
 *
 ****************************************************
 */

public class PostRequire {

    public static void main(String[] args) throws UnsupportedEncodingException,
	    IOException {
	// TODO Auto-generated method stub
	String access_token = "23.d50bfe61fafcfe1877784472f9a6f416.2592000.1418103395.3928183261-4580296";
	String type = "doc";
	String urlStr = "https://pcs.baidu.com/rest/2.0/pcs/stream?method=list&access_token="
		+ access_token +"&type=" + type;
	// URL url = new URL("http://localhost:9000/wbsc/spi/visitorvisit.do");
	URL url = new URL(urlStr);
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
	String sCurrentLine;
	String sTotalString;
	sCurrentLine = "";
	sTotalString = "";
	InputStream l_urlStream;
	l_urlStream = connection.getInputStream();

	BufferedReader l_reader = new BufferedReader(new InputStreamReader(
		l_urlStream));
	while ((sCurrentLine = l_reader.readLine()) != null) {
	    sTotalString += sCurrentLine + Character.LINE_SEPARATOR;
	}

	System.out.println(sTotalString);

    }

}
