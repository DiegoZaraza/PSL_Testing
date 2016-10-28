package Utilitarios;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class createPDF
{
	Document documento;
	Paragraph parrafo;
	
	public void createPdf() throws DocumentException, MalformedURLException, IOException
	{
		/** Creación carpeta para guardar resultados */
		File folder0 = new File("C:\\tmp");
		folder0.mkdir();
		
		//SE CREA UN DOCUMENTO CON TAMAÑO CARTA Y SE ESCOJE LA RUTA Y NOMBRE DEL ARCHIVO 
		documento = new Document(PageSize.LETTER, 80, 80, 75, 75);
		String archivo = "C:\\tmp\\EjecucionPruebas.pdf";
		@SuppressWarnings("unused")
		PdfWriter writer = PdfWriter.getInstance(documento, new FileOutputStream(archivo, true));

		//SE LE AGREGA EL TITULO Y AUTOR AL DOCUMENTO
		documento.addTitle("Registro Pruebas de Regresión" );
		documento.open();

		//SE CREA UN PARAGRAFO QUE LLEBA EL ENCABEZADO DEL DOCUMENTO TITULO LETRA GRANDE 
		Paragraph titulo = new Paragraph();
		titulo.setAlignment(Paragraph.ALIGN_CENTER);
		titulo.setFont(FontFactory.getFont("Sans", 15, Font.BOLD));
		titulo.add("REGISTRO PRUEBAS DE REGRESION" + "\n\n");

		//SE AGREGA EL PARAGRAFO AL DOCUMENTO 
		documento.add(titulo);
	}

	/* Método para añadir texto al PDF */
	public void addTexto(String Cadena) throws DocumentException
	{

		//System.out.println(documento.get
		parrafo = new Paragraph();
		parrafo.setFont(FontFactory.getFont("Sans", 12, Font.NORMAL));
		parrafo.setAlignment(Paragraph.ALIGN_LEFT);
		parrafo.add(Cadena);
		documento.add(parrafo);
	}

	/* Méotdo para agregar una imágen a un PDF */
	public void addImage(String ruta) throws MalformedURLException, IOException, DocumentException
	{
		Image imagen = Image.getInstance(ruta);
		imagen.setAlignment(Image.ALIGN_CENTER);
		imagen.setUseVariableBorders(true);
		imagen.scalePercent(40);
		documento.add(imagen);
	}

	/* Método para cerrar PDF */
	public void closePDF() throws DocumentException
	{
		documento.close();
	}
}