package ro.teamnet.zerotohero.oop.RunApp;

import ro.teamnet.zerotohero.oop.canvas.Canvas;
import ro.teamnet.zerotohero.oop.graphicshape.*;

/**
 * Created by Adrian.Purcaru on 04/07/2017.
 */
public class RunApp {
    public static void main(String[] args) {
        Circles circles = new Circles();
        System.out.println("The default circle area is: " + circles.getAreaPub());
        circles.getAreaDef();

        Canvas canvas = new Canvas();
        //canvas.paint();

        Shape s = new Circle(10);
        System.out.println(s.area());

        ShapeBehaviour sb = new Square(10);
        System.out.println(sb.area());

        Object p1 = new Point(10, 20);
        Object p2 = new Point(50, 100);
        Object p3 = new Point(10, 20);

        System.out.println("p1 equals p2 is " + p1.equals(p2));
        System.out.println("p1 equals p3 is " + p1.equals(p3));

    }
}
