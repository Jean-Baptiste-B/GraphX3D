package com.yahiab.graphix.controller.pdf;

import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;
import com.sun.pdfview.PDFPrintPage;
import com.yahiab.graphix.view.SVGPathIcons;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.SVGPath;

import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ResourceBundle;

/**
 * Created by Yahia on 02/05/2015.
 */
public class PDFController implements Initializable {

    private ObjectProperty<PDFFile> currentFile;
    private ObjectProperty<ImageView> currentImage;

    @FXML
    private ScrollPane PDFScrollPane;
    @FXML
    private Button btnPrint;
    @FXML
    private Button btnCube;
    @FXML
    private Button btnBox;
    @FXML
    private Button btnCone;
    @FXML
    private Button btnCylinder;
    @FXML
    private Button btnPyramid;
    @FXML
    private Button btnPrism;
    @FXML
    private Button btnTetrahedron;
    @FXML
    private Button btnOctahedron;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        SVGPath SVGPrint = new SVGPath();
        SVGPrint.setContent(SVGPathIcons.print);
        btnPrint.setGraphic(SVGPrint);

        ImageView cube = new ImageView(new Image("/res/PNGs/cube.png"));
        cube.setFitHeight(110);
        cube.setFitWidth(110);
        btnCube.setGraphic(cube);

        ImageView box = new ImageView(new Image("/res/PNGs/box.png"));
        box.setFitHeight(110);
        box.setFitWidth(110);
        btnBox.setGraphic(box);

        ImageView cylinder = new ImageView(new Image("/res/PNGs/cylinder.png"));
        cylinder.setFitHeight(110);
        cylinder.setFitWidth(110);
        btnCylinder.setGraphic(cylinder);

        ImageView cone = new ImageView(new Image("/res/PNGs/cone.png"));
        cone.setFitHeight(110);
        cone.setFitWidth(110);
        btnCone.setGraphic(cone);

        ImageView pyramid = new ImageView(new Image("/res/PNGs/pyramid.png"));
        pyramid.setFitHeight(110);
        pyramid.setFitWidth(110);
        btnPyramid.setGraphic(pyramid);

        ImageView prism = new ImageView(new Image("/res/PNGs/prism.png"));
        prism.setFitHeight(110);
        prism.setFitWidth(110);
        btnPrism.setGraphic(prism);

        ImageView tetrahedron = new ImageView(new Image("/res/PNGs/tetrahedron.png"));
        tetrahedron.setFitHeight(110);
        tetrahedron.setFitWidth(110);
        btnTetrahedron.setGraphic(tetrahedron);

        ImageView octahedron = new ImageView(new Image("/res/PNGs/octahedron.png"));
        octahedron.setFitHeight(110);
        octahedron.setFitWidth(110);
        btnOctahedron.setGraphic(octahedron);

        currentFile = new SimpleObjectProperty<>();
        currentImage = new SimpleObjectProperty<>();
        currentFile.set(null);
        currentImage.set(null);
        PDFScrollPane.contentProperty().bind(currentImage);
        btnPrint.setDisable(true);
    }

    private void loadFile(String path) {
        if (path != null) {
            try {
                RandomAccessFile raf = new RandomAccessFile(path, "r");
                FileChannel channel = raf.getChannel();
                ByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
                PDFFile pdfFile = new PDFFile(buffer);
                currentFile.set(pdfFile);

                ///// updating the ImageView

                PDFPage page = currentFile.get().getPage(0);
                Rectangle2D bbox = page.getBBox();
                final double actualPageWidth = bbox.getWidth();
                final double actualPageHeight = bbox.getHeight();
                final int width = (int) (actualPageWidth);
                final int height = (int) (actualPageHeight);
                // retrieve image for page:
                // width, height, clip, imageObserver, paintBackground, waitUntilLoaded:
                java.awt.Image awtImage = page.getImage(width, height, bbox, null, true, true);
                // draw image to buffered image:
                BufferedImage buffImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                buffImage.createGraphics().drawImage(awtImage, 0, 0, null);
                // convert to JavaFX image:
                Image image = SwingFXUtils.toFXImage(buffImage, null);
                // wrap in image view and return:
                ImageView imageView = new ImageView(image);
                imageView.setPreserveRatio(true);
                currentImage.set(imageView);
                btnPrint.setDisable(false);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @FXML
    public void printPDF() {
        java.awt.print.PrinterJob pjob = java.awt.print.PrinterJob.getPrinterJob();
        pjob.setJobName("Impression PDF des formes prédéfinies à couper.");
        Book book = new Book();
        PDFPrintPage pages = new PDFPrintPage(this.currentFile.get());

        // landscape page orientation
        PageFormat landscapePageFormat = new PageFormat();
        landscapePageFormat.setOrientation(PageFormat.LANDSCAPE);

        book.append(pages, landscapePageFormat, this.currentFile.get().getNumPages());
        pjob.setPageable(book);
        if (pjob.printDialog()) {
            (new PrintThread(pages, pjob)).start();
        }
    }

    @FXML
    public void loadCubePDF() {
        loadFile("res/PDFs/cube.pdf");
    }

    @FXML
    public void loadConePDF() {
        loadFile("res/PDFs/cone.pdf");
    }

    @FXML
    public void loadBoxPDF() {
        loadFile("res/PDFs/box.pdf");
    }

    @FXML
    public void loadCylinderPDF() {
        loadFile("res/PDFs/cylinder.pdf");
    }

    @FXML
    public void loadDodecahedronPDF() {
        loadFile("res/PDFs/dodecahedron.pdf");
    }

    @FXML
    public void loadOctahedronPDF() {
        loadFile("res/PDFs/octahedron.pdf");
    }

    @FXML
    public void loadPrismPDF() {
        loadFile("res/PDFs/prism.pdf");
    }

    @FXML
    public void loadPyramidPDF() {
        loadFile("res/PDFs/pyramid.pdf");
    }

    @FXML
    public void loadTetrahedronPDF() {
        loadFile("res/PDFs/tetrahedron.pdf");
    }

    class PrintThread extends Thread {
        PDFPrintPage ptPages;
        java.awt.print.PrinterJob ptPjob;

        public PrintThread(PDFPrintPage pages, java.awt.print.PrinterJob pjob) {
            this.ptPages = pages;
            this.ptPjob = pjob;
            this.setName(this.getClass().getName());
        }

        public void run() {
            try {
                this.ptPages.show(this.ptPjob);
                this.ptPjob.print();
            } catch (PrinterException var2) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Erreur d'impression.");
                alert.setContentText("Impossible d'imprimer le fichier PDF.");
                alert.showAndWait();
            }

            this.ptPages.hide();
        }
    }
}
