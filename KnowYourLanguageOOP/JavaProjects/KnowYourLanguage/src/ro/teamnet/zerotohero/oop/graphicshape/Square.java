package ro.teamnet.zerotohero.oop.graphicshape;

/**
 * Created by Adrian.Purcaru on 04/07/2017.
 */
public class Square extends Shape implements ShapeBehaviour{
    private int side;

    public Square(int side){
        this.side = side;
    }

    @Override
    public double area() {
        return side * side;
    }
}
