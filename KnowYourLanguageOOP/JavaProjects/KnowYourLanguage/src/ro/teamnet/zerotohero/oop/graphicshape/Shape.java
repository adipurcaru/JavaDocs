package ro.teamnet.zerotohero.oop.graphicshape;

/**
 * Created by Adrian.Purcaru on 04/07/2017.
 */
public class Shape extends AbstractShape
{
    protected int color;
    protected float saturation;

    public void setColor(int color) {
        this.color = color;
    }

    public void setSaturation(float saturation) {
        this.saturation = saturation;
    }

    public double area(){
        return 1;
    }
}
