package ru.clevertec.utils.checkCreator;


import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;
import ru.clevertec.entity.Cart;
import ru.clevertec.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class CreateRecipePdf {
    private static final Logger logger = LoggerFactory.getLogger(CreateRecipePdf.class);
    public void createCashRecipe(Cart cart) throws IOException {

        String path = DirToSaveCheck.directoryCreateFolder();

        PdfReader pdfReader = new PdfReader(String.valueOf(ClassLoader.getSystemResource("Clevertec_Template.pdf")));

        PdfWriter pdfWriter = new PdfWriter(path);
        PdfDocument pdfDocument = new PdfDocument(pdfReader,pdfWriter);
        Document document = new Document(pdfDocument);
        pdfDocument.setDefaultPageSize(PageSize.A4);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH.mm");

        float col = 280f;
        float[] columnWidth = {col,col};
        Table table = new Table(columnWidth);

        table.setBackgroundColor(new DeviceRgb(176,170,230))
                        .setFontColor(new DeviceRgb(255,255,255)).setMarginTop(80);
        //row coll 0
        table.addCell("CASH RECIPE");
        table.getCell(0,0)
                .setTextAlignment(TextAlignment.CENTER)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setMarginTop(30f)
                .setMarginBottom(30f)
                .setFontSize(30f)
                .setBorder(Border.NO_BORDER);
        //row 0 coll 1
        table.addCell("BlackHills Angels co\n#6875 Hokkaido\n9875254136");
        table.getCell(0,1)
                .setMarginTop(30f)
                .setTextAlignment(TextAlignment.RIGHT)
                .setMarginTop(30f)
                .setBorder(Border.NO_BORDER)
                .setMarginRight(10f)
                .setFontSize(10f);

        float[] colWidth = {80, 300, 100, 80};
        Table cust = new Table(new float[]{560f});
        cust.addCell("Customer Information")
                .setBold()
                .setBorder(Border.NO_BORDER);
        cust.getCell(0,0).setBorder(Border.NO_BORDER);

        Table customerInfoTable = new Table(colWidth);
        //row 0 col 0
        customerInfoTable.addCell("Name");
        customerInfoTable.getCell(0,0).setBorder(Border.NO_BORDER);

        //row 0 col 1
        customerInfoTable.addCell("Garry Ford");
        customerInfoTable.getCell(0,1).setBorder(Border.NO_BORDER);

        //row 0 col 2
        customerInfoTable.addCell("Recipe No.");
        customerInfoTable.getCell(0,2).setBorder(Border.NO_BORDER);

        //row 0 col 3
        customerInfoTable.addCell("56123");
        customerInfoTable.getCell(0,3).setBorder(Border.NO_BORDER);

        //row 1 col 0
        customerInfoTable.addCell("Pers. ID.");
        customerInfoTable.getCell(1,0).setBorder(Border.NO_BORDER);

        //row 1 col 1
        customerInfoTable.addCell("756423364");
        customerInfoTable.getCell(1,1).setBorder(Border.NO_BORDER);

        //row 1 col 2
        customerInfoTable.addCell("Date:");
        customerInfoTable.getCell(1,2).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);

        //row 1 col 3
        customerInfoTable.addCell(dtf.format(LocalDateTime.now()));
        customerInfoTable.getCell(1,3).setBorder(Border.NO_BORDER);

        float[] timeTableWidth ={380,100,80};
        Table timeTable = new Table(timeTableWidth);
        timeTable.addCell("");
        timeTable.getCell(0,0)
                        .setBorder(Border.NO_BORDER);
        timeTable.addCell("Time:");
        timeTable.getCell(0,1)
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.LEFT);
        timeTable.addCell(tf.format(LocalDateTime.now()));
        timeTable.getCell(0,2)
                .setBorder(Border.NO_BORDER);

        float[] itemInfoColumWidth = {20,440,50,50};
        Table itemInfoTable = new Table(itemInfoColumWidth);
        //row 0 col 0
        itemInfoTable.addCell("QTY");
        itemInfoTable.getCell(0,0)
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.CENTER)
                .setBold();
        //row 0 col 1
        itemInfoTable.addCell("DESCRIPTION");
        itemInfoTable.getCell(0,1)
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.LEFT)
                .setBold();
        //row 0 col 2
        itemInfoTable.addCell("PRICE");
        itemInfoTable.getCell(0,2)
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.RIGHT)
                .setBold();
        //row 0 col 3
        itemInfoTable.addCell("TOTAL");
        itemInfoTable.getCell(0,3)
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.RIGHT)
                .setBold();






        //вывод данных из корзины в чек
        float[] itemsAndPricesWidth = {30,430,50,50};
        Table itemsAndPricesTable = new Table(itemsAndPricesWidth);

        Map<Product,Integer> map = new Converter<Product,Integer>().getMapFromList(cart.getListProduct());

        map.forEach((key,value) -> {
            itemsAndPricesTable.addCell(new Cell().add(new Paragraph(String.valueOf(value)))
                    .setTextAlignment(TextAlignment.CENTER))
                    .setFontSize(8f)
                    .setBorder(Border.NO_BORDER);
            itemsAndPricesTable.addCell(new Cell().add(new Paragraph(key.getName()))
                    .setTextAlignment(TextAlignment.LEFT))
                    .setFontSize(8f)
                    .setBorder(Border.NO_BORDER);
            itemsAndPricesTable.addCell(new Cell().add(new Paragraph("$"+(key.getPrice())))
                    .setTextAlignment(TextAlignment.RIGHT))
                    .setFontSize(8f)
                    .setBorder(Border.NO_BORDER);
            itemsAndPricesTable.addCell(new Cell().add(new Paragraph("$"+(value*key.getPrice())))
                    .setTextAlignment(TextAlignment.RIGHT))
                    .setFontSize(8f)
                    .setBorder(Border.NO_BORDER);
        });

        float[] totalAmountTableWidth ={280,280};
        Table totalAmountTable = new Table(totalAmountTableWidth);

        totalAmountTable.addCell(new Cell().add(new Paragraph("Full PRICE"))
                .setTextAlignment(TextAlignment.LEFT)
                .setFontSize(8f)
                .setBorder(Border.NO_BORDER));

        totalAmountTable.addCell(new Cell().add(new Paragraph(String.valueOf(cart.getAmountWithoutDis())))
                .setTextAlignment(TextAlignment.RIGHT)
                .setFontSize(8f)
                .setBorder(Border.NO_BORDER));

        totalAmountTable.addCell(new Cell().add(new Paragraph("%10 Promo"))
                .setTextAlignment(TextAlignment.LEFT)
                .setFontSize(8f)
                .setBorder(Border.NO_BORDER));

        totalAmountTable.addCell(new Cell().add(new Paragraph("-$"+cart.getPromoDisc()))
                .setTextAlignment(TextAlignment.RIGHT)
                .setFontSize(8f)
                .setBorder(Border.NO_BORDER));


        if (cart.getDiscountCard()!= null){
            totalAmountTable.addCell(new Cell().add(new Paragraph("% DISC = "+cart.getDiscountCard().getPercentDiscount()))
                    .setTextAlignment(TextAlignment.LEFT)
                    .setFontSize(8f)
                    .setBorder(Border.NO_BORDER));
            totalAmountTable.addCell(new Cell().add(new Paragraph("-$"+cart.getCardDisc()))
                    .setTextAlignment(TextAlignment.RIGHT)
                    .setFontSize(8f)
                    .setBorder(Border.NO_BORDER));
        }else {
            totalAmountTable.addCell(new Cell().add(new Paragraph("% DISC = without DiscountCard"))
                    .setTextAlignment(TextAlignment.LEFT)
                    .setFontSize(8f)
                    .setBorder(Border.NO_BORDER));
            totalAmountTable.addCell(new Cell().add(new Paragraph("-$0.00"))
                    .setTextAlignment(TextAlignment.RIGHT)
                    .setFontSize(8f)
                    .setBorder(Border.NO_BORDER));
        }

        totalAmountTable.addCell(new Cell().add(new Paragraph("TOTAL PRICE"))
                .setTextAlignment(TextAlignment.LEFT)
                .setBold()
                .setFontSize(15f)
                .setBorder(Border.NO_BORDER)
                .setBackgroundColor(new DeviceRgb(176,170,230)));

        totalAmountTable.addCell(new Cell().add(new Paragraph("$"+cart.getAmountWithDis()))
                .setTextAlignment(TextAlignment.RIGHT)
                .setBold()
                .setFontSize(15f)
                .setBorder(Border.NO_BORDER)
                .setBackgroundColor(new DeviceRgb(176,170,230)));






        document.add(table);
        document.add(new Paragraph("\n"));
        document.add(cust);
        document.add(customerInfoTable);
        document.add(timeTable);
        document.add(new Paragraph("------------------------------------" +
                "------------------------------------------------------------"));
        document.add(itemInfoTable);
        document.add(itemsAndPricesTable);
        document.add(new Paragraph("------------------------------------" +
                "------------------------------------------------------------"));
        document.add(totalAmountTable);
        document.close();
        logger.info("Cash recipe created in  "+path);

    }
}
