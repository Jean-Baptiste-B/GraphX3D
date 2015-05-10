package com.yahiab.graphix.model._3d;

import com.yahiab.graphix.model._3d.helpers.SelectedAxe;
import com.yahiab.graphix.model._3d.helpers.Xform;
import eu.mihosoft.vrl.v3d.CSG;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.BoundingBox;
import javafx.scene.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.ZoomEvent;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Shape3D;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * 3D Content Model for Viewer App. Contains the 3D scene and everything related to it: light, cameras etc.
 */
public class ContentModel implements Initializable {

    private final SimpleObjectProperty<SubScene> subScene = new SimpleObjectProperty<>();
    private final Group root3D = new Group();
    private final PerspectiveCamera camera = new PerspectiveCamera(true);
    private final Rotate cameraXRotate = new Rotate(-20, 0, 0, 0, Rotate.X_AXIS);
    private final Rotate cameraYRotate = new Rotate(-20, 0, 0, 0, Rotate.Y_AXIS);
    private final Rotate cameraLookXRotate = new Rotate(0, 0, 0, 0, Rotate.X_AXIS);
    private final Rotate cameraLookZRotate = new Rotate(0, 0, 0, 0, Rotate.Z_AXIS);
    private final Translate cameraPosition = new Translate(0, 0, 0);
    private final Xform cameraXform1 = new Xform();
    private final Xform cameraXform2 = new Xform();
    private final Xform cameraXform3 = new Xform();
    private final double cameraDistance = 1000;

    //////////////// LIGHTING /////////////////////////
    private final EventHandler<ScrollEvent> scrollEventHandler = event -> {
        if (event.getTouchCount() > 0) { // touch pad scroll
            cameraXform2.t.setX(cameraXform2.t.getX() - (0.01 * event.getDeltaX()));  // -
            cameraXform2.t.setY(cameraXform2.t.getY() + (0.01 * event.getDeltaY()));  // -
        } else {
            double z = cameraPosition.getZ() - (event.getDeltaY() * 0.2);
            z = Math.max(z, -1000);
            z = Math.min(z, 0);
            cameraPosition.setZ(z);
        }
    };
    private final EventHandler<ZoomEvent> zoomEventHandler = event -> {
        if (!Double.isNaN(event.getZoomFactor()) && event.getZoomFactor() > 0.8 && event.getZoomFactor() < 1.2) {
            double z = cameraPosition.getZ() / event.getZoomFactor();
            z = Math.max(z, -1000);
            z = Math.min(z, 0);
            cameraPosition.setZ(z);
        }
    };
    private final EventHandler<KeyEvent> keyEventHandler = event -> {
        /*
        if (!Double.isNaN(event.getZoomFactor()) && event.getZoomFactor() > 0.8 && event.getZoomFactor() < 1.2) {
            double z = cameraPosition.getZ()/event.getZoomFactor();
            z = Math.max(z,-1000);
            z = Math.min(z,0);
            cameraPosition.setZ(z);
        }
        */
        System.out.println("KeyEvent ...");
        double CONTROL_MULTIPLIER = 0.1;
        double SHIFT_MULTIPLIER = 0.1;
        double ALT_MULTIPLIER = 0.5;
        //System.out.println("--> handleKeyboard>handle");

        // event.getEventType();

        switch (event.getCode()) {
            case F:
                if (event.isControlDown()) {
                    //onButtonSave();
                }
                break;
            case O:
                if (event.isControlDown()) {
                    //onButtonLoad();
                }
                break;
            case Z:
                if (event.isShiftDown()) {
                    cameraXform1.ry.setAngle(0.0);
                    cameraXform1.rx.setAngle(0.0);
                    camera.setTranslateZ(-300.0);
                }
                cameraXform2.t.setX(0.0);
                cameraXform2.t.setY(0.0);
                break;
            case UP:
                if (event.isControlDown() && event.isShiftDown()) {
                    cameraXform2.t.setY(cameraXform2.t.getY() - 10.0 * CONTROL_MULTIPLIER);
                } else if (event.isAltDown() && event.isShiftDown()) {
                    cameraXform1.rx.setAngle(cameraXform1.rx.getAngle() - 10.0 * ALT_MULTIPLIER);
                } else if (event.isControlDown()) {
                    cameraXform2.t.setY(cameraXform2.t.getY() - 1.0 * CONTROL_MULTIPLIER);
                } else if (event.isAltDown()) {
                    cameraXform1.rx.setAngle(cameraXform1.rx.getAngle() - 2.0 * ALT_MULTIPLIER);
                } else if (event.isShiftDown()) {
                    double z = camera.getTranslateZ();
                    double newZ = z + 5.0 * SHIFT_MULTIPLIER;
                    camera.setTranslateZ(newZ);
                }
                break;
            case DOWN:
                if (event.isControlDown() && event.isShiftDown()) {
                    cameraXform2.t.setY(cameraXform2.t.getY() + 10.0 * CONTROL_MULTIPLIER);
                } else if (event.isAltDown() && event.isShiftDown()) {
                    cameraXform1.rx.setAngle(cameraXform1.rx.getAngle() + 10.0 * ALT_MULTIPLIER);
                } else if (event.isControlDown()) {
                    cameraXform2.t.setY(cameraXform2.t.getY() + 1.0 * CONTROL_MULTIPLIER);
                } else if (event.isAltDown()) {
                    cameraXform1.rx.setAngle(cameraXform1.rx.getAngle() + 2.0 * ALT_MULTIPLIER);
                } else if (event.isShiftDown()) {
                    double z = camera.getTranslateZ();
                    double newZ = z - 5.0 * SHIFT_MULTIPLIER;
                    camera.setTranslateZ(newZ);
                }
                break;
        }
        //System.out.println(cameraXform.getTranslateX() + ", " + cameraXform.getTranslateY() + ", " + cameraXform.getTranslateZ());
    };
    private AmbientLight ambientLight = new AmbientLight(Color.DARKGREY);
    private PointLight light1 = new PointLight(Color.WHITE);
    private PointLight light2 = new PointLight(Color.ANTIQUEWHITE);
    private PointLight light3 = new PointLight(Color.ALICEBLUE);
    private SimpleBooleanProperty ambientLightEnabled = new SimpleBooleanProperty(false) {
        @Override
        protected void invalidated() {
            if (get()) {
                root3D.getChildren().add(ambientLight);
            } else {
                root3D.getChildren().remove(ambientLight);
            }
        }
    };

