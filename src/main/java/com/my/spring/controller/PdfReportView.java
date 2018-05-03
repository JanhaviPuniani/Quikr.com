package com.my.spring.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class PdfReportView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document pdfdoc, PdfWriter pdfwriter,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//model was added to the scope by the Controller
		String itemName = (String) model.get("itemName");
		String itemPrice = (String) model.get("itemPrice");
		String customerName = (String) model.get("customerName");
		String address = (String) model.get("address");
		//pdfdoc.add(new Paragraph("Item Name: " + itemName + " " +"customerName:"+customerName));
		
		PdfPTable table = new PdfPTable(4);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

        table.addCell("Customer Name");
        table.addCell("Address");
        table.addCell("Item Name");
        table.addCell("Item Price");
        
        
        table.addCell(customerName);
        table.addCell(address);
        table.addCell(itemName);
        table.addCell(itemPrice);
        
        //pdfdoc.add(new Paragraph("Item Name: " + itemName + " " +"customerName:"+customerName));
        pdfdoc.add(table);
	}

	

}
