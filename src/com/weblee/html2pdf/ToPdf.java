package com.weblee.html2pdf;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

/**
 * @Author: weblee
 * @Email: likaiweb@163.com
 * @Blog: http://www.cnblogs.com/lkzf/
 * @Time: 2014年11月13日上午10:54:02
 * 
 *************        function description ***************
 *
 ****************************************************
 */

public class ToPdf {
    public static void main(String[] args) throws FileNotFoundException,
	    IOException, DocumentException {
	String url = "http://auto.firefox.news.cn/14/1114/09/21ZPJ6C53FABWGVE.html";

	html2pdf(downHTMLbyURL(url, "./"));
	 //String htmlLocate = "./hero.html";
	// html2pdf(htmlLocate);
    }

    public static String html2pdf(String htmlLocate)
	    throws FileNotFoundException, IOException, DocumentException {
	String pdfPath;
	if (htmlLocate.contains("/")) {
	    pdfPath = htmlLocate.split("/")[htmlLocate.split("/").length - 1]
		    .split("\\.")[0] + ".pdf";
	} else {
	    pdfPath = htmlLocate.split("\\.")[0] + ".pdf";
	}

	Document document = new Document();

	PdfWriter writer = PdfWriter.getInstance(document,
		new FileOutputStream(pdfPath));

	document.open();

	XMLWorkerHelper.getInstance().parseXHtml(writer, document,
		new FileInputStream(htmlLocate));

	document.close();

	return pdfPath;
    }

    public static String downHTMLbyURL(String url, String savePthDir) {
	// 生成文件名
	String fileName = url.split("/")[url.split("/").length - 1];
	// 生成文件路径及文件名
	fileName = savePthDir + fileName;

	HttpURLConnection httpUrl = null;
	BufferedInputStream bis = null;
	FileOutputStream fos = null;

	try {
	    // 得到 httpurl 连接
	    httpUrl = (HttpURLConnection) new URL(url).openConnection();
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

	return fileName;
    }

}
