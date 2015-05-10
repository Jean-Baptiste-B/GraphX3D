package com.yahiab.app2d;

import com.sun.javafx.scene.DirtyBits;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.DoublePropertyBase;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;

/**
 * @author magic
 */


public final class Hexagon extends Polygon {


    Polygon shape = new Polygon();
    private DoubleProperty x;
    private DoubleProperty y;
    private DoubleProperty height = new DoublePropertyBase() {

        @Override
        public void invalidated() {
            impl_markDirty(DirtyBits.NODE_GEOMETRY);
            impl_geomChanged();
        }

        @Override
        public Object getBean() {
            return Hexagon.this;
        }

        @Override
        public String getName() {
            return "height";
        }
    };

    public Hexagon() {


    }


    public Hexagon(double height) {
        setHeight(height);
    }


    public Hexagon(double height, Paint fill) {
        setHeight(height);
        setFill(fill);
    }

    public Hexagon(double x, double y, double height) {
        setHeight(height);
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
                    return Hexagon.this;
                }

                @Override
                public String getName() {
                    return "x";
                }
            };
        }
        return x;
    }

    public Polygon createHexagon() {


        shape.getPoints().addAll(
                100.0, 0.0,
                getHeight() + 100.0, 0.0,
                100.0 + getHeight() + (getHeight() / Math.sqrt(3)), getHeight() * Math.sqrt(((double) 2) / ((double) 3)),
                getHeight() + 100.0, getHeight() * Math.sqrt(((double) 2) / ((double) 3)) * 2,
                100.0, getHeight() * Math.sqrt(((double) 2) / ((double) 3)) * 2,
                100.0 - getHeight() / Math.sqrt(3), getHeight() * Math.sqrt(((double) 2) / ((double) 3))
        );


        return (getShape());
    }

    public void setTranslationX(double x) {
        shape.setTranslateX(x);
    }

    public void setTranslationY(double y) {
        shape.setTranslateY(y);
    }

    public double getY() {
        return y == null ? 0.0 : y.get();
    }

    public void setY(double value) {
        if (y != null || value != 0.0) {
            yProperty().set(value);
        }
    }

    public DoubleProperty yProperty() {
        if (y == null) {
            y = new DoublePropertyBase() {

                @Override
                public void invalidated() {
                    impl_markDirty(DirtyBits.NODE_GEOMETRY);
                    impl_geomChanged();
                }

                @Override
                public Object getBean() {
                    return Hexagon.this;
                }

                @Override
                public String getName() {
                    return "y";
                }
            };
        }
        return y;
    }

    public double getHeight() {
        return height.get();
    }

    public void setHeight(double value) {
        height.set(value);
    }

    public DoubleProperty heightProperty() {
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

	
	
