package com.yahiab.graphix.controller._3d;

import com.yahiab.graphix.controller._3d.controls.numberspinner.NumberSpinner;
import com.yahiab.graphix.view.SVGPathIcons;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.fxyz.shapes.primitives.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Yahia on 25/04/2015.
 */
public class NewShapeController implements Initializable {

    private final PerspectiveCamera camera = new PerspectiveCamera(true);
    private Main3DController parentController;
    private Stage newShapeStage;
    private ObjectProperty<Group> content = new SimpleObjectProperty<>();
    private Timeline animation;
    private Shape3D shapeToCreate;

    @FXML
    private Button btnPlay;
    @FXML
    private Button btnPause;
    @FXML
    private SubScene newShapeSubscene;
    @FXML
    private Label newShapeDimensionLabel1;
    @FXML
    private Label newShapeDimensionLabel2;
    @FXML
    private Label newShapeDimensionLabel3;
    @FXML
    private Label newShapeDimensionLabel4;
    @FXML
    private NumberSpinner newShapeDimensionField1;
    @FXML
    private NumberSpinner newShapeDimensionField2;
    @FXML
    private NumberSpinner newShapeDimensionField3;
    @FXML
    private NumberSpinner newShapeDimensionField4;
    @FXML
    private NumberSpinner newShapeXCoord;
    @FXML
    private NumberSpinner newShapeYCoord;
    @FXML
    private NumberSpinner newShapeZCoord;
    @FXML
    private Slider newShapeSlider;

    /////////////////////////////////////////////////////////////////////////////////

    {
        camera.setNearClip(1.0);
        camera.setFarClip(10000.0);
        camera.getTransforms().addAll(
                new Rotate(-20, Rotate.Y_AXIS),
                new Rotate(-20, Rotate.X_AXIS),
                new Rotate(-20, Rotate.Z_AXIS),
                new Translate(0, 0, -500)
        );
        content.set(new Group());
    }

    public ObjectProperty<Group> contentProperty() {
        return content;
    }

    public PerspectiveCamera getCamera() {
        return camera;
    }

