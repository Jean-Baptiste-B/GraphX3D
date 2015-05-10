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

public class YahiaPrintDialog extends Stage {

    private SimpleObjectProperty<Printer> printerProperty = new SimpleObjectProperty<>();
    private SimpleObjectProperty<PageOrientation> orientationProperty = new SimpleObjectProperty<>();
    private SimpleObjectProperty<Paper> paperProperty = new SimpleObjectProperty<>();
    private SimpleObjectProperty<Printer.MarginType> marginProperty = new SimpleObjectProperty<>();
    private PageLayout pageLayout;

    //public YahiaPrintDialog(Stage owner, boolean modality, String title, Node printNode) {
    public YahiaPrintDialog(boolean modality, String title, Node printNode) {
        super();
        //initOwner(owner);
        Modality m = modality ? Modality.APPLICATION_MODAL : Modality.NONE;
        initModality(m);
        setTitle(title);
        Group root = new Group();
        Scene scene = new Scene(root, 450, 250, Color.WHITE);
        setScene(scene);

        GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(5));
        gridpane.setHgap(5);
        gridpane.setVgap(5);


        Label printerLabel = new Label("Imprimante : ");
        gridpane.add(printerLabel, 0, 1);

        Label layoutLabel = new Label("Orientation : ");
        gridpane.add(layoutLabel, 0, 2);

        Label paperLabel = new Label("Papier : ");
        gridpane.add(paperLabel, 0, 3);

        Label marginLabel = new Label("Marge : ");
        gridpane.add(marginLabel, 0, 4);

        ChoiceBox<Printer> printerChooser = new ChoiceBox(FXCollections.observableArrayList(Printer.getAllPrinters()));
        printerChooser.getSelectionModel().selectFirst();
        printerProperty.bind(printerChooser.getSelectionModel().selectedItemProperty());
        gridpane.add(printerChooser, 1, 1);

        ChoiceBox<PageOrientation> orientationChooser = new ChoiceBox(FXCollections.observableArrayList(
                PageOrientation.LANDSCAPE, PageOrientation.PORTRAIT
        ));
        orientationChooser.getSelectionModel().selectFirst();
        orientationProperty.bind(orientationChooser.getSelectionModel().selectedItemProperty());
        gridpane.add(orientationChooser, 1, 2);

        ChoiceBox<Paper> paperChooser = new ChoiceBox(FXCollections.observableArrayList(Paper.A4, Paper.A3, Paper.A5));
        paperChooser.getSelectionModel().selectFirst();
        paperProperty.bind(paperChooser.getSelectionModel().selectedItemProperty());
        gridpane.add(paperChooser, 1, 3);

        ChoiceBox<Printer.MarginType> margineChooser = new ChoiceBox(FXCollections.observableArrayList(Printer.MarginType.values()));
        margineChooser.getSelectionModel().selectFirst();
        marginProperty.bind(margineChooser.getSelectionModel().selectedItemProperty());
        gridpane.add(margineChooser, 1, 4);

        Button printButton = new Button("Print");
        printButton.setOnAction((ActionEvent event) -> {
            this.pageLayout = printerProperty.get().createPageLayout(paperProperty.get(), orientationProperty.get(), marginProperty.get());
            print(printNode, printerProperty.get());
        });
        gridpane.add(printButton, 0, 5);

        GridPane.setHalignment(printButton, HPos.CENTER);
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
