import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


public class Pad {

    private int dx;         
    
    private int x = 220;    
    private int y = 435;    
    private int width = 60; 
    private int height = 6; 

    public Pad() {

        x = 220;
        y = 435;
        width = 50;
        height = 6;

    }

    public void move() { //mueve el pad haciendo los topes de la pantalla

        x += dx;
        if (x < 1) { //si el pad esta a la izquierda
            x = 1;
        } else if (x > 500 - width) {  //si el pad esta a la derecha de la pantalla
            x = 500 - width;
        }

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int newX) {
        x = newX;
    }

    public int getWidth() {
        return width;
    }
    public void setWidth(int n) {
    	width = n;
    }


    public int getHeight() {
        return height;
    }


    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }


    public void pintaPad(Graphics g) {     //pinta el pad en la pantalla
            g.setColor(Color.white);
            g.fillRect(x, y, width, height);
    }

    public void resetPad() { //resetea el pad a la posicion inicial
        x = 220;
        y = 435;

    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = -2;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 2;
        }
    }



    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

    }
}