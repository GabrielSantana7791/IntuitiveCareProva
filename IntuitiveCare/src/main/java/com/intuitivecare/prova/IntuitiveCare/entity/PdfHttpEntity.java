package com.intuitivecare.prova.IntuitiveCare.entity;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import com.intuitivecare.prova.IntuitiveCare.util.PdfURL;

public class PdfHttpEntity {
	private InputStream inputStreamPDF;
	private byte[] bytePDF;
	private String title;

	public PdfHttpEntity() throws IOException {

		URL url = new URL(PdfURL.getPdfUrl());
		URLConnection connection = url.openConnection();
		this.inputStreamPDF = connection.getInputStream();
		this.bytePDF = this.inputStreamPDF.readAllBytes();
		
		this.title = url.getFile().substring(url.getFile().lastIndexOf("/") + 1);
	}

	public HttpEntity<byte[]> getPdfHttpEntity() throws IOException {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Content-Disposition", "attachment;filename=\"" + this.title + ".pdf");
		HttpEntity<byte[]> entity = new HttpEntity<byte[]>(this.bytePDF, httpHeaders);
		return entity;
	}
}
