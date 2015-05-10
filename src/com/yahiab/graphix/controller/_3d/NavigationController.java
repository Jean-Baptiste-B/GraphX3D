package com.yahiab.graphix.controller._3d;

import com.yahiab.graphix.model._3d.ContentModel;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollBar;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class for settings panel
 */
public class NavigationController implements Initializable {
    //    public FourWayNavControl eyeNav;
    public ScrollBar zoomBar;
    //    public FourWayNavControl camNav;
    private ContentModel contentModel = Main3DController.getContentModel();

    @Override public void initialize(URL location, ResourceBundle resources) {
        zoomBar.setMin(-2500);
        zoomBar.setMax(-500);
        zoomBar.setValue(contentModel.getCameraPosition().getZ());
        zoomBar.setVisibleAmount(5);
        contentModel.getCameraPosition().zProperty().bindBidirectional(zoomBar.valueProperty());
//        eyeNav.setListener(new FourWayNavControl.FourWayListener() {
//            @Override public void navigateStep(Side direction, double amount) {
//                switch (direction) {
//                    case TOP:
//                        contentModel.getCameraLookXRotate().setAngle(contentModel.getCameraLookXRotate().getAngle()+amount);
//                        break;
//                    case BOTTOM:
//                        contentModel.getCameraLookXRotate().setAngle(contentModel.getCameraLookXRotate().getAngle()-amount);
//                        break;
//                    case LEFT:
//                        contentModel.getCameraLookZRotate().setAngle(contentModel.getCameraLookZRotate().getAngle()-amount);
//                        break;
//                    case RIGHT:
//                        contentModel.getCameraLookZRotate().setAngle(contentModel.getCameraLookZRotate().getAngle()+amount);
//                        break;
//                }
//            }
//        });
//        camNav.setListener(new FourWayNavControl.FourWayListener() {
//            @Override public void navigateStep(Side direction, double amount) {
//                switch (direction) {
//                    case TOP:
//                        contentModel.getCameraXRotate().setAngle(contentModel.getCameraXRotate().getAngle()-amount);
//                        break;
//                    case BOTTOM:
//                        contentModel.getCameraXRotate().setAngle(contentModel.getCameraXRotate().getAngle()+amount);
//                        break;
//                    case LEFT:
//                        contentModel.getCameraYRotate().setAngle(contentModel.getCameraYRotate().getAngle()+amount);
//                        break;
//                    case RIGHT:
//                        contentModel.getCameraYRotate().setAngle(contentModel.getCameraYRotate().getAngle()-amount);
//                        break;
//                }
//            }
//        });
    }
}
