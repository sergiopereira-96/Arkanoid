import java.awt.Color;
import java.awt.Rectangle;

public class Ladrillo {

    private int x;             
    private int y;             
    private int width;         
    private int height;       
    private int tipoLadrillo;      //tipo de ladrillo
    private boolean visible;    //si el ladrillo es visible
    private Color color;        //color del ladrillo

    public Ladrillo(int btype, int bx, int by) {

        tipoLadrillo = btype;
        //envia un color dependiendo el tipo de ladrillo
        if (btype == 1) {
            color = Color.lightGray;  //normal
        } else if (btype == 2) {
            color = Color.gray; //duro
        } else if (btype == 3) {
            color = Color.black; //super duro
        }
        visible = true;
        x = bx;
        y = by;
        width = 44;
        height = 9;

    }

    public int getX() { 
        return x;
    }

    public int getY() { 
        return y;
    }

    public int getWidth() { 
        return width;
    }

    public Color getColor() 
    {
        return color;
    }

    public int getHeight() {   
        return height;
    }


    public int getTipoLadrillo() 
    {
        return tipoLadrillo;
    }

    public Rectangle getBounds() {  
        return new Rectangle(x, y, width, height);
    }

    public void cambiaLadrillo() 
    {
        tipoLadrillo = 1;
        color = Color.lightGray;
    }
    public void cambiaLadrillo2() 
    {
        tipoLadrillo = 2;
        color = Color.gray;
    }
}