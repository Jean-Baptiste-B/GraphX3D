package com.yahiab.graphix.model._3d.exporters;

import javafx.collections.ObservableArray;
import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;
import org.fxyz.shapes.primitives.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Export a 3D MeshView to FXML file. */
public class GraphixFXMLExporter {

    private PrintWriter printWriter;
    private Set<String> imports = new TreeSet<>();
    private Map<String, String> simpleNames = new HashMap<>();
    private Map<Class, List<Property>> propertiesCache = new HashMap<>();

    public GraphixFXMLExporter(String filename) {
        File file = new File(filename);
        try {
            printWriter = new PrintWriter(file);
            System.out.println("Saving FMXL to " + file.getAbsolutePath());
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("Failed to export FXML to " + file.getAbsolutePath(), ex);
        }
    }

    public GraphixFXMLExporter(OutputStream outputStream) {
        printWriter = new PrintWriter(outputStream);
    }

    public void export(Node node) {
        printWriter.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        FXML fxmlTree = exportToFXML(node);
        for (String importString : imports) {
            printWriter.println("<?import " + importString + ".*?>");
        }
        printWriter.println();
        fxmlTree.export("");
        printWriter.close();
    }

    private List<Property> getProperties(Class aClass) {
        List<Property> res = new ArrayList<>();
        try {
            if (Point3D.class.isAssignableFrom(aClass)) {
                res.add(new Property(aClass.getMethod("getX"), "x"));
                res.add(new Property(aClass.getMethod("getY"), "y"));
                res.add(new Property(aClass.getMethod("getZ"), "z"));
            }
            if (Translate.class.isAssignableFrom(aClass)) {
                res.add(new Property(aClass.getMethod("getX"), "x"));
                res.add(new Property(aClass.getMethod("getY"), "y"));
                res.add(new Property(aClass.getMethod("getZ"), "z"));
            }
            if (Rotate.class.isAssignableFrom(aClass)) {
                res.add(new Property(aClass.getMethod("getAngle"), "angle"));
                res.add(new Property(aClass.getMethod("getAxis"), "axis"));
            }
            if (Affine.class.isAssignableFrom(aClass)) {
                res.add(new Property(aClass.getMethod("getMxx"), "mxx"));
                res.add(new Property(aClass.getMethod("getMxy"), "mxy"));
                res.add(new Property(aClass.getMethod("getMxz"), "mxz"));
                res.add(new Property(aClass.getMethod("getMyx"), "myx"));
                res.add(new Property(aClass.getMethod("getMyy"), "myy"));
                res.add(new Property(aClass.getMethod("getMyz"), "myz"));
                res.add(new Property(aClass.getMethod("getMzx"), "mzx"));
                res.add(new Property(aClass.getMethod("getMzy"), "mzy"));
                res.add(new Property(aClass.getMethod("getMzz"), "mzz"));
                res.add(new Property(aClass.getMethod("getTx"), "tx"));
                res.add(new Property(aClass.getMethod("getTy"), "ty"));
                res.add(new Property(aClass.getMethod("getTz"), "tz"));
            }
            if (PhongMaterial.class.isAssignableFrom(aClass)) {
                res.add(new Property(aClass.getMethod("getDiffuseColor"), "diffuseColor"));
                res.add(new Property(aClass.getMethod("getSpecularColor"), "specularColor"));
                res.add(new Property(aClass.getMethod("getBumpMap"), "bumpMap"));
                res.add(new Property(aClass.getMethod("getDiffuseMap"), "diffuseMap"));
                res.add(new Property(aClass.getMethod("getSpecularMap"), "specularMap"));
                res.add(new Property(aClass.getMethod("getSelfIlluminationMap"), "selfIlluminationMap"));
            }
            if (Node.class.isAssignableFrom(aClass)) {
                res.add(new Property(aClass.getMethod("getId"), "id"));
                res.add(new Property(aClass.getMethod("getTransforms"), "transforms"));
            }
            if (Parent.class.isAssignableFrom(aClass)) {
                res.add(new Property(aClass.getMethod("getChildrenUnmodifiable"), "children"));
            }
            if (Shape3D.class.isAssignableFrom(aClass)) {
                res.add(new Property(aClass.getMethod("getMaterial"), "material"));
                res.add(new Property(aClass.getMethod("getTranslateX"), "translateX"));
                res.add(new Property(aClass.getMethod("getTranslateY"), "translateY"));
                res.add(new Property(aClass.getMethod("getTranslateZ"), "translateZ"));
            }
            if (CubeMesh.class.isAssignableFrom(aClass)) {
                res.add(new Property(aClass.getMethod("getSize"), "size"));
            } else if (Box.class.isAssignableFrom(aClass)) {
                res.add(new Property(aClass.getMethod("getWidth"), "width"));
                res.add(new Property(aClass.getMethod("getHeight"), "height"));
                res.add(new Property(aClass.getMethod("getDepth"), "depth"));
            }
            if (Sphere.class.isAssignableFrom(aClass)) {
                res.add(new Property(aClass.getMethod("getRadius"), "radius"));
            }
            if (Cylinder.class.isAssignableFrom(aClass)) {
                res.add(new Property(aClass.getMethod("getHeight"), "height"));
                res.add(new Property(aClass.getMethod("getRadius"), "radius"));
            }
            if (ConeMesh.class.isAssignableFrom(aClass)) {
                res.add(new Property(aClass.getMethod("getHeight"), "height"));
                res.add(new Property(aClass.getMethod("getRadius"), "radius"));
            }
            if (PyramidMesh.class.isAssignableFrom(aClass)) {
                res.add(new Property(aClass.getMethod("getHeight"), "height"));
                res.add(new Property(aClass.getMethod("getHypotenuse"), "hypotenuse"));
            }
            if (TriangularPrismMesh.class.isAssignableFrom(aClass)) {
                res.add(new Property(aClass.getMethod("getHeight"), "height"));
                res.add(new Property(aClass.getMethod("getWidth"), "width"));
                res.add(new Property(aClass.getMethod("getDepth"), "depth"));
            }
            if (TrapezoidMesh.class.isAssignableFrom(aClass)) {
                res.add(new Property(aClass.getMethod("getHeight"), "height"));
                res.add(new Property(aClass.getMethod("getDepth"), "depth"));
                res.add(new Property(aClass.getMethod("getSmallSize"), "smallSize"));
                res.add(new Property(aClass.getMethod("getBigSize"), "bigSize"));
            }
            if (CapsuleMesh.class.isAssignableFrom(aClass)) {
                res.add(new Property(aClass.getMethod("getHeight"), "height"));
                res.add(new Property(aClass.getMethod("getRadius"), "radius"));
            }
            if (SpheroidMesh.class.isAssignableFrom(aClass)) {
                res.add(new Property(aClass.getMethod("getMajorRadius"), "majorRadius"));
                res.add(new Property(aClass.getMethod("getMinorRadius"), "minorRadius"));
            }
            if (SpringMesh.class.isAssignableFrom(aClass)) {
                res.add(new Property(aClass.getMethod("getMeanRadius"), "meanRadius"));
                res.add(new Property(aClass.getMethod("getWireRadius"), "wireRadius"));
                res.add(new Property(aClass.getMethod("getPitch"), "pitch"));
                res.add(new Property(aClass.getMethod("getLength"), "length"));
            }
            if (TorusMesh.class.isAssignableFrom(aClass)) {
                res.add(new Property(aClass.getMethod("getRadius"), "radius"));
                res.add(new Property(aClass.getMethod("getTubeRadius"), "tubeRadius"));
            }
            if (TetrahedronMesh.class.isAssignableFrom(aClass)) {
                res.add(new Property(aClass.getMethod("getHeight"), "height"));
            }
            if (OctahedronMesh.class.isAssignableFrom(aClass)) {
                res.add(new Property(aClass.getMethod("getHeight"), "height"));
                res.add(new Property(aClass.getMethod("getHypotenuse"), "hypotenuse"));
            }
            if (MeshView.class.equals(aClass)) {
                res.add(new Property(aClass.getMethod("getMesh"), "mesh"));
            }
            if (TriangleMesh.class.isAssignableFrom(aClass)) {
                res.add(new Property(aClass.getMethod("getPoints"), "points"));
                res.add(new Property(aClass.getMethod("getTexCoords"), "texCoords"));
                res.add(new Property(aClass.getMethod("getFaces"), "faces"));
                res.add(new Property(aClass.getMethod("getFaceSmoothingGroups"), "faceSmoothingGroups"));
            }
        } catch (NoSuchMethodException | SecurityException ex) {
            Logger.getLogger(GraphixFXMLExporter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    private FXML exportToFXML(Object object) {
        if (object instanceof Transform && ((Transform) object).isIdentity()) {
            return null;
        }

        FXML fxml = new FXML(object.getClass());

        List<Property> properties = propertiesCache.get(object.getClass());
        if (properties == null) {
            properties = getProperties(object.getClass());
            propertiesCache.put(object.getClass(), properties);
        }

        for (Property property : properties) {
            try {
                Object[] parameters = new Object[property.getter.getParameterTypes().length];
                Object value = property.getter.invoke(object, parameters);
                if (value != null) {
                    if (value instanceof Collection) {
                        Collection collection = (Collection) value;
                        if (!collection.isEmpty()) {
                            FXML container = fxml.addContainer(property.name);
                            for (Object item : collection) {
                                container.addChild(exportToFXML(item));
                            }
                        }
                    } else if (value instanceof ObservableArray) {
                        int length = ((ObservableArray) value).size();
                        if (length > 0) {
                            FXML container = fxml.addContainer(property.name);
                            container.setValue(value);
                        }
                    } else if (property.getter.getReturnType().isPrimitive()
                            || String.class.equals(value.getClass())
                            || Color.class.equals(value.getClass())) {
                        fxml.addProperty(property.name, String.valueOf(value));
                    } else {
                        FXML container = fxml.addContainer(property.name);
                        container.addChild(exportToFXML(value));
                    }
                }
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(GraphixFXMLExporter.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        }

        return fxml;
    }

    private class Property {
        Method getter;
        String name;

        public Property(String name) {
            this.name = name;
        }

        public Property(Method getter, String name) {
            this.getter = getter;
            this.name = name;
        }
    }

    private class FXML {
        List<Entry> properties;
        List<FXML> nested;
        Object value;
        private String tagName;

        public FXML(String tagName) {
            this.tagName = tagName;
        }

        public FXML(Class cls) {
            String fullName = simpleNames.get(cls.getSimpleName());
            if (fullName == null) {

                // this short name is not used, so adding it to imports and
                // to shortNames map
                fullName = cls.getName();
                imports.add(cls.getPackage().getName());
                simpleNames.put(cls.getSimpleName(), fullName);
                tagName = cls.getSimpleName();
            } else if (!fullName.equals(cls.getName())) {

                // short name is already used for some other class so we have
                // to use full name
                tagName = cls.getName();
            } else {

                // short name matches this class
                tagName = cls.getSimpleName();
            }
        }

        private FXML addContainer(String containerTag) {
            if (nested != null) {
                for (FXML n : nested) {
                    if (n.tagName.equals(containerTag)) {
                        return n;
                    }
                }
            }
            FXML fxml = new FXML(containerTag);
            addChild(fxml);
            return fxml;
        }

        void setValue(Object value) {
            this.value = value;
        }

        void addProperty(String key, String value) {
            if (properties == null) {
                properties = new ArrayList<>();
            }
            properties.add(new Entry(key, value));
        }

        void export(String indent) {
            printWriter.append(indent).append('<').append(tagName);
            if (properties != null) {
                for (Entry entry : properties) {
                    printWriter.append(' ').append(entry.key).append("=\"")
                            .append(entry.value).append("\"");
                }
            }
            if (nested != null || value != null) {
                printWriter.append(">\n");
                String indent1 = indent + "  ";
                if (nested != null) {
                    for (FXML fxml : nested) {
                        fxml.export(indent1);
                    }
                }
                if (value != null) {
                    String toString;
                    if (value instanceof ObservableArray) {
                        toString = value.toString();
                    } else {
                        throw new UnsupportedOperationException("Only ObservableArrays are currently supported");
                    }
                    printWriter.append(indent1).append(toString.substring(1, toString.length() - 1)).append("\n");
                }
                printWriter.append(indent).append("</").append(tagName).append(">\n");
            } else {
                printWriter.append("/>\n");
            }
        }

        private void addChild(FXML fxml) {
            if (fxml == null) {
                return;
            }
            if (nested == null) {
                nested = new ArrayList<>();
            }
            nested.add(fxml);
        }

        private class Entry {
            String key;
            String value;

            public Entry(String key, String value) {
                this.key = key;
                this.value = value;
            }
        }
    }

}