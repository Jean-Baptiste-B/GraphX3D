package com.yahiab.graphix.controller._3d;

import com.yahiab.graphix.GraphiXX;
import com.yahiab.graphix.controller._3d.controls.numberspinner.NumberSpinner;
import com.yahiab.graphix.controller._3d.controls.numberspinner.NumberSpinnerLight;
import com.yahiab.graphix.model._3d.ContentModel;
import com.yahiab.graphix.model._3d.exporters.GraphixFXMLExporter;
import com.yahiab.graphix.model._3d.helpers.SelectedAxe;
import com.yahiab.graphix.model._3d.helpers.SubScenePrintDialog;
import com.yahiab.graphix.model._3d.helpers.SubSceneResizer;
import com.yahiab.graphix.model._3d.importers.Importer3D;
import com.yahiab.graphix.model._3d.importers.Optimizer;
import com.yahiab.graphix.view.SVGPathIcons;
import eu.mihosoft.vrl.v3d.CSG;
import eu.mihosoft.vrl.v3d.PropertyStorage;
import eu.mihosoft.vrl.v3d.Vector3d;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableFloatArray;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTreeTableCell;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.PickResult;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Transform;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;
import javafx.util.StringConverter;
import org.fxyz.shapes.primitives.*;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Controller class for main fxml file.
 */
public class Main3DController implements Initializable {

    private static ContentModel contentModel = new ContentModel();
    private ObjectProperty<Group> CSGselectedShapesList = new SimpleObjectProperty<>(new Group());

    @FXML
    private Label statusNumberOfNodes;
    @FXML
    private Label statusSelectedShape;
    @FXML
    private SplitPane splitPane;
    @FXML
    private SplitPane outerSplitPane;
    @FXML
    private Accordion settings;
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private CheckBox showAxisCheckBox;
    @FXML
    private Slider fovSlider;
    @FXML
    private ColorPicker backgroundColorPicker;
    @FXML
    private TreeTableView<Node> hierarachyTreeTable;
    @FXML
    private TreeTableColumn<Node, String> nodeColumn;
    @FXML
    private TreeTableColumn<Node, String> idColumn;
    @FXML
    private TreeTableColumn<Node, Boolean> visibilityColumn;
    @FXML
    private TreeTableColumn<Node, Double> widthColumn;
    @FXML
    private TreeTableColumn<Node, Double> heightColumn;
    @FXML
    private TreeTableColumn<Node, Double> depthColumn;
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private GridPane selectedShapeGridPane;
    @FXML
    private TextField selectedShapeTypeLabel;
    @FXML
    private ColorPicker selectedShapeColorPicker;
    @FXML
    private Label selectedShapeD1Label;
    @FXML
    private Label selectedShapeD2Label;
    @FXML
    private Label selectedShapeD3Label;
    @FXML
    private Label selectedShapeD4Label;
    @FXML
    private NumberSpinner selectedShapeD1Field;
    @FXML
    private NumberSpinner selectedShapeD2Field;
    @FXML
    private NumberSpinner selectedShapeD3Field;
    @FXML
    private NumberSpinner selectedShapeD4Field;
    @FXML
    private NumberSpinnerLight selectedShapeCoordX;
    @FXML
    private NumberSpinnerLight selectedShapeCoordY;
    @FXML
    private NumberSpinnerLight selectedShapeCoordZ;
    @FXML
    private NumberSpinner selectedShapeTranslateX;
    @FXML
    private NumberSpinner selectedShapeTranslateY;
    @FXML
    private NumberSpinner selectedShapeTranslateZ;
    @FXML
    private NumberSpinner selectedShapeRotateX;
    @FXML
    private NumberSpinner selectedShapeRotateY;
    @FXML
    private NumberSpinner selectedShapeRotateZ;
    @FXML
    private NumberSpinner selectedShapeScaleX;
    @FXML
    private NumberSpinner selectedShapeScaleY;
    @FXML
    private NumberSpinner selectedShapeScaleZ;
    @FXML
    private TextField selectedShapeID;
    @FXML
    private Button btnOpenMenu;
    @FXML
    private ToggleButton btnSettings;
    @FXML
    private Button btnPrint;
    @FXML
    private MenuButton btnSave;
    @FXML
    private Button btn3DPrint;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnDeleteAll;
    @FXML
    private CheckBox creationParameters;
    //////////////////////////////////////////// NEW SHAPE DIALOG PANE //////////////////////////////////////////////
    private Parent newShapeDialogPane;
    private Scene newShapeScene;
    private NewShapeController newShapeController;
    private Stage newShapeStage = new Stage();
    ////////////////////////////////////////// CSG Boolean Operation /////////////////////////////////////////////////
    @FXML
    private TreeTableView<Node> CSGModeTreeTable;
    @FXML
    private TreeTableColumn<Node, String> CSGShapeColumn;
    @FXML
    private TreeTableColumn<Node, Boolean> CSGVisibilityColumn;
    @FXML
    private TreeTableColumn<Node, String> CSGShapeID;
    @FXML
    private CheckBox CSGModeCheckBox;
    @FXML
    private Button CSGUnionButton;
    @FXML
    private Button CSGIntersectionButton;
    @FXML
    private Button CSGDifferenceButton;
    ////////////////////////////////////////// BOOLEAN OPERATION DIALOG PANE ////////////////////////////////////////
    private Parent booleanOperationResultDialogPane;
    private Scene booleanOperationResultScene;
    private BooleanOperationController booleanOperationResultController;
    private Stage booleanOperationStage = new Stage();
    @FXML
    private Button btnCube;
    @FXML
    private Button btnBox;
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private Button btnSphere;
    @FXML
    private Button btnCylinder;
    @FXML
    private Button btnCone;
    @FXML
    private Button btnPyramid;
    @FXML
    private Button btnPrism;
    @FXML
    private Button btnTrapezoid;
    @FXML
    private Button btnCapsule;
    @FXML
    private Button btnTorus;
    @FXML
    private Button btnSpheroid;
    @FXML
    private Button btnSpring;
    @FXML
    private Button btnTetrahedron;
    @FXML
    private Button btnOctahedron;

    /////////////////////////// LIGTHING CONTROLS ////////////////////////////////////////

    @FXML
    private ColorPicker ambientColorPicker;
    @FXML
    private CheckBox ambientEnableCheckbox;
    @FXML
    private ColorPicker light1ColorPicker;
    @FXML
    private CheckBox light1EnabledCheckBox;
    @FXML
    private CheckBox light1followCameraCheckBox;
    @FXML
    private Slider light1x;
    @FXML
    private Slider light1y;
    @FXML
    private Slider light1z;
    @FXML
    private CheckBox light2EnabledCheckBox;
    @FXML
    private Slider light2x;
    @FXML
    private Slider light2y;
    @FXML
    private Slider light2z;
    @FXML
    private CheckBox light3EnabledCheckBox;
    @FXML
    private Slider light3x;
    @FXML
    private Slider light3y;
    @FXML
    private Slider light3z;
    @FXML
    private ColorPicker light2ColorPicker;
    @FXML
    private ColorPicker light3ColorPicker;

    ///////////////////////////////////////////////////////////////////////////////////////
    private double settingsLastWidth = -1;
    private SimpleObjectProperty<Shape3D> selectedShapeProperty = contentModel.getSelectedShapePropertyProperty();
    ////// NEEDED FOR DRAG EVENT HANDLER
    private double mousePosX;
    private double mousePosY;
    private Point3D vecIni, vecPos;
    private double distance;
    private final EventHandler<javafx.scene.input.MouseEvent> selectedShapeEventHandler = event -> {
        if (contentModel.getSelectedAxe() != SelectedAxe.None) {
            if (event.getEventType() == javafx.scene.input.MouseEvent.MOUSE_PRESSED) {
                mousePosX = event.getSceneX();
                mousePosY = event.getSceneY();
                distance = event.getPickResult().getIntersectedDistance();
                vecIni = unProjectDirection(mousePosX, mousePosY, contentModel.getSubScene().getWidth(),
                        contentModel.getSubScene().getHeight());
            }
            if (event.getEventType() == javafx.scene.input.MouseEvent.DRAG_DETECTED) {
                mousePosX = event.getSceneX();
                mousePosY = event.getSceneY();
                vecPos = unProjectDirection(mousePosX, mousePosY, contentModel.getSubScene().getWidth(),
                        contentModel.getSubScene().getHeight());
                Point3D point = vecPos.subtract(vecIni).multiply(distance);
                switch (contentModel.getSelectedAxe()) {
                    case X_Axis:
                        selectedShapeProperty.get().setTranslateX(selectedShapeProperty.get().getTranslateX() + point.getX());
                        break;
                    case Y_Axis:
                        selectedShapeProperty.get().setTranslateY(selectedShapeProperty.get().getTranslateY() + point.getY());
                        break;
                    case Z_Axis:
                        selectedShapeProperty.get().setTranslateZ(selectedShapeProperty.get().getTranslateZ() + point.getZ());
                        break;
                }
                vecIni = vecPos;
                distance = event.getPickResult().getIntersectedDistance();
            }
        }
    };

    {
        newShapeStage.setTitle("Cr\u00e9ation param\u00e8tr\u00e9e d'une nouvelle forme 3D");
        newShapeStage.setResizable(false);
    }

    {
        booleanOperationStage.setTitle("Op\u00e9rations bool\u00e9ennes entre les formes 3D");
        booleanOperationStage.setResizable(false);
    }

    public static ContentModel getContentModel() {
        return contentModel;
    }

