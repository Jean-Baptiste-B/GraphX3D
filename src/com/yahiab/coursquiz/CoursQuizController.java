package com.yahiab.coursquiz;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class CoursQuizController {

    @FXML
    AnchorPane pere;
    @FXML
    Pane reponsePave;
    @FXML
    Pane reponseCylindre;
    @FXML
    Pane reponseTriangle;
    @FXML
    Pane reponseRectangle;
    @FXML
    Pane reponseCercle;
    @FXML
    Pane reponseSphere;
    @FXML
    Pane reponsePyramide;
    @FXML
    Pane reponseCone;
    @FXML
    Pane reponsePrisme;
    @FXML
    Pane courCylindre;
    @FXML
    Pane courParallelepipede;
    @FXML
    Pane courTriangle;
    @FXML
    Pane courCercle;
    @FXML
    Pane courRectangle;
    @FXML
    Pane courSphere;
    @FXML
    Pane courPyramide;
    @FXML
    Pane courCone;
    @FXML
    Pane courPrisme;
    @FXML
    Pane quizCylindre;
    @FXML
    Pane quizParallelepipede;
    @FXML
    Pane quizTriangle;
    @FXML
    Pane quizRectangle;
    @FXML
    Pane quizCercle;
    @FXML
    Pane quizSphere;
    @FXML
    Pane quizPyramide;
    @FXML
    Pane quizCone;
    @FXML
    Pane quizPrisme;


    @FXML
    public void afficherReponsePave() {
        if (!reponsePave.isVisible()) {
            reponsePave.setVisible(true);
            reponsePave.setDisable(false);
        } else {
            reponsePave.setVisible(false);
            reponsePave.setDisable(true);
        }

    }

    @FXML
    public void afficherReponseCylindre() {
        if (!reponseCylindre.isVisible()) {
            reponseCylindre.setVisible(true);
            reponseCylindre.setDisable(false);

        } else {
            reponseCylindre.setVisible(false);
            reponseCylindre.setDisable(true);
        }

    }

    @FXML
    public void afficherReponseTriangle() {
        if (!reponseTriangle.isVisible()) {
            reponseTriangle.setVisible(true);
            reponseTriangle.setDisable(false);

        } else {
            reponseTriangle.setVisible(false);
            reponseTriangle.setDisable(true);
        }
    }

    @FXML
    public void afficherReponseRectangle() {
        if (!reponseRectangle.isVisible()) {
            reponseRectangle.setVisible(true);
            reponseRectangle.setDisable(false);

        } else {
            reponseRectangle.setVisible(false);
            reponseRectangle.setDisable(true);
        }
    }

    @FXML
    public void afficherReponseCercle() {
        if (!reponseCercle.isVisible()) {
            reponseCercle.setVisible(true);
            reponseCercle.setDisable(false);

        } else {
            reponseCercle.setVisible(false);
            reponseCercle.setDisable(true);
        }
    }

    @FXML
    public void afficherReponseSphere() {
        if (!reponseSphere.isVisible()) {
            reponseSphere.setVisible(true);
            reponseSphere.setDisable(false);

        } else {
            reponseSphere.setVisible(false);
            reponseSphere.setDisable(true);
        }
    }

    @FXML
    public void afficherReponsePyramide() {
        if (!reponsePyramide.isVisible()) {
            reponsePyramide.setVisible(true);
            reponsePyramide.setDisable(false);

        } else {
            reponsePyramide.setVisible(false);
            reponsePyramide.setDisable(true);
        }
    }

    @FXML
    public void afficherReponseCone() {
        if (!reponseCone.isVisible()) {
            reponseCone.setVisible(true);
            reponseCone.setDisable(false);

        } else {
            reponseCone.setVisible(false);
            reponseCone.setDisable(true);
        }
    }

    @FXML
    public void afficherReponsePrisme() {
        if (!reponsePrisme.isVisible()) {
            reponsePrisme.setVisible(true);
            reponsePrisme.setDisable(false);

        } else {
            reponsePrisme.setVisible(false);
            reponsePrisme.setDisable(true);
        }
    }

    @FXML
    public void afficherCourParallelepipede() {
        this.disableAll();
        courParallelepipede.setVisible(true);
        courParallelepipede.setDisable(false);
    }

    @FXML
    public void afficherCourCylindre() {
        this.disableAll();
        courCylindre.setVisible(true);
        courCylindre.setDisable(false);
    }

    @FXML
    public void afficherCourTriangle() {
        this.disableAll();
        courTriangle.setVisible(true);
        courTriangle.setDisable(false);
    }

    @FXML
    public void afficherCourCercle() {
        this.disableAll();
        courCercle.setVisible(true);
        courCercle.setDisable(false);
    }

    @FXML
    public void afficherCourRectangle() {
        this.disableAll();
        courRectangle.setVisible(true);
        courRectangle.setDisable(false);
    }

    @FXML
    public void afficherCourPyramide() {
        this.disableAll();
        courPyramide.setVisible(true);
        courPyramide.setDisable(false);
    }

    @FXML
    public void afficherCourSphere() {
        this.disableAll();
        courSphere.setVisible(true);
        courSphere.setDisable(false);
    }

    @FXML
    public void afficherCourCone() {
        this.disableAll();
        courCone.setVisible(true);
        courCone.setDisable(false);
    }

    @FXML
    public void afficherCourPrisme() {
        this.disableAll();
        courPrisme.setVisible(true);
        courPrisme.setDisable(false);
    }

    @FXML
    public void afficherQuizCylindre() {
        this.disableAll();
        quizCylindre.setVisible(true);
        quizCylindre.setDisable(false);
    }

    @FXML
    public void afficherQuizParallelepipede() {
        this.disableAll();
        quizParallelepipede.setVisible(true);
        quizParallelepipede.setDisable(false);
    }

    @FXML
    public void afficherQuizTriangle() {
        this.disableAll();
        quizTriangle.setVisible(true);
        quizTriangle.setDisable(false);
    }

    @FXML
    public void afficherQuizRectangle() {
        this.disableAll();
        quizRectangle.setVisible(true);
        quizRectangle.setDisable(false);
    }

    @FXML
    public void afficherQuizCercle() {
        this.disableAll();
        quizCercle.setVisible(true);
        quizCercle.setDisable(false);
    }

    @FXML
    public void afficherQuizSphere() {
        this.disableAll();
        quizSphere.setVisible(true);
        quizSphere.setDisable(false);
    }

    @FXML
    public void afficherQuizPyramide() {
        this.disableAll();
        quizPyramide.setVisible(true);
        quizPyramide.setDisable(false);
    }

    @FXML
    public void afficherQuizCone() {
        this.disableAll();
        quizCone.setVisible(true);
        quizCone.setDisable(false);
    }

    @FXML
    public void afficherQuizPrisme() {
        this.disableAll();
        quizPrisme.setVisible(true);
        quizPrisme.setDisable(false);
    }

    @FXML
    public void disableAll() {
        courParallelepipede.setVisible(false);
        courParallelepipede.setDisable(true);
        courCylindre.setVisible(false);
        courCylindre.setDisable(true);
        courTriangle.setVisible(false);
        courTriangle.setDisable(true);
        courCercle.setVisible(false);
        courCercle.setDisable(true);
        courRectangle.setVisible(false);
        courRectangle.setDisable(true);
        courSphere.setVisible(false);
        courSphere.setDisable(true);
        courPyramide.setVisible(false);
        courPyramide.setDisable(true);
        courCone.setVisible(false);
        courCone.setDisable(true);
        courPrisme.setVisible(false);
        courPrisme.setDisable(true);
        quizCylindre.setVisible(false);
        quizCylindre.setDisable(true);
        quizParallelepipede.setVisible(false);
        quizParallelepipede.setDisable(true);
        quizTriangle.setVisible(false);
        quizTriangle.setDisable(true);
        quizRectangle.setVisible(false);
        quizRectangle.setDisable(true);
        quizCercle.setVisible(false);
        quizCercle.setDisable(true);
        quizSphere.setVisible(false);
        quizSphere.setDisable(true);
        quizPyramide.setVisible(false);
        quizPyramide.setDisable(true);
        quizCone.setVisible(false);
        quizCone.setDisable(true);
        quizPrisme.setVisible(false);
        quizPrisme.setDisable(true);
    }


}
