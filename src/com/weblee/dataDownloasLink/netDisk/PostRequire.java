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
 * @Time: 2014��11��9������1:52:19
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
	 * Ȼ���������Ϊ���ģʽ��URLConnectionͨ����Ϊ������ʹ�ã���������һ��Webҳ��
	 * ͨ����URLConnection��Ϊ���������԰����������Webҳ���͡��������������
	 */
	connection.setDoOutput(true);
	/**
	 * ���Ϊ�˵õ�OutputStream�������������Լ����Writer���ҷ���POST��Ϣ�У����磺 ...
	 */
	OutputStreamWriter out = new OutputStreamWriter(
		connection.getOutputStream(), "UTF-8");

	// һ�����ͳɹ��������·����Ϳ��Եõ��������Ļ�Ӧ��
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
