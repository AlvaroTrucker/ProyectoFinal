package controlador;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import modelo.PersonaDTO;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class CrearPDF {
	
	public static void crearPDF(List<PersonaDTO> lista, File archivo){
		try {
			Document documento = new Document();
			PdfWriter.getInstance(documento, new FileOutputStream(archivo));
			Phrase texto = new Phrase();
			documento.open();
			texto.add("Nombre");
			PdfPCell celdaCabeceraNombre = new PdfPCell();
			celdaCabeceraNombre.addElement(texto);
			texto.clear();
			texto.add("Apellidos");
			PdfPCell celdaCabeceraApellidos = new PdfPCell();
			celdaCabeceraApellidos.addElement(texto);
			texto.clear();
			texto.add("Genero");
			PdfPCell celdaCabeceraGenero = new PdfPCell();
			celdaCabeceraGenero.addElement(texto);
			texto.clear();
			texto.add("Pais");
			PdfPCell celdaCabeceraPais = new PdfPCell();
			celdaCabeceraPais.addElement(texto);
			texto.clear();
	
			PdfPTable filaCabecera = new PdfPTable(4);
			//filaCabecera.setSpacingAfter(5);
			filaCabecera.addCell(celdaCabeceraNombre);
			filaCabecera.addCell(celdaCabeceraApellidos);
			filaCabecera.addCell(celdaCabeceraGenero);
			filaCabecera.addCell(celdaCabeceraPais);
			
			Phrase envolturaFilaCabecera = new Phrase();
			envolturaFilaCabecera.add(filaCabecera);
			documento.add(new Paragraph(envolturaFilaCabecera));
			
			for (PersonaDTO persona : lista) {				
				texto.add(persona.getNombre());
				PdfPCell celdaNombre = new PdfPCell();
				celdaNombre.addElement(texto);
				texto.clear();
				texto.add(persona.getApellidos());
				PdfPCell celdaApellidos = new PdfPCell();
				celdaApellidos.addElement(texto);
				texto.clear();
				texto.add(persona.getGenero());
				PdfPCell celdaGenero = new PdfPCell();
				celdaGenero.addElement(texto);
				texto.clear();
				texto.add(persona.getPais());
				PdfPCell celdaPais = new PdfPCell();
				celdaPais.addElement(texto);
				texto.clear();
				
				PdfPTable fila = new PdfPTable(4);
				//fila.setSpacingAfter(5);
				fila.addCell(celdaNombre);
				fila.addCell(celdaApellidos);
				fila.addCell(celdaGenero);
				fila.addCell(celdaPais);
				
				Phrase envolturaFila = new Phrase();
				envolturaFila.add(fila);
				documento.add(new Paragraph(envolturaFila));
			}
			documento.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}

	}
	
}
