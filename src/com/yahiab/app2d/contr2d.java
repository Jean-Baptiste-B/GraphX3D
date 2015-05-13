package com.yahiab.app2d;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


/**
 * @author kikim
 */
public class contr2d {


    @FXML
    Pane paneca;
    @FXML
    Label textca;
    @FXML
    GridPane panecara;
    @FXML
    Button retour_num_tria;
    @FXML
    TextField textcote;
    @FXML
    GridPane affichage_cote;
    @FXML
    AnchorPane body;
    @FXML
    VBox view3dRootVBox;
    @FXML
    private ImageView r;
    @FXML
    private ImageView c;
    @FXML
    private Button e;
    @FXML
    private Pane place;
    @FXML
    private ColorPicker idcolor;
    @FXML
    private Circle colorca;
    @FXML
    private GridPane hauteur_largeur;
    @FXML
    private TextField hauteur;
    @FXML
    private TextField largeur;
    @FXML
    private TextField value_rayon;
    @FXML
    private GridPane rayon;
    @FXML
    private ImageView pas_element_2D;
    @FXML
    private Separator bare1;
    @FXML
    private TextField posx;
    @FXML
    private TextField posy;
    @FXML
    private GridPane gridepos;
    @FXML
    private Text error;
    @FXML
    private Pane panerotate;
    @FXML
    private TextField rotatevalue;
    @FXML
    private Pane footerca;
    @FXML
    private Polygon recT;
    @FXML
    private Button dessiner_t;
        


    
        /*@FXML 
    private  TextField pos_input;*/
    /**
     * ****************** des Attributs non relatifs à fxml **************************
     */
    /*190  /// 380*//*337*/

    private int ir = 0;// nb rectangle
    private int ic = 0; // nb circle
    private int it = 0; // nb triangle
    private int ihex = 0;
    private int ipara = 0, ipenta = 0, irh = 0, itp = 0, its = 0, ite = 0;
    private Polygon recpara, recpenta, recrh, rectis, recte;
    private Shape rectp;
    private Rectangle recR;
    private Circle recC;
    private int lenode = -1;
    /*
    0 Rectangle ,
    1 Circle,
    2 triangle,
    3 Hexagone,
    4 parallelograme,
    5 pentagon ,
    6 rhombus,
    7 TrianglePerpenduculaire
    */
    private Color resColor = Color.WHITE;/* couleur res*/
    private boolean shape_existe = false;
    private boolean creation_triangle = false;
    private double recx;
    private double recy;
    private double[] respoitx = {0.0, 0.0, 0.0};
    private double[] respoity = {0.0, 0.0, 0.0};
    private int nbpoint = 0;
    private Circle[] points = {null, null, null};
    private boolean point_place = false;
    private Text[] points_triangle_num = {null, null, null};
    private Polygon rechex;


    /**
     * ****************** des Attributs non relatifs à fxml **************************
     */
    
 
    
    /*_____________________fxml _______________*/
  /* création  d'un rectangle ,changement de ,resR ,lenode,type " text" ,ir,appeler la methode*/
    public void desactive_triangle_button() {
        dessiner_t.setVisible(false);
        dessiner_t.setDisable(true);
        retour_num_tria.setDisable(true);
        retour_num_tria.setVisible(false);
    }

    @FXML
    public void at() {
        dessiner_t.setVisible(true);
        dessiner_t.setDisable(false);

    }


