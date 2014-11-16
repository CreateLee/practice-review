package com.weblee.html2pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.xhtmlrenderer.pdf.ITextRenderer;

import com.itextpdf.text.DocumentException;
 

/**
 * @Author: weblee
 * @Email: likaiweb@163.com
 * @Blog: http://www.cnblogs.com/lkzf/
 * @Time: 2014年11月15日下午9:48:24
 * 
 *************        function description ***************
 *
 ****************************************************
 */

public class FirstDoc {
    public static void main(String[] args) throws DocumentException {

	String inputFile = "./file/text.html";
	String url = new File(inputFile).toURI().toURL().toString();
	String outputFile = "./file/pdfOut/fistdoc.pdf";

	OutputStream os = new FileOutputStream(outputFile);

	ITextRenderer render = new ITextRenderer();
	render.setDocument(url);
	render.layout();

	render.createPDF(os);

	os.close();
    }
}