    public void bindNewShapeDimensionsFields(Shape3D shape) {
        if (shape instanceof CubeMesh) {
            newShapeDimensionLabel1.setVisible(false);
            newShapeDimensionField1.setVisible(false);

            newShapeDimensionLabel2.setVisible(true);
            newShapeDimensionLabel2.setText("Ar\u00eate : ");
            newShapeDimensionField2.setVisible(true);
            newShapeDimensionField2.valueProperty().bindBidirectional(((CubeMesh) shape).sizeProperty());

            newShapeDimensionLabel3.setVisible(false);
            newShapeDimensionField3.setVisible(false);
            newShapeDimensionLabel4.setVisible(false);
            newShapeDimensionField4.setVisible(false);
        } else if (shape instanceof Box) {
            newShapeDimensionLabel1.setVisible(true);
            newShapeDimensionLabel1.setText("Hauteur : ");
            newShapeDimensionField1.setVisible(true);
            newShapeDimensionField1.valueProperty().bindBidirectional(((Box) shape).heightProperty());

            newShapeDimensionLabel2.setVisible(true);
            newShapeDimensionLabel2.setText("Largeur : ");
            newShapeDimensionField2.setVisible(true);
            newShapeDimensionField2.valueProperty().bindBidirectional(((Box) shape).widthProperty());

            newShapeDimensionLabel3.setVisible(true);
            newShapeDimensionLabel3.setText("Profondeur : ");
            newShapeDimensionField3.setVisible(true);
            newShapeDimensionField3.valueProperty().bindBidirectional(((Box) shape).depthProperty());

            newShapeDimensionLabel4.setVisible(false);
            newShapeDimensionField4.setVisible(false);

        } else if (shape instanceof Cylinder) {
            newShapeDimensionLabel1.setVisible(true);
            newShapeDimensionLabel1.setText("Hauteur : ");
            newShapeDimensionField1.setVisible(true);
            newShapeDimensionField1.valueProperty().bindBidirectional(((Cylinder) shape).heightProperty());

            newShapeDimensionLabel2.setVisible(true);
            newShapeDimensionLabel2.setText("Rayon : ");
            newShapeDimensionField2.setVisible(true);
            newShapeDimensionField2.valueProperty().bindBidirectional(((Cylinder) shape).radiusProperty());

            newShapeDimensionLabel3.setVisible(false);
            newShapeDimensionField3.setVisible(false);
            newShapeDimensionLabel4.setVisible(false);
            newShapeDimensionField4.setVisible(false);

        } else if (shape instanceof Sphere) {
            newShapeDimensionLabel1.setVisible(false);
            newShapeDimensionField1.setVisible(false);

            newShapeDimensionLabel2.setVisible(true);
            newShapeDimensionLabel2.setText("Rayon : ");
            newShapeDimensionField2.setVisible(true);
            newShapeDimensionField2.valueProperty().bindBidirectional(((Sphere) shape).radiusProperty());

            newShapeDimensionLabel3.setVisible(false);
            newShapeDimensionField3.setVisible(false);
            newShapeDimensionLabel4.setVisible(false);
            newShapeDimensionField4.setVisible(false);

        } else if (shape instanceof PyramidMesh) {

            newShapeDimensionLabel1.setVisible(true);
            newShapeDimensionLabel1.setText("Hauteur : ");
            newShapeDimensionField1.setVisible(true);
            newShapeDimensionField1.valueProperty().bindBidirectional(((PyramidMesh) shape).heightProperty());

            newShapeDimensionLabel2.setVisible(true);
            newShapeDimensionLabel2.setText("Base : ");
            newShapeDimensionField2.setVisible(true);
            newShapeDimensionField2.valueProperty().bindBidirectional(((PyramidMesh) shape).hypotenuseProperty());

            newShapeDimensionLabel3.setVisible(false);
            newShapeDimensionField3.setVisible(false);
            newShapeDimensionLabel4.setVisible(false);
            newShapeDimensionField4.setVisible(false);

        } else if (shape instanceof ConeMesh) {

            newShapeDimensionLabel1.setVisible(true);
            newShapeDimensionLabel1.setText("Hauteur : ");
            newShapeDimensionField1.setVisible(true);
            newShapeDimensionField1.valueProperty().bindBidirectional(((ConeMesh) shape).heightProperty());

            newShapeDimensionLabel2.setVisible(true);
            newShapeDimensionLabel2.setText("Rayon : ");
            newShapeDimensionField2.setVisible(true);
            newShapeDimensionField2.valueProperty().bindBidirectional(((ConeMesh) shape).radiusProperty());

            newShapeDimensionLabel3.setVisible(false);
            newShapeDimensionField3.setVisible(false);
            newShapeDimensionLabel4.setVisible(false);
            newShapeDimensionField4.setVisible(false);

        } else if (shape instanceof CapsuleMesh) {

            newShapeDimensionLabel1.setVisible(true);
            newShapeDimensionLabel1.setText("Hauteur : ");
            newShapeDimensionField1.setVisible(true);
            newShapeDimensionField1.valueProperty().bindBidirectional(((CapsuleMesh) shape).heightProperty());

            newShapeDimensionLabel2.setVisible(true);
            newShapeDimensionLabel2.setText("Rayon : ");
            newShapeDimensionField2.setVisible(true);
            newShapeDimensionField2.valueProperty().bindBidirectional(((CapsuleMesh) shape).radiusProperty());

            newShapeDimensionLabel3.setVisible(false);
            newShapeDimensionField3.setVisible(false);
            newShapeDimensionLabel4.setVisible(false);
            newShapeDimensionField4.setVisible(false);

        } else if (shape instanceof TorusMesh) {
            newShapeDimensionLabel1.setVisible(true);
            newShapeDimensionLabel1.setText("Rayon : ");
            newShapeDimensionField1.setVisible(true);
            newShapeDimensionField1.valueProperty().bindBidirectional(((TorusMesh) shape).radiusProperty());

            newShapeDimensionLabel2.setVisible(true);
            newShapeDimensionLabel2.setText("Rayon du tube : ");
            newShapeDimensionField2.setVisible(true);
            newShapeDimensionField2.valueProperty().bindBidirectional(((TorusMesh) shape).tubeRadiusProperty());

            newShapeDimensionLabel3.setVisible(false);
            newShapeDimensionField3.setVisible(false);
            newShapeDimensionLabel4.setVisible(false);
            newShapeDimensionField4.setVisible(false);

        } else if (shape instanceof SpheroidMesh) {
            newShapeDimensionLabel1.setVisible(true);
            newShapeDimensionLabel1.setText("Grand rayon : ");
            newShapeDimensionField1.setVisible(true);
            newShapeDimensionField1.valueProperty().bindBidirectional(((SpheroidMesh) shape).majorRadiusProperty());

            newShapeDimensionLabel2.setVisible(true);
            newShapeDimensionLabel2.setText("Petit rayon : ");
            newShapeDimensionField2.setVisible(true);
            newShapeDimensionField2.valueProperty().bindBidirectional(((SpheroidMesh) shape).minorRadiusProperty());

            newShapeDimensionLabel3.setVisible(false);
            newShapeDimensionField3.setVisible(false);
            newShapeDimensionLabel4.setVisible(false);
            newShapeDimensionField4.setVisible(false);

        } else if (shape instanceof SpringMesh) {

            newShapeDimensionLabel1.setVisible(true);
            newShapeDimensionLabel1.setText("Rayon moyen : ");
            newShapeDimensionField1.setVisible(true);
            newShapeDimensionField1.valueProperty().bindBidirectional(((SpringMesh) shape).meanRadiusProperty());

            newShapeDimensionLabel2.setVisible(true);
            newShapeDimensionLabel2.setText("Rayon du tube : ");
            newShapeDimensionField2.setVisible(true);
            newShapeDimensionField2.valueProperty().bindBidirectional(((SpringMesh) shape).wireRadiusProperty());

            newShapeDimensionLabel3.setVisible(true);
            newShapeDimensionLabel3.setText("Pas : ");
            newShapeDimensionField3.setVisible(true);
            newShapeDimensionField3.valueProperty().bindBidirectional(((SpringMesh) shape).pitchProperty());

            newShapeDimensionLabel4.setVisible(true);
            newShapeDimensionLabel4.setText("Longueur : ");
            newShapeDimensionField4.setVisible(true);
            newShapeDimensionField4.valueProperty().bindBidirectional(((SpringMesh) shape).lengthProperty());

        } else if (shape instanceof TrapezoidMesh) {

            newShapeDimensionLabel1.setVisible(true);
            newShapeDimensionLabel1.setText("Petite base : ");
            newShapeDimensionField1.setVisible(true);
            newShapeDimensionField1.valueProperty().bindBidirectional(((TrapezoidMesh) shape).sizeSmallProperty());

            newShapeDimensionLabel2.setVisible(true);
            newShapeDimensionLabel2.setText("Grande base : ");
            newShapeDimensionField2.setVisible(true);
            newShapeDimensionField2.valueProperty().bindBidirectional(((TrapezoidMesh) shape).sizeBigProperty());

            newShapeDimensionLabel3.setVisible(true);
            newShapeDimensionLabel3.setText("Hauteur : ");
            newShapeDimensionField3.setVisible(true);
            newShapeDimensionField3.valueProperty().bindBidirectional(((TrapezoidMesh) shape).heightProperty());

            newShapeDimensionLabel4.setVisible(true);
            newShapeDimensionLabel4.setText("Profondeur : ");
            newShapeDimensionField4.setVisible(true);
            newShapeDimensionField4.valueProperty().bindBidirectional(((TrapezoidMesh) shape).depthProperty());

        } else if (shape instanceof TriangularPrismMesh) {

            newShapeDimensionLabel1.setVisible(true);
            newShapeDimensionLabel1.setText("Hauteur : ");
            newShapeDimensionField1.setVisible(true);
            newShapeDimensionField1.valueProperty().bindBidirectional(((TriangularPrismMesh) shape).heightProperty());

            newShapeDimensionLabel2.setVisible(true);
            newShapeDimensionLabel2.setText("Largeur : ");
            newShapeDimensionField2.setVisible(true);
            newShapeDimensionField2.valueProperty().bindBidirectional(((TriangularPrismMesh) shape).widthProperty());

            newShapeDimensionLabel3.setVisible(true);
            newShapeDimensionLabel3.setText("Profondeur : ");
            newShapeDimensionField3.setVisible(true);
            newShapeDimensionField3.valueProperty().bindBidirectional(((TriangularPrismMesh) shape).depthProperty());

            newShapeDimensionLabel4.setVisible(false);
            newShapeDimensionField4.setVisible(false);

        } else if (shape instanceof TetrahedronMesh) {

            newShapeDimensionLabel1.setVisible(false);
            newShapeDimensionField1.setVisible(false);

            newShapeDimensionLabel2.setVisible(true);
            newShapeDimensionLabel2.setText("Hauteur : ");
            newShapeDimensionField2.setVisible(true);
            newShapeDimensionField2.valueProperty().bindBidirectional(((TetrahedronMesh) shape).heightProperty());

            newShapeDimensionLabel3.setVisible(false);
            newShapeDimensionField3.setVisible(false);
            newShapeDimensionLabel4.setVisible(false);
            newShapeDimensionField4.setVisible(false);

        } else if (shape instanceof OctahedronMesh) {

            newShapeDimensionLabel1.setVisible(true);
            newShapeDimensionLabel1.setText("Hauteur : ");
            newShapeDimensionField1.setVisible(true);
            newShapeDimensionField1.valueProperty().bindBidirectional(((OctahedronMesh) shape).heightProperty());

            newShapeDimensionLabel2.setVisible(true);
            newShapeDimensionLabel2.setText("Base : ");
            newShapeDimensionField2.setVisible(true);
            newShapeDimensionField2.valueProperty().bindBidirectional(((OctahedronMesh) shape).hypotenuseProperty());

            newShapeDimensionLabel3.setVisible(false);
            newShapeDimensionField3.setVisible(false);
            newShapeDimensionLabel4.setVisible(false);
            newShapeDimensionField4.setVisible(false);

        }
    }

