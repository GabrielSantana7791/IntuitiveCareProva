package com.intuitivecare.prova.IntuitiveCare.entity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

public class ZipTableHttpEntity {
	private List<TissTable> tableList;
	
	public ZipTableHttpEntity(List<TissTable> tableList){
		this.tableList = tableList;
	}
	
	
	public HttpEntity<byte[]> GetZipTableHttpEntity() throws IOException {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Content-Disposition", "attachment;filename=Teste_Intuitive_Care_Gabriel de Paula Santana.zip");
		
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		ZipOutputStream zipOut = new ZipOutputStream(byteOut);
		
		for(TissTable table : tableList) {
			ZipEntry entry = new ZipEntry(table.getTableName() + ".csv");
			zipOut.putNextEntry(entry);
			zipOut.write(table.getTableByte());
		}
			
		zipOut.close();
		byteOut.close();
		HttpEntity<byte[]> entity = new HttpEntity<byte[]>(byteOut.toByteArray(), httpHeaders);
		
		return entity;
	}
}
