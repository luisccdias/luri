/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fatura;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

/**
 *
 * @author Formação
 */
public class Fatura {

    PDDocument invc;
    int n;
    double total = 0;
    double price = 0;
    double precoFinal = 0;
    String nomeCli;
    String contribuinteCli;
    String empNome = "LURI LDA";
    String empNIF = "999999999";
    String codFatura;
    String data;
    String codPostalCli;
    String moradaCli;
    String paisCli;

    PDImageXObject pdImage;
    ArrayList<String> produto = new ArrayList<String>();
    ArrayList<String> descricao = new ArrayList<String>();
    ArrayList<Double> iva = new ArrayList<Double>();
    ArrayList<Double> precoUnitario = new ArrayList<Double>();
    ArrayList<Double> quantidade = new ArrayList<Double>();
    ArrayList<Double> desconto = new ArrayList<Double>();
    String InvoiceTitle;
    String SubTitle;

    //Using the constructor to create a PDF with a blank page

    public Fatura(int n, String pathImagem, String codFatura, String nomeCli, String contribuinteCli, String codPostalCli, String moradaCli, String paisCli ,String data ) throws IOException {
        this.n = n;
        this.codFatura = codFatura;
        this.nomeCli = nomeCli;
        this.contribuinteCli = contribuinteCli;
        this.moradaCli = moradaCli;
        this.codPostalCli = codPostalCli;
        this.paisCli = paisCli;
        this.data = data;
        
         //Create Document
        invc = new PDDocument();
        //Create Blank Page
        PDPage newpage = new PDPage();

        this.pdImage = PDImageXObject.createFromFile(pathImagem, invc);
        //Add the blank page
        invc.addPage(newpage);
    }

   public void getdata(String produto, String descricao, double quantidade , double precoUnitario,double desconto, double iva ) {

        
        for (int i = 0; i < n; i++) {

            this.produto.add(produto);
            this.descricao.add(descricao);
            this.desconto.add(desconto);
            this.iva.add(iva);
            this.precoUnitario.add(precoUnitario);
            this.quantidade.add(quantidade);
 
        }
    }

    public ArrayList<String> getProduto() {
        return produto;
    }

    public ArrayList<String> getDescricao() {
        return descricao;
    }

    public ArrayList<Double> getIva() {
        return iva;
    }

    public ArrayList<Double> getPrecoUnitario() {
        return precoUnitario;
    }

    public ArrayList<Double> getQuantidade() {
        return quantidade;
    }

    public ArrayList<Double> getDesconto() {
        return desconto;
    }
   
   
    
    public void WriteInvoice() {
        //get the page
        PDPage mypage = invc.getPage(0);

        try {
            //Prepare Content Stream
            PDPageContentStream cs = new PDPageContentStream(invc, mypage);

            cs.drawImage(pdImage, 72, 660, 75, 75);
            //Writing Single Line text
            //Writing the Invoice title
            cs.beginText();
            cs.setFont(PDType1Font.HELVETICA_BOLD, 14);
            cs.newLineAtOffset(520, 760);
            cs.showText(codFatura);
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.HELVETICA, 10);
            cs.setLeading(20f);
            cs.newLineAtOffset(390, 740);
            cs.showText("Data: " + data);
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.HELVETICA, 10);
            cs.setLeading(20f);
            cs.newLineAtOffset(470, 740);
            cs.showText("Contribuinte: " + contribuinteCli);
            cs.endText();

            //Writing Multiple Lines
            //writing the customer details
            cs.beginText();
            cs.setFont(PDType1Font.HELVETICA_BOLD, 10);
            cs.setLeading(20f);
            cs.newLineAtOffset(420, 660);
            cs.showText("Exmo (s) Sr (s) ");
            cs.endText();
         