    public void unbindNewShapeDimensionsFields(Shape3D shape) {
        if (shape instanceof CubeMesh) {
            newShapeDimensionLabel1.setVisible(false);
            newShapeDimensionField1.setVisible(false);

            newShapeDimensionLabel2.setVisible(true);
            newShapeDimensionLabel2.setText("Ar\u00eate : ");
            newShapeDimensionField2.setVisible(true);
            newShapeDimensionField2.valueProperty().unbindBidirectional(((CubeMesh) shape).sizeProperty());

            newShapeDimensionLabel3.setVisible(false);
            newShapeDimensionField3.setVisible(false);
            newShapeDimensionLabel4.setVisible(false);
            newShapeDimensionField4.setVisible(false);
        } else if (shape instanceof Box) {
            newShapeDimensionLabel1.setVisible(true);
            newShapeDimensionLabel1.setText("Hauteur : ");
            newShapeDimensionField1.setVisible(true);
            newShapeDimensionField1.valueProperty().unbindBidirectional(((Box) shape).heightProperty());

            newShapeDimensionLabel2.setVisible(true);
            newShapeDimensionLabel2.setText("Largeur : ");
            newShapeDimensionField2.setVisible(true);
            newShapeDimensionField2.valueProperty().unbindBidirectional(((Box) shape).widthProperty());

            newShapeDimensionLabel3.setVisible(true);
            newShapeDimensionLabel3.setText("Profondeur : ");
            newShapeDimensionField3.setVisible(true);
            newShapeDimensionField3.valueProperty().unbindBidirectional(((Box) shape).depthProperty());

            newShapeDimensionLabel4.setVisible(false);
            newShapeDimensionField4.setVisible(false);

        } else if (shape instanceof Cylinder) {
            newShapeDimensionLabel1.setVisible(true);
            newShapeDimensionLabel1.setText("Hauteur : ");
            newShapeDimensionField1.setVisible(true);
            newShapeDimensionField1.valueProperty().unbindBidirectional(((Cylinder) shape).heightProperty());

            newShapeDimensionLabel2.setVisible(true);
            newShapeDimensionLabel2.setText("Rayon : ");
            newShapeDimensionField2.setVisible(true);
            newShapeDimensionField2.valueProperty().unbindBidirectional(((Cylinder) shape).radiusProperty());

            newShapeDimensionLabel3.setVisible(false);
            newShapeDimensionField3.setVisible(false);
            newShapeDimensionLabel4.setVisible(false);
            newShapeDimensionField4.setVisible(false);

        } else if (shape instanceof Sphere) {
            newShapeDimensionLabel1.setVisible(false);
            newShapeDimensionField1.setVisible(false);

            newShapeDimensionLabel2.setVisible(true);
            newShapeDimensionLabel2.setText("Rayon : ");
            newShapeDimensionField2.setVisible(true);
            newShapeDimensionField2.valueProperty().unbindBidirectional(((Sphere) shape).radiusProperty());

            newShapeDimensionLabel3.setVisible(false);
            newShapeDimensionField3.setVisible(false);
            newShapeDimensionLabel4.setVisible(false);
            newShapeDimensionField4.setVisible(false);

        } else if (shape instanceof PyramidMesh) {

            newShapeDimensionLabel1.setVisible(true);
            newShapeDimensionLabel1.setText("Hauteur : ");
            newShapeDimensionField1.setVisible(true);
            newShapeDimensionField1.valueProperty().unbindBidirectional(((PyramidMesh) shape).heightProperty());

            newShapeDimensionLabel2.setVisible(true);
            newShapeDimensionLabel2.setText("Base : ");
            newShapeDimensionField2.setVisible(true);
            newShapeDimensionField2.valueProperty().unbindBidirectional(((PyramidMesh) shape).hypotenuseProperty());

            newShapeDimensionLabel3.setVisible(false);
            newShapeDimensionField3.setVisible(false);
            newShapeDimensionLabel4.setVisible(false);
            newShapeDimensionField4.setVisible(false);

        } else if (shape instanceof ConeMesh) {

            newShapeDimensionLabel1.setVisible(true);
            newShapeDimensionLabel1.setText("Hauteur : ");
            newShapeDimensionField1.setVisible(true);
            newShapeDimensionField1.valueProperty().unbindBidirectional(((ConeMesh) shape).heightProperty());

            newShapeDimensionLabel2.setVisible(true);
            newShapeDimensionLabel2.setText("Rayon : ");
            newShapeDimensionField2.setVisible(true);
            newShapeDimensionField2.valueProperty().unbindBidirectional(((ConeMesh) shape).radiusProperty());

            newShapeDimensionLabel3.setVisible(false);
            newShapeDimensionField3.setVisible(false);
            newShapeDimensionLabel4.setVisible(false);
            newShapeDimensionField4.setVisible(false);

        } else if (shape instanceof CapsuleMesh) {

            newShapeDimensionLabel1.setVisible(true);
            newShapeDimensionLabel1.setText("Hauteur : ");
            newShapeDimensionField1.setVisible(true);
            newShapeDimensionField1.valueProperty().unbindBidirectional(((CapsuleMesh) shape).heightProperty());

            newShapeDimensionLabel2.setVisible(true);
            newShapeDimensionLabel2.setText("Rayon : ");
            newShapeDimensionField2.setVisible(true);
            newShapeDimensionField2.valueProperty().unbindBidirectional(((CapsuleMesh) shape).radiusProperty());

            newShapeDimensionLabel3.setVisible(false);
            newShapeDimensionField3.setVisible(false);
            newShapeDimensionLabel4.setVisible(false);
            newShapeDimensionField4.setVisible(false);

        } else if (shape instanceof TorusMesh) {
            newShapeDimensionLabel1.setVisible(true);
            newShapeDimensionLabel1.setText("Rayon : ");
            newShapeDimensionField1.setVisible(true);
            newShapeDimensionField1.valueProperty().unbindBidirectional(((TorusMesh) shape).radiusProperty());

            newShapeDimensionLabel2.setVisible(true);
            newShapeDimensionLabel2.setText("Rayon du tube : ");
            newShapeDimensionField2.setVisible(true);
            newShapeDimensionField2.valueProperty().unbindBidirectional(((TorusMesh) shape).tubeRadiusProperty());

            newShapeDimensionLabel3.setVisible(false);
            newShapeDimensionField3.setVisible(false);
            newShapeDimensionLabel4.setVisible(false);
            newShapeDimensionField4.setVisible(false);

        } else if (shape instanceof SpheroidMesh) {
            newShapeDimensionLabel1.setVisible(true);
            newShapeDimensionLabel1.setText("Grand rayon : ");
            newShapeDimensionField1.setVisible(true);
            newShapeDimensionField1.valueProperty().unbindBidirectional(((SpheroidMesh) shape).majorRadiusProperty());

            newShapeDimensionLabel2.setVisible(true);
            newShapeDimensionLabel2.setText("Petit rayon : ");
            newShapeDimensionField2.setVisible(true);
            newShapeDimensionField2.valueProperty().unbindBidirectional(((SpheroidMesh) shape).minorRadiusProperty());

            newShapeDimensionLabel3.setVisible(false);
            newShapeDimensionField3.setVisible(false);
            newShapeDimensionLabel4.setVisible(false);
            newShapeDimensionField4.setVisible(false);

        } else if (shape instanceof SpringMesh) {

            newShapeDimensionLabel1.setVisible(true);
            newShapeDimensionLabel1.setText("Rayon moyen : ");
            newShapeDimensionField1.setVisible(true);
            newShapeDimensionField1.valueProperty().unbindBidirectional(((SpringMesh) shape).meanRadiusProperty());

            newShapeDimensionLabel2.setVisible(true);
            newShapeDimensionLabel2.setText("Rayon du tube : ");
            newShapeDimensionField2.setVisible(true);
            newShapeDimensionField2.valueProperty().unbindBidirectional(((SpringMesh) shape).wireRadiusProperty());

            newShapeDimensionLabel3.setVisible(true);
            newShapeDimensionLabel3.setText("Pas : ");
            newShapeDimensionField3.setVisible(true);
            newShapeDimensionField3.valueProperty().unbindBidirectional(((SpringMesh) shape).pitchProperty());

            newShapeDimensionLabel4.setVisible(true);
            newShapeDimensionLabel4.setText("Longueur : ");
            newShapeDimensionField4.setVisible(true);
            newShapeDimensionField4.valueProperty().unbindBidirectional(((SpringMesh) shape).lengthProperty());

        } else if (shape instanceof TrapezoidMesh) {

            newShapeDimensionLabel1.setVisible(true);
            newShapeDimensionLabel1.setText("Petite base : ");
            newShapeDimensionField1.setVisible(true);
            newShapeDimensionField1.valueProperty().unbindBidirectional(((TrapezoidMesh) shape).sizeSmallProperty());

            newShapeDimensionLabel2.setVisible(true);
            newShapeDimensionLabel2.setText("Grande base : ");
            newShapeDimensionField2.setVisible(true);
            newShapeDimensionField2.valueProperty().unbindBidirectional(((TrapezoidMesh) shape).sizeBigProperty());

            newShapeDimensionLabel3.setVisible(true);
            newShapeDimensionLabel3.setText("Hauteur : ");
            newShapeDimensionField3.setVisible(true);
            newShapeDimensionField3.valueProperty().unbindBidirectional(((TrapezoidMesh) shape).heightProperty());

            newShapeDimensionLabel4.setVisible(true);
            newShapeDimensionLabel4.setText("Profondeur : ");
            newShapeDimensionField4.setVisible(true);
            newShapeDimensionField4.valueProperty().unbindBidirectional(((TrapezoidMesh) shape).depthProperty());
        } else if (shape instanceof TriangularPrismMesh) {

            newShapeDimensionLabel1.setVisible(true);
            newShapeDimensionLabel1.setText("Hauteur : ");
            newShapeDimensionField1.setVisible(true);
            newShapeDimensionField1.valueProperty().unbindBidirectional(((TriangularPrismMesh) shape).heightProperty());

            newShapeDimensionLabel2.setVisible(true);
            newShapeDimensionLabel2.setText("Largeur : ");
            newShapeDimensionField2.setVisible(true);
            newShapeDimensionField2.valueProperty().unbindBidirectional(((TriangularPrismMesh) shape).widthProperty());

            newShapeDimensionLabel3.setVisible(true);
            newShapeDimensionLabel3.setText("Profondeur : ");
            newShapeDimensionField3.setVisible(true);
            newShapeDimensionField3.valueProperty().unbindBidirectional(((TriangularPrismMesh) shape).depthProperty());

            newShapeDimensionLabel4.setVisible(false);
            newShapeDimensionField4.setVisible(false);

        } else if (shape instanceof TetrahedronMesh) {

            newShapeDimensionLabel1.setVisible(false);
            newShapeDimensionField1.setVisible(false);

            newShapeDimensionLabel2.setVisible(true);
            newShapeDimensionLabel2.setText("Hauteur : ");
            newShapeDimensionField2.setVisible(true);
            newShapeDimensionField2.valueProperty().unbindBidirectional(((TetrahedronMesh) shape).heightProperty());

            newShapeDimensionLabel3.setVisible(false);
            newShapeDimensionField3.setVisible(false);
            newShapeDimensionLabel4.setVisible(false);
            newShapeDimensionField4.setVisible(false);

        } else if (shape instanceof OctahedronMesh) {

            newShapeDimensionLabel1.setVisible(true);
            newShapeDimensionLabel1.setText("Hauteu : ");
            newShapeDimensionField1.setVisible(true);
            newShapeDimensionField1.valueProperty().unbindBidirectional(((OctahedronMesh) shape).heightProperty());

            newShapeDimensionLabel2.setVisible(true);
            newShapeDimensionLabel2.setText("Base : ");
            newShapeDimensionField2.setVisible(true);
            newShapeDimensionField2.valueProperty().unbindBidirectional(((OctahedronMesh) shape).hypotenuseProperty());

            newShapeDimensionLabel3.setVisible(false);
            newShapeDimensionField3.setVisible(false);
            newShapeDimensionLabel4.setVisible(false);
            newShapeDimensionField4.setVisible(false);

        }
    }