    @FXML
    public void color()/**/ {
        desactiver_error();
        String s = "";
        resColor = idcolor.getValue();
        if (lenode == 0) {
            recR.setFill(resColor);
            s = "Rectangle";
            if (recR.getWidth() == recR.getHeight()) {
                s = "Carré";
            }
        } else {
            if (lenode == 1) {
                recC.setFill(resColor);
                s = "Cercle";
            } else {
                if (lenode == 2) {
                    recT.setFill(resColor);
                    s = "Triangle";


                } else {
                    if (lenode == 3) {
                        rechex.setFill(resColor);
                        s = "Hexagon";
                    } else {
                        if (lenode == 4) {
                            recpara.setFill(resColor);
                            s = "Parallélogramme";
                        } else {
                            if (lenode == 5) {
                                recpenta.setFill(resColor);
                                s = "Pentagon";
                            } else {
                                if (lenode == 6) {
                                    recrh.setFill(resColor);
                                    s = "Rhombus";
                                } else {
                                    if (lenode == 7) {
                                        rectp.setFill(resColor);
                                        s = "Triangle Perpenduculaire";
                                    } else {
                                        if (lenode == 8) {
                                            rectis.setFill(resColor);
                                            s = "Triangle isocèle";
                                        } else {
                                            if (lenode == 9) {
                                                recte.setFill(resColor);
                                                s = "Triangle Equilateral";
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }
        mise_a_jour_ca(s);
    }


    @FXML
    public void appliquer_largeur_hauteur() {
        if (lenode == 0) {
            double x1 = Float.valueOf(largeur.getText());
            double y1 = Float.valueOf(hauteur.getText());
            double x = recR.getTranslateX();
            double y = recR.getTranslateY();
            boolean ok = true;

            if (x1 + x > place.getWidth() && x1 <= place.getWidth()) {
                while (x1 + x > place.getWidth()) {
                    x--;
                }
            }
            if (y1 + y > place.getHeight() && y1 <= place.getHeight()) {
                while (y1 + y > place.getHeight()) {
                    y--;
                }
            }
            if (x < 0 || y < 0 || y1 > place.getHeight() || x1 > place.getWidth()) {
                error.setText("Largeur Max :" + (place.getWidth() - 1) + "  Hauteur Max :" + (place.getHeight() - 1));
                error.setVisible(true);
                error.setDisable(false);
                ok = false;
            }


            if (lenode == 0 && ok) {
                desactiver_error();
                recR.setTranslateX(x);
                recR.setTranslateY(y);
                posy.setText(recR.getTranslateY() + "");
                posx.setText(recR.getTranslateX() + "");
                recR.setWidth(Float.valueOf(largeur.getText()));
                recR.setHeight(Float.valueOf(hauteur.getText()));
                if (recR.getWidth() == recR.getHeight()) {
                    mise_a_jour_ca("Carré");
                } else {
                    mise_a_jour_ca("Rectangle");
                }

            }// les autres shapes qui ont la largeur et la hauteur
        } else {
            //  ((Parallelogram)recpara).setWidth(Float.valueOf(largeur.getText()));
            //  ((Parallelogram)recpara).setHeight(Float.valueOf(hauteur.getText()));
        }


    }

    @FXML
    public void appliquer_rayon() {
        desactiver_error();
        double r = Float.valueOf(value_rayon.getText());
        double x = recC.getTranslateX();
        double y = recC.getTranslateY();
        boolean mb = true;
        if (r * 2 > place.getHeight() || r * 2 > place.getWidth()) {
           /* place.setScaleX((place.getWidth()+200)/r*2);
            place.setScaleY((place.getHeight()+200)/r*2);*/
            mb = false;
        }
        if (x - r < 0) {
            while (x - r < 0) {
                x++;
            }
        }
        if (x + r > place.getWidth()) {
            while (x + r > place.getWidth())//window min 700px;
            {
                x--;
            }
        }
        if (y - r < 0) {
            while (y - r < 0) {
                y++;
            }
        }
        if (y + r > place.getHeight()) {
            while (y + r > place.getHeight())//window min 700px;
            {
                y--;
            }
        }


        if (mb) {
            recC.setTranslateX(x);
            recC.setTranslateY(y);
            posx.setText(recC.getTranslateX() + recC.getLayoutX() + "");
            posy.setText(recC.getTranslateY() + recC.getLayoutY() + "");
            recC.setRadius(Float.valueOf(value_rayon.getText()));
        } else {
            error.setText("Rayon Max " + place.getHeight() / 2);
            error.setVisible(true);
            error.setDisable(false);
        }
    }


    @FXML
    public void delete() {
        desactiver_error();
        if (ic + ir + it + ihex + ipara + ipenta + irh + itp + its+ite > 0) {
            desactive_retate();
            desactive_pos();
            desactiver_car_cote();
            desactiver_hateur_largeur();
            deletecara();
            if (lenode == 0) {

                deletecara();
                rotatevalue.setText("0");
                ir--;
                place.getChildren().remove(recR);
                desactiver_hateur_largeur();
                pas_element_2D.setVisible(true);
                pas_element_2D.setDisable(false);

            } else {
                if (lenode == 1) {
                    deletecara();
                    rotatevalue.setText("0");
                    ic--;

                    desactiver_rayon();
                    place.getChildren().remove(recC);
                    pas_element_2D.setVisible(true);
                    pas_element_2D.setDisable(false);

                } else {
                    if (lenode == 2) {
                        deletecara();
                        rotatevalue.setText("0");
                        it--;
                        place.getChildren().remove(recT);
                        desactiver_hateur_largeur();
                        pas_element_2D.setVisible(true);
                        pas_element_2D.setDisable(false);

                    } else {
                        if (lenode == 3) {
                            deletecara();
                            rotatevalue.setText("0");
                            ihex--;
                            place.getChildren().remove(rechex);
                            desactiver_hateur_largeur();
                            pas_element_2D.setVisible(true);
                            pas_element_2D.setDisable(false);
                        } else {
                            if (lenode == 4) {
                                deletecara();
                                rotatevalue.setText("0");
                                ipara--;
                                place.getChildren().remove(recpara);
                                desactiver_hateur_largeur();
                                pas_element_2D.setVisible(true);
                                pas_element_2D.setDisable(false);

                            } else {
                                if (lenode == 5) {
                                    rotatevalue.setText("0");
                                    ipenta--;
                                    place.getChildren().remove(recpenta);
                                    desactiver_hateur_largeur();
                                    pas_element_2D.setVisible(true);
                                    pas_element_2D.setDisable(false);
                                } else {
                                    //Rhombus
                                    if (lenode == 6) {
                                        irh--;
                                        place.getChildren().remove(recrh);
                                        desactiver_hateur_largeur();
                                        pas_element_2D.setVisible(true);
                                        pas_element_2D.setDisable(false);
                                    } else {
                                        if (lenode == 7) {
                                            itp--;
                                            place.getChildren().remove(rectp);
                                            desactiver_hateur_largeur();
                                            pas_element_2D.setVisible(true);
                                            pas_element_2D.setDisable(false);
                                        } else {
                                            if (lenode == 8) {
                                                its--;
                                                place.getChildren().remove(rectis);
                                                desactiver_hateur_largeur();
                                                pas_element_2D.setVisible(true);
                                                pas_element_2D.setDisable(false);
                                            } else {
                                                if (lenode == 9) {
                                                    ite--;
                                                    place.getChildren().remove(recte);
                                                    desactiver_hateur_largeur();
                                                    pas_element_2D.setVisible(true);
                                                    pas_element_2D.setDisable(false);
                                                }
                                            }
                                        }

                                    }

                                }
                            }
                        }
                    }

                }
            }
        }
        lenode = -1;
    }

    /*public void (Shape sh)
    {
        if (sh.getLayoutX()+sh.getTranslateX()<0)
        {
            if (lenode==0)
            {
             sh.setTranslateX(0);
             sh.setLayoutX(0);
            }else
            {
                if (lenode==1)
                {
                 sh.setTranslateX(recC.getRadius());
                 sh.setLayoutX(0);
                }

            }

        }
        
        if (sh.getLayoutX()+sh.getTranslateX()>place.getWidth())
        {
                        if (lenode==0)
            {
             sh.setTranslateX(place.getWidth()-recR.getWidth());
             sh.setLayoutX(0);
            }else
              {
                 if (lenode==1)
                    {
                        sh.setTranslateX(place.getWidth()-recC.getRadius());
                         sh.setLayoutX(0);
                                
                    }
               }
                    
        }
        
        
        
    }*/
    @FXML
    public void appliquer_pos() {
        desactiver_error();
        if (lenode == 0 && ir > 0) {

            recR.setLayoutX(0);
            recR.setLayoutY(0);

            recR.setTranslateX(Float.valueOf(posx.getText()) - recR.getLayoutX());
            recR.setTranslateY(Float.valueOf(posy.getText()) - recR.getLayoutY());
        } else {
            if (lenode == 1 && ic > 0) {
                recC.setLayoutX(0);
                recC.setLayoutY(0);
                recC.setTranslateX(Float.valueOf(posx.getText()) - recC.getLayoutX());
                recC.setTranslateY(Float.valueOf(posy.getText()) - recC.getLayoutX());
            } else {
                if (lenode == 2) {
                    recT.setTranslateX(Float.valueOf(posx.getText()) - recT.getLayoutX());
                    recT.setTranslateY(Float.valueOf(posy.getText()) - recT.getLayoutY());
                } else {
                    if (lenode == 3) {
                        rechex.setTranslateX(Float.valueOf(posx.getText()) - rechex.getLayoutX());
                        rechex.setTranslateY(Float.valueOf(posy.getText()) - rechex.getLayoutY());
                    } else {
                        if (lenode == 4) {
                            recpara.setTranslateX(Float.valueOf(posx.getText()) - recpara.getLayoutX());
                            recpara.setTranslateY(Float.valueOf(posy.getText()) - recpara.getLayoutY());
                        } else {
                            if (lenode == 5) {
                                recpenta.setTranslateX(Float.valueOf(posx.getText()) - recpenta.getLayoutX());
                                recpenta.setTranslateY(Float.valueOf(posy.getText()) - recpenta.getLayoutY());
                            } else {
                                if (lenode == 6) {
                                    recrh.setTranslateX(Float.valueOf(posx.getText()) - recrh.getLayoutX());
                                    recrh.setTranslateY(Float.valueOf(posy.getText()) - recrh.getLayoutY());
                                } else {
                                    if (lenode == 7) {
                                        rectp.setTranslateX(Float.valueOf(posx.getText()) - rectp.getLayoutX());
                                        rectp.setTranslateY(Float.valueOf(posy.getText()) - rectp.getLayoutY());
                                    } else {
                                        if (lenode == 8) {
                                            rectis.setTranslateX(Float.valueOf(posx.getText()) - rectis.getLayoutX());
                                            rectis.setTranslateY(Float.valueOf(posy.getText()) - rectis.getLayoutY());
                                        } else {
                                            if (lenode == 9) if (lenode == 8) {
                                                recte.setTranslateX(Float.valueOf(posx.getText()) - recte.getLayoutX());
                                                recte.setTranslateY(Float.valueOf(posy.getText()) - recte.getLayoutY());
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
  /* 
public String gy(Shape s)
{
    return s.getLayoutY()+s.getTranslateY()+"";
}

public String gx(Shape s)
{
    return s.getLayoutX()+s.getTranslateX()+"";
}*/
            
           
  /*
    @FXML 
    public void pos_top()
    {desactiver_error();
        if (Float.valueOf(pos_input.getText())>0)
        {
            if (lenode==0&&ir>0)
            {
                
                
                if (-1*Float.valueOf(pos_input.getText())+recR.getTranslateY()>0)
                {
                    
                recR.setTranslateY(-1*Float.valueOf(pos_input.getText())+recR.getTranslateY());
                posy.setText(recR.getTranslateY()+"");
                }else
                {
                      recR.setTranslateY(0);
                      posy.setText((recR.getTranslateY()+recR.getLayoutY())+"");
                }
            }else
            {
                if (lenode==1&&ic>0)
                {
                 if (-1*Float.valueOf(pos_input.getText())+recC.getTranslateY()-recC.getRadius()>0)
                {
                recC.setTranslateY(-1*Float.valueOf(pos_input.getText())+recC.getTranslateY());
                posy.setText((recC.getTranslateY()+recC.getLayoutY())+"");
                }else
                {
                      recC.setTranslateY(recC.getRadius());
                      posy.setText(recC.getTranslateY()+"");
                      
                }
                }else
                {
                    if (lenode==2&&it>0)
                    {
                        recT.setTranslateY(-1*Float.valueOf(pos_input.getText())+recT.getTranslateY());
                        
                    }
                }
            }
            
        }
        
    }
        @FXML 
    public void pos_left()
    {
        desactiver_error();
 
 if (Float.valueOf(pos_input.getText())>0)
        {
            if (lenode==0&&ir>0)
            {
                if (-1*Float.valueOf(pos_input.getText())+recR.getTranslateX()>0)
                {
                recR.setTranslateX(-1*Float.valueOf(pos_input.getText())+recR.getTranslateX());
                 posx.setText(recR.getTranslateX()+"");
                }else
                {
                      recR.setTranslateX(0);
                       posx.setText(recR.getTranslateX()+"");
                }
                func_verif_pos(recR);
            }else
            {
                if (lenode==1&&ic>0)
                {
                 if (-1*Float.valueOf(pos_input.getText())+recC.getTranslateX()>recC.getRadius())
                {
                recC.setTranslateX(-1*Float.valueOf(pos_input.getText())+recC.getTranslateX());
                 posx.setText(recC.getTranslateX()+"");
                }else
                {
                      recC.setTranslateX(recC.getRadius());
                       posx.setText(recC.getTranslateX()+"");
                }
                }
                func_verif_pos(recC);
            }
            
        }
            
        
        
    }
        @FXML 
    public void pos_right()
    {
        desactiver_error();

 if (Float.valueOf(pos_input.getText())>0)
        {
            if (lenode==0&&ir>0)
            {
                if (Float.valueOf(pos_input.getText())+recR.getTranslateX()<place.getWidth()-recR.getWidth())
                {
                recR.setTranslateX(Float.valueOf(pos_input.getText())+recR.getTranslateX());
               posx.setText(recR.getTranslateX()+"");
        
                }else
                {
                      recR.setTranslateX(place.getWidth()-recR.getWidth());
                       posx.setText(recR.getTranslateX()+"");
                }
            }else
            {
                if (lenode==1&&ic>0)
                {
                 if (Float.valueOf(pos_input.getText())+recC.getTranslateX()+recC.getRadius()<place.getWidth())
                {
                recC.setTranslateX(Float.valueOf(pos_input.getText())+recC.getTranslateX());
                posx.setText(recC.getTranslateX()+"");
                }else
                {
                      recC.setTranslateX(place.getWidth()-recC.getRadius());
                      posx.setText(recC.getTranslateX()+"");
                }
                }
            }
            
        
            
        }
    }
        @FXML 
    public void pos_bottom()
    {desactiver_error();
        if (Float.valueOf(pos_input.getText())>0)
        {
            if (lenode==0&&ir>0)
            {
                if (Float.valueOf(pos_input.getText())+recR.getTranslateY()<place.getHeight()-recR.getHeight())
                {
                recR.setTranslateY(Float.valueOf(pos_input.getText())+recR.getTranslateY());
                posy.setText(recR.getTranslateY()+"");
                }else
                {
                      recR.setTranslateY(place.getHeight()-recR.getHeight());
                       posy.setText(recR.getTranslateY()+"");
                }
            }else
            {
                if (lenode==1&&ic>0)
                {
                 if (Float.valueOf(pos_input.getText())+recC.getTranslateY()+recC.getRadius()<place.getHeight())
                {
                recC.setTranslateY(Float.valueOf(pos_input.getText())+recC.getTranslateY());
                posy.setText(recC.getTranslateY()+"");
                }else
                {
                      recC.setTranslateY(place.getHeight()-recC.getRadius());
                      posy.setText(recC.getTranslateY()+"");
                }
                }
            }
            
        }
    }*/


    /**
     * *****************Triangle**************************
     */
    @FXML
    public void dessiner_tf() {
        retour_num_tria.setDisable(true);
        retour_num_tria.setVisible(false);
        if (dessiner_t.getText() == "Fin") {

            int i = 0;
            point_place = false;
            Polygon c = new Polygon();
            desactiver_car_cote();
            c.getPoints().addAll(respoitx[0], respoity[0],
                    respoitx[1], respoity[1],
                    respoitx[2], respoity[2]
            );


            for (i = 0; i < 3; i++) {
         /* points[i].setDisable(true);
          points[i].setVisible(false);*/
                place.getChildren().remove(points_triangle_num[i]);

                respoitx[i] = 0.0;
                respoity[i] = 0.0;

            }
            nbpoint = 0;
            dessiner_t.setText("Cliquer ici pour dessiner votre triangle");


            activer_carac_triangle();
            c.setFill(resColor);
            desactiver_error();
            active_pos();
            active_retate();
            pas_element_2D.setVisible(false);
            pas_element_2D.setDisable(true);
            desactiver_rayon();
            c.setCursor(Cursor.HAND);
            c.setStroke(Color.WHITE);
            mis_0_focus_coleur_border();
            posx.setText(c.getTranslateX() + "");
            posy.setText(c.getTranslateY() + "");
            rotatevalue.setText("0");

            c.setStrokeWidth(3);
            c.setStroke(Color.valueOf("rgb(19,170,220)"));

            this.place.getChildren().add(c);
            this.it++;
            mise_a_jour_shape_existe();
            recT = c;
            lenode = 2;
            dragAndDrop(recT);
            mise_a_jour_ca("Triangle");
            desactiver_hateur_largeur();
            c.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {

                    desactiver_error();
                    mise_a_jour_shape_existe();
                    desactiver_rayon();
                    desactiver_hateur_largeur();
                    active_pos();
                    active_retate();
                    activer_carac_triangle();
                    pas_element_2D.setVisible(false);
                    pas_element_2D.setDisable(true);
                    mis_0_focus_coleur_border();
                    recT = (Polygon) e.getTarget();
                    lenode = 2;
                    resColor = (Color) recT.getFill();
                    posx.setText(recT.getTranslateX() + "");
                    posy.setText(recT.getTranslateY() + "");
                    mise_a_jour_retate();
                    dragAndDrop(recT);


                    recT.setStrokeWidth(3);
                    recT.setStroke(Color.valueOf("rgb(19,170,220)"));


                    mise_a_jour_ca("Triangle");

                }
            });

        } else {
            point_place = true;
            dessiner_t.setText("Etape " + nbpoint + " sur 3");

        }


    }


    @FXML
    public void clique_place(MouseEvent e) {
        double xx = e.getX();
        double xy = e.getY();

        if (this.nbpoint == 2) {
            dessiner_t.setText("Fin");
        }


        if (point_place && this.nbpoint < 3) {
            respoitx[nbpoint] = xx;
            respoity[nbpoint] = xy;
            points_triangle_num[nbpoint] = new Text("x (" + (nbpoint + 1) + ")");
            points_triangle_num[nbpoint].setX(num_triangle_x(xx));
            points_triangle_num[nbpoint].setY(num_triangle_y(xy));
            points_triangle_num[nbpoint].setFill(Color.valueOf("rgb(19,180,230)"));
            points_triangle_num[nbpoint].setFont(new Font(18));


            this.nbpoint++;
            if (nbpoint != 3) {
                dessiner_t.setText("Etape " + nbpoint + " sur 3");
            }
        
        /*Circle point=new Circle(0);
        point.setFill(Color.BLACK);
        point.setOpacity(0.7);
        point.setTranslateX(xx);
        point.setTranslateY(xy);
        place.getChildren().add(point);*/
            place.getChildren().add(points_triangle_num[nbpoint - 1]);
       
       /* points[nbpoint-1]=point;*/
            if (nbpoint == 1) {
                retour_num_tria.setDisable(!true);
                retour_num_tria.setVisible(!false);
            }


        }


    }

    @FXML
    public void polygon(MouseEvent e) {

    }


    public void desactiver_carac_triangle() {

        nbpoint = 0;
        desactive_triangle_button();
        dessiner_t.setText("Cliquer ici");
        int i;
        point_place = false;
        for (i = 0; i < 3; i++) {
          /*if (points[i]!=null){
          points[i].setDisable(true);
          points[i].setVisible(false);
          points[i]=null;}*/
            place.getChildren().remove(points_triangle_num[i]);

            respoitx[i] = 0.0;
            respoity[i] = 0.0;

        }


    }

    public void activer_carac_triangle() {


    }

    public double num_triangle_x(double xx) {
        double a;
        if (xx > 8 && xx < place.getWidth() - 15.0) {
            a = xx;
        } else {
            if (xx < 8.0) {
                a = xx + 10.0;
            } else {
                a = place.getWidth() - 18.0;
            }
        }
        return a;

    }

    public double num_triangle_y(double xy) {
        double a;
        if (xy > 10.0 && xy < place.getHeight() - 10) {
            a = xy + 6.0;
        } else {
            if (xy < 10.0) {
                a = 11.0;
            } else {
                a = place.getHeight() - 15.0;
            }
        }
        return a;

    }


    @FXML
    public void retour_num_triaf() {
        if (point_place) {
            if (nbpoint != 0) {
                respoitx[nbpoint - 1] = 0.0;
                respoity[nbpoint - 1] = 0.0;
                place.getChildren().remove(points_triangle_num[nbpoint - 1]);
                nbpoint--;
                dessiner_t.setText("Etape " + nbpoint + " sur 3");

                if (nbpoint == 0) {
                    retour_num_tria.setDisable(true);
                    retour_num_tria.setVisible(false);


                }

            }

        }

    }


    public void active_pos() {
        paneca.setPrefHeight(269);
        gridepos.setVisible(true);
        gridepos.setDisable(false);
        bare1.setVisible(true);
        bare1.setDisable(false);
        /*grid_auto_pose.setVisible(true);
        grid_auto_pose.setDisable(false);*/
        footerca.setLayoutY(269);


    }

    public void active_retate() {
        paneca.setPrefHeight(312);
        panerotate.setDisable(false);
        panerotate.setVisible(true);
        footerca.setLayoutY(312);

    }

    public void desactive_retate() {
        panerotate.setDisable(true);
        panerotate.setVisible(false);
        paneca.setPrefHeight(210);
        footerca.setLayoutY(210);
    }

    public void desactive_pos() {

        paneca.setPrefHeight(210);
        gridepos.setVisible(false);
        gridepos.setDisable(true);
        bare1.setVisible(false);
        bare1.setDisable(true);
       /* grid_auto_pose.setVisible(false);
        grid_auto_pose.setDisable(true);  */
        footerca.setLayoutY(210);


    }


    public void mise_a_jour_shape_existe() {

        shape_existe = ic + ir + it + ihex + ipara + ipenta + irh + itp + its + ite > 0;
        panecara.setVisible(shape_existe);
        panecara.setDisable(!shape_existe);
    }
    
   
   
    
    /*_______________________________________________________*/
    
    /* mise_a_jour changement de  pane panecara , resShape*/

    public void mise_a_jour_ca(String s) {
        textca.setText(s);
        colorca.setFill(resColor);


    }
    
     /*_______________________________________________________*/

    public void mis_0_focus_coleur_border() {

        if (lenode == 0) {

            recR.setStrokeWidth(0);

        } else {
            if (lenode == 1) {
                recC.setStrokeWidth(0);

            } else {
                if (lenode == 2) {
                    recT.setStrokeWidth(0);

                } else {
                    if (lenode == 3) {
                        rechex.setStrokeWidth(0);
                    } else {
                        if (lenode == 4) {
                            recpara.setStrokeWidth(0);
                        } else {
                            if (lenode == 5) {
                                recpenta.setStrokeWidth(0);
                            } else {
                                if (lenode == 6) {
                                    recrh.setStrokeWidth(0);
                                } else {
                                    if (lenode == 7) if (lenode == 6) {
                                        rectp.setStrokeWidth(0);

                                    } else {
                                        if (lenode == 8) {
                                            rectis.setStrokeWidth(0);

                                        } else {
                                            if (lenode == 9) {
                                                recte.setStrokeWidth(0);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void desactiver_error() {


        if (lenode == 7)
            rectp.setStrokeWidth(0);


        if (lenode == 8)
            rectis.setStrokeWidth(0);
        if (lenode == 9) {
            recte.setStrokeWidth(0);
        }


        error.setVisible(false);
        error.setDisable(true);

    }

    public void deletecara() {
        panecara.setVisible(false);
        panecara.setDisable(true);
        pas_element_2D.setVisible(true);
        pas_element_2D.setDisable(false);
    }


    public void activer_hateur_largeur() //pour  Rectangle
    {
        hauteur_largeur.setVisible(true);
        hauteur_largeur.setDisable(false);
        if (lenode == 0) {
            hauteur.setText("" + recR.getHeight() + "");
            largeur.setText("" + recR.getWidth() + "");
        } else {
            if (lenode == 4) {
                //hauteur.setText(""+rechex+"");
                //largeur.setText(""+rechex.getWidth()+"");

            } else {
                if (lenode == 7) {
                    //rectp.set mossabbe !!
                } else {
                    if (lenode == 8) {

                    }

                }
            }
        }

    }

    public void desactiver_hateur_largeur() //pour Rectangle
    {
        hauteur_largeur.setVisible(false);
        hauteur_largeur.setDisable(true);
    }


    public void activer_rayon()// pour Circle
    {
        rayon.setVisible(true);
        rayon.setDisable(false);
        value_rayon.setText(recC.getRadius() + "");
    }

    public void desactiver_rayon() //pour Circle
    {
        rayon.setVisible(false);
        rayon.setDisable(true);
    }

    public boolean controle_x_y_creation_r(Rectangle R) //géré le layout
    {

        if (place.getWidth() > R.getWidth() && place.getHeight() > R.getHeight()) {
            double x = R.getWidth() + R.getTranslateX();
            double y = R.getHeight() + R.getTranslateY();


            while (x > place.getWidth() && x != 0) {
                x = x - 1;

            }

            while (y > place.getHeight() && y != 0) {
                y = y - 1;
            }

            if (x != 0 && y != 0) {
                R.setTranslateX(x - R.getWidth());
                R.setTranslateY(y - R.getHeight());
                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }

    }

    public boolean controle_x_y_creation_c(Circle c) {
        double x = c.getTranslateX();
        double y = c.getTranslateY();
        double r = c.getRadius();
        boolean bx = false;
        boolean by = false;


        if (x - r > 0 && x + r < place.getWidth()) {
            bx = true;
        } else {
            if (x - r < 0) {
                while (x - r < 0) {
                    x++;
                }
            } else {
                while (x + r > place.getWidth()) {
                    x--;
                }
            }
            if (x - r > 0 && x + r < place.getWidth()) {
                bx = true;
                c.setTranslateX(x);
            } else {
                bx = false;
            }

        }
        if (y - r > 0 && y + r < place.getHeight()) {
            by = true;
        } else {
            if (y - r < 0) {
                while (y - r < 0) {
                    y++;
                }
            } else {
                while (y + r > place.getHeight()) {
                    y--;
                }
            }
            if (y - r > 0 && y + r < place.getHeight()) {
                by = true;
                c.setTranslateY(y);
            } else {
                by = false;
            }

        }
        return by && bx;

    }

    // conroele creation circle

    public void mise_a_jour_retate() {
        if (lenode == 0) {
            rotatevalue.setText(recR.getRotate() + " ");
        } else {
            if (lenode == 3) {
                rotatevalue.setText(rechex.getRotate() + " ");
            } else {
                if (lenode == 2) {
                    rotatevalue.setText(recT.getRotate() + " ");
                } else {
                    if (lenode == 3) {
                        rotatevalue.setText(rechex.getRotate() + " ");
                    } else {
                        if (lenode == 4) {
                            rotatevalue.setText(recpara.getRotate() + " ");
                        } else {
                            if (lenode == 5) {
                                rotatevalue.setText(recpenta.getRotate() + " ");
                            } else {
                                if (lenode == 6) {
                                    rotatevalue.setText(recrh.getRotate() + " ");
                                } else {
                                    if (lenode == 7) {
                                        rotatevalue.setText(rectp.getRotate() + " ");
                                    } else {
                                        if (lenode == 8) {
                                            rotatevalue.setText(rectis.getRotate() + " ");
                                        } else {
                                            if (lenode == 9) {
                                                rotatevalue.setText(recte.getRotate() + " ");
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }


    }


    @FXML
    public void appliquer_retate() {
        if (lenode == 0) {
            recR.setRotate(Float.valueOf(rotatevalue.getText()));
        } else {
            if (lenode == 2) {
                recT.setRotate(Float.valueOf(rotatevalue.getText()));
            } else {
                if (lenode == 3) {
                    rechex.setRotate(Float.valueOf(rotatevalue.getText()));
                } else {
                    if (lenode == 4) {
                        recpara.setRotate(Float.valueOf(rotatevalue.getText()));
                    } else {
                        if (lenode == 5) {
                            recpenta.setRotate(Float.valueOf(rotatevalue.getText()));
                        } else {
                            if (lenode == 6) {
                                recrh.setRotate(Float.valueOf(rotatevalue.getText()));
                            } else {
                                if (lenode == 7) {
                                    rectp.setRotate(Float.valueOf(rotatevalue.getText()));
                                } else {
                                    if (lenode == 8) {
                                        rectis.setRotate(Float.valueOf(rotatevalue.getText()));
                                    } else {
                                        if (lenode == 9) {
                                            recte.setRotate(Float.valueOf(rotatevalue.getText()));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    }

    @FXML
    public void appliquer_cote() {

        // ((Hexagon)rechex).setHeight(50.0);


    }

    public void afficher_car_cote() {
        affichage_cote.setVisible(true);
        affichage_cote.setDisable(false);
        if (lenode == 3 && ihex > 0) {
            //  textcote.setText(""+rechex.getHeight());
        }
    }

    public void desactiver_car_cote() {
        affichage_cote.setVisible(!true);
        affichage_cote.setDisable(!false);

    }


    //====================================================================

    public void dragAndDrop(Shape shape) {
        Delta delta = new Delta();
        final DropShadow dropShadow = new DropShadow();
        final Glow glow = new Glow();
        shape.setEffect(dropShadow);

        shape.setStrokeWidth(3);
        shape.setStroke(Color.valueOf("rgb(19,170,220)"));

        shape.setOnMousePressed((MouseEvent mouseEvent) -> {
            shape.setStrokeWidth(3);
            shape.setStroke(Color.valueOf("rgb(19,170,220)"));
            delta.x = shape.getLayoutX() - mouseEvent.getSceneX();
            delta.y = shape.getLayoutY() - mouseEvent.getSceneY();
            shape.setCursor(Cursor.MOVE);


        });

        shape.setOnMouseReleased((MouseEvent mouseEvent) -> {
            shape.setCursor(Cursor.HAND);
        });
        shape.setOnMouseDragged((MouseEvent mouseEvent) -> {
            shape.setStrokeWidth(3);
            shape.setStroke(Color.valueOf("rgb(19,170,220)"));
            shape.setLayoutX(mouseEvent.getSceneX() + delta.x);
            shape.setLayoutY(mouseEvent.getSceneY() + delta.y);


        });
        shape.setOnMouseEntered((MouseEvent mouseEvent) -> {

            shape.setCursor(Cursor.HAND);
            dropShadow.setInput(glow);


        });

        shape.setOnMouseExited((MouseEvent mouseEvent) -> {
            posx.setText((shape.getLayoutX() + shape.getTranslateX()) + "");
            posy.setText((shape.getLayoutY() + shape.getTranslateY()) + "");


            dropShadow.setInput(null);


        });

    }

    @FXML


    /******************************* TrianglePerpenduculaire *************************************/
    public void ahex() {
        Hexagon f = new Hexagon(75.0);
        afficher_car_cote();
        Polygon tp = f.createHexagon();
        tp.setTranslateX(Math.random() * 800);  //random
        tp.setTranslateY(Math.random() * 400);   //random
        ihex++;
        desactiver_hateur_largeur();
        desactiver_error();
        desactiver_carac_triangle();
        active_pos();
        active_retate();
        pas_element_2D.setVisible(false);
        pas_element_2D.setDisable(true);
        desactiver_rayon();
        tp.setCursor(Cursor.HAND);
        mis_0_focus_coleur_border();
        posx.setText(tp.getLayoutX() + tp.getTranslateX() + "");
        posy.setText(tp.getLayoutY() + tp.getTranslateY() + "");
        rotatevalue.setText("0");
        tp.setStrokeWidth(3);
        tp.setStroke(Color.valueOf("rgb(19,170,220)"));
        tp.setFill(resColor);
        this.place.getChildren().add(tp);
        lenode = 3;
        mise_a_jour_shape_existe();
        rechex = tp;

        mise_a_jour_ca("Hexagon");
        // activer_hateur_largeur();
        dragAndDrop(rechex);
        tp.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent e) {
                afficher_car_cote();
                desactiver_hateur_largeur();
                desactiver_error();
                desactiver_carac_triangle();
                active_pos();
                active_retate();
                mise_a_jour_shape_existe();
                desactiver_rayon();
                pas_element_2D.setVisible(false);
                pas_element_2D.setDisable(true);
                mis_0_focus_coleur_border();
                rechex = (Polygon) e.getTarget();

                lenode = 3;
                dragAndDrop(rechex);
                resColor = (Color) rechex.getFill();
                //activer_hateur_largeur();
                posx.setText(rechex.getLayoutX() + rechex.getTranslateX() + "");
                posy.setText(rechex.getLayoutY() + rechex.getTranslateY() + "");
                mise_a_jour_retate();

                rechex.setStrokeWidth(3);
                rechex.setStroke(Color.valueOf("rgb(19,170,220)"));


                mise_a_jour_ca("Hexagon");

            }
        });


    }

    @FXML
    public void aparall() /**/ {
        desactiver_error();
        Parallelogram para = new Parallelogram(120.0, 90.0);
        Polygon a = para.createParallelogram();
        a.setTranslateX(Math.random() * 800);
        a.setTranslateY(Math.random() * 400);

        desactiver_car_cote();
        desactiver_carac_triangle();
        active_pos();
        active_retate();
        pas_element_2D.setVisible(false);
        pas_element_2D.setDisable(true);
        desactiver_rayon();
        a.setCursor(Cursor.HAND);
        a.setStroke(Color.WHITE);
        mis_0_focus_coleur_border();
        posx.setText(a.getLayoutX() + a.getTranslateX() + "");
        posy.setText(a.getLayoutY() + a.getTranslateY() + "");
        rotatevalue.setText("0");
        a.setStrokeWidth(3);
        a.setStroke(Color.valueOf("rgb(19,170,220)"));

        a.setFill(resColor);
        this.place.getChildren().add(a);
        this.ipara++;
        mise_a_jour_shape_existe();
        recpara = a;
        lenode = 4;
        mise_a_jour_ca("Parallélogramme");
        activer_hateur_largeur();
        dragAndDrop(a);
        a.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                desactiver_car_cote();
                desactiver_error();
                desactiver_carac_triangle();
                active_pos();
                active_retate();
                mise_a_jour_shape_existe();
                desactiver_rayon();
                pas_element_2D.setVisible(false);
                pas_element_2D.setDisable(true);
                mis_0_focus_coleur_border();
                recpara = (Polygon) e.getTarget();
                lenode = 4;
                dragAndDrop(recpara);
                resColor = (Color) recpara.getFill();
                activer_hateur_largeur();
                posx.setText(recpara.getLayoutX() + recpara.getTranslateX() + "");
                posy.setText(recpara.getLayoutY() + recpara.getTranslateY() + "");
                mise_a_jour_retate();

                recpara.setStrokeWidth(3);
                recpara.setStroke(Color.valueOf("rgb(19,170,220)"));


                mise_a_jour_ca("Parallélogramme");

            }
        });


    }

    @FXML
    public void apenta() {
        Pentagon f = new Pentagon(75.0);
        afficher_car_cote();
        Polygon tp = f.createPentagon();
        tp.setTranslateX(Math.random() * 800);  //random
        tp.setTranslateY(Math.random() * 400);   //random
        ipenta++;
        desactiver_hateur_largeur();
        desactiver_error();
        desactiver_carac_triangle();
        active_pos();
        active_retate();
        pas_element_2D.setVisible(false);
        pas_element_2D.setDisable(true);
        mis_0_focus_coleur_border();
        desactiver_rayon();
        tp.setCursor(Cursor.HAND);

        posx.setText(tp.getLayoutX() + tp.getTranslateX() + "");
        posy.setText(tp.getLayoutY() + tp.getTranslateY() + "");
        rotatevalue.setText("0");
        tp.setStrokeWidth(3);
        tp.setStroke(Color.valueOf("rgb(19,170,220)"));
        tp.setFill(resColor);
        this.place.getChildren().add(tp);
        lenode = 5;
        mise_a_jour_shape_existe();
        recpenta = tp;

        mise_a_jour_ca("Pentagon");
        // activer_hateur_largeur();
        dragAndDrop(recpenta);
        tp.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent e) {
                afficher_car_cote();
                desactiver_hateur_largeur();
                desactiver_error();
                desactiver_carac_triangle();
                active_pos();
                active_retate();
                mise_a_jour_shape_existe();
                desactiver_rayon();
                pas_element_2D.setVisible(false);
                pas_element_2D.setDisable(true);
                mis_0_focus_coleur_border();
                recpenta = (Polygon) e.getTarget();

                lenode = 5;
                dragAndDrop(recpenta);
                resColor = (Color) recpenta.getFill();
                //activer_hateur_largeur();
                posx.setText(recpenta.getLayoutX() + recpenta.getTranslateX() + "");
                posy.setText(recpenta.getLayoutY() + recpenta.getTranslateY() + "");
                mise_a_jour_retate();

                recpenta.setStrokeWidth(3);
                recpenta.setStroke(Color.valueOf("rgb(19,170,220)"));


                mise_a_jour_ca("Pentagon");

            }
        });
    }

    @FXML
    public void arho() /**/ {
        desactiver_error();
        Rhombus para = new Rhombus(120.0, 90.0);
        Polygon a = para.createRhombus();
        a.setTranslateX(Math.random() * 800);
        a.setTranslateY(Math.random() * 400);

        desactiver_car_cote();
        desactiver_carac_triangle();
        active_pos();
        active_retate();
        pas_element_2D.setVisible(false);
        pas_element_2D.setDisable(true);
        desactiver_rayon();
        a.setCursor(Cursor.HAND);
        a.setStroke(Color.WHITE);
        mis_0_focus_coleur_border();
        posx.setText(a.getLayoutX() + a.getTranslateX() + "");
        posy.setText(a.getLayoutY() + a.getTranslateY() + "");
        rotatevalue.setText("0");
        a.setStrokeWidth(3);
        a.setStroke(Color.valueOf("rgb(19,170,220)"));

        a.setFill(resColor);
        this.place.getChildren().add(a);
        this.irh++;
        mise_a_jour_shape_existe();
        recrh = a;
        lenode = 6;
        mise_a_jour_ca("Rhombus");
        activer_hateur_largeur();
        dragAndDrop(a);
        a.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                desactiver_car_cote();
                desactiver_error();
                desactiver_carac_triangle();
                active_pos();
                active_retate();
                mise_a_jour_shape_existe();
                desactiver_rayon();
                pas_element_2D.setVisible(false);
                pas_element_2D.setDisable(true);
                mis_0_focus_coleur_border();
                recrh = (Polygon) e.getTarget();
                lenode = 6;
                dragAndDrop(recrh);
                resColor = (Color) recrh.getFill();
                activer_hateur_largeur();
                posx.setText(recrh.getLayoutX() + recrh.getTranslateX() + "");
                posy.setText(recrh.getLayoutY() + recrh.getTranslateY() + "");
                mise_a_jour_retate();

                recrh.setStrokeWidth(3);
                recrh.setStroke(Color.valueOf("rgb(19,170,220)"));


                mise_a_jour_ca("Rhombus");

            }
        });


    }

    public void atp() /**/

    {
        mis_0_focus_coleur_border();
        desactiver_error();
        TrianglePerpenduculaire s = new TrianglePerpenduculaire(120, 80);
        Shape a = s.createTrianglePerpenduculaire();
        a.setTranslateX(Math.random() * 800);
        a.setTranslateY(Math.random() * 400);

        desactiver_car_cote();
        desactiver_carac_triangle();
        active_pos();
        active_retate();
        pas_element_2D.setVisible(false);
        pas_element_2D.setDisable(true);
        desactiver_rayon();
        a.setCursor(Cursor.HAND);
        a.setStroke(Color.WHITE);

        posx.setText(a.getLayoutX() + a.getTranslateX() + "");
        posy.setText(a.getLayoutY() + a.getTranslateY() + "");
        rotatevalue.setText("0");
        a.setStrokeWidth(3);
        a.setStroke(Color.valueOf("rgb(19,170,220)"));

        a.setFill(resColor);
        this.place.getChildren().add(a);
        this.itp++;

        rectp = a;
        lenode = 7;
        mise_a_jour_shape_existe();
        mise_a_jour_ca("Triangle Perpenduculaire");
        activer_hateur_largeur();
        dragAndDrop(a);
        a.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                mis_0_focus_coleur_border();
                desactiver_car_cote();
                desactiver_error();
                desactiver_carac_triangle();
                active_pos();
                active_retate();

                desactiver_rayon();
                pas_element_2D.setVisible(false);
                pas_element_2D.setDisable(true);

                rectp = (Shape) e.getTarget();
                lenode = 7;
                mise_a_jour_shape_existe();
                dragAndDrop(rectp);
                resColor = (Color) rectp.getFill();
                activer_hateur_largeur();
                posx.setText(rectp.getLayoutX() + rectp.getTranslateX() + "");
                posy.setText(rectp.getLayoutY() + rectp.getTranslateY() + "");
                mise_a_jour_retate();

                rectp.setStrokeWidth(3);
                rectp.setStroke(Color.valueOf("rgb(19,170,220)"));
                mise_a_jour_ca("Triangle Perpenduculaire");

            }
        });


    }

    @FXML
    public void ar() /**/ {
        desactiver_error();
        Rectangle a = new Rectangle(120, 90);
        a.setTranslateX(Math.random() * 800);
        a.setTranslateY(Math.random() * 400);
        if (controle_x_y_creation_r(a))

        {
            desactiver_car_cote();
            desactiver_carac_triangle();
            active_pos();
            active_retate();
            pas_element_2D.setVisible(false);
            pas_element_2D.setDisable(true);
            desactiver_rayon();
            a.setCursor(Cursor.HAND);
            a.setStroke(Color.WHITE);
            mis_0_focus_coleur_border();
            posx.setText(a.getLayoutX() + a.getTranslateX() + "");
            posy.setText(a.getLayoutY() + a.getTranslateY() + "");
            rotatevalue.setText("0");
            a.setStrokeWidth(3);
            a.setStroke(Color.valueOf("rgb(19,170,220)"));

            a.setFill(resColor);
            this.place.getChildren().add(a);
            this.ir++;
            mise_a_jour_shape_existe();
            recR = a;
            lenode = 0;
            mise_a_jour_ca("Rectangle");
            activer_hateur_largeur();
            dragAndDrop(a);
            a.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    desactiver_car_cote();
                    desactiver_error();
                    desactiver_carac_triangle();
                    active_pos();
                    active_retate();
                    mise_a_jour_shape_existe();
                    desactiver_rayon();
                    pas_element_2D.setVisible(false);
                    pas_element_2D.setDisable(true);
                    mis_0_focus_coleur_border();
                    recR = (Rectangle) e.getTarget();
                    lenode = 0;
                    dragAndDrop(recR);
                    resColor = (Color) recR.getFill();
                    activer_hateur_largeur();
                    posx.setText(recR.getLayoutX() + recR.getTranslateX() + "");
                    posy.setText(recR.getLayoutY() + recR.getTranslateY() + "");
                    mise_a_jour_retate();

                    recR.setStrokeWidth(3);
                    recR.setStroke(Color.valueOf("rgb(19,170,220)"));

                    if (recR.getWidth() == recR.getHeight()) {
                        mise_a_jour_ca("Carré");
                    } else {
                        mise_a_jour_ca("Rectangle");
                    }
                }
            });
        }

    }

    @FXML
 /* création  d'un Cercle ,changement de ,resR ,lenode,type "Circle" ,ir,appeler la methode*/
    public void ac() {
        desactiver_error();
        Circle b = new Circle(30);

        b.setTranslateX(Math.random() * 800);
        b.setTranslateY(Math.random() * 400);
        if (controle_x_y_creation_c(b)) {
            desactiver_hateur_largeur();
            desactiver_car_cote();
            desactiver_carac_triangle();
            desactive_retate();
            rotatevalue.setText("0");
            active_pos();
            mis_0_focus_coleur_border();

            pas_element_2D.setVisible(false);
            pas_element_2D.setDisable(true);
            posx.setText(b.getTranslateX() + b.getLayoutX() + "");
            posy.setText(b.getTranslateY() + b.getLayoutY() + "");

            b.setFill(resColor);
            dragAndDrop(b);

            b.setStrokeWidth(3);
            b.setStroke(Color.valueOf("rgb(19,170,220)"));

            b.setCursor(Cursor.HAND);
            this.place.getChildren().add(b);
            lenode = 1;
            recC = b;
            this.ic++;
            mise_a_jour_ca("Cercle");
            mise_a_jour_shape_existe();
            activer_rayon();
        }
        b.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                desactiver_car_cote();

                desactiver_carac_triangle();
                desactive_retate();
                rotatevalue.setText("0");
                active_pos();
                desactiver_error();
                pas_element_2D.setVisible(false);
                pas_element_2D.setDisable(true);
                mise_a_jour_shape_existe();
                desactiver_hateur_largeur();
                mis_0_focus_coleur_border();
                recC = (Circle) e.getTarget();
                lenode = 1;
                resColor = (Color) recC.getFill();
                activer_rayon();
                posx.setText(recC.getTranslateX() + recC.getLayoutX() + "");
                posy.setText(recC.getTranslateY() + recC.getLayoutY() + "");
                dragAndDrop(recC);

                recC.setStrokeWidth(3);
                recC.setStroke(Color.valueOf("rgb(19,170,220)"));

                mise_a_jour_ca("Cercle");
            }
        });
    }

    @FXML
    public void atis() /**/ {
        desactiver_error();
        TriangleIsosceles para = new TriangleIsosceles(120.0, 90.0);
        Polygon a = para.createTriangleIsosceles();
        a.setTranslateX(Math.random() * 800);
        a.setTranslateY(Math.random() * 400);

        desactiver_car_cote();
        desactiver_carac_triangle();
        active_pos();
        active_retate();
        pas_element_2D.setVisible(false);
        pas_element_2D.setDisable(true);
        desactiver_rayon();
        a.setCursor(Cursor.HAND);
        a.setStroke(Color.WHITE);
        mis_0_focus_coleur_border();
        posx.setText(a.getLayoutX() + a.getTranslateX() + "");
        posy.setText(a.getLayoutY() + a.getTranslateY() + "");
        rotatevalue.setText("0");
        a.setStrokeWidth(3);
        a.setStroke(Color.valueOf("rgb(19,170,220)"));

        a.setFill(resColor);
        this.place.getChildren().add(a);
        this.ite++;
        mise_a_jour_shape_existe();
        recte = a;
        lenode = 9;
        mise_a_jour_ca("Triangle Equilateral");
        activer_hateur_largeur();
        dragAndDrop(a);
        a.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                desactiver_car_cote();
                desactiver_error();
                desactiver_carac_triangle();
                active_pos();
                active_retate();
                mise_a_jour_shape_existe();
                desactiver_rayon();
                pas_element_2D.setVisible(false);
                pas_element_2D.setDisable(true);
                mis_0_focus_coleur_border();
                recte = (Polygon) e.getTarget();
                lenode = 9;
                dragAndDrop(recte);
                resColor = (Color) recte.getFill();
                activer_hateur_largeur();
                posx.setText(recte.getLayoutX() + recte.getTranslateX() + "");
                posy.setText(recte.getLayoutY() + recte.getTranslateY() + "");
                mise_a_jour_retate();

                recte.setStrokeWidth(3);
                recte.setStroke(Color.valueOf("rgb(19,170,220)"));


                mise_a_jour_ca("Triangle Equilateral");

            }
        });


    }

    @FXML
    public void ae() /**/ {
        desactiver_error();
        TriangleEquilateral para = new TriangleEquilateral(150.0);
        Polygon a = para.createTriangleEquilateral();
        a.setTranslateX(Math.random() * 800);
        a.setTranslateY(Math.random() * 400);

        desactiver_car_cote();
        desactiver_carac_triangle();
        active_pos();
        active_retate();
        pas_element_2D.setVisible(false);
        pas_element_2D.setDisable(true);
        desactiver_rayon();
        a.setCursor(Cursor.HAND);
        a.setStroke(Color.WHITE);
        mis_0_focus_coleur_border();
        posx.setText(a.getLayoutX() + a.getTranslateX() + "");
        posy.setText(a.getLayoutY() + a.getTranslateY() + "");
        rotatevalue.setText("0");
        a.setStrokeWidth(3);
        a.setStroke(Color.valueOf("rgb(19,170,220)"));

        a.setFill(resColor);
        this.place.getChildren().add(a);
        this.its++;
        mise_a_jour_shape_existe();
        rectis = a;
        lenode = 8;
        mise_a_jour_ca("Triangle isocèle");
        activer_hateur_largeur();
        dragAndDrop(a);
        a.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                desactiver_car_cote();
                desactiver_error();
                desactiver_carac_triangle();
                active_pos();
                active_retate();
                mise_a_jour_shape_existe();
                desactiver_rayon();
                pas_element_2D.setVisible(false);
                pas_element_2D.setDisable(true);
                mis_0_focus_coleur_border();
                rectis = (Polygon) e.getTarget();
                lenode = 8;
                dragAndDrop(rectis);
                resColor = (Color) rectis.getFill();
                activer_hateur_largeur();
                posx.setText(rectis.getLayoutX() + rectis.getTranslateX() + "");
                posy.setText(rectis.getLayoutY() + rectis.getTranslateY() + "");
                mise_a_jour_retate();

                rectis.setStrokeWidth(3);
                rectis.setStroke(Color.valueOf("rgb(19,170,220)"));


                mise_a_jour_ca("Triangle isocèle");

            }
        });


    }

    @FXML
    public void export_img() {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Exporter comme une image .PNG");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image files (*.png)", "*.png"));
        File f = fileChooser.showSaveDialog(null);
        if (f == null) return;
        String fName = f.getAbsolutePath();
        if (!fName.toLowerCase().endsWith(".png")) {
            fName += ".png";
        }

        Shape curr_ch = null;
        if (lenode == 0) {
            recR.setStrokeWidth(0);
            curr_ch = recR;
        } else {
            if (lenode == 1) {
                recC.setStrokeWidth(0);
                curr_ch = recC;
            } else {
                if (lenode == 2) {
                    recT.setStrokeWidth(0);
                    curr_ch = recT;
                } else {
                    if (lenode == 3) {
                        rechex.setStrokeWidth(0);
                        curr_ch = rechex;
                    } else {
                        if (lenode == 4) {
                            recpara.setStrokeWidth(0);
                            curr_ch = recpara;
                        } else {
                            if (lenode == 5) {
                                recpenta.setStrokeWidth(0);
                                curr_ch = recpenta;
                            } else {
                                if (lenode == 6) {
                                    recrh.setStrokeWidth(0);
                                    curr_ch = recrh;
                                } else {

                                    if (lenode == 7) if (lenode == 6) {
                                        rectp.setStrokeWidth(0);
                                        curr_ch = rectp;
                                    } else {
                                        if (lenode == 8) {
                                            curr_ch = rectis;
                                        }


                                    }
                                }
                            }
                        }
                    }
                }
            }
        }


        WritableImage image = place.snapshot(new SnapshotParameters(), null);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", new File(fName));
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur lors de l'enregistrement de l'image");
            alert.setContentText("Une erreur lors de l'enregistrement de l'image sous format .PNG est survenue.\n" +
                    "Impossible d'enregistrer l'image sous format .PNG");
            alert.showAndWait();
            e.printStackTrace();
        }
        curr_ch.setStrokeWidth(3);
    }

    class Delta {
        double x, y;
    }
}
