package com.yahiab.app2d;

/**
 * @author magic
 */


import com.sun.javafx.scene.DirtyBits;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.DoublePropertyBase;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;

public class Pentagon extends Polygon {

    private final DoubleProperty height = new DoublePropertyBase() {

        @Override
        public void invalidated() {
            impl_markDirty(DirtyBits.NODE_GEOMETRY);
            impl_geomChanged();
        }

        @Override
        public Object getBean() {
            return Pentagon.this;
        }

        @Override
        public String getName() {
            return "height";
        }
    };
    Polygon shape = new Polygon();
    private DoublePropertyBase x;
    private DoubleProperty y;

    public Pentagon() {


    }


    public Pentagon(double height) {
        setHeight(height);
    }


    public Pentagon(double height, Paint fill) {
        setHeight(height);
        setFill(fill);
    }

    public Pentagon(double x, double y, double height) {
        this(height);
        setX(x);
        setY(y);
    }

    public final double getX() {
        return x == null ? 0.0 : x.get();
    }

    public final void setX(double value) {
        if (x != null || value != 0.0) {
            xProperty().set(value);
        }
    }

    public final DoubleProperty xProperty() {
        if (x == null) {
            x = new DoublePropertyBase() {

                @Override
                public void invalidated() {
                    impl_markDirty(DirtyBits.NODE_GEOMETRY);
                    impl_geomChanged();
                }

                @Override
                public Object getBean() {
                    return Pentagon.this;
                }

                @Override
                public String getName() {
                    return "x";
                }
            };
        }
        return x;
    }

    public Polygon createPentagon() {


        shape.getPoints().addAll(100.0, 0.0,
                (getHeight() / Math.sqrt(2)) + 100.0, (getHeight() / Math.sqrt(2)),
                (getHeight() / Math.sqrt(2) / 2) + 100.0, getHeight() * ((Math.sqrt(2) + Math.sqrt(3)) / 2),
                100.0 - (getHeight() / Math.sqrt(2) / 2), getHeight() * ((Math.sqrt(2) + Math.sqrt(3)) / 2),
                100.0 - (getHeight() / Math.sqrt(2)), (getHeight() / Math.sqrt(2)));


        return (getShape());
    }

    public void setTranslationX(double x) {
        shape.setTranslateX(x);
    }

    public void setTranslationY(double y) {
        shape.setTranslateY(y);
    }

    public final double getY() {
        return y == null ? 0.0 : y.get();
    }

    public final void setY(double value) {
        if (y != null || value != 0.0) {
            yProperty().set(value);
        }
    }

    public final DoubleProperty yProperty() {
        if (y == null) {
            y = new DoublePropertyBase() {

                @Override
                public void invalidated() {
                    impl_markDirty(DirtyBits.NODE_GEOMETRY);
                    impl_geomChanged();
                }

                @Override
                public Object getBean() {
                    return Pentagon.this;
                }

                @Override
                public String getName() {
                    return "y";
                }
            };
        }
        return y;
    }

    public final double getHeight() {
        return height.get();
    }

    public final void setHeight(double value) {
        height.set(value);
    }

    public final DoubleProperty heightProperty() {
        return height;
    }


    /**
     * @return the shape
     */
    public Polygon getShape() {
        return shape;
    }


    /**
     * @param shape the shape to set
     */
    public void setShape(Polygon shape) {
        this.shape = shape;
    }


}