    public void bindCoordinatesFields(Shape3D shape) {
        newShapeXCoord.valueProperty().bindBidirectional(shape.translateXProperty());
        newShapeYCoord.valueProperty().bindBidirectional(shape.translateYProperty());
        newShapeZCoord.valueProperty().bindBidirectional(shape.translateZProperty());
    }

    public void unbindCoordinatesFields(Shape3D shape) {
        newShapeXCoord.valueProperty().unbindBidirectional(shape.translateXProperty());
        newShapeYCoord.valueProperty().unbindBidirectional(shape.translateYProperty());
        newShapeZCoord.valueProperty().unbindBidirectional(shape.translateZProperty());
    }

    public void setNewShape(Shape3D shape) {
        this.shapeToCreate = shape;
        this.content.get().getChildren().clear();
        this.content.get().getChildren().addAll(shape);

        bindNewShapeDimensionsFields(this.shapeToCreate);
        bindCoordinatesFields(this.shapeToCreate);

        shape.setRotationAxis(Rotate.Y_AXIS);
        animation = new Timeline();
        animation.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(shape.rotateProperty(), 0d)),
                new KeyFrame(Duration.seconds(5),
                        new KeyValue(shape.rotateProperty(), 360d)));
        animation.setCycleCount(Timeline.INDEFINITE);
        this.playAnimation();
    }

    public void clearSubScene() {
        this.content.get().getChildren().clear();
    }

    public void playAnimation() {
        animation.play();
    }

    public void pauseAnimation() {
        animation.pause();
    }

    public void setParentController(Main3DController parentController) {
        this.parentController = parentController;
    }

    public void setNewShapeStage(Stage newShapeStage) {
        this.newShapeStage = newShapeStage;
    }

    public void btnOKCreateParametricShape() {
        this.unbindNewShapeDimensionsFields(this.shapeToCreate);
        this.unbindCoordinatesFields(this.shapeToCreate);
        this.parentController.actionCreateParametricShape(this.shapeToCreate);
        this.newShapeStage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        newShapeSubscene.setRoot(content.get());
        newShapeSubscene.setFill(Color.ALICEBLUE);
        newShapeSubscene.setCamera(camera);
        newShapeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            for (Transform transform : camera.getTransforms()) {
                if (transform instanceof Translate)
                    ((Translate) transform).setZ(newValue.doubleValue() * (-1));
            }
        });

        SVGPath play = new SVGPath();
        play.setContent(SVGPathIcons.play);
        play.setScaleX(0.4);
        play.setScaleY(0.4);
        play.setScaleZ(0.4);
        btnPlay.setGraphic(play);

        SVGPath pause = new SVGPath();
        pause.setContent(SVGPathIcons.pause);
        pause.setScaleX(0.4);
        pause.setScaleY(0.4);
        pause.setScaleZ(0.4);
        btnPause.setGraphic(pause);
    }
}
