package com.yahiab.graphix.model._3d.helpers;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.print.*;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by Yahia on 17/04/2015.
 */

public class SubScenePrintDialog extends Stage {

    private SimpleObjectProperty<Printer> printerProperty = new SimpleObjectProperty<>();
    private PageLayout pageLayout;

    public SubScenePrintDialog(boolean modality, String title, Node printNode) {
        super();
        setResizable(false);
        //initOwner(owner);
        Modality m = modality ? Modality.APPLICATION_MODAL : Modality.NONE;
        initModality(m);
        setTitle(title);
        Group root = new Group();
        Scene scene = new Scene(root, 350, 80, Color.WHITE);
        setScene(scene);

        GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(5));
        gridpane.setHgap(5);
        gridpane.setVgap(5);


        Label printerLabel = new Label("Imprimante : ");
        gridpane.add(printerLabel, 0, 1);

        ChoiceBox<Printer> printerChooser = new ChoiceBox(FXCollections.observableArrayList(Printer.getAllPrinters()));
        printerChooser.getSelectionModel().selectFirst();
        printerProperty.bind(printerChooser.getSelectionModel().selectedItemProperty());
        gridpane.add(printerChooser, 1, 1);

        Button printButton = new Button("Imprimer");
        printButton.setOnAction((ActionEvent event) -> {
            this.pageLayout = printerProperty.get().createPageLayout(
                    Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.HARDWARE_MINIMUM);
            print(printNode, printerProperty.get());
        });
        gridpane.add(printButton, 0, 2);
        GridPane.setHalignment(printButton, HPos.CENTER);
        GridPane.setColumnSpan(printButton, 2);
        root.getChildren().add(gridpane);
    }

    public void print(final Node node, Printer printer) {

        PrinterJob job = PrinterJob.createPrinterJob();
        job.setPrinter(printer);
        if (job != null) {
            boolean success = job.printPage(this.pageLayout, node);
            if (success) {
                job.endJob();
            }
        }
    }
}
