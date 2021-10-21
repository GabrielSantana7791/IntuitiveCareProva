package com.intuitivecare.prova.IntuitiveCare.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intuitivecare.prova.IntuitiveCare.TableString;
import com.intuitivecare.prova.IntuitiveCare.entity.PdfHttpEntity;
import com.intuitivecare.prova.IntuitiveCare.entity.TissTable;
import com.intuitivecare.prova.IntuitiveCare.entity.ZipTableHttpEntity;

@RestController
public class tissController {
	
	@GetMapping(value= "/downloadTissPdf")
	public HttpEntity<byte[]> downloadTissPdf() throws IOException{
		PdfHttpEntity pdf = new PdfHttpEntity();
		return pdf.getPdfHttpEntity();
	}
	
	@GetMapping(value= "/downloadCsvZip")
	public HttpEntity<byte[]> downloadCsvZip(boolean cb_padraoTiss, boolean cb_demandante, boolean cb_solicitacao) throws IOException{
		List<TissTable> tableList = new ArrayList<>();
		
		if(cb_padraoTiss == true) {
			TissTable padraoTiss = new TissTable(TableString.TABELA_PADRAO_TISS());	
			tableList.add(padraoTiss);
		}
		
		if(cb_demandante == true) {
			TissTable demandante = new TissTable(TableString.TABELA_DEMANDANTE());
			tableList.add(demandante);
		}
		
		if(cb_solicitacao == true) {
			TissTable solicitacao = new TissTable(TableString.TABELA_SOLICITACAO());
			tableList.add(solicitacao);
		}
		
		if(cb_padraoTiss == false && cb_demandante == false && cb_solicitacao == false) {
		return null;
		}
		
	
		ZipTableHttpEntity zip = new ZipTableHttpEntity(tableList);
		
		return zip.GetZipTableHttpEntity();
	}
}