    @Override public void initialize(URL location, ResourceBundle resources) {
        try {

            /// Setting minimum and maximum values on all NumberSpinners
            selectedShapeRotateX.setMinValue(-360);
            selectedShapeRotateX.setMaxValue(+360);
            selectedShapeRotateY.setMinValue(-360);
            selectedShapeRotateY.setMaxValue(+360);
            selectedShapeRotateZ.setMinValue(-360);
            selectedShapeRotateZ.setMaxValue(+360);

            selectedShapeScaleX.setMinValue(0);
            selectedShapeScaleY.setMinValue(0);
            selectedShapeScaleZ.setMinValue(0);

            selectedShapeTranslateX.setMinValue(-10000);
            selectedShapeTranslateX.setMaxValue(+10000);
            selectedShapeTranslateY.setMinValue(-10000);
            selectedShapeTranslateY.setMaxValue(+10000);
            selectedShapeTranslateZ.setMinValue(-10000);
            selectedShapeTranslateZ.setMaxValue(+10000);
            selectedShapeTranslateX.setValue(0);
            selectedShapeTranslateY.setValue(0);
            selectedShapeTranslateZ.setValue(0);

            selectedShapeCoordX.setMinValue(-10000);
            selectedShapeCoordX.setMaxValue(+10000);
            selectedShapeCoordY.setMinValue(-10000);
            selectedShapeCoordY.setMaxValue(+10000);
            selectedShapeCoordZ.setMinValue(-10000);
            selectedShapeCoordZ.setMaxValue(+10000);

            selectedShapeD1Field.setMinValue(0);
            selectedShapeD2Field.setMinValue(0);
            selectedShapeD3Field.setMinValue(0);
            selectedShapeD4Field.setMinValue(0);

            // load the zoom slider from FXML and create the subscene
            Parent navigationPanel = FXMLLoader.load(GraphiXX.class.getResource("view/_3d/navigation.fxml"));
            splitPane.getItems().add(0, new SubSceneResizer(contentModel.subSceneProperty(), navigationPanel));
            splitPane.getItems().remove(1);
            splitPane.getDividers().get(0).setPosition(0.75);
            outerSplitPane.getDividers().get(0).setPosition(0.2);

            // load new shape dialog pane FXML
            FXMLLoader newShapeLoader = new FXMLLoader(GraphiXX.class.getResource("view/_3d/new_shape_view.fxml"));
            newShapeDialogPane = newShapeLoader.load();
            newShapeController = newShapeLoader.<NewShapeController>getController();
            newShapeController.setParentController(this);
            newShapeScene = new Scene(newShapeDialogPane, 700, 400, true, SceneAntialiasing.BALANCED);
            newShapeStage.setScene(newShapeScene);
            newShapeController.setNewShapeStage(newShapeStage);

            // load boolean operation result dialog pane FXML
            FXMLLoader booleanOperationResultLoader = new FXMLLoader(GraphiXX.class.getResource("view/_3d/BooleanOperationView.fxml"));
            booleanOperationResultDialogPane = booleanOperationResultLoader.load();
            booleanOperationResultController = booleanOperationResultLoader.getController();
            booleanOperationResultController.setParentController(this);
            booleanOperationResultScene = new Scene(booleanOperationResultDialogPane, 700, 400, true, SceneAntialiasing.BALANCED);
            booleanOperationStage.setScene(booleanOperationResultScene);
            booleanOperationResultController.setBooleanOperationStage(booleanOperationStage);

            ///// Initializing buttons with SVG data

            SVGPath SVGPathOpen = new SVGPath();
            SVGPathOpen.setContent(SVGPathIcons.openFolder);
            SVGPathOpen.setScaleX(0.7);
            SVGPathOpen.setScaleY(0.7);
            SVGPathOpen.setScaleZ(0.7);
            SVGPathOpen.setFill(Paint.valueOf("#ffffff"));
            btnOpenMenu.setGraphic(SVGPathOpen);

            SVGPath rocket = new SVGPath();
            rocket.setContent(SVGPathIcons.rocket);
            rocket.setScaleX(0.7);
            rocket.setScaleY(0.7);
            rocket.setScaleZ(0.7);
            rocket.setFill(Paint.valueOf("#ffffff"));
            btn3DPrint.setGraphic(rocket);

            SVGPath print = new SVGPath();
            print.setContent(SVGPathIcons.print);
            print.setScaleX(0.7);
            print.setScaleY(0.7);
            print.setScaleZ(0.7);
            print.setFill(Paint.valueOf("#ffffff"));
            btnPrint.setGraphic(print);

            SVGPath floppy = new SVGPath();
            floppy.setContent(SVGPathIcons.floppydisk);
            floppy.setScaleX(0.7);
            floppy.setScaleY(0.7);
            floppy.setScaleZ(0.7);
            floppy.setFill(Paint.valueOf("#ffffff"));
            btnSave.setGraphic(floppy);

            SVGPath cross = new SVGPath();
            cross.setContent(SVGPathIcons.cross);
            cross.setScaleX(0.7);
            cross.setScaleY(0.7);
            cross.setScaleZ(0.7);
            cross.setFill(Paint.valueOf("#ffff"));
            btnDelete.setGraphic(cross);

            SVGPath bin = new SVGPath();
            bin.setContent(SVGPathIcons.bin);
            bin.setScaleX(0.7);
            bin.setScaleY(0.7);
            bin.setScaleZ(0.7);
            bin.setFill(Paint.valueOf("#ffffff"));
            btnDeleteAll.setGraphic(bin);

            SVGPath cog = new SVGPath();
            cog.setContent(SVGPathIcons.cog);
            cog.setScaleX(0.7);
            cog.setScaleY(0.7);
            cog.setScaleZ(0.7);
            cog.setFill(Paint.valueOf("#ffffff"));
            btnSettings.setGraphic(cog);

            ImageView cube = new ImageView(new Image("res/PNGs/cube.png"));
            cube.setFitHeight(75);
            cube.setFitWidth(75);
            btnCube.setGraphic(cube);

            ImageView box = new ImageView(new Image("/res/PNGs/box.png"));
            box.setFitHeight(75);
            box.setFitWidth(75);
            btnBox.setGraphic(box);

            ImageView cylinder = new ImageView(new Image("/res/PNGs/cylinder.png"));
            cylinder.setFitHeight(75);
            cylinder.setFitWidth(75);
            btnCylinder.setGraphic(cylinder);

            ImageView cone = new ImageView(new Image("/res/PNGs/cone.png"));
            cone.setFitHeight(75);
            cone.setFitWidth(75);
            btnCone.setGraphic(cone);

            ImageView pyramid = new ImageView(new Image("/res/PNGs/pyramid.png"));
            pyramid.setFitHeight(75);
            pyramid.setFitWidth(75);
            btnPyramid.setGraphic(pyramid);

            ImageView prism = new ImageView(new Image("/res/PNGs/prism.png"));
            prism.setFitHeight(75);
            prism.setFitWidth(75);
            btnPrism.setGraphic(prism);

            ImageView tetrahedron = new ImageView(new Image("/res/PNGs/tetrahedron.png"));
            tetrahedron.setFitHeight(75);
            tetrahedron.setFitWidth(75);
            btnTetrahedron.setGraphic(tetrahedron);

            ImageView octahedron = new ImageView(new Image("/res/PNGs/octahedron.png"));
            octahedron.setFitHeight(75);
            octahedron.setFitWidth(75);
            btnOctahedron.setGraphic(octahedron);

            ImageView sphere = new ImageView(new Image("/res/PNGs/sphere.png"));
            sphere.setFitHeight(75);
            sphere.setFitWidth(75);
            btnSphere.setGraphic(sphere);

            ImageView spheroid = new ImageView(new Image("/res/PNGs/spheroid.png"));
            spheroid.setFitHeight(75);
            spheroid.setFitWidth(75);
            btnSpheroid.setGraphic(spheroid);

            ImageView capsule = new ImageView(new Image("/res/PNGs/capsule.png"));
            capsule.setFitHeight(75);
            capsule.setFitWidth(75);
            btnCapsule.setGraphic(capsule);

            ImageView torus = new ImageView(new Image("/res/PNGs/torus.png"));
            torus.setFitHeight(75);
            torus.setFitWidth(75);
            btnTorus.setGraphic(torus);

            ImageView trapezoid = new ImageView(new Image("/res/PNGs/trapezoid.png"));
            trapezoid.setFitHeight(75);
            trapezoid.setFitWidth(75);
            btnTrapezoid.setGraphic(trapezoid);
            ////////////////////////////////////////////////////////////////////////////////////////////////////////


            // keep one pane open always
            settings.expandedPaneProperty().addListener((observable, oldValue, newValue) -> Platform.runLater(
                    () -> {
                        if (settings.getExpandedPane() == null)
                            settings.setExpandedPane(settings.getPanes().get(0));
                    }));

            // wire up settings in OPTIONS
            contentModel.showAxisProperty().bind(showAxisCheckBox.selectedProperty());
            backgroundColorPicker.setValue((Color) contentModel.getSubScene().getFill());

            contentModel.getSubScene().fillProperty().bind(backgroundColorPicker.valueProperty());

            // wire up settings in CAMERA
            fovSlider.setValue(contentModel.getCamera().getFieldOfView());
            contentModel.getCamera().fieldOfViewProperty().bind(fovSlider.valueProperty());

            // wire up settings in LIGHTING
            ambientEnableCheckbox.setSelected(contentModel.getAmbientLightEnabled());
            contentModel.ambientLightEnabledProperty().bind(ambientEnableCheckbox.selectedProperty());
            ambientColorPicker.setValue(contentModel.getAmbientLight().getColor());
            contentModel.getAmbientLight().colorProperty().bind(ambientColorPicker.valueProperty());

            // LIGHT 1
            light1EnabledCheckBox.setSelected(contentModel.getLight1Enabled());
            contentModel.light1EnabledProperty().bind(light1EnabledCheckBox.selectedProperty());
            light1ColorPicker.setValue(contentModel.getLight1().getColor());
            contentModel.getLight1().colorProperty().bind(light1ColorPicker.valueProperty());
            light1x.disableProperty().bind(light1followCameraCheckBox.selectedProperty());
            light1y.disableProperty().bind(light1followCameraCheckBox.selectedProperty());
            light1z.disableProperty().bind(light1followCameraCheckBox.selectedProperty());
            light1followCameraCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue) {
                    contentModel.getLight1().translateXProperty().bind(new DoubleBinding() {
                        {
                            bind(contentModel.getCamera().boundsInParentProperty());
                        }

                        @Override
                        protected double computeValue() {
                            return contentModel.getCamera().getBoundsInParent().getMinX();
                        }
                    });
                    contentModel.getLight1().translateYProperty().bind(new DoubleBinding() {
                        {
                            bind(contentModel.getCamera().boundsInParentProperty());
                        }

                        @Override
                        protected double computeValue() {
                            return contentModel.getCamera().getBoundsInParent().getMinY();
                        }
                    });
                    contentModel.getLight1().translateZProperty().bind(new DoubleBinding() {
                        {
                            bind(contentModel.getCamera().boundsInParentProperty());
                        }

                        @Override
                        protected double computeValue() {
                            return contentModel.getCamera().getBoundsInParent().getMinZ();
                        }
                    });
                } else {
                    contentModel.getLight1().translateXProperty().bind(light1x.valueProperty());
                    contentModel.getLight1().translateYProperty().bind(light1y.valueProperty());
                    contentModel.getLight1().translateZProperty().bind(light1z.valueProperty());
                }
            });
            // LIGHT 2
            light2EnabledCheckBox.setSelected(contentModel.getLight2Enabled());
            contentModel.light2EnabledProperty().bind(light2EnabledCheckBox.selectedProperty());
            light2ColorPicker.setValue(contentModel.getLight2().getColor());
            contentModel.getLight2().colorProperty().bind(light2ColorPicker.valueProperty());
            contentModel.getLight2().translateXProperty().bind(light2x.valueProperty());
            contentModel.getLight2().translateYProperty().bind(light2y.valueProperty());
            contentModel.getLight2().translateZProperty().bind(light2z.valueProperty());
            // LIGHT 3
            light3EnabledCheckBox.setSelected(contentModel.getLight3Enabled());
            contentModel.light3EnabledProperty().bind(light3EnabledCheckBox.selectedProperty());
            light3ColorPicker.setValue(contentModel.getLight3().getColor());
            contentModel.getLight3().colorProperty().bind(light3ColorPicker.valueProperty());
            contentModel.getLight3().translateXProperty().bind(light3x.valueProperty());
            contentModel.getLight3().translateYProperty().bind(light3y.valueProperty());
            contentModel.getLight3().translateZProperty().bind(light3z.valueProperty());


            // adding the mouse event handler
            contentModel.getSubScene().addEventHandler(javafx.scene.input.MouseEvent.ANY, selectedShapeEventHandler);

            // wire up the text in the status bar
            statusNumberOfNodes.setText(statusBarNumberOfNodes());
            statusSelectedShape.setText(statusBarSelectedShape());

            ///////////////////////////////////////////////////////////////
            //////////////////////// BINDINGS /////////////////////////////

            btnDelete.disableProperty().bind(new BooleanBinding() {
                {
                    bind(selectedShapeProperty);
                }

                @Override
                protected boolean computeValue() {
                    return selectedShapeProperty.get() == null ? true : false;
                }
            });

            selectedShapeTypeLabel.textProperty().bind(new ObjectBinding<String>() {
                {bind(selectedShapeProperty);}
                @Override
                protected String computeValue() {
                    if (selectedShapeProperty.get() != null)
                        return shapeName(selectedShapeProperty.get());
                    else
                        return "Aucune forme 3D";
                }
            });

            BooleanBinding disabledBinding = new BooleanBinding() {
                {bind(selectedShapeProperty);}
                @Override
                protected boolean computeValue() {
                    return selectedShapeProperty.get() == null;
                }
            };

            selectedShapeGridPane.disableProperty().bind(disabledBinding);

            BooleanBinding CSGOptionsBinding = new BooleanBinding() {
                {
                    bind(CSGModeCheckBox.selectedProperty());
                }

                @Override
                protected boolean computeValue() {
                    return CSGModeCheckBox.isSelected() == false;
                }
            };

            CSGModeTreeTable.disableProperty().bind(CSGOptionsBinding);
            CSGUnionButton.disableProperty().bind(CSGOptionsBinding);
            CSGIntersectionButton.disableProperty().bind(CSGOptionsBinding);
            CSGDifferenceButton.disableProperty().bind(CSGOptionsBinding);

            hierarachyTreeTable.rootProperty().bind(new ObjectBinding<TreeItem<Node>>() {
                {
                    bind(contentModel.contentProperty());
                }

                @Override
                protected TreeItem<Node> computeValue() {
                    return (contentModel.getContent() != null) ? new TreeItemImpl(contentModel.getContent()) : null;
                }
            });

            CSGModeTreeTable.rootProperty().bind(new ObjectBinding<TreeItem<Node>>() {
                {
                    bind(CSGselectedShapesList);
                }

                @Override
                protected TreeItem<Node> computeValue() {
                    return (CSGselectedShapesList.get() != null) ? new CSGTreeItemImpl(CSGselectedShapesList.get()) : null;
                }
            });

            ////////////////////////// END OF BINDINGS /////////////////////////////



            ///////////////////////// CHANGE LISTENERS /////////////////////////////

            selectedShapeRotateX.valueProperty().addListener((observable, oldValue, newValue) ->
                    {
                        if (selectedShapeProperty.get() != null)
                            setTotalRotationsX(selectedShapeProperty.get(), newValue.doubleValue());
                    }
            );

            selectedShapeRotateY.valueProperty().addListener((observable, oldValue, newValue) ->
                    {
                        if (selectedShapeProperty.get() != null)
                            setTotalRotationsY(selectedShapeProperty.get(), newValue.doubleValue());
                    }
            );

            selectedShapeRotateZ.valueProperty().addListener((observable, oldValue, newValue) ->
                    {
                        if (selectedShapeProperty.get() != null)
                            setTotalRotationsZ(selectedShapeProperty.get(), newValue.doubleValue());
                    }
            );

            CSGModeCheckBox.selectedProperty().addListener(((observable1, oldValue1, newValue) -> {
                if (newValue == false) CSGselectedShapesList.get().getChildren().clear();
            }));

            contentModel.getSubScene().setOnMouseEntered(event1 -> {

            });

            contentModel.getSubScene().setOnMousePressed(event -> {
                PickResult pickResult = event.getPickResult();
                Node intersectedNode = pickResult.getIntersectedNode();

                if (intersectedNode == null) {

                    selectedShapeProperty.set(null);
                    contentModel.removeSelectedShapeAxis();

                } else if (intersectedNode != null) {
                    if (intersectedNode.getId() == null)
                        intersectedNode.setId("");
                    if (intersectedNode instanceof Shape3D && !intersectedNode.getId().startsWith("sys_")) {

                        if (CSGModeCheckBox.isSelected()) {

                            if (CSGselectedShapesList.get().getChildren().contains(intersectedNode) == false) {
                                if (intersectedNode instanceof CapsuleMesh) {
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setTitle("Op\u00e9ration bool\u00e9enne non sopport\u00e9e");
                                    alert.setHeaderText("Op\u00e9ration bool\u00e9nne non sopport\u00e9e pour le moment");
                                    alert.setContentText("Pour le moment, les op\u00e9rations bool\u00e9nnes comprenant des formes 3D de type \"Capsules\" ne sont pas sopport\u00e9es.");
                                    alert.showAndWait();
                                } else if (intersectedNode instanceof SpheroidMesh) {
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setTitle("Op\u00e9ration bool\u00e9enne non sopport\u00e9e");
                                    alert.setHeaderText("Op\u00e9ration bool\u00e9nne non sopport\u00e9e pour le moment");
                                    alert.setContentText("Pour le moment, les op\u00e9rations bool\u00e9nnes comprenant des formes 3D de type \"Sph\u00e8roides\" ne sont pas sopport\u00e9es.");
                                    alert.showAndWait();
                                } else if (intersectedNode instanceof SpringMesh) {
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setTitle("Op\u00e9ration bool\u00e9enne non sopport\u00e9e");
                                    alert.setHeaderText("Op\u00e9ration bool\u00e9nne non sopport\u00e9e pour le moment");
                                    alert.setContentText("Pour le moment, les op\u00e9rations bool\u00e9nnes comprenant des formes 3D de type \"H\u00e9lices\" ne sont pas sopport\u00e9es");
                                    alert.showAndWait();
                                } else if (intersectedNode instanceof TorusMesh) {
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setTitle("Op\u00e9ration bool\u00e9enne non sopport\u00e9e");
                                    alert.setHeaderText("Op\u00e9ration bool\u00e9nne non sopport\u00e9e pour le moment");
                                    alert.setContentText("Pour le moment, les op\u00e9rations bool\u00e9nnes comprenant des formes 3D de type \"Torus\" ne sont pas sopport\u00e9es");
                                    alert.showAndWait();
                                } else {
                                    CSGselectedShapesList.get().getChildren().addAll(cloneShape((Shape3D) intersectedNode));
                                }
                            }

                        } else {
                            selectedShapeProperty.set((Shape3D) intersectedNode);
                            if (contentModel.getSelectedAxe() == SelectedAxe.None)
                                contentModel.addSelectedShapeAxis();
                        }

                    } else if (intersectedNode.getId().startsWith("sys_selectedShape")) {

                        if (intersectedNode.getId().startsWith("sys_selectedShape_x"))
                            contentModel.setSelectedAxe(SelectedAxe.X_Axis);
                        else if (intersectedNode.getId().startsWith("sys_selectedShape_y"))
                            contentModel.setSelectedAxe(SelectedAxe.Y_Axis);
                        else if (intersectedNode.getId().startsWith("sys_selectedShape_z"))
                            contentModel.setSelectedAxe(SelectedAxe.Z_Axis);

                    } else {
                        selectedShapeProperty.set(null);
                        contentModel.removeSelectedShapeAxis();
                    }
                }
            });

            selectedShapeColorPicker.valueProperty().addListener((observable, oldValue, newValue) -> {
                if (selectedShapeProperty.get() != null) {
                    PhongMaterial phongMaterial = new PhongMaterial(newValue);
                    phongMaterial.setSpecularColor(newValue);
                    selectedShapeProperty.get().setMaterial(phongMaterial);
                }
            });

            selectedShapeProperty.addListener((observableValue, oldValue, newValue) -> {
                if (newValue == null) {
                    // unbinding colors
                    selectedShapeColorPicker.setValue(Color.WHITE);
                    // unbinding positions
                    unbindPositionFields(oldValue);
                    // unbinding dimensions
                    unbindDimensionsFields(oldValue);
                    selectedShapeD1Label.setVisible(true);
                    selectedShapeD2Label.setVisible(true);
                    selectedShapeD3Label.setVisible(true);
                    selectedShapeD4Label.setVisible(true);
                    selectedShapeD1Label.setText("Dimension 1");
                    selectedShapeD2Label.setText("Dimension 2");
                    selectedShapeD3Label.setText("Dimension 3");
                    selectedShapeD4Label.setText("Dimension 4");
                    selectedShapeD1Field.setVisible(true);
                    selectedShapeD2Field.setVisible(true);
                    selectedShapeD3Field.setVisible(true);
                    selectedShapeD4Field.setVisible(true);
                    selectedShapeD1Field.setValue(0);
                    selectedShapeD2Field.setValue(0);
                    selectedShapeD3Field.setValue(0);
                    selectedShapeD3Field.setValue(0);
                    selectedShapeD4Field.setValue(0);
                    // unbinding scale
                    unbindScaleFields(oldValue);
                    selectedShapeID.textProperty().unbindBidirectional(oldValue.idProperty());

                } else if (newValue != null) {
                    // getting rid of possibly the old shape information
                    if (oldValue != null) {
                        unbindPositionFields(oldValue);
                        unbindDimensionsFields(oldValue);
                        unbindScaleFields(oldValue);
                        selectedShapeID.textProperty().unbindBidirectional(oldValue.idProperty());
                    }
                    // binding colors
                    if (selectedShapeProperty.get().getMaterial() != null)
                        selectedShapeColorPicker.setValue(((PhongMaterial) newValue.getMaterial()).getDiffuseColor());
                    else selectedShapeColorPicker.setValue(Color.WHITE);
                    // binding positions fields
                    bindPositionFields(newValue);
                    // binding dimensions fields
                    bindDimensionsFields(newValue);
                    // binding scale fields
                    bindScaleFields(newValue);
                    // updating rotation fields
                    updateRotationFields(newValue);
                    // binding ID field
                    selectedShapeID.textProperty().bindBidirectional(newValue.idProperty());
                }
            });

            btnDeleteAll.setDisable(true);
            contentModel.getContent().getChildren().addListener(new ListChangeListener<Node>() {
                @Override
                public void onChanged(Change<? extends Node> c) {
                    hierarachyTreeTable.rootProperty().bind(new ObjectBinding<TreeItem<Node>>() {
                        {
                            bind(contentModel.contentProperty());
                        }

                        @Override
                        protected TreeItem<Node> computeValue() {
                            return (contentModel.getContent() != null) ? new TreeItemImpl(contentModel.getContent()) : null;
                        }
                    });

                    statusNumberOfNodes.textProperty().bind(new StringBinding() {
                        @Override
                        protected String computeValue() {
                            return statusBarNumberOfNodes();
                        }
                    });

                    if (c.getList().size() == 0)
                        btnDeleteAll.setDisable(true);
                    else
                        btnDeleteAll.setDisable(false);
                }
            });

            selectedShapeProperty.addListener((observable, oldValue, newValue) -> {
                statusSelectedShape.textProperty().bind(new StringBinding() {
                    @Override
                    protected String computeValue() {
                        return statusBarSelectedShape();
                    }
                });
            });

            CSGselectedShapesList.get().getChildren().addListener(new ListChangeListener<Node>() {
                @Override
                public void onChanged(Change<? extends Node> c) {
                    CSGModeTreeTable.rootProperty().bind(new ObjectBinding<TreeItem<Node>>() {
                        {
                            bind(CSGselectedShapesList);
                        }

                        @Override
                        protected TreeItem<Node> computeValue() {
                            return (CSGselectedShapesList.get() != null) ? new CSGTreeItemImpl(CSGselectedShapesList.get()) : null;
                        }
                    });
                }
            });

            hierarachyTreeTable.setOnKeyPressed(t -> {
                if (t.getCode() == KeyCode.SPACE) {
                    TreeItem<Node> selectedItem = hierarachyTreeTable.getSelectionModel().getSelectedItem();
                    if (selectedItem != null) {
                        Node node = selectedItem.getValue();
                        node.setVisible(!node.isVisible());
                    }
                    t.consume();
                }
            });

            CSGShapeColumn.setCellValueFactory(p -> p.getValue().valueProperty().asString());
            CSGShapeID.setCellValueFactory(p -> p.getValue().getValue().idProperty());
            CSGVisibilityColumn.setCellValueFactory(p -> p.getValue().getValue().visibleProperty());
            CSGVisibilityColumn.setCellFactory(CheckBoxTreeTableCell.forTreeTableColumn(CSGVisibilityColumn));

            nodeColumn.setCellValueFactory(p -> p.getValue().valueProperty().asString());
            idColumn.setCellValueFactory(p -> p.getValue().getValue().idProperty());
            visibilityColumn.setCellValueFactory(p -> p.getValue().getValue().visibleProperty());
            visibilityColumn.setCellFactory(CheckBoxTreeTableCell.forTreeTableColumn(visibilityColumn));
            widthColumn.setCellValueFactory(p -> new ObjectBinding<Double>() {
                {  bind(p.getValue().getValue().boundsInLocalProperty()); }
                @Override protected Double computeValue() {
                    return p.getValue().getValue().getBoundsInLocal().getWidth();
                }
            });
            StringConverter<Double> niceDoubleStringConverter = new StringConverter<Double>() {
                @Override
                public String toString(Double t) {
                    return String.format("%.2f", t);
                }

                @Override
                public Double fromString(String string) {
                    throw new UnsupportedOperationException("Not supported yet."); //Not needed so far
                }
            };
            widthColumn.setCellFactory(TextFieldTreeTableCell.<Node, Double>forTreeTableColumn(niceDoubleStringConverter));
            heightColumn.setCellFactory(TextFieldTreeTableCell.<Node, Double>forTreeTableColumn(niceDoubleStringConverter));
            depthColumn.setCellFactory(TextFieldTreeTableCell.<Node, Double>forTreeTableColumn(niceDoubleStringConverter));
            heightColumn.setCellValueFactory(p -> new ObjectBinding<Double>() {
                {  bind(p.getValue().getValue().boundsInLocalProperty()); }
                @Override protected Double computeValue() {
                    return p.getValue().getValue().getBoundsInLocal().getHeight();
                }
            });
            depthColumn.setCellValueFactory(p -> new ObjectBinding<Double>() {
                {  bind(p.getValue().getValue().boundsInLocalProperty()); }
                @Override protected Double computeValue() {
                    return p.getValue().getValue().getBoundsInLocal().getDepth();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /// functions needed for 3D Drag & Drop
    public Point3D unProjectDirection (double sceneX, double sceneY, double sWidth, double sHeight) {
        double tanHFov = Math.tan(Math.toRadians(contentModel.getCamera().getFieldOfView()) * 0.5f);
        Point3D vMouse = new Point3D(tanHFov*(2*sceneX/sWidth-1), tanHFov*(2*sceneY/sWidth-sHeight/sWidth), 1);

        Point3D result = localToSceneDirection(vMouse);
        return result.normalize();
    }

    public Point3D localToScene(Point3D pt) {
        Point3D res = contentModel.getCamera().localToParentTransformProperty().get().transform(pt);
        if (contentModel.getCamera().getParent() != null) {
            res = contentModel.getCamera().getParent().localToSceneTransformProperty().get().transform(res);
        }
        return res;
    }

    public Point3D localToSceneDirection(Point3D dir) {
        Point3D res = localToScene(dir);
        return res.subtract(localToScene(new Point3D(0, 0, 0)));
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////

    private void bindPositionFields(Shape3D shape) {
        selectedShapeCoordX.valueProperty().bindBidirectional(shape.translateXProperty());
        selectedShapeCoordY.valueProperty().bindBidirectional(shape.translateYProperty());
        selectedShapeCoordZ.valueProperty().bindBidirectional(shape.translateZProperty());
    }

    private void unbindPositionFields(Shape3D shape) {
        selectedShapeCoordX.valueProperty().unbindBidirectional(shape.translateXProperty());
        selectedShapeCoordY.valueProperty().unbindBidirectional(shape.translateYProperty());
        selectedShapeCoordZ.valueProperty().unbindBidirectional(shape.translateZProperty());
    }

    private void bindDimensionsFields(Shape3D shape) {
        if (shape instanceof CubeMesh) {
            selectedShapeD1Label.setVisible(false);
            selectedShapeD1Field.setVisible(false);

            selectedShapeD2Label.setVisible(true);
            selectedShapeD2Label.setText("Ar\u00eate : ");
            selectedShapeD2Field.setVisible(true);
            selectedShapeD2Field.valueProperty().bindBidirectional(((CubeMesh) shape).sizeProperty());

            selectedShapeD3Label.setVisible(false);
            selectedShapeD3Field.setVisible(false);
            selectedShapeD4Label.setVisible(false);
            selectedShapeD4Field.setVisible(false);

        } else if (shape instanceof Box) {
            selectedShapeD1Label.setVisible(true);
            selectedShapeD1Label.setText("Hauteur : ");
            selectedShapeD1Field.setVisible(true);
            selectedShapeD1Field.valueProperty().bindBidirectional(((Box) shape).heightProperty());

            selectedShapeD2Label.setVisible(true);
            selectedShapeD2Label.setText("Largeur : ");
            selectedShapeD2Field.setVisible(true);
            selectedShapeD2Field.valueProperty().bindBidirectional(((Box) shape).widthProperty());

            selectedShapeD3Label.setVisible(true);
            selectedShapeD3Label.setText("Profondeur : ");
            selectedShapeD3Field.setVisible(true);
            selectedShapeD3Field.valueProperty().bindBidirectional(((Box) shape).depthProperty());

            selectedShapeD4Label.setVisible(false);
            selectedShapeD4Field.setVisible(false);

        } else if (shape instanceof Cylinder) {
            selectedShapeD1Label.setVisible(true);
            selectedShapeD1Label.setText("Hauteur : ");
            selectedShapeD1Field.setVisible(true);
            selectedShapeD1Field.valueProperty().bindBidirectional(((Cylinder) shape).heightProperty());

            selectedShapeD2Label.setVisible(true);
            selectedShapeD2Label.setText("Rayon : ");
            selectedShapeD2Field.setVisible(true);
            selectedShapeD2Field.valueProperty().bindBidirectional(((Cylinder) shape).radiusProperty());

            selectedShapeD3Label.setVisible(false);
            selectedShapeD3Field.setVisible(false);
            selectedShapeD4Label.setVisible(false);
            selectedShapeD4Field.setVisible(false);

        } else if (shape instanceof Sphere) {
            selectedShapeD1Label.setVisible(false);
            selectedShapeD1Field.setVisible(false);

            selectedShapeD2Label.setVisible(true);
            selectedShapeD2Label.setText("Rayon : ");
            selectedShapeD2Field.setVisible(true);
            selectedShapeD2Field.valueProperty().bindBidirectional(((Sphere) shape).radiusProperty());

            selectedShapeD3Label.setVisible(false);
            selectedShapeD3Field.setVisible(false);
            selectedShapeD4Label.setVisible(false);
            selectedShapeD4Field.setVisible(false);

        } else if (shape instanceof PyramidMesh) {

            selectedShapeD1Label.setVisible(true);
            selectedShapeD1Label.setText("Hauteur : ");
            selectedShapeD1Field.setVisible(true);
            selectedShapeD1Field.valueProperty().bindBidirectional(((PyramidMesh) shape).heightProperty());

            selectedShapeD2Label.setVisible(true);
            selectedShapeD2Label.setText("Base : ");
            selectedShapeD2Field.setVisible(true);
            selectedShapeD2Field.valueProperty().bindBidirectional(((PyramidMesh) shape).hypotenuseProperty());

            selectedShapeD3Label.setVisible(false);
            selectedShapeD3Field.setVisible(false);
            selectedShapeD4Label.setVisible(false);
            selectedShapeD4Field.setVisible(false);

        } else if (shape instanceof ConeMesh) {

            selectedShapeD1Label.setVisible(true);
            selectedShapeD1Label.setText("Hauteur : ");
            selectedShapeD1Field.setVisible(true);
            selectedShapeD1Field.valueProperty().bindBidirectional(((ConeMesh) shape).heightProperty());

            selectedShapeD2Label.setVisible(true);
            selectedShapeD2Label.setText("Rayon : ");
            selectedShapeD2Field.setVisible(true);
            selectedShapeD2Field.valueProperty().bindBidirectional(((ConeMesh) shape).radiusProperty());

            selectedShapeD3Label.setVisible(false);
            selectedShapeD3Field.setVisible(false);
            selectedShapeD4Label.setVisible(false);
            selectedShapeD4Field.setVisible(false);

        } else if (shape instanceof CapsuleMesh) {

            selectedShapeD1Label.setVisible(true);
            selectedShapeD1Label.setText("Hauteur : ");
            selectedShapeD1Field.setVisible(true);
            selectedShapeD1Field.valueProperty().bindBidirectional(((CapsuleMesh) shape).heightProperty());

            selectedShapeD2Label.setVisible(true);
            selectedShapeD2Label.setText("Rayon : ");
            selectedShapeD2Field.setVisible(true);
            selectedShapeD2Field.valueProperty().bindBidirectional(((CapsuleMesh) shape).radiusProperty());

            selectedShapeD3Label.setVisible(false);
            selectedShapeD3Field.setVisible(false);
            selectedShapeD4Label.setVisible(false);
            selectedShapeD4Field.setVisible(false);

        } else if (shape instanceof TorusMesh) {
            selectedShapeD1Label.setVisible(true);
            selectedShapeD1Label.setText("Rayon : ");
            selectedShapeD1Field.setVisible(true);
            selectedShapeD1Field.valueProperty().bindBidirectional(((TorusMesh) shape).radiusProperty());

            selectedShapeD2Label.setVisible(true);
            selectedShapeD2Label.setText("Rayon du tube : ");
            selectedShapeD2Field.setVisible(true);
            selectedShapeD2Field.valueProperty().bindBidirectional(((TorusMesh) shape).tubeRadiusProperty());

            selectedShapeD3Label.setVisible(false);
            selectedShapeD3Field.setVisible(false);
            selectedShapeD4Label.setVisible(false);
            selectedShapeD4Field.setVisible(false);

        } else if (shape instanceof SpheroidMesh) {
            selectedShapeD1Label.setVisible(true);
            selectedShapeD1Label.setText("Grand rayon : ");
            selectedShapeD1Field.setVisible(true);
            selectedShapeD1Field.valueProperty().bindBidirectional(((SpheroidMesh) shape).majorRadiusProperty());

            selectedShapeD2Label.setVisible(true);
            selectedShapeD2Label.setText("Petit rayon : ");
            selectedShapeD2Field.setVisible(true);
            selectedShapeD2Field.valueProperty().bindBidirectional(((SpheroidMesh) shape).minorRadiusProperty());

            selectedShapeD3Label.setVisible(false);
            selectedShapeD3Field.setVisible(false);
            selectedShapeD4Label.setVisible(false);
            selectedShapeD4Field.setVisible(false);

        } else if (shape instanceof SpringMesh) {

            selectedShapeD1Label.setVisible(true);
            selectedShapeD1Label.setText("Rayon moyen : ");
            selectedShapeD1Field.setVisible(true);
            selectedShapeD1Field.valueProperty().bindBidirectional(((SpringMesh) shape).meanRadiusProperty());

            selectedShapeD2Label.setVisible(true);
            selectedShapeD2Label.setText("Rayon du tube : ");
            selectedShapeD2Field.setVisible(true);
            selectedShapeD2Field.valueProperty().bindBidirectional(((SpringMesh) shape).wireRadiusProperty());

            selectedShapeD3Label.setVisible(true);
            selectedShapeD3Label.setText("Pas : ");
            selectedShapeD3Field.setVisible(true);
            selectedShapeD3Field.valueProperty().bindBidirectional(((SpringMesh) shape).pitchProperty());

            selectedShapeD4Label.setVisible(true);
            selectedShapeD4Label.setText("Longueur : ");
            selectedShapeD4Field.setVisible(true);
            selectedShapeD4Field.valueProperty().bindBidirectional(((SpringMesh) shape).lengthProperty());

        } else if (shape instanceof TrapezoidMesh) {

            selectedShapeD1Label.setVisible(true);
            selectedShapeD1Label.setText("Petite base : ");
            selectedShapeD1Field.setVisible(true);
            selectedShapeD1Field.valueProperty().bindBidirectional(((TrapezoidMesh) shape).sizeSmallProperty());

            selectedShapeD2Label.setVisible(true);
            selectedShapeD2Label.setText("Grande base : ");
            selectedShapeD2Field.setVisible(true);
            selectedShapeD2Field.valueProperty().bindBidirectional(((TrapezoidMesh) shape).sizeBigProperty());

            selectedShapeD3Label.setVisible(true);
            selectedShapeD3Label.setText("Hauteur : ");
            selectedShapeD3Field.setVisible(true);
            selectedShapeD3Field.valueProperty().bindBidirectional(((TrapezoidMesh) shape).heightProperty());

            selectedShapeD4Label.setVisible(true);
            selectedShapeD4Label.setText("Profondeur : ");
            selectedShapeD4Field.setVisible(true);
            selectedShapeD4Field.valueProperty().bindBidirectional(((TrapezoidMesh) shape).depthProperty());

        } else if (shape instanceof TriangularPrismMesh) {
            selectedShapeD1Label.setVisible(true);
            selectedShapeD1Label.setText("Hauteur : ");
            selectedShapeD1Field.setVisible(true);
            selectedShapeD1Field.valueProperty().bindBidirectional(((TriangularPrismMesh) shape).heightProperty());

            selectedShapeD2Label.setVisible(true);
            selectedShapeD2Label.setText("Largeur : ");
            selectedShapeD2Field.setVisible(true);
            selectedShapeD2Field.valueProperty().bindBidirectional(((TriangularPrismMesh) shape).widthProperty());

            selectedShapeD3Label.setVisible(true);
            selectedShapeD3Label.setText("Profondeur : ");
            selectedShapeD3Field.setVisible(true);
            selectedShapeD3Field.valueProperty().bindBidirectional(((TriangularPrismMesh) shape).depthProperty());

            selectedShapeD4Label.setVisible(false);
            selectedShapeD4Field.setVisible(false);

        } else if (shape instanceof TetrahedronMesh) {
            selectedShapeD1Label.setVisible(false);
            selectedShapeD1Field.setVisible(false);

            selectedShapeD2Label.setVisible(true);
            selectedShapeD2Label.setText("Hauteur : ");
            selectedShapeD2Field.setVisible(true);
            selectedShapeD2Field.valueProperty().bindBidirectional(((TetrahedronMesh) shape).heightProperty());

            selectedShapeD3Label.setVisible(false);
            selectedShapeD3Field.setVisible(false);
            selectedShapeD4Label.setVisible(false);
            selectedShapeD4Field.setVisible(false);

        } else if (shape instanceof OctahedronMesh) {
            selectedShapeD1Label.setVisible(true);
            selectedShapeD1Label.setText("Hauteur : ");
            selectedShapeD1Field.setVisible(true);
            selectedShapeD1Field.valueProperty().bindBidirectional(((OctahedronMesh) shape).heightProperty());

            selectedShapeD2Label.setVisible(true);
            selectedShapeD2Label.setText("Base : ");
            selectedShapeD2Field.setVisible(true);
            selectedShapeD2Field.valueProperty().bindBidirectional(((OctahedronMesh) shape).hypotenuseProperty());

            selectedShapeD3Label.setVisible(false);
            selectedShapeD3Field.setVisible(false);
            selectedShapeD4Label.setVisible(false);
            selectedShapeD4Field.setVisible(false);

        } else if (shape instanceof MeshView) {

            selectedShapeD1Label.setVisible(false);
            selectedShapeD1Field.setVisible(false);
            selectedShapeD2Label.setVisible(false);
            selectedShapeD2Field.setVisible(false);
            selectedShapeD3Label.setVisible(false);
            selectedShapeD3Field.setVisible(false);
            selectedShapeD4Label.setVisible(false);
            selectedShapeD4Field.setVisible(false);
        }
    }

    private void unbindDimensionsFields(Shape3D shape) {
        if (shape instanceof CubeMesh) {
            selectedShapeD1Label.setVisible(false);
            selectedShapeD1Field.setVisible(false);

            selectedShapeD2Label.setVisible(true);
            selectedShapeD2Label.setText("Ar\u00eate : ");
            selectedShapeD2Field.setVisible(true);
            selectedShapeD2Field.valueProperty().unbindBidirectional(((CubeMesh) shape).sizeProperty());

            selectedShapeD3Label.setVisible(false);
            selectedShapeD3Field.setVisible(false);
            selectedShapeD4Label.setVisible(false);
            selectedShapeD4Field.setVisible(false);
        } else if (shape instanceof Box) {
            selectedShapeD1Label.setVisible(true);
            selectedShapeD1Label.setText("Hauteur : ");
            selectedShapeD1Field.setVisible(true);
            selectedShapeD1Field.valueProperty().unbindBidirectional(((Box) shape).heightProperty());

            selectedShapeD2Label.setVisible(true);
            selectedShapeD2Label.setText("Largeur : ");
            selectedShapeD2Field.setVisible(true);
            selectedShapeD2Field.valueProperty().unbindBidirectional(((Box) shape).widthProperty());

            selectedShapeD3Label.setVisible(true);
            selectedShapeD3Label.setText("Profondeur : ");
            selectedShapeD3Field.setVisible(true);
            selectedShapeD3Field.valueProperty().unbindBidirectional(((Box) shape).depthProperty());

            selectedShapeD4Label.setVisible(false);
            selectedShapeD4Field.setVisible(false);

        } else if (shape instanceof Cylinder) {
            selectedShapeD1Label.setVisible(true);
            selectedShapeD1Label.setText("Hauteur : ");
            selectedShapeD1Field.setVisible(true);
            selectedShapeD1Field.valueProperty().unbindBidirectional(((Cylinder) shape).heightProperty());

            selectedShapeD2Label.setVisible(true);
            selectedShapeD2Label.setText("Rayon : ");
            selectedShapeD2Field.setVisible(true);
            selectedShapeD2Field.valueProperty().unbindBidirectional(((Cylinder) shape).radiusProperty());

            selectedShapeD3Label.setVisible(false);
            selectedShapeD3Field.setVisible(false);
            selectedShapeD4Label.setVisible(false);
            selectedShapeD4Field.setVisible(false);

        } else if (shape instanceof Sphere) {
            selectedShapeD1Label.setVisible(false);
            selectedShapeD1Field.setVisible(false);

            selectedShapeD2Label.setVisible(true);
            selectedShapeD2Label.setText("Rayon : ");
            selectedShapeD2Field.setVisible(true);
            selectedShapeD2Field.valueProperty().unbindBidirectional(((Sphere) shape).radiusProperty());

            selectedShapeD3Label.setVisible(false);
            selectedShapeD3Field.setVisible(false);
            selectedShapeD4Label.setVisible(false);
            selectedShapeD4Field.setVisible(false);

        } else if (shape instanceof PyramidMesh) {

            selectedShapeD1Label.setVisible(true);
            selectedShapeD1Label.setText("Hauteur : ");
            selectedShapeD1Field.setVisible(true);
            selectedShapeD1Field.valueProperty().unbindBidirectional(((PyramidMesh) shape).heightProperty());

            selectedShapeD2Label.setVisible(true);
            selectedShapeD2Label.setText("Base : ");
            selectedShapeD2Field.setVisible(true);
            selectedShapeD2Field.valueProperty().unbindBidirectional(((PyramidMesh) shape).hypotenuseProperty());

            selectedShapeD3Label.setVisible(false);
            selectedShapeD3Field.setVisible(false);
            selectedShapeD4Label.setVisible(false);
            selectedShapeD4Field.setVisible(false);

        } else if (shape instanceof ConeMesh) {

            selectedShapeD1Label.setVisible(true);
            selectedShapeD1Label.setText("Hauteur : ");
            selectedShapeD1Field.setVisible(true);
            selectedShapeD1Field.valueProperty().unbindBidirectional(((ConeMesh) shape).heightProperty());

            selectedShapeD2Label.setVisible(true);
            selectedShapeD2Label.setText("Rayon : ");
            selectedShapeD2Field.setVisible(true);
            selectedShapeD2Field.valueProperty().unbindBidirectional(((ConeMesh) shape).radiusProperty());

            selectedShapeD3Label.setVisible(false);
            selectedShapeD3Field.setVisible(false);
            selectedShapeD4Label.setVisible(false);
            selectedShapeD4Field.setVisible(false);

        } else if (shape instanceof CapsuleMesh) {

            selectedShapeD1Label.setVisible(true);
            selectedShapeD1Label.setText("Hauteur : ");
            selectedShapeD1Field.setVisible(true);
            selectedShapeD1Field.valueProperty().unbindBidirectional(((CapsuleMesh) shape).heightProperty());

            selectedShapeD2Label.setVisible(true);
            selectedShapeD2Label.setText("Rayon : ");
            selectedShapeD2Field.setVisible(true);
            selectedShapeD2Field.valueProperty().unbindBidirectional(((CapsuleMesh) shape).radiusProperty());

            selectedShapeD3Label.setVisible(false);
            selectedShapeD3Field.setVisible(false);
            selectedShapeD4Label.setVisible(false);
            selectedShapeD4Field.setVisible(false);

        } else if (shape instanceof TorusMesh) {
            selectedShapeD1Label.setVisible(true);
            selectedShapeD1Label.setText("Rayon : ");
            selectedShapeD1Field.setVisible(true);
            selectedShapeD1Field.valueProperty().unbindBidirectional(((TorusMesh) shape).radiusProperty());

            selectedShapeD2Label.setVisible(true);
            selectedShapeD2Label.setText("Rayon du tube : ");
            selectedShapeD2Field.setVisible(true);
            selectedShapeD2Field.valueProperty().unbindBidirectional(((TorusMesh) shape).tubeRadiusProperty());

            selectedShapeD3Label.setVisible(false);
            selectedShapeD3Field.setVisible(false);
            selectedShapeD4Label.setVisible(false);
            selectedShapeD4Field.setVisible(false);

        } else if (shape instanceof SpheroidMesh) {
            selectedShapeD1Label.setVisible(true);
            selectedShapeD1Label.setText("Grand rayon : ");
            selectedShapeD1Field.setVisible(true);
            selectedShapeD1Field.valueProperty().unbindBidirectional(((SpheroidMesh) shape).majorRadiusProperty());

            selectedShapeD2Label.setVisible(true);
            selectedShapeD2Label.setText("Petit rayon : ");
            selectedShapeD2Field.setVisible(true);
            selectedShapeD2Field.valueProperty().unbindBidirectional(((SpheroidMesh) shape).minorRadiusProperty());

            selectedShapeD3Label.setVisible(false);
            selectedShapeD3Field.setVisible(false);
            selectedShapeD4Label.setVisible(false);
            selectedShapeD4Field.setVisible(false);

        } else if (shape instanceof SpringMesh) {

            selectedShapeD1Label.setVisible(true);
            selectedShapeD1Label.setText("Rayon moyen : ");
            selectedShapeD1Field.setVisible(true);
            selectedShapeD1Field.valueProperty().unbindBidirectional(((SpringMesh) shape).meanRadiusProperty());

            selectedShapeD2Label.setVisible(true);
            selectedShapeD2Label.setText("Rayon du tube : ");
            selectedShapeD2Field.setVisible(true);
            selectedShapeD2Field.valueProperty().unbindBidirectional(((SpringMesh) shape).wireRadiusProperty());

            selectedShapeD3Label.setVisible(true);
            selectedShapeD3Label.setText("Pas : ");
            selectedShapeD3Field.setVisible(true);
            selectedShapeD3Field.valueProperty().unbindBidirectional(((SpringMesh) shape).pitchProperty());

            selectedShapeD4Label.setVisible(true);
            selectedShapeD4Label.setText("Longueur : ");
            selectedShapeD4Field.setVisible(true);
            selectedShapeD4Field.valueProperty().unbindBidirectional(((SpringMesh) shape).lengthProperty());

        } else if (shape instanceof TrapezoidMesh) {

            selectedShapeD1Label.setVisible(true);
            selectedShapeD1Label.setText("Petite base : ");
            selectedShapeD1Field.setVisible(true);
            selectedShapeD1Field.valueProperty().unbindBidirectional(((TrapezoidMesh) shape).sizeSmallProperty());

            selectedShapeD2Label.setVisible(true);
            selectedShapeD2Label.setText("Grande base : ");
            selectedShapeD2Field.setVisible(true);
            selectedShapeD2Field.valueProperty().unbindBidirectional(((TrapezoidMesh) shape).sizeBigProperty());

            selectedShapeD3Label.setVisible(true);
            selectedShapeD3Label.setText("Hauteur : ");
            selectedShapeD3Field.setVisible(true);
            selectedShapeD3Field.valueProperty().unbindBidirectional(((TrapezoidMesh) shape).heightProperty());

            selectedShapeD4Label.setVisible(true);
            selectedShapeD4Label.setText("Profondeur : ");
            selectedShapeD4Field.setVisible(true);
            selectedShapeD4Field.valueProperty().unbindBidirectional(((TrapezoidMesh) shape).depthProperty());

        } else if (shape instanceof TriangularPrismMesh) {
            selectedShapeD1Label.setVisible(true);
            selectedShapeD1Label.setText("Hauteur : ");
            selectedShapeD1Field.setVisible(true);
            selectedShapeD1Field.valueProperty().unbindBidirectional(((TriangularPrismMesh) shape).heightProperty());

            selectedShapeD2Label.setVisible(true);
            selectedShapeD2Label.setText("Largeur : ");
            selectedShapeD2Field.setVisible(true);
            selectedShapeD2Field.valueProperty().unbindBidirectional(((TriangularPrismMesh) shape).widthProperty());

            selectedShapeD3Label.setVisible(true);
            selectedShapeD3Label.setText("Profondeur : ");
            selectedShapeD3Field.setVisible(true);
            selectedShapeD3Field.valueProperty().unbindBidirectional(((TriangularPrismMesh) shape).depthProperty());

            selectedShapeD4Label.setVisible(false);
            selectedShapeD4Field.setVisible(false);

        } else if (shape instanceof TetrahedronMesh) {
            selectedShapeD1Label.setVisible(false);
            selectedShapeD1Field.setVisible(false);

            selectedShapeD2Label.setVisible(true);
            selectedShapeD2Label.setText("Hauteur : ");
            selectedShapeD2Field.setVisible(true);
            selectedShapeD2Field.valueProperty().unbindBidirectional(((TetrahedronMesh) shape).heightProperty());

            selectedShapeD3Label.setVisible(false);
            selectedShapeD3Field.setVisible(false);
            selectedShapeD4Label.setVisible(false);
            selectedShapeD4Field.setVisible(false);

        } else if (shape instanceof OctahedronMesh) {
            selectedShapeD1Label.setVisible(true);
            selectedShapeD1Label.setText("Hauteur : ");
            selectedShapeD1Field.setVisible(true);
            selectedShapeD1Field.valueProperty().unbindBidirectional(((OctahedronMesh) shape).heightProperty());

            selectedShapeD2Label.setVisible(true);
            selectedShapeD2Label.setText("Base : ");
            selectedShapeD2Field.setVisible(true);
            selectedShapeD2Field.valueProperty().unbindBidirectional(((OctahedronMesh) shape).hypotenuseProperty());

            selectedShapeD3Label.setVisible(false);
            selectedShapeD3Field.setVisible(false);
            selectedShapeD4Label.setVisible(false);
            selectedShapeD4Field.setVisible(false);

        } else if (shape instanceof MeshView) {
            selectedShapeD1Label.setVisible(false);
            selectedShapeD1Field.setVisible(false);
            selectedShapeD2Label.setVisible(false);
            selectedShapeD2Field.setVisible(false);
            selectedShapeD3Label.setVisible(false);
            selectedShapeD3Field.setVisible(false);
            selectedShapeD4Label.setVisible(false);
            selectedShapeD4Field.setVisible(false);
        }
    }

    private void bindScaleFields(Shape3D shape) {
        selectedShapeScaleX.valueProperty().bindBidirectional(shape.scaleXProperty());
        selectedShapeScaleY.valueProperty().bindBidirectional(shape.scaleYProperty());
        selectedShapeScaleZ.valueProperty().bindBidirectional(shape.scaleZProperty());
    }

    private void unbindScaleFields(Shape3D shape) {
        selectedShapeScaleX.valueProperty().unbindBidirectional(shape.scaleXProperty());
        selectedShapeScaleY.valueProperty().unbindBidirectional(shape.scaleYProperty());
        selectedShapeScaleZ.valueProperty().unbindBidirectional(shape.scaleZProperty());
    }

    private void updateRotationFields(Shape3D shape) {
        selectedShapeRotateX.valueProperty().setValue(getTotalRotationsX(shape));
        selectedShapeRotateY.valueProperty().setValue(getTotalRotationsY(shape));
        selectedShapeRotateZ.valueProperty().setValue(getTotalRotationsZ(shape));
    }

    public void toggleSettings() {
        final SplitPane.Divider divider = splitPane.getDividers().get(0);
        if (btnSettings.isSelected()) {
            if (settingsLastWidth == -1) {
                settingsLastWidth = settings.prefWidth(-1);
            }
            final double divPos = 1 - (settingsLastWidth / splitPane.getWidth());
            new Timeline(
                    new KeyFrame(Duration.seconds(0.3),
                            new EventHandler<ActionEvent>() {
                                @Override public void handle(ActionEvent event) {
                                    settings.setMinWidth(Region.USE_PREF_SIZE);
                                }
                            },
                            new KeyValue(divider.positionProperty(),divPos, Interpolator.EASE_BOTH)
                    )
            ).play();
        } else {
            settingsLastWidth = settings.getWidth();
            settings.setMinWidth(0);
            new Timeline(new KeyFrame(Duration.seconds(0.3),new KeyValue(divider.positionProperty(),1))).play();
        }
    }

    public void actionCreateShape(ShapeType shape) {
        switch (shape) {
            case CUBE:
                CubeMesh cube = new CubeMesh(100.0);
                contentModel.addShapeToContent(cube);
                break;
            case BOX:
                Box box = new Box(100.0, 200.0, 300.0);
                contentModel.addShapeToContent(box);
                break;
            case SPHERE:
                Sphere sphere = new Sphere(100.0);
                contentModel.addShapeToContent(sphere);
                break;
            case CYLINDER:
                Cylinder cylinder = new Cylinder(50.0, 100);
                contentModel.addShapeToContent(cylinder);
                break;
            case CONE:
                ConeMesh cone = new ConeMesh(50, 100);
                contentModel.addShapeToContent(cone);
                break;
            case PYRAMID:
                PyramidMesh pyramid = new PyramidMesh(50, 100);
                contentModel.addShapeToContent(pyramid);
                break;
            case TRIANGULAR_PRISM:
                TriangularPrismMesh prism = new TriangularPrismMesh(50, 100, 150);
                contentModel.addShapeToContent(prism);
                break;
            case TRAPEZOID:
                TrapezoidMesh trapezoid = new TrapezoidMesh(100, 200, 50, 75);
                contentModel.addShapeToContent(trapezoid);
                break;
            case CAPSULE:
                CapsuleMesh capsule = new CapsuleMesh(50, 200);
                contentModel.addShapeToContent(capsule);
                break;
            case SPHEROID:
                SpheroidMesh spheroid = new SpheroidMesh(100, 20);
                contentModel.addShapeToContent(spheroid);
                break;
            case SPRING:
                SpringMesh spring = new SpringMesh(100, 10, 50, 500);
                contentModel.addShapeToContent(spring);
                break;
            case TORUS:
                TorusMesh torus = new TorusMesh(200, 50);
                contentModel.addShapeToContent(torus);
                break;
            case TETRAHEDRON:
                TetrahedronMesh tetrahedron = new TetrahedronMesh(50);
                contentModel.addShapeToContent(tetrahedron);
                break;
            case OCTAHEDRON:
                OctahedronMesh octahedron = new OctahedronMesh(100, 100);
                contentModel.addShapeToContent(octahedron);
                break;
        }
    }

    public void actionCreateCylinder() {
        if (creationParameters.isSelected()) {
            Cylinder cylinder = new Cylinder(50, 100);
            cylinder.setMaterial(new PhongMaterial(Color.BLUE));
            newShapeController.clearSubScene();
            newShapeController.setNewShape(cylinder);
            newShapeStage.show();
        } else {
            actionCreateShape(ShapeType.CYLINDER);
        }
    }

    public void actionCreateSphere() {
        if (creationParameters.isSelected()) {
            Sphere sphere = new Sphere(50);
            sphere.setMaterial(new PhongMaterial(Color.BLUE));
            newShapeController.clearSubScene();
            newShapeController.setNewShape(sphere);
            newShapeStage.show();
        } else {
            actionCreateShape(ShapeType.SPHERE);
        }
    }

    public void actionCreateBox() {
        if (creationParameters.isSelected()) {
            Box box = new Box(50, 100, 150);
            box.setMaterial(new PhongMaterial(Color.BLUE));
            newShapeController.clearSubScene();
            newShapeController.setNewShape(box);
            newShapeStage.show();
        } else {
            actionCreateShape(ShapeType.BOX);
        }
    }

    public void actionCreateCube() {
        if (creationParameters.isSelected()) {
            CubeMesh cube = new CubeMesh(50);
            cube.setMaterial(new PhongMaterial(Color.BLUE));
            newShapeController.clearSubScene();
            newShapeController.setNewShape(cube);
            newShapeStage.show();
        } else {
            actionCreateShape(ShapeType.CUBE);
        }
    }

    public void actionCreateCone() {
        if (creationParameters.isSelected()) {
            ConeMesh cone = new ConeMesh(50, 100);
            cone.setMaterial(new PhongMaterial(Color.BLUE));
            newShapeController.clearSubScene();
            newShapeController.setNewShape(cone);
            newShapeStage.show();
        } else {
            actionCreateShape(ShapeType.CONE);
        }
    }

    public void actionCreatePyramid() {
        if (creationParameters.isSelected()) {
            PyramidMesh pyramid = new PyramidMesh(75, 100);
            pyramid.setMaterial(new PhongMaterial(Color.BLUE));
            newShapeController.clearSubScene();
            newShapeController.setNewShape(pyramid);
            newShapeStage.show();
        } else {
            actionCreateShape(ShapeType.PYRAMID);
        }
    }

    public void actionCreatePrism() {
        if (creationParameters.isSelected()) {
            TriangularPrismMesh prism = new TriangularPrismMesh(50, 100, 150);
            prism.setMaterial(new PhongMaterial(Color.BLUE));
            newShapeController.clearSubScene();
            newShapeController.setNewShape(prism);
            newShapeStage.show();
        } else {
            actionCreateShape(ShapeType.TRIANGULAR_PRISM);
        }
    }

    public void actionCreateTrapezoid() {
        if (creationParameters.isSelected()) {
            TrapezoidMesh trapezoid = new TrapezoidMesh(50, 100, 50, 50);
            trapezoid.setMaterial(new PhongMaterial(Color.BLUE));
            newShapeController.clearSubScene();
            newShapeController.setNewShape(trapezoid);
            newShapeStage.show();
        } else {
            actionCreateShape(ShapeType.TRAPEZOID);
        }
    }

    public void actionCreateCapsule() {
        if (creationParameters.isSelected()) {
            CapsuleMesh capsule = new CapsuleMesh(50, 100);
            capsule.setMaterial(new PhongMaterial(Color.BLUE));
            newShapeController.clearSubScene();
            newShapeController.setNewShape(capsule);
            newShapeStage.show();
        } else {
            actionCreateShape(ShapeType.CAPSULE);
        }
    }

    public void actionCreateSpheroid() {
        if (creationParameters.isSelected()) {
            SpheroidMesh spheroid = new SpheroidMesh(100, 50);
            spheroid.setMaterial(new PhongMaterial(Color.BLUE));
            newShapeController.clearSubScene();
            newShapeController.setNewShape(spheroid);
            newShapeStage.show();
        } else {
            actionCreateShape(ShapeType.SPHEROID);
        }
    }

    public void actionCreateSpring() {
        if (creationParameters.isSelected()) {
            SpringMesh spring = new SpringMesh(75, 10, 50, 500);
            spring.setMaterial(new PhongMaterial(Color.BLUE));
            newShapeController.clearSubScene();
            newShapeController.setNewShape(spring);
            newShapeStage.show();
        } else {
            actionCreateShape(ShapeType.SPRING);
        }
    }

    public void actionCreateTorus() {
        if (creationParameters.isSelected()) {
            TorusMesh torus = new TorusMesh(75, 10);
            torus.setMaterial(new PhongMaterial(Color.BLUE));
            newShapeController.clearSubScene();
            newShapeController.setNewShape(torus);
            newShapeStage.show();
        } else {
            actionCreateShape(ShapeType.TORUS);
        }
    }

    public void actionCreateTetrahedron() {
        if (creationParameters.isSelected()) {
            TetrahedronMesh tetrahedron = new TetrahedronMesh(75);
            tetrahedron.setMaterial(new PhongMaterial(Color.BLUE));
            newShapeController.clearSubScene();
            newShapeController.setNewShape(tetrahedron);
            newShapeStage.show();
        } else {
            actionCreateShape(ShapeType.TETRAHEDRON);
        }
    }

    public void actionCreateOctahedron() {
        if (creationParameters.isSelected()) {
            OctahedronMesh octahedron = new OctahedronMesh(50, 100);
            octahedron.setMaterial(new PhongMaterial(Color.BLUE));
            newShapeController.clearSubScene();
            newShapeController.setNewShape(octahedron);
            newShapeStage.show();
        } else {
            actionCreateShape(ShapeType.OCTAHEDRON);
        }
    }

    public void actionCreateParametricShape(Shape3D s) {
        Shape3D newShape = null;

        if (s instanceof CubeMesh)
            newShape = new CubeMesh(((CubeMesh) s).getSize());
        else if (s instanceof Box)
            newShape = new Box(((Box) s).getWidth(), ((Box) s).getHeight(), ((Box) s).getDepth());
        else if (s instanceof Sphere)
            newShape = new Sphere(((Sphere) s).getRadius());
        else if (s instanceof Cylinder)
            newShape = new Cylinder(((Cylinder) s).getRadius(), ((Cylinder) s).getHeight());
        else if (s instanceof PyramidMesh)
            newShape = new PyramidMesh(((PyramidMesh) s).getHeight(), ((PyramidMesh) s).getHypotenuse());
        else if (s instanceof ConeMesh)
            newShape = new ConeMesh(((ConeMesh) s).getRadius(), ((ConeMesh) s).getHeight());
        else if (s instanceof TrapezoidMesh)
            newShape = new TrapezoidMesh(((TrapezoidMesh) s).getSmallSize(), ((TrapezoidMesh) s).getBigSize(),
                    ((TrapezoidMesh) s).getHeight(), ((TrapezoidMesh) s).getDepth());
        else if (s instanceof TriangularPrismMesh)
            newShape = new TriangularPrismMesh(((TriangularPrismMesh) s).getWidth(), ((TriangularPrismMesh) s).getDepth(), ((TriangularPrismMesh) s).getHeight());
        else if (s instanceof CapsuleMesh)
            newShape = new CapsuleMesh(((CapsuleMesh) s).getRadius(), ((CapsuleMesh) s).getHeight());
        else if (s instanceof TorusMesh)
            newShape = new TorusMesh(((TorusMesh) s).getRadius(), ((TorusMesh) s).getTubeRadius());
        else if (s instanceof SpheroidMesh)
            newShape = new SpheroidMesh(((SpheroidMesh) s).getMajorRadius(), ((SpheroidMesh) s).getMinorRadius());
        else if (s instanceof SpringMesh)
            newShape = new SpringMesh(((SpringMesh) s).getMeanRadius(), ((SpringMesh) s).getWireRadius(),
                    ((SpringMesh) s).getPitch(), ((SpringMesh) s).getLength());
        else if (s instanceof TetrahedronMesh)
            newShape = new TetrahedronMesh(((TetrahedronMesh) s).getHeight());
        else if (s instanceof OctahedronMesh)
            newShape = new OctahedronMesh(((OctahedronMesh) s).getHeight(), ((OctahedronMesh) s).getHypotenuse());
        else if (s instanceof MeshView)
            newShape = new MeshView(((MeshView) s).getMesh());

        newShape.setTranslateX(s.getTranslateX());
        newShape.setTranslateY(s.getTranslateY());
        newShape.setTranslateZ(s.getTranslateZ());
        newShape.setMaterial(new PhongMaterial(((PhongMaterial) s.getMaterial()).getDiffuseColor()));
        contentModel.addShapeToContent(newShape);
    }

    private Shape3D cloneShape(Shape3D s) {
        Shape3D newShape = null;
        if (s instanceof CubeMesh)
            newShape = new CubeMesh(((CubeMesh) s).getSize());
        else if (s instanceof Box)
            newShape = new Box(((Box) s).getWidth(), ((Box) s).getHeight(), ((Box) s).getDepth());
        else if (s instanceof Sphere)
            newShape = new Sphere(((Sphere) s).getRadius());
        else if (s instanceof Cylinder)
            newShape = new Cylinder(((Cylinder) s).getRadius(), ((Cylinder) s).getHeight());
        else if (s instanceof PyramidMesh)
            newShape = new PyramidMesh(((PyramidMesh) s).getHeight(), ((PyramidMesh) s).getHypotenuse());
        else if (s instanceof ConeMesh)
            newShape = new ConeMesh(((ConeMesh) s).getRadius(), ((ConeMesh) s).getHeight());
        else if (s instanceof TrapezoidMesh)
            newShape = new TrapezoidMesh(((TrapezoidMesh) s).getSmallSize(), ((TrapezoidMesh) s).getBigSize(),
                    ((TrapezoidMesh) s).getHeight(), ((TrapezoidMesh) s).getDepth());
        else if (s instanceof TriangularPrismMesh)
            newShape = new TriangularPrismMesh(((TriangularPrismMesh) s).getWidth(), ((TriangularPrismMesh) s).getDepth(), ((TriangularPrismMesh) s).getHeight());
        else if (s instanceof CapsuleMesh)
            newShape = new CapsuleMesh(((CapsuleMesh) s).getRadius(), ((CapsuleMesh) s).getHeight());
        else if (s instanceof TorusMesh)
            newShape = new TorusMesh(((TorusMesh) s).getRadius(), ((TorusMesh) s).getTubeRadius());
        else if (s instanceof SpheroidMesh)
            newShape = new SpheroidMesh(((SpheroidMesh) s).getMajorRadius(), ((SpheroidMesh) s).getMinorRadius());
        else if (s instanceof SpringMesh)
            newShape = new SpringMesh(((SpringMesh) s).getMeanRadius(), ((SpringMesh) s).getWireRadius(),
                    ((SpringMesh) s).getPitch(), ((SpringMesh) s).getLength());
        else if (s instanceof TetrahedronMesh)
            newShape = new TetrahedronMesh(((TetrahedronMesh) s).getHeight());
        else if (s instanceof OctahedronMesh)
            newShape = new OctahedronMesh(((OctahedronMesh) s).getHeight(), ((OctahedronMesh) s).getHypotenuse());
        else if (s instanceof MeshView)
            newShape = new MeshView(((MeshView) s).getMesh());

        newShape.setTranslateX(s.getTranslateX());
        newShape.setTranslateY(s.getTranslateY());
        newShape.setTranslateZ(s.getTranslateZ());
        setTotalRotationsX(newShape, getTotalRotationsX(s));
        setTotalRotationsY(newShape, getTotalRotationsY(s));
        setTotalRotationsZ(newShape, getTotalRotationsZ(s));
        newShape.setScaleX(s.getScaleX());
        newShape.setScaleY(s.getScaleY());
        newShape.setScaleZ(s.getScaleZ());
        if (s.getMaterial() != null)
            newShape.setMaterial(new PhongMaterial(((PhongMaterial) s.getMaterial()).getDiffuseColor()));
        return newShape;
    }

    @FXML
    private void buttonDeleteSelectedShape() {
        contentModel.deleteShapeFromContent(selectedShapeProperty.get());
        selectedShapeProperty.set(null);
        contentModel.removeSelectedShapeAxis();
    }

    @FXML
    private void buttonDeleteAll() {
        selectedShapeProperty.set(null);
        contentModel.getContent().getChildren().clear();
        contentModel.removeSelectedShapeAxis();
    }

    @FXML
    private void buttonOkTranslate() {
        if (selectedShapeProperty.get() != null) {
            Shape3D selectedShape = selectedShapeProperty.get();
            double x = selectedShape.getTranslateX() + selectedShapeTranslateX.getValue().doubleValue();
            double y = selectedShape.getTranslateY() + selectedShapeTranslateY.getValue().doubleValue();
            double z = selectedShape.getTranslateZ() + selectedShapeTranslateZ.getValue().doubleValue();
            selectedShape.setTranslateX(x);
            selectedShape.setTranslateY(y);
            selectedShape.setTranslateZ(z);
        }
    }

    @FXML
    private void saveAs() {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("FXML", "*.fxml")
        );
        chooser.setTitle("Exporter le mod\u00e8le 3D sous format FXMl");
        File newFile = chooser.showSaveDialog(btnOpenMenu.getScene().getWindow());
        if (newFile != null) {
            String extension = newFile.getName().substring(newFile.getName().lastIndexOf('.')+1,newFile.getName().length()).toLowerCase();
            //System.out.println("extension = " + extension);
            if ("fxml".equals(extension)) {
                new GraphixFXMLExporter(newFile.getAbsolutePath()).export(contentModel.getContent());
            } else {
                System.err.println("Can not export a file of type [."+extension+"]");
            }
        }
    }

    @FXML
    private void saveAsSTLFile(ActionEvent e) {

        if (contentModel.getCSGContent() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible d'exporter sous format .STL");
            alert.setContentText("Veuillez cr\u00e9er une forme avec des op\u00e9rations bool\u00e9enes pour pouvoir exporter sous format .STL");
            alert.showAndWait();
        } else {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Exporter sous format .STL");
            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter(
                            "STL files (*.stl)",
                            "*.stl"));

            File f = fileChooser.showSaveDialog(null);
            if (f == null)
                return;

            String fName = f.getAbsolutePath();

            if (!fName.toLowerCase().endsWith(".stl"))
                fName += ".stl";

            try {
                eu.mihosoft.vrl.v3d.FileUtil.write(
                        Paths.get(fName), contentModel.getCSGContent().toStlString());
            } catch (IOException ex) {
                Logger.getLogger(Main3DController.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void saveAsOBJFile(ActionEvent e) {

        if (contentModel.getCSGContent() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible d'exporter sour format .OBJ");
            alert.setContentText("Veuillez cr\u00e9er une forme avec des op\u00e9rations bool\u00e9enes pour pouvoir exporter sous format .OBJ");
            alert.showAndWait();
        } else {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Exporter sous format .OBJ");
            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter(
                            "OBJ files (*.obj)",
                            "*.obj"));

            File f = fileChooser.showSaveDialog(null);
            if (f == null)
                return;

            String fName = f.getAbsolutePath();

            if (!fName.toLowerCase().endsWith(".obj"))
                fName += ".obj";

            try {
                eu.mihosoft.vrl.v3d.FileUtil.write(
                        Paths.get(fName), contentModel.getCSGContent().toObjString());
            } catch (IOException ex) {
                Logger.getLogger(Main3DController.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        }
    }

    public void printOnPaper() {

        PerspectiveCamera snCam = new PerspectiveCamera(false);
        snCam.setTranslateZ(-100);
        SnapshotParameters snapshotParameters = new SnapshotParameters();
        snapshotParameters.setCamera(snCam);
        snapshotParameters.setDepthBuffer(true);
        snapshotParameters.setFill(Color.TRANSPARENT);
        int height = (int) contentModel.getSubScene().getHeight();
        int width = (int) contentModel.getSubScene().getWidth();

        WritableImage snapshot = new WritableImage(width, height);

        Paint tempPaint = contentModel.getSubScene().getFill();
        boolean tempShowAxis = contentModel.getShowAxis();
        if (contentModel.getSubScene().fillProperty().isBound())
            contentModel.getSubScene().fillProperty().unbind();
        if (contentModel.showAxisProperty().isBound())
            contentModel.showAxisProperty().unbind();

        contentModel.getSubScene().setFill(Color.TRANSPARENT);
        contentModel.setShowAxis(false);

        contentModel.getSubScene().snapshot(snapshotParameters, snapshot);

        contentModel.getSubScene().setFill(tempPaint);
        contentModel.getSubScene().fillProperty().bind(backgroundColorPicker.valueProperty());
        contentModel.setShowAxis(tempShowAxis);
        contentModel.showAxisProperty().bind(showAxisCheckBox.selectedProperty());

        SubScenePrintDialog printDialog = new SubScenePrintDialog(true, "Imprimer la sc\u00e8ne", new ImageView(snapshot));
        printDialog.show();
        /*
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        System.out.println("Number of print services: " + printServices.length);

        for (PrintService printer : printServices)
            System.out.println("Printer: " + printer.getName());
            */
        /*
        if (printer != null) {
            PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.DEFAULT);
            double scaleX = pageLayout.getPrintableWidth() / contentModel.getSubScene().getBoundsInParent().getWidth();
            double scaleY = pageLayout.getPrintableHeight() / contentModel.getSubScene().getBoundsInParent().getHeight();
            Node node = contentModel.getSubScene();
            node.getTransforms().add(new Scale(scaleX, scaleY));
            PrinterJob job = PrinterJob.createPrinterJob();
            if (job != null) {
                boolean success = job.printPage(node);
                if (success) {
                    job.endJob();
                }
            }
        }*/
    }

    @FXML
    public void saveAsPNG() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Exporter comme une image .PNG");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image files (*.png)", "*.png"));
        File f = fileChooser.showSaveDialog(null);
        if (f == null) return;
        String fName = f.getAbsolutePath();
        if (!fName.toLowerCase().endsWith(".png")) {fName += ".png";}

        int snWidth = 2048;
        int snHeight = 2048;
        double realWidth = contentModel.getSubScene().getWidth();
        double realHeight = contentModel.getSubScene().getHeight();
        double scaleX = snWidth  / realWidth;
        double scaleY = snHeight / realHeight;
        double scale = Math.min(scaleX, scaleY);

        PerspectiveCamera snCam = new PerspectiveCamera(false);
        snCam.setTranslateZ(-100);
        SnapshotParameters snapshotParameters = new SnapshotParameters();
        snapshotParameters.setTransform(new Scale(scale, scale));
        snapshotParameters.setCamera(snCam);
        snapshotParameters.setDepthBuffer(true);
        snapshotParameters.setFill(Color.TRANSPARENT);

        WritableImage snapshot = new WritableImage(snWidth, (int) (realHeight * scale));
        contentModel.getSubScene().snapshot(snapshotParameters, snapshot);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", new File(fName));
        } catch (IOException ex) {
            Logger.getLogger(Main3DController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void saveAsPNGTransparentBackground() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Exporter comme une image .PNG (sur fond transparent");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image files (*.png)", "*.png"));
        File f = fileChooser.showSaveDialog(null);
        if (f == null) return;
        String fName = f.getAbsolutePath();
        if (!fName.toLowerCase().endsWith(".png")) {
            fName += ".png";
        }

        int snWidth = 2048;
        int snHeight = 2048;
        double realWidth = contentModel.getSubScene().getWidth();
        double realHeight = contentModel.getSubScene().getHeight();
        double scaleX = snWidth / realWidth;
        double scaleY = snHeight / realHeight;
        double scale = Math.min(scaleX, scaleY);

        PerspectiveCamera snCam = new PerspectiveCamera(false);
        snCam.setTranslateZ(-100);
        SnapshotParameters snapshotParameters = new SnapshotParameters();
        snapshotParameters.setTransform(new Scale(scale, scale));
        snapshotParameters.setCamera(snCam);
        snapshotParameters.setDepthBuffer(true);
        snapshotParameters.setFill(Color.TRANSPARENT);

        WritableImage snapshot = new WritableImage(snWidth, (int) (realHeight * scale));

        Paint tempPaint = contentModel.getSubScene().getFill();
        boolean tempShowAxis = contentModel.getShowAxis();
        if (contentModel.getSubScene().fillProperty().isBound())
            contentModel.getSubScene().fillProperty().unbind();
        if (contentModel.showAxisProperty().isBound())
            contentModel.showAxisProperty().unbind();

        contentModel.getSubScene().setFill(Color.TRANSPARENT);
        contentModel.setShowAxis(false);

        contentModel.getSubScene().snapshot(snapshotParameters, snapshot);

        contentModel.getSubScene().setFill(tempPaint);
        contentModel.getSubScene().fillProperty().bind(backgroundColorPicker.valueProperty());
        contentModel.setShowAxis(tempShowAxis);
        contentModel.showAxisProperty().bind(showAxisCheckBox.selectedProperty());

        try {
            ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", new File(fName));
        } catch (IOException ex) {
            Logger.getLogger(Main3DController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void open() {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Supported files", Importer3D.getSupportedFormatExtensionFilters()));
        chooser.setTitle("Choisissez un fichier");
        File newFile = chooser.showOpenDialog(btnOpenMenu.getScene().getWindow());
        if (newFile != null) {
            load(newFile);
        }
    }

    private void load(File file) {
        try {
            doLoad(file.toURI().toURL().toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void load(String fileUrl) {
        try {
            doLoad(fileUrl);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void doLoad(String fileUrl) {
        try {
            Pair<Node,Timeline> content = Importer3D.loadIncludingAnimation(
                    fileUrl, true);
            Timeline timeline = content.getValue();
            Node root = content.getKey();
            if (true) {
                new Optimizer(timeline, root, true).optimize();
            }
            contentModel.getContent().getChildren().addAll(((Group) root).getChildren());

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private double getTotalRotationsX(Shape3D shape) {
        double result = 0;
        for (Transform transform : shape.getTransforms())
            if (transform instanceof Rotate) {
                Rotate r = (Rotate) transform;
                if (r.getAxis().getX() == 1 && r.getAxis().getY() == 0 && r.getAxis().getZ() == 0)
                    result += ((Rotate) transform).getAngle();
            }
        return result;
    }

    private double getTotalRotationsY(Shape3D shape) {
        double result = 0;
        for (Transform transform : shape.getTransforms())
            if (transform instanceof Rotate) {
                Rotate r = (Rotate) transform;
                if (r.getAxis().getX() == 0 && r.getAxis().getY() == 1 && r.getAxis().getZ() == 0)
                    result += ((Rotate) transform).getAngle();
            }
        return result;
    }

    private double getTotalRotationsZ(Shape3D shape) {
        double result = 0;
        for (Transform transform : shape.getTransforms())
            if (transform instanceof Rotate) {
                Rotate r = (Rotate) transform;
                if (r.getAxis().getX() == 0 && r.getAxis().getY() == 0 && r.getAxis().getZ() == 1)
                    result += ((Rotate) transform).getAngle();
            }
        return result;
    }

    public void setTotalRotationsX(Shape3D shape, double angle) {
        double totalX = getTotalRotationsX(shape);
        double totalY = getTotalRotationsY(shape);
        double totalZ = getTotalRotationsZ(shape);
        shape.getTransforms().clear();
        shape.getTransforms().addAll(
                new Rotate((angle % 360), Rotate.X_AXIS),
                new Rotate(totalY, Rotate.Y_AXIS),
                new Rotate(totalZ, Rotate.Z_AXIS));
    }

    public void setTotalRotationsY(Shape3D shape, double angle) {
        double totalX = getTotalRotationsX(shape);
        double totalY = getTotalRotationsY(shape);
        double totalZ = getTotalRotationsZ(shape);

        shape.getTransforms().clear();
        shape.getTransforms().addAll(
                new Rotate(totalX, Rotate.X_AXIS),
                new Rotate((angle % 360), Rotate.Y_AXIS),
                new Rotate(totalZ, Rotate.Z_AXIS));
    }

    public void setTotalRotationsZ(Shape3D shape, double angle) {
        double totalX = getTotalRotationsX(shape);
        double totalY = getTotalRotationsY(shape);
        double totalZ = getTotalRotationsZ(shape);

        shape.getTransforms().clear();
        shape.getTransforms().addAll(
                new Rotate(totalX, Rotate.X_AXIS),
                new Rotate(totalY, Rotate.Y_AXIS),
                new Rotate((angle % 360), Rotate.Z_AXIS));
    }

    public CSG meshViewToCSG(MeshView mesh) {
        return MeshToCSG(mesh.getMesh());
    }

    public CSG MeshToCSG(Mesh mesh) {

        List<eu.mihosoft.vrl.v3d.Polygon> polygons = new ArrayList<>();
        List<Vector3d> vertices = new ArrayList<>();
        if (mesh instanceof TriangleMesh) {
            // Get faces
            ObservableFaceArray faces = ((TriangleMesh) mesh).getFaces();
            int[] f = new int[faces.size()];
            faces.toArray(f);

            // Get vertices
            ObservableFloatArray points = ((TriangleMesh) mesh).getPoints();
            float[] p = new float[points.size()];
            points.toArray(p);

            // convert faces to polygons
            for (int i = 0; i < faces.size() / 6; i++) {
                int i0 = f[6 * i], i1 = f[6 * i + 2], i2 = f[6 * i + 4];
                vertices.add(new Vector3d(p[3 * i0], p[3 * i0 + 1], p[3 * i0 + 2]));
                vertices.add(new Vector3d(p[3 * i1], p[3 * i1 + 1], p[3 * i1 + 2]));
                vertices.add(new Vector3d(p[3 * i2], p[3 * i2 + 1], p[3 * i2 + 2]));
                polygons.add(eu.mihosoft.vrl.v3d.Polygon.fromPoints(vertices));
                vertices = new ArrayList<>();
            }
        }

        return CSG.fromPolygons(new PropertyStorage(), polygons);
    }

    private CSG convertShapeToCSG(Shape3D s) {
        double x = s.getTranslateX();
        double y = s.getTranslateY();
        double z = s.getTranslateZ();
        double rotX = getTotalRotationsX(s);
        double rotY = getTotalRotationsY(s);
        double rotZ = getTotalRotationsZ(s);
        double sX = s.getScaleX();
        double sY = s.getScaleY();
        double sZ = s.getScaleZ();

        CSG csg = null;

        if (s instanceof CubeMesh)
            csg = (new eu.mihosoft.vrl.v3d.Cube(((CubeMesh) s).getSize())).toCSG();
        else if (s instanceof Box)
            csg = (new eu.mihosoft.vrl.v3d.Cube(((Box) s).getWidth(), ((Box) s).getHeight(), ((Box) s).getDepth())).toCSG();
        else if (s instanceof Cylinder) {
            FrustumMesh temp = new FrustumMesh(((Cylinder) s).getRadius(), ((Cylinder) s).getRadius(), ((Cylinder) s).getHeight());
            csg = meshViewToCSG(temp);
        }
        else if (s instanceof Sphere)
            csg = (new eu.mihosoft.vrl.v3d.Sphere(((Sphere) s).getRadius())).toCSG();
        else if (s instanceof MeshView)
            csg = meshViewToCSG((MeshView) s);
        if (csg != null)
            csg = csg.transformed(eu.mihosoft.vrl.v3d.Transform.unity().translate(x, y, z).rot(rotX, rotY, rotZ).scale(sX, sY, sZ));
        return csg;
    }

    private MeshView convertCSGToMeshView(CSG csg) {
        return csg.toJavaFXMesh().getAsMeshViews().get(0);
    }

    private CSG CSGOperation(Group shapesList, CSGBooleanOperation operation) {
        List<CSG> CSGList = new ArrayList<>();
        CSG first = convertShapeToCSG((Shape3D) shapesList.getChildren().get(0));
        shapesList.getChildren().remove(0);

        for (Node shape : shapesList.getChildren())
            CSGList.add(convertShapeToCSG((Shape3D) shape));

        CSG result = null;
        switch (operation) {
            case UNION:
                result = first.union(CSGList);
                break;
            case INTERSECTION:
                result = first.intersect(CSGList);
                break;
            case DIFFERENCE:
                result = first.difference(CSGList);
        }
        contentModel.setCSGContent(result);
        return result;
    }

    public void btnUnion() {
        CSG result = CSGOperation(CSGselectedShapesList.get(), CSGBooleanOperation.UNION);
        MeshView meshView = convertCSGToMeshView(result);
        booleanOperationResultController.clearSubScene();
        booleanOperationResultController.setMeshViewResult(meshView);
        booleanOperationStage.show();
    }

    public void btnIntersection() {
        CSG result = CSGOperation(CSGselectedShapesList.get(), CSGBooleanOperation.INTERSECTION);
        MeshView meshView = convertCSGToMeshView(result);
        booleanOperationResultController.clearSubScene();
        booleanOperationResultController.setMeshViewResult(meshView);
        booleanOperationStage.show();
    }

    public void btnDifference() {
        CSG result = CSGOperation(CSGselectedShapesList.get(), CSGBooleanOperation.DIFFERENCE);
        MeshView meshView = convertCSGToMeshView(result);
        booleanOperationResultController.clearSubScene();
        booleanOperationResultController.setMeshViewResult(meshView);
        booleanOperationStage.show();
    }

    public void addBooleanOperationResult(MeshView result) {
        MeshView newOne = new MeshView(result.getMesh());
        newOne.setMaterial(result.getMaterial());
        newOne.setTranslateZ(100);

        contentModel.addShapeToContent(newOne);
        CSGModeCheckBox.setSelected(false);
    }

    private String shapeName(Shape3D shape) {
        if (shape == null)
            return "Aucune forme";
        else if (shape instanceof CubeMesh)
            return "Cube";
        else if (shape instanceof Box)
            return "Parall\u00e9lipip\u00e8de";
        else if (shape instanceof Sphere)
            return "Sph\u00e8re";
        else if (shape instanceof Cylinder)
            return "Cylindre";
        else if (shape instanceof ConeMesh)
            return "Cone";
        else if (shape instanceof PyramidMesh)
            return "Pyramide";
        else if (shape instanceof TriangularPrismMesh)
            return "Prisme";
        else if (shape instanceof TrapezoidMesh)
            return "Tap\u00e8zoide";
        else if (shape instanceof CapsuleMesh)
            return "Capsule";
        else if (shape instanceof SpheroidMesh)
            return "Sph\u00e8roide";
        else if (shape instanceof SpringMesh)
            return "Ressort";
        else if (shape instanceof TorusMesh)
            return "Tore";
        else if (shape instanceof TetrahedronMesh)
            return "Tetra\u00e8dre";
        else if (shape instanceof OctahedronMesh)
            return "Octa\u00e8dre";
        else if (shape instanceof MeshView)
            return "Forme quelconque";
        else
            return "Forme inconnue";
    }

    private String statusBarNumberOfNodes() {
        return String.format("La sc\u00e8ne contient [%d] formes 3D.\t\t", contentModel.getContent().getChildren().size());
    }

    private String statusBarSelectedShape() {
        return String.format("\t\tLa forme 3D s\u00e9lectionn\u00e9e est : %s", shapeName(selectedShapeProperty.get()).toLowerCase());
    }

    ////// NEEDED FOR UNDO/REDO ///////////////////////////////////////////////////////////////////

    private enum ShapeType {
        CUBE, BOX, SPHERE, CYLINDER, CONE, PYRAMID, TRIANGULAR_PRISM, TRAPEZOID, CAPSULE, SPHEROID,
        SPRING, TORUS, TETRAHEDRON, OCTAHEDRON
    }

    private enum CSGBooleanOperation {UNION, INTERSECTION, DIFFERENCE}

    private class TreeItemImpl extends TreeItem<Node> {

        public TreeItemImpl(Node node) {
            super(node);
            if (node instanceof Parent) {
                for (Node n : ((Parent) node).getChildrenUnmodifiable()) {
                    getChildren().add(new TreeItemImpl(n));
                }
            }
            node.setOnMouseClicked(t -> {
                TreeItem<Node> parent = getParent();
                while (parent != null) {
                    parent.setExpanded(true);
                    parent = parent.getParent();
                }
                hierarachyTreeTable.getSelectionModel().select(TreeItemImpl.this);
                hierarachyTreeTable.scrollTo(hierarachyTreeTable.getSelectionModel().getSelectedIndex());
                t.consume();
            });
        }
    }

    private class CSGTreeItemImpl extends TreeItem<Node> {

        public CSGTreeItemImpl(Node node) {
            super(node);
            if (node instanceof Parent) {
                for (Node n : ((Parent) node).getChildrenUnmodifiable()) {
                    getChildren().add(new CSGTreeItemImpl(n));
                }
            }
            node.setOnMouseClicked(t -> {
                TreeItem<Node> parent = getParent();
                while (parent != null) {
                    parent.setExpanded(true);
                    parent = parent.getParent();
                }
                CSGModeTreeTable.getSelectionModel().select(CSGTreeItemImpl.this);
                CSGModeTreeTable.scrollTo(CSGModeTreeTable.getSelectionModel().getSelectedIndex());
                t.consume();
            });
        }
    }
}
