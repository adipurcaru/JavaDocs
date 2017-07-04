package ro.teamnet.zerotohero.oop.graphicshape;

/**
 * Created by Adrian.Purcaru on 04/07/2017.
 */
public class Circle extends Shape {
    private int xPos;
    private int yPos;
    private int radius;

    public Circle() {
        xPos = 2;
        yPos = 1;
        radius = 2;
    }

    public Circle(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public Circle(int xPos) {
        this.xPos = xPos;
    }

    public Circle(int xPos, int yPos, int radius) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    public void fillColour(float b){
        setSaturation(b);
        System.out.println("the circle saturation is now "+ b);
    }

    public void fillColour(){
        System.out.println(super.color);
    }

    public void fillColour(int a){
        setColor(a);
        System.out.println("the circle colour is now "+ a);
    }

    @Override
    public String toString() {
        return "center("+xPos+", "+yPos+") and radius+ "+radius;
    }
}
