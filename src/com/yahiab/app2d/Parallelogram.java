package com.yahiab.app2d;

/**
 * @author magic
 */


import com.sun.javafx.scene.DirtyBits;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.DoublePropertyBase;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;

public class Parallelogram extends Polygon {

    private final DoubleProperty width = new DoublePropertyBase() {

        @Override
        public void invalidated() {
            impl_markDirty(DirtyBits.NODE_GEOMETRY);
            impl_geomChanged();
        }

        @Override
        public Object getBean() {
            return Parallelogram.this;
        }

        @Override
        public String getName() {
            return "width";
        }
    };
    private final DoubleProperty height = new DoublePropertyBase() {

        @Override
        public void invalidated() {
            impl_markDirty(DirtyBits.NODE_GEOMETRY);
            impl_geomChanged();
        }

        @Override
        public Object getBean() {
            return Parallelogram.this;
        }

        @Override
        public String getName() {
            return "height";
        }
    };
    Polygon shape = new Polygon();
    private DoubleProperty x;
    private DoubleProperty y;


    public Parallelogram() {


    }


    public Parallelogram(double width, double height) {
        setWidth(width);
        setHeight(height);
    }

    public Parallelogram(double width, double height, Paint fill) {
        setWidth(width);
        setHeight(height);
        setFill(fill);
    }

    public Parallelogram(double x, double y, double width, double height) {
        this(width, height);
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
                    return Parallelogram.this;
                }

                @Override
                public String getName() {
                    return "x";
                }
            };
        }
        return x;
    }

    public Polygon createParallelogram() {


        shape.getPoints().addAll(100.0, 0.0,
                getWidth() + 100.0, 0.0,
                getWidth(), getHeight(),
                0.0, getHeight());


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
                    return Parallelogram.this;
                }

                @Override
                public String getName() {
                    return "y";
                }
            };
        }
        return y;
    }

    public final double getWidth() {
        return width.get();
    }

    public final void setWidth(double value) {
        width.set(value);
    }

    public final DoubleProperty widthProperty() {
        return width;
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
