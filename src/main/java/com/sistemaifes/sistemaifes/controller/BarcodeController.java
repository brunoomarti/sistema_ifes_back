package com.sistemaifes.sistemaifes.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.print.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class BarcodeController {

    private static final Logger logger = LoggerFactory.getLogger(BarcodeController.class);

    @PostMapping("/print-barcode")
    public ResponseEntity<byte[]> printBarcode(@RequestBody Map<String, String> request) {
        String matricula = request.get("matricula");
        logger.debug("Received request to print barcode for matricula: " + matricula);
        
        try {
            // Define the desired width and height of the image directly
            int newWidth = 85; // Adjust as needed
            int newHeight = 35; // Adjust as needed
            int dpi = 1200; // Increase resolution to improve quality

            // Calculate the size in pixels
            int widthInPixels = (int) (newWidth * dpi / 25.4); // 1 inch = 25.4 mm
            int heightInPixels = (int) (newHeight * dpi / 25.4);

            BitMatrix bitMatrix = new MultiFormatWriter().encode(matricula, BarcodeFormat.CODE_39, widthInPixels, heightInPixels);
            MatrixToImageConfig config = new MatrixToImageConfig(BufferedImage.TYPE_BYTE_BINARY, 0xFFFFFFFF);

            BufferedImage barcodeImage = MatrixToImageWriter.toBufferedImage(bitMatrix, config);

            BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_BYTE_BINARY);
            Graphics2D g2d = resizedImage.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.drawImage(barcodeImage, 0, 0, newWidth, newHeight, null);
            g2d.dispose();

            // Add the matricula text below the barcode
            BufferedImage combinedImage = new BufferedImage(newWidth, newHeight + 20, BufferedImage.TYPE_BYTE_BINARY);
            Graphics2D combinedG2d = combinedImage.createGraphics();
            combinedG2d.drawImage(resizedImage, 0, 0, null);
            combinedG2d.setFont(new Font("Arial", Font.PLAIN, 12));
            FontMetrics fontMetrics = combinedG2d.getFontMetrics();
            int textWidth = fontMetrics.stringWidth(matricula);
            int x = (newWidth - textWidth) / 2;
            int y = newHeight + fontMetrics.getHeight();
            combinedG2d.drawString(matricula, x, y);
            combinedG2d.dispose();

            ByteArrayOutputStream baosResized = new ByteArrayOutputStream();
            ImageIO.write(combinedImage, "png", baosResized);
            byte[] imageBytes = baosResized.toByteArray();

            // Save the image to verify it is being generated correctly
            File outputfile = new File("barcode.png");
            ImageIO.write(combinedImage, "png", outputfile);
            logger.debug("Barcode image saved to: " + outputfile.getAbsolutePath());

            // Send image to printer
            if (printImage(combinedImage)) {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_PNG);
                logger.debug("Barcode image successfully generated and sent to printer.");
                return ResponseEntity.ok().headers(headers).body(imageBytes);
            } else {
                logger.error("Failed to find the printer 'Tally Dascom DL-20Z'.");
                return ResponseEntity.status(500).body(null);
            }

        } catch (Exception e) {
            logger.error("Error generating or printing barcode: ", e);
            return ResponseEntity.status(500).build();
        }
    }

    public boolean printImage(BufferedImage image) throws IOException, PrinterException {
        PrinterJob printJob = PrinterJob.getPrinterJob();
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);

        for (PrintService printer : printServices) {
            logger.debug("Found printer: " + printer.getName());
            if (printer.getName().equalsIgnoreCase("Tally Dascom DL-200Z")) {
                printJob.setPrintService(printer);

                // Define custom paper size
                Paper paper = new Paper();
                double paperWidth = 105 * 2.83465; // 105mm in points (1 inch = 72 points, 1 mm = 2.83465 points)
                double paperHeight = 22 * 2.83465; // 22mm in points
                paper.setSize(paperWidth, paperHeight);
                paper.setImageableArea(0, 0, paperWidth, paperHeight);

                PageFormat pageFormat = printJob.defaultPage();
                pageFormat.setPaper(paper);

                printJob.setPrintable((graphics, pageFormat1, pageIndex) -> {
                    if (pageIndex != 0) {
                        return Printable.NO_SUCH_PAGE;
                    }
                    Graphics2D g2d = (Graphics2D) graphics;
                    g2d.translate((int) pageFormat1.getImageableX(), (int) pageFormat1.getImageableY());

                    // Center the image on the paper
                    int x = (int) (pageFormat1.getImageableWidth() - image.getWidth()) / 2;
                    int y = (int) (pageFormat1.getImageableHeight() - image.getHeight()) / 2;
                    g2d.drawImage(image, x, y, image.getWidth(), image.getHeight(), null);
                    return Printable.PAGE_EXISTS;
                }, pageFormat);

                printJob.print();
                return true;
            }
        }
        return false;
    }
}