    ///////////////////////////////////////////////////
    private SimpleBooleanProperty light1Enabled = new SimpleBooleanProperty(false) {
        @Override
        protected void invalidated() {
            if (get()) {
                root3D.getChildren().add(light1);
            } else {
                root3D.getChildren().remove(light1);
            }
        }
    };
    private SimpleBooleanProperty light2Enabled = new SimpleBooleanProperty(false) {
        @Override
        protected void invalidated() {
            if (get()) {
                root3D.getChildren().add(light2);
            } else {
                root3D.getChildren().remove(light2);
            }
        }
    };
    private SimpleBooleanProperty light3Enabled = new SimpleBooleanProperty(false) {
        @Override
        protected void invalidated() {
            if (get()) {
                root3D.getChildren().add(light3);
            } else {
                root3D.getChildren().remove(light3);
            }
        }
    };
    private double dragStartX, dragStartY, dragStartRotateX, dragStartRotateY;
    private ObjectProperty<Group> content = new SimpleObjectProperty<>();

    /////////////// ADDED CSG CONTENT FOR 3D PRINTING /////////////////////////
    private CSG CSGContent;
    //////////////////////////////////////////////////////////////////////////

    private Box xAxis, yAxis, zAxis;
    private Sphere xSphere, ySphere, zSphere;
    // created by Yahia
    private SimpleObjectProperty<Shape3D> selectedShapeProperty = new SimpleObjectProperty<>();
    private Group selectedShapeAxis = new Group();
    private Box selectedShapeXAxis, selectedShapeYAxis, selectedShapeZAxis;
    private Sphere selectedShapeXSphere, selectedShapeYSphere, selectedShapeZSphere;
    private SelectedAxe selectedAxe = SelectedAxe.None;
    // to keep
    private Rotate yUpRotate = new Rotate(180, 0, 0, 0, Rotate.X_AXIS);
    private SimpleBooleanProperty showAxis = new SimpleBooleanProperty(false) {
        @Override
        protected void invalidated() {
            if (get()) {
                if (xAxis == null) createAxes();
                if (!root3D.getChildren().contains(xAxis))
                    root3D.getChildren().addAll(xAxis);
                if (!root3D.getChildren().contains(yAxis))
                    root3D.getChildren().addAll(yAxis);
                if (!root3D.getChildren().contains(zAxis))
                    root3D.getChildren().addAll(zAxis);
                if (!root3D.getChildren().contains(xSphere))
                    root3D.getChildren().addAll(xSphere);
                if (!root3D.getChildren().contains(ySphere))
                    root3D.getChildren().addAll(ySphere);
                if (!root3D.getChildren().contains(zSphere))
                    root3D.getChildren().addAll(zSphere);
            } else if (xAxis != null) {
                root3D.getChildren().removeAll(xAxis, yAxis, zAxis);
                root3D.getChildren().removeAll(xSphere, ySphere, zSphere);
            }
        }
    };
    private double mousePosX;
    private double mousePosY;
    private double mouseOldX;
    private double mouseOldY;
    private double mouseDeltaX;
    private double mouseDeltaY;
    private final EventHandler<MouseEvent> mouseEventHandler = event -> {
        // System.out.println("MouseEvent ...");

        double yFlip = 1.0;

        if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
            dragStartX = event.getSceneX();
            dragStartY = event.getSceneY();
            dragStartRotateX = cameraXRotate.getAngle();
            dragStartRotateY = cameraYRotate.getAngle();
            mousePosX = event.getSceneX();
            mousePosY = event.getSceneY();
            mouseOldX = event.getSceneX();
            mouseOldY = event.getSceneY();

        } else if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
            double xDelta = event.getSceneX() - dragStartX;
            double yDelta = event.getSceneY() - dragStartY;
            //cameraXRotate.setAngle(dragStartRotateX - (yDelta*0.7));
            //cameraYRotate.setAngle(dragStartRotateY + (xDelta*0.7));

