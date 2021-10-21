package com.intuitivecare.prova.IntuitiveCare.entity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import com.intuitivecare.prova.IntuitiveCare.util.PdfURL;

public class TissTable {
	private String tableName;
	private byte[] TableByte;
	private InputStream pdfInputStream;
	
	public TissTable(String tableName) throws IOException {
		URL url = new URL(PdfURL.getPdfUrl());
		URLConnection urlConnection = url.openConnection();

		pdfInputStream = urlConnection.getInputStream();
		this.tableName = tableName;
		
		setTableByte();
	}
	
	public String getTableName() {
		return tableName;
	}
	
	public byte[] getTableByte() {
		return TableByte;
	}
	
	private void setTableByte() throws IOException {
		PDFTextStripper stripper = new PDFTextStripper();

		String texto = stripper.getText(PDDocument.load(this.pdfInputStream));
		
		//Não consegui encontrar outro critério para saber o fim.
		texto = texto.substring(texto.indexOf(tableName) + tableName.length());
		texto = texto.substring(0, texto.indexOf("•"));

		Scanner scanner = new Scanner(texto);
		scanner.nextLine();

		ByteArrayOutputStream file = new ByteArrayOutputStream();
		PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(file, StandardCharsets.ISO_8859_1));

		while (scanner.hasNext()) {
			String line = scanner.nextLine();

			if (!line.contains("•") && !line.contains("  ")  && !line.contains("Padrão TISS - Componente Organizacional")) {
				printWriter.append(line.substring(0, line.indexOf(" ")) + ";");
				printWriter.append(line.substring(line.indexOf(" ") + 1));
				printWriter.append("\n");
		
			}

		}
		scanner.close();
		printWriter.close();
		this.TableByte = file.toByteArray();
	}
	
}