            cs.beginText();
            cs.setFont(PDType1Font.HELVETICA, 8);
            cs.setLeading(20f);
            cs.newLineAtOffset(420, 640);
            cs.showText(nomeCli);
            cs.newLine();
            cs.showText(moradaCli);
            cs.newLine();
            cs.showText(codPostalCli);
            cs.newLine();
            cs.showText(paisCli);
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.HELVETICA_BOLD, 10);
            cs.setLeading(20f);
            cs.newLineAtOffset(80, 660);
            cs.showText(empNome);
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.HELVETICA, 8);
            cs.newLineAtOffset(80, 640);
            cs.showText("999999999");
            cs.newLine();
            cs.showText("Lugar dos Aventureiros, 567");
            cs.newLine();
            cs.showText("4795-040");
            cs.newLine();
            cs.showText("Portugal");
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.HELVETICA_BOLD, 9);
            cs.newLineAtOffset(50, 540);
            cs.showText("Produto");
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.HELVETICA_BOLD, 9);
            cs.newLineAtOffset(150, 540);
            cs.showText("Descrição");
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.HELVETICA_BOLD, 9);
            cs.newLineAtOffset(250, 540);
            cs.showText("Quantidade");
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.HELVETICA_BOLD, 9);
            cs.newLineAtOffset(320, 540);
            cs.showText("Preço Unitário");
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.HELVETICA_BOLD, 9);
            cs.newLineAtOffset(400, 540);
            cs.showText("Desconto");
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.HELVETICA_BOLD, 9);
            cs.newLineAtOffset(465, 540);
            cs.showText("Preço");
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.HELVETICA_BOLD, 9);
            cs.newLineAtOffset(520, 540);
            cs.showText("IVA");
            cs.endText();

            //Inserir Produtos
            cs.beginText();
            cs.setFont(PDType1Font.HELVETICA, 8);
            cs.setLeading(20f);
            cs.newLineAtOffset(50, 520);
            for (int i = 0; i < n; i++) {
                cs.showText(produto.get(i));
                cs.newLine();
            }
            cs.endText();

            //Inserir Descrição
            cs.beginText();
            cs.setFont(PDType1Font.HELVETICA, 8);
            cs.setLeading(20f);
            cs.newLineAtOffset(125, 520);
            for (int i = 0; i < n; i++) {
                cs.showText(descricao.get(i));
                cs.newLine();
            }
            cs.endText();

            //Inserir Quantidade
            cs.beginText();
            cs.setFont(PDType1Font.HELVETICA, 8);
            cs.setLeading(20f);
            cs.newLineAtOffset(250, 520);
            for (int i = 0; i < n; i++) {
                cs.showText(quantidade.get(i).toString());
                cs.newLine();
            }
            cs.endText();

            //Inserir Preço unitário
            cs.beginText();
            cs.setFont(PDType1Font.HELVETICA, 8);
            cs.setLeading(20f);
            cs.newLineAtOffset(320, 520);
            for (int i = 0; i < n; i++) {
                cs.showText(precoUnitario.get(i).toString());
                cs.newLine();
            }
            cs.endText();

            //Inserir Desconto
            cs.beginText();
            cs.setFont(PDType1Font.HELVETICA, 8);
            cs.setLeading(20f);
            cs.newLineAtOffset(400, 520);
            for (int i = 0; i < n; i++) {
                cs.showText(desconto.get(i).toString());
                cs.newLine();
            }
            cs.endText();

            //Inserir Preço total
            cs.beginText();
            cs.setFont(PDType1Font.HELVETICA, 8);
            cs.setLeading(20f);
            cs.newLineAtOffset(465, 520);
            for (int i = 0; i < n; i++) {
                price = precoUnitario.get(i) * quantidade.get(i) ;
                System.out.println(price);
                precoFinal = price - price * (desconto.get(i) / 100);
                System.out.println(precoFinal);
                cs.showText(Double.toString(precoFinal));
                cs.newLine();
            }
            cs.endText();

            //Inserir IVA
            cs.beginText();
            cs.setFont(PDType1Font.HELVETICA, 8);
            cs.setLeading(20f);
            cs.newLineAtOffset(523, 520);
            for (int i = 0; i < n; i++) {
                cs.showText(iva.get(i).toString());
                cs.newLine();
            }
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.HELVETICA_BOLD, 10);
            cs.newLineAtOffset(330, (500 - (20 * n)));
            cs.showText("Total: ");
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.HELVETICA, 10);
            //Calculating where total is to be written using number of products
            cs.newLineAtOffset(430, (500 - (20 * n)));
            for (int i = 0; i < n; i++) {
                total += precoFinal + (precoFinal *  (iva.get(i) / 100));
            }
            cs.showText(Double.toString(Math.round(total)) + " €");
            cs.endText();

            //Close the content stream
            cs.close();
            //Save the PDF
            System.out.println(codFatura+".pdf");
            invc.save("C:\\Users\\Formação\\Desktop\\"+codFatura+".pdf");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
