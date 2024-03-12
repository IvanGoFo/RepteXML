package com.levent.fopdemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class Main {

    public static void main(String[] args) {
        try {
            // Leer el archivo XML usando JAXB
            JAXBContext jaxbContext = JAXBContext.newInstance(YourXMLClass.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            YourXMLClass data = (YourXMLClass) jaxbUnmarshaller.unmarshal(new FileInputStream("archivo.xml"));

            // Crear documento PDF
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);

            // Escribir datos del XML en el PDF
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(100, 700);
            contentStream.showText("Datos del XML:");
            contentStream.newLine();
            contentStream.showText(data.toString()); // Ajusta esto según la estructura de tu XML
            contentStream.endText();
            contentStream.close();

            // Guardar el PDF
            document.save("archivo.pdf");
            document.close();

            System.out.println("PDF generado correctamente.");
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }
}