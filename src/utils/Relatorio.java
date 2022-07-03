package utils;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.Item;
import model.ManagementItens;
import model.ManagementProducts;
import model.Product;
import model.Provider;
import model.Sale;

/** Classe responsavel por gerar os relatorios em PDF
 * 
 * @author Washington Luis Ferreira de Oliveira Junior
 * @author Tassio Carvalho Rodrigues
 *
 */
public class Relatorio {
	
	/**Procedimento para gerar o relatorio do estoque
	 * 
	 * @param lista	Lista de produtos no estoque
	 * @param qtd	Quantidade de produtos no estoque
	 * @throws Exception	Excecao em caso de falha ao gerar o pdf
	 */
	public static void relatorioEstoque(ArrayList<Product> lista, int qtd) throws Exception {
		Document documento = new Document();
		String nome = "RelatorioEstoque" + dataEHora() + ".pdf";
		Calendar c;
		Date data;
		DateFormat f=DateFormat.getDateInstance(DateFormat.FULL);
		
		try {
			//Iniciando arquivo
			PdfWriter.getInstance(documento, new FileOutputStream(nome));
            documento.open();
            Font bold = new Font(Font.FontFamily.UNDEFINED, 12, Font.BOLD);
            
			Paragraph pEstoque = new Paragraph("Relatorio Estoque",bold);
			pEstoque.setAlignment(1);
			documento.add(pEstoque);
			
			pEstoque = new Paragraph(" ");
			documento.add(pEstoque);
			
			pEstoque = new Paragraph("Quantidade total do estoque: " + qtd);
			documento.add(pEstoque);
			
			pEstoque = new Paragraph(" ");
			documento.add(pEstoque);
			//Dimensionando Tabela
			PdfPTable tabela = new PdfPTable(6);
			tabela.setTotalWidth(100f);
			tabela.setWidths(new float[] {22f,22f,22f,22f,22f,22f});
			//Atribuindo Nomes das Colunas
			PdfPCell celulaPDF1 = new PdfPCell(new Paragraph("Codigo",bold));
			celulaPDF1.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell celulaPDF2 = new PdfPCell(new Paragraph("Nome",bold));
			celulaPDF2.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell celulaPDF3 = new PdfPCell(new Paragraph("Quantidade",bold));
			celulaPDF3.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell celulaPDF4 = new PdfPCell(new Paragraph("Medida",bold));
			celulaPDF4.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell celulaPDF5 = new PdfPCell(new Paragraph("Validade",bold));
			celulaPDF5.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell celulaPDF6 = new PdfPCell(new Paragraph("Preco",bold));
			celulaPDF6.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			
			tabela.addCell(celulaPDF1);
			tabela.addCell(celulaPDF2);
			tabela.addCell(celulaPDF3);
			tabela.addCell(celulaPDF4);
			tabela.addCell(celulaPDF5);
			tabela.addCell(celulaPDF6);
			//Preenchendo as linhas
			for (Product produto : lista) {
				//c = produto.getValidity(LocalDate.now());
				//data = c.getTime();
				
				celulaPDF1 = new PdfPCell(new Paragraph(Integer.toString(produto.getId())));
				celulaPDF1.setHorizontalAlignment(Element.ALIGN_CENTER);
				celulaPDF2 = new PdfPCell(new Paragraph(produto.getName()));
				celulaPDF1.setHorizontalAlignment(Element.ALIGN_CENTER);
				celulaPDF3 = new PdfPCell(new Paragraph(Integer.toString(produto.getQtd())));
				celulaPDF1.setHorizontalAlignment(Element.ALIGN_CENTER);
				celulaPDF4 = new PdfPCell(new Paragraph(produto.getMedida()));
				celulaPDF1.setHorizontalAlignment(Element.ALIGN_CENTER);
				celulaPDF5 = new PdfPCell(new Paragraph(produto.getValidity().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG))));
				//celulaPDF5 = new PdfPCell(new Paragraph(f.format(data)));
				celulaPDF1.setHorizontalAlignment(Element.ALIGN_CENTER);
				celulaPDF6 = new PdfPCell(new Paragraph("R$ "+Float.toString(produto.getPrice())));
				celulaPDF1.setHorizontalAlignment(Element.ALIGN_CENTER);

				tabela.addCell(celulaPDF1);
				tabela.addCell(celulaPDF2);
				tabela.addCell(celulaPDF3);
				tabela.addCell(celulaPDF4);
				tabela.addCell(celulaPDF5);
				tabela.addCell(celulaPDF6);

			}
			
