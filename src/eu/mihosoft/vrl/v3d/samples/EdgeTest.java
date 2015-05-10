/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.mihosoft.vrl.v3d.samples;

import eu.mihosoft.vrl.v3d.*;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import static eu.mihosoft.vrl.v3d.Transform.unity;

/**
 * Average Chicken Egg.
 *
 * @author Michael Hoffer &lt;info@michaelhoffer.de&gt;
 */
public class EdgeTest {

    public static void main(String[] args) throws IOException {
        FileUtil.write(Paths.get("edge-test.stl"), new EdgeTest().toCSG(true).toStlString());
        FileUtil.write(Paths.get("edge-test-orig.stl"), new EdgeTest().toCSG(false).toStlString());
    }

    public CSG toCSG(boolean optimized) {
        double radius = 22;
        double stretch = 1.50;
        int resolution = 64;

        CSG cylinder = new Cylinder(1, 0.3, 8).toCSG();

        CSG sphere = new Sphere(0.1, 8, 4).toCSG().transformed(unity().translateZ(0.15));

        CSG cyl = new Cylinder(0.08, 0.3, 8).toCSG();

//        CSG csg = cylinder.difference(cyl).union(sphere);
        CSG csg = cylinder.difference(cyl);
//        CSG csg = cylinder.union(sphere);

        if (!optimized) {
            return csg;
        } else {

            List<Polygon> boundaryPolygons = Edge.boundaryPolygons(csg);

            System.out.println("#groups: " + boundaryPolygons.size());

//        List<Polygon> polys = boundaryPolygons.stream().peek(p->System.out.println("verts: "+p.vertices)).map(p->PolygonUtil.concaveToConvex(p)).flatMap(pList->pList.stream()).collect(Collectors.toList());
            return CSG.fromPolygons(boundaryPolygons);
        }

//        return csg;
    }
}
