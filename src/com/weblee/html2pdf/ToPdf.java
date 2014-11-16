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
 * @Time: 2014��11��13������10:54:02
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
	// �����ļ���
	String fileName = url.split("/")[url.split("/").length - 1];
	// �����ļ�·�����ļ���
	fileName = savePthDir + fileName;

	HttpURLConnection httpUrl = null;
	BufferedInputStream bis = null;
	FileOutputStream fos = null;

	try {
	    // �õ� httpurl ����
	    httpUrl = (HttpURLConnection) new URL(url).openConnection();
	    // ����ָ����������Դ
	    httpUrl.connect();
	    // ��ȡ����������
	    bis = new BufferedInputStream(httpUrl.getInputStream());
	    //
	    fos = new FileOutputStream(fileName);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	// ���ô��������
	/*
	 * System.getProperties().put("proxySet", "true");
	 * System.getProperties().put("proxyHost", "10.154.134.110");
	 * System.getProperties().put("proxyPort", "8080");
	 */

	int temp;
	// �����ļ�
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
