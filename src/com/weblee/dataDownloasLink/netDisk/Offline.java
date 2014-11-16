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
 * @Time: 2014��11��9������2:08:44
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
     * ���� ��ѯ������������ID�б�������Ϣ��
     */
    public static String listTask(String accessToken) throws IOException {
	StringBuffer https = new StringBuffer(
		"https://pcs.baidu.com/rest/2.0/pcs/services/cloud_dl");

	// ƴ��https
	https.append("?method=list_task&access_token=");
	https.append(accessToken);

	// return requireSubmit(https.toString());
	return sendPost(https.toString());
    }

    /*
     * 
     * ���� Ϊ��ǰ�û�����һ��Ŀ¼��
     */
    public static String mkdir(String path, String accessToken)
	    throws IOException {
	StringBuffer https = new StringBuffer(
		"https://pcs.baidu.com/rest/2.0/pcs/file");

	// ƴ��https
	https.append("?method=mkdir&access_token=");
	https.append(accessToken);
	https.append("&path=");
	https.append(path);

	// return requireSubmit(https.toString());
	return sendPost(https.toString());
    }

    /*
     * ���� ���������������ʵ�ֵ����ļ��������ء�
     */
    public static String addTask(String sourceUrl, String savePath,
	    String accessToken) throws IOException {
	StringBuffer https = new StringBuffer(
		"https://pcs.baidu.com/rest/2.0/pcs/services/cloud_dl");

	// ƴ��https
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
     * ��ָ�� URL ����POST����������
     * 
     * @param url
     *            ��������� URL
     * @param param
     *            ����������������Ӧ���� name1=value1&name2=value2 ����ʽ��
     * @return ������Զ����Դ����Ӧ���
     */
    public static String sendPost(String url) {
	PrintWriter out = null;
	BufferedReader in = null;
	StringBuffer result = new StringBuffer();
	try {
	    URL realUrl = new URL(url);
	    // �򿪺�URL֮�������
	    URLConnection conn = realUrl.openConnection();
	    // ����ͨ�õ���������
	    conn.setRequestProperty("accept", "*/*");
	    conn.setRequestProperty("connection", "Keep-Alive");
	    conn.setRequestProperty("user-agent",
		    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
	    // ����POST�������������������
	    conn.setDoOutput(true);
	    conn.setDoInput(true);

	    // ��ȡURLConnection�����Ӧ�������
	    out = new PrintWriter(conn.getOutputStream());

	    // flush������Ļ���
	    out.flush();
	    // ����BufferedReader����������ȡURL����Ӧ
	    in = new BufferedReader(
		    new InputStreamReader(conn.getInputStream()));

	    String line;
	    while ((line = in.readLine()) != null) {
		result.append(line + Character.LINE_SEPARATOR);
	    }
	} catch (Exception e) {
	    System.out.println("���� POST ��������쳣��" + e);
	    System.out.println("reTesting ������������������������������������" + url);
	    //sendPost(url);
	    e.printStackTrace();
	}
	// ʹ��finally�����ر��������������
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