			documento.add(tabela);
			documento.close();
			Desktop.getDesktop().open(new File(nome));
		} catch (Exception e) {
				throw new Exception();
		}
	}
	
	
	
	/**Procedimento para gerar o relatorio das vendas
	 * 
	 * @param lista	Lista de vendas
	 * @param qtd	Quantidade total pratos vendidos
	 * @param priceTotal	Valor total proveniente de todas as vendas
	 * @throws Exception	Excecao em caso de falha ao gerar arquivo
	 */
	public static void relatorioVendas(ArrayList<Sale> lista, int qtd, float priceTotal) throws Exception {
		Document documento = new Document();
		String nome = "RelatorioVenda" + dataEHora() + ".pdf";
		ArrayList<Integer> pratosId;
		Item prato;
		Calendar c;
		Date data;
		DateFormat f=DateFormat.getDateInstance(DateFormat.FULL);
		
		try {
			//Iniciando arquivo
			PdfWriter.getInstance(documento, new FileOutputStream(nome));
            documento.open();
            Font bold = new Font(Font.FontFamily.UNDEFINED, 12, Font.BOLD);
            
			Paragraph pVenda = new Paragraph("Relatorio Venda",bold);
			pVenda.setAlignment(1);
			documento.add(pVenda);
			
			pVenda = new Paragraph(" ");
			documento.add(pVenda);
			
			// VER SOBRE A QUANTIDADE TOTAL DE PRATOS VENDIDOS
			pVenda = new Paragraph("Quantidade total de pratos vendidos: " + qtd);
			documento.add(pVenda);
			
			pVenda = new Paragraph("Valor total das vendas: " + priceTotal);
			documento.add(pVenda);
			
			pVenda = new Paragraph(" ");
			documento.add(pVenda);
			//Dimensionando Tabela
			PdfPTable tabela = new PdfPTable(5);
			tabela.setTotalWidth(100f);
			tabela.setWidths(new float[] {22f,22f,22f,22f,22f});
			//Atribuindo Nomes das Colunas
			PdfPCell celulaPDF1 = new PdfPCell(new Paragraph("Codigo",bold));
			celulaPDF1.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell celulaPDF2 = new PdfPCell(new Paragraph("Metodo de Pagamento",bold));
			celulaPDF2.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell celulaPDF3 = new PdfPCell(new Paragraph("Data",bold));
			celulaPDF3.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell celulaPDF4 = new PdfPCell(new Paragraph("Prato",bold));
			celulaPDF4.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell celulaPDF5 = new PdfPCell(new Paragraph("Preco",bold));
			celulaPDF5.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			
			tabela.addCell(celulaPDF1);
			tabela.addCell(celulaPDF2);
			tabela.addCell(celulaPDF3);
			tabela.addCell(celulaPDF4);
			tabela.addCell(celulaPDF5);
			//Preenchendo as linhas
			for (Sale venda : lista) {// percorre a lista de vendas
				pratosId = venda.getItens();//pega os ids dos pratos vendidos
				
				for(Integer id: pratosId) {//percorre a lista dos ids dos pratos vendidos
					prato = ManagementItens.getOne(id);//busca um prato com esse id na gestao de pratos
					//prato = null;
					if (prato != null) {
						celulaPDF1 = new PdfPCell(new Paragraph(Integer.toString(venda.getId())));//c�digo
						celulaPDF1.setHorizontalAlignment(Element.ALIGN_CENTER);
						celulaPDF2 = new PdfPCell(new Paragraph(venda.getPaymentMethod()));//pagamento
						celulaPDF1.setHorizontalAlignment(Element.ALIGN_CENTER);
						//celulaPDF3 = new PdfPCell(new Paragraph(f.format(data)));//data da venda
						celulaPDF1.setHorizontalAlignment(Element.ALIGN_CENTER);
						celulaPDF4 = new PdfPCell(new Paragraph(prato.getName()));//nome do prato
						celulaPDF1.setHorizontalAlignment(Element.ALIGN_CENTER);
						celulaPDF5 = new PdfPCell(new Paragraph(Float.toString(prato.getPrice())));//valor do prato
						celulaPDF1.setHorizontalAlignment(Element.ALIGN_CENTER);

						tabela.addCell(celulaPDF1);
						tabela.addCell(celulaPDF2);
						tabela.addCell(celulaPDF3);
						tabela.addCell(celulaPDF4);
						tabela.addCell(celulaPDF5);
					}
				}

			}
			
			documento.add(tabela);
			documento.close();
			Desktop.getDesktop().open(new File(nome));
		} catch (Exception e) {
				throw new Exception();
		}
	}
	
	
	/** Procedimento para gerar o relatorio de fornecedores
	 * 
	 * @param lista	Lista de fornecedores
	 * @param qtd	Quantidade de fornecedores
	 * @throws Exception	Excecao em caso de falha em gerar o arquivo
	 */
	public static void relatorioFornecedor(ArrayList<Provider> lista, int qtd) throws Exception {
		Document documento = new Document();
		String nome = "RelatorioFornecedor" + dataEHora() + ".pdf";
		ArrayList<Integer> produtosId;
		Product produto;
		try {
			//Iniciando arquivo
			PdfWriter.getInstance(documento, new FileOutputStream(nome));
            documento.open();
            Font bold = new Font(Font.FontFamily.UNDEFINED, 12, Font.BOLD);
            
			Paragraph pFornecedor = new Paragraph("Relatorio Fornecedor",bold);
			pFornecedor.setAlignment(1);
			documento.add(pFornecedor);
			
			pFornecedor = new Paragraph(" ");
			documento.add(pFornecedor);
			
			// VER SOBRE A QUANTIDADE TOTAL DE PRATOS VENDIDOS
			pFornecedor = new Paragraph("Quantidade total de fornecedores: " + qtd);
			documento.add(pFornecedor);

			
			pFornecedor = new Paragraph(" ");
			documento.add(pFornecedor);
			//Dimensionando Tabela
			PdfPTable tabela = new PdfPTable(5);
			tabela.setTotalWidth(100f);
			tabela.setWidths(new float[] {22f,22f,22f,22f,22f});
			//Atribuindo Nomes das Colunas
			PdfPCell celulaPDF1 = new PdfPCell(new Paragraph("Codigo",bold));
			celulaPDF1.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell celulaPDF2 = new PdfPCell(new Paragraph("Nome",bold));
			celulaPDF2.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell celulaPDF3 = new PdfPCell(new Paragraph("Cnpj",bold));
			celulaPDF3.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell celulaPDF4 = new PdfPCell(new Paragraph("Endereco",bold));
			celulaPDF4.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell celulaPDF5 = new PdfPCell(new Paragraph("Produto",bold));
			celulaPDF5.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			
			tabela.addCell(celulaPDF1);
			tabela.addCell(celulaPDF2);
			tabela.addCell(celulaPDF3);
			tabela.addCell(celulaPDF4);
			tabela.addCell(celulaPDF5);
			//Preenchendo as linhas
			for (Provider fornecedor : lista) {// percorre a lista de vendas
				
				produtosId = fornecedor.getProducts();//pega os ids dos pratos vendidos
				
				for(Integer id: produtosId) {//percorre a lista dos ids dos pratos vendidos
					produto = ManagementProducts.getOne(id);//busca um prato com esse id na gestao de pratos
					//prato = null;
					if (produto != null) {
						celulaPDF1 = new PdfPCell(new Paragraph(Integer.toString(fornecedor.getId())));//c�digo
						celulaPDF1.setHorizontalAlignment(Element.ALIGN_CENTER);
						celulaPDF2 = new PdfPCell(new Paragraph(fornecedor.getName()));//nome
						celulaPDF1.setHorizontalAlignment(Element.ALIGN_CENTER);
						celulaPDF3 = new PdfPCell(new Paragraph(fornecedor.getCnpj()));//cnpj
						celulaPDF1.setHorizontalAlignment(Element.ALIGN_CENTER);
						celulaPDF4 = new PdfPCell(new Paragraph(fornecedor.getAddress()));//endere�o
						celulaPDF1.setHorizontalAlignment(Element.ALIGN_CENTER);
						celulaPDF5 = new PdfPCell(new Paragraph( produto.getName()));//nome do produto
						celulaPDF1.setHorizontalAlignment(Element.ALIGN_CENTER);

						tabela.addCell(celulaPDF1);
						tabela.addCell(celulaPDF2);
						tabela.addCell(celulaPDF3);
						tabela.addCell(celulaPDF4);
						tabela.addCell(celulaPDF5);
					}
				}

			}
			
			documento.add(tabela);
			documento.close();
			Desktop.getDesktop().open(new File(nome));
		} catch (Exception e) {
				throw new Exception();
		}
	}
	
	
	
	/**Metodo para obter a string e por no nome do arquivo, garantindo que cada arquivo 
	 * vai ter nome unico
	 * 
	 * @return String - uma string no formato dd.MM.yyyy_hh.mm.ss
	 */
	public static String dataEHora() {
		Date d = Calendar.getInstance().getTime();
		String formatString = "dd.MM.yyyy_hh.mm.ss" ;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat (formatString);
		String formattedDate = simpleDateFormat.format(d) ;
		
		return formattedDate;
		
	}
}