            double modifier = 1.0;
            double modifierFactor = 0.3;

            if (event.isControlDown()) {
                modifier = 0.1;
            }
            if (event.isShiftDown()) {
                modifier = 10.0;
            }

            mouseOldX = mousePosX;
            mouseOldY = mousePosY;
            mousePosX = event.getSceneX();
            mousePosY = event.getSceneY();
            mouseDeltaX = (mousePosX - mouseOldX); //*DELTA_MULTIPLIER;
            mouseDeltaY = (mousePosY - mouseOldY); //*DELTA_MULTIPLIER;

            double flip = -1.0;

            boolean alt = (false || event.isAltDown());  // For now, don't require ALT to be pressed
            if (alt && (event.isMiddleButtonDown() || (event.isPrimaryButtonDown() && event.isSecondaryButtonDown()))) {
                cameraXform2.t.setX(cameraXform2.t.getX() + flip * mouseDeltaX * modifierFactor * modifier * 0.3);  // -
                cameraXform2.t.setY(cameraXform2.t.getY() + yFlip * mouseDeltaY * modifierFactor * modifier * 0.3);  // -
            } else if (alt && event.isPrimaryButtonDown()) {
                cameraXform1.ry.setAngle(cameraXform1.ry.getAngle() - yFlip * mouseDeltaX * modifierFactor * modifier * 2.0);  // +
                cameraXform1.rx.setAngle(cameraXform1.rx.getAngle() + flip * mouseDeltaY * modifierFactor * modifier * 2.0);  // -
            } else if (alt && event.isSecondaryButtonDown()) {
                double z = cameraPosition.getZ();
                // double z = camera.getTranslateZ();
                // double newZ = z + yFlip*flip*mouseDeltaX*modifierFactor*modifier;
                double newZ = z - flip * (mouseDeltaX + mouseDeltaY) * modifierFactor * modifier;
                //System.out.println("newZ = " + newZ);
                cameraPosition.setZ(newZ);
                // camera.setTranslateZ(newZ);
            }

        }
    };

    {
        contentProperty().addListener((ov, oldContent, newContent) -> {
            root3D.getChildren().remove(oldContent);
            root3D.getChildren().add(newContent);
        });
    }

    public ContentModel() {
        // CAMERA
        camera.setNearClip(1.0); // TODO: Workaround as per RT-31255
        camera.setFarClip(10000.0); // TODO: Workaround as per RT-31255

        camera.getTransforms().addAll(
                yUpRotate,
                //cameraXRotate,
                //cameraYRotate,
                cameraPosition,
                cameraLookXRotate,
                cameraLookZRotate);
        //root3D.getChildren().add(camera);
        cameraXform1.rx.setAngle(320);
        cameraXform1.ry.setAngle(45);
        root3D.getChildren().add(cameraXform1);
        cameraXform1.getChildren().add(cameraXform2);
        cameraXform2.getChildren().add(cameraXform3);
        cameraXform3.getChildren().add(camera);
        cameraPosition.setZ(-cameraDistance);
        // camera.setTranslateZ(-cameraDistance);
        content.set(new Group());
        /**
         SessionManager sessionManager = SessionManager.getSessionManager();
         sessionManager.bind(cameraLookXRotate.angleProperty(), "cameraLookXRotate");
         sessionManager.bind(cameraLookZRotate.angleProperty(), "cameraLookZRotate");
         sessionManager.bind(cameraPosition.xProperty(), "cameraPosition.x");
         sessionManager.bind(cameraPosition.yProperty(), "cameraPosition.y");
         sessionManager.bind(cameraPosition.zProperty(), "cameraPosition.z");
         sessionManager.bind(cameraXRotate.angleProperty(), "cameraXRotate");
         sessionManager.bind(cameraYRotate.angleProperty(), "cameraYRotate");
         sessionManager.bind(camera.nearClipProperty(), "cameraNearClip");
         sessionManager.bind(camera.farClipProperty(), "cameraFarClip");
         */

        // Build SubScene
        rebuildSubScene();
    }

    private void rebuildSubScene() {
        SubScene oldSubScene = this.subScene.get();
        if (oldSubScene != null) {
            oldSubScene.setRoot(new Region());
            oldSubScene.setCamera(null);
            oldSubScene.removeEventHandler(MouseEvent.ANY, mouseEventHandler);
            oldSubScene.removeEventHandler(KeyEvent.ANY, keyEventHandler);
            oldSubScene.removeEventHandler(ScrollEvent.ANY, scrollEventHandler);
        }

        SubScene subScene = new SubScene(root3D, 800, 600, true, SceneAntialiasing.BALANCED);
        this.subScene.set(subScene);
        subScene.setFill(Color.ALICEBLUE);
        subScene.setCamera(camera);
        // SCENE EVENT HANDLING FOR CAMERA NAV
        subScene.addEventHandler(MouseEvent.ANY, mouseEventHandler);
        subScene.addEventHandler(KeyEvent.ANY, keyEventHandler);
        // subScene.addEventFilter(KeyEvent.ANY, keyEventHandler);
        subScene.addEventHandler(ZoomEvent.ANY, zoomEventHandler);
        subScene.addEventHandler(ScrollEvent.ANY, scrollEventHandler);

        // Scene scene = subScene.getScene();
        // scene.addEventFilter(KeyEvent.ANY, keyEventHandler);

        /*
        subScene.sceneProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue ov, Object t, Object t1) {
                System.out.println("hello world");
            }
        });
        */
    }

    public boolean getShowAxis() {
        return showAxis.get();
    }

    public void setShowAxis(boolean showAxis) {
        this.showAxis.set(showAxis);
    }

    public SimpleBooleanProperty showAxisProperty() {
        return showAxis;
    }

    public ObjectProperty<Group> contentProperty() {
        return content;
    }

    public Group getContent() {
        return content.get();
    }

    public void setContent(Group content) {
        this.content.set(content);
    }

    public SubScene getSubScene() {
        return subScene.get();
    }
    public SimpleObjectProperty<SubScene> subSceneProperty() {
        return subScene;
    }
    public Group getRoot3D() {
        return root3D;
    }
    public PerspectiveCamera getCamera() {
        return camera;
    }
    public Rotate getCameraXRotate() {
        return cameraXRotate;
    }
    public Rotate getCameraYRotate() {
        return cameraYRotate;
    }
    public Translate getCameraPosition() {
        return cameraPosition;
    }
    public Rotate getCameraLookXRotate() {
        return cameraLookXRotate;
    }
    public Rotate getCameraLookZRotate() {
        return cameraLookZRotate;
    }

    public SimpleObjectProperty<Shape3D> getSelectedShapePropertyProperty() {return selectedShapeProperty;}
    public void setSelectedShapeProperty(Shape3D selectedShapeProperty) {this.selectedShapeProperty.set(selectedShapeProperty);}

    public SelectedAxe getSelectedAxe() {
        return selectedAxe;
    }

    public void setSelectedAxe(SelectedAxe selectedAxe) {
        this.selectedAxe = selectedAxe;
        ////// setting the correct color to each axe
        final PhongMaterial redMaterial = new PhongMaterial(Color.RED);
        redMaterial.setSpecularColor(Color.RED);
        final PhongMaterial greenMaterial = new PhongMaterial(Color.GREEN);
        greenMaterial.setSpecularColor(Color.GREEN);
        final PhongMaterial blueMaterial = new PhongMaterial(Color.BLUE);
        blueMaterial.setSpecularColor(Color.BLUE);
        final  PhongMaterial yellowMaterial = new PhongMaterial(Color.YELLOW);
        yellowMaterial.setSpecularColor(Color.YELLOW);

        selectedShapeXAxis.setMaterial(redMaterial);
        selectedShapeXSphere.setMaterial(redMaterial);
        selectedShapeYAxis.setMaterial(greenMaterial);
        selectedShapeYSphere.setMaterial(greenMaterial);
        selectedShapeZAxis.setMaterial(blueMaterial);
        selectedShapeZSphere.setMaterial(blueMaterial);

        switch (selectedAxe) {
            case X_Axis:
                selectedShapeXAxis.setMaterial(yellowMaterial);
                selectedShapeXSphere.setMaterial(yellowMaterial);
                break;
            case Y_Axis:
                selectedShapeYAxis.setMaterial(yellowMaterial);
                selectedShapeYSphere.setMaterial(yellowMaterial);
                break;
            case Z_Axis:
                selectedShapeZAxis.setMaterial(yellowMaterial);
                selectedShapeZSphere.setMaterial(yellowMaterial);
                break;
        }
    }

    private void createAxes() {
        double length = 300.0;
        double width = 1.0;
        double radius = 2.0;
        final PhongMaterial redMaterial = new PhongMaterial();
        redMaterial.setDiffuseColor(Color.DARKRED);
        redMaterial.setSpecularColor(Color.RED);
        final PhongMaterial greenMaterial = new PhongMaterial();
        greenMaterial.setDiffuseColor(Color.DARKGREEN);
        greenMaterial.setSpecularColor(Color.GREEN);
        final PhongMaterial blueMaterial = new PhongMaterial();
        blueMaterial.setDiffuseColor(Color.DARKBLUE);
        blueMaterial.setSpecularColor(Color.BLUE);

        xSphere = new Sphere(radius);
        ySphere = new Sphere(radius);
        zSphere = new Sphere(radius);
        xSphere.setMaterial(redMaterial);
        ySphere.setMaterial(greenMaterial);
        zSphere.setMaterial(blueMaterial);

        xSphere.setTranslateX(length);
        ySphere.setTranslateY(length);
        zSphere.setTranslateZ(length);

        xAxis = new Box(length, width, width);
        yAxis = new Box(width, length, width);
        zAxis = new Box(width, width, length);
        xAxis.setMaterial(redMaterial);
        yAxis.setMaterial(greenMaterial);
        zAxis.setMaterial(blueMaterial);
        xAxis.setTranslateX(length*0.5);
        yAxis.setTranslateY(length*0.5);
        zAxis.setTranslateZ(length*0.5);

        xAxis.setId("sys_xAxis");
        yAxis.setId("sys_yAxis");
        zAxis.setId("sys_zAxis");
        xSphere.setId("sys_xSphere");
        ySphere.setId("sys_ySphere");
        zSphere.setId("sys_zSphere");
    }

    private void createSelectionAxis() {
        BoundingBox boundingBox = (BoundingBox) this.selectedShapeProperty.get().getBoundsInParent();
        double boundsHeight = boundingBox.getHeight();
        double boundsWidth  = boundingBox.getWidth();
        double boundsDepth  = boundingBox.getDepth();

        double length = Double.max(boundsHeight, Double.max(boundsWidth, boundsDepth));

        double width = 2.0;
        double radius = 20.0;
        final PhongMaterial redMaterial = new PhongMaterial();
        redMaterial.setDiffuseColor(Color.DARKRED);
        redMaterial.setSpecularColor(Color.RED);
        final PhongMaterial greenMaterial = new PhongMaterial();
        greenMaterial.setDiffuseColor(Color.DARKGREEN);
        greenMaterial.setSpecularColor(Color.GREEN);
        final PhongMaterial blueMaterial = new PhongMaterial();
        blueMaterial.setDiffuseColor(Color.DARKBLUE);
        blueMaterial.setSpecularColor(Color.BLUE);

        selectedShapeXSphere = new Sphere(radius);
        selectedShapeYSphere = new Sphere(radius);
        selectedShapeZSphere = new Sphere(radius);
        selectedShapeXSphere.setMaterial(redMaterial);
        selectedShapeYSphere.setMaterial(greenMaterial);
        selectedShapeZSphere.setMaterial(blueMaterial);


        selectedShapeXSphere.translateXProperty().bind(selectedShapeProperty.get().translateXProperty().add(length));
        selectedShapeXSphere.translateYProperty().bind(selectedShapeProperty.get().translateYProperty());
        selectedShapeXSphere.translateZProperty().bind(selectedShapeProperty.get().translateZProperty());
        ///// Y SPHERE
        selectedShapeYSphere.translateXProperty().bind(selectedShapeProperty.get().translateXProperty());
        selectedShapeYSphere.translateYProperty().bind(selectedShapeProperty.get().translateYProperty().add(length));
        selectedShapeYSphere.translateZProperty().bind(selectedShapeProperty.get().translateZProperty());

        ///// Z SPHERE
        selectedShapeZSphere.translateXProperty().bind(selectedShapeProperty.get().translateXProperty());
        selectedShapeZSphere.translateYProperty().bind(selectedShapeProperty.get().translateYProperty());
        selectedShapeZSphere.translateZProperty().bind(selectedShapeProperty.get().translateZProperty().add(length));

        /////////////// creating axis ////////////////////////////////

        selectedShapeXAxis = new Box(length, width, width);
        selectedShapeYAxis = new Box(width, length, width);
        selectedShapeZAxis = new Box(width, width, length);
        selectedShapeXAxis.setMaterial(redMaterial);
        selectedShapeYAxis.setMaterial(greenMaterial);
        selectedShapeZAxis.setMaterial(blueMaterial);

        selectedShapeXAxis.translateXProperty().bind(selectedShapeProperty.get().translateXProperty().add(0.5 * length));
        selectedShapeXAxis.translateYProperty().bind(selectedShapeProperty.get().translateYProperty());
        selectedShapeXAxis.translateZProperty().bind(selectedShapeProperty.get().translateZProperty());

        selectedShapeYAxis.translateXProperty().bind(selectedShapeProperty.get().translateXProperty());
        selectedShapeYAxis.translateYProperty().bind(selectedShapeProperty.get().translateYProperty().add(0.5 * length));
        selectedShapeYAxis.translateZProperty().bind(selectedShapeProperty.get().translateZProperty());

        selectedShapeZAxis.translateXProperty().bind(selectedShapeProperty.get().translateXProperty());
        selectedShapeZAxis.translateYProperty().bind(selectedShapeProperty.get().translateYProperty());
        selectedShapeZAxis.translateZProperty().bind(selectedShapeProperty.get().translateZProperty().add(0.5 * length));

        selectedShapeXAxis.setId("sys_selectedShape_xAxis");
        selectedShapeYAxis.setId("sys_selectedShape_xAxis");
        selectedShapeZAxis.setId("sys_selectedShape_xAxis");
        selectedShapeXSphere.setId("sys_selectedShape_xSphere");
        selectedShapeYSphere.setId("sys_selectedShape_ySphere");
        selectedShapeZSphere.setId("sys_selectedShape_zSphere");

        this.selectedShapeAxis.getChildren().clear();
        this.selectedShapeAxis.getChildren().addAll(selectedShapeXAxis, selectedShapeYAxis, selectedShapeZAxis,
                selectedShapeXSphere, selectedShapeYSphere, selectedShapeZSphere);
    }

    public void addSelectedShapeAxis() {
        this.createSelectionAxis();
        this.selectedAxe = SelectedAxe.None;
        if (root3D.getChildren().contains(selectedShapeAxis) == false)
            root3D.getChildren().add(selectedShapeAxis);
    }
    public void removeSelectedShapeAxis() {
        selectedShapeAxis.getChildren().clear();
        this.selectedAxe = SelectedAxe.None;
        root3D.getChildren().remove(selectedShapeAxis);
    }

    public void addShapeToContent(Node node) {
        node.setId("");
        this.content.get().getChildren().addAll(node);
    }

    public void deleteShapeFromContent(Node node) {
        this.content.get().getChildren().remove(node);
    }

    public CSG getCSGContent() {
        return this.CSGContent;
    }

    public void setCSGContent(CSG csg) {
        this.CSGContent = csg;
    }

    public boolean getAmbientLightEnabled() {
        return ambientLightEnabled.get();
    }

    public void setAmbientLightEnabled(boolean ambientLightEnabled) {
        this.ambientLightEnabled.set(ambientLightEnabled);
    }

    public SimpleBooleanProperty ambientLightEnabledProperty() {
        return ambientLightEnabled;
    }

    public boolean getLight1Enabled() {
        return light1Enabled.get();
    }

    public void setLight1Enabled(boolean light1Enabled) {
        this.light1Enabled.set(light1Enabled);
    }

    public SimpleBooleanProperty light1EnabledProperty() {
        return light1Enabled;
    }

    public boolean getLight2Enabled() {
        return light2Enabled.get();
    }

    public void setLight2Enabled(boolean light2Enabled) {
        this.light2Enabled.set(light2Enabled);
    }

    public SimpleBooleanProperty light2EnabledProperty() {
        return light2Enabled;
    }

    public boolean getLight3Enabled() {
        return light3Enabled.get();
    }

    public void setLight3Enabled(boolean light3Enabled) {
        this.light3Enabled.set(light3Enabled);
    }

    public SimpleBooleanProperty light3EnabledProperty() {
        return light3Enabled;
    }

    public AmbientLight getAmbientLight() {
        return ambientLight;
    }

    public PointLight getLight1() {
        return light1;
    }

    public PointLight getLight2() {
        return light2;
    }

    public PointLight getLight3() {
        return light3;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selectedShapeAxis.visibleProperty().bind(new ObjectBinding<Boolean>() {
            {bind(selectedShapeProperty);}
            @Override
            protected Boolean computeValue() {
                return selectedShapeProperty.get() != null;
            }
        });
    }
}