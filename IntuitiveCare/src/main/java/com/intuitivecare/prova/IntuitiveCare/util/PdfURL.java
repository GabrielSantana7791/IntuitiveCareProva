package com.intuitivecare.prova.IntuitiveCare.util;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class PdfURL {
	
	public static String getPdfUrl() throws IOException {
		
		String tissPageUrl = "https://www.gov.br/ans/pt-br/assuntos/prestadores/padrao-para-troca-de-informacao-de-saude-suplementar-2013-tiss";
		Document documentMainPage = Jsoup.connect(tissPageUrl).get();
		
		//texto referencia
		Elements element = documentMainPage.getElementsContainingOwnText("Clique aqui para acessar a versão");
		String urlTiss = element.attr("href");
		
		Document documentPdfPage = Jsoup.connect(urlTiss).get();
		
		element = documentPdfPage.getElementsByAttributeValueContaining("href", "https://www.gov.br/ans/pt-br/arquivos/assuntos/prestadores/padrao-para-tr"
				+ "oca-de-informacao-de-saude-suplementar-tiss/padrao-tiss/padrao_tiss_componente_organizacional_");
		
		String urlPdf = element.attr("href");
		return urlPdf;
	}

}
