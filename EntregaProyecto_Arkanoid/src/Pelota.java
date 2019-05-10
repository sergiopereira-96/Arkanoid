import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Pelota {

    private int dx;         //cambio de la pelota en x
    private int dy;         //cambio de la pelota en y
    private int x;          //posicion x 
    private int y;          //posicion y
    private int width;      //ancho pelota
    private int height;     //alto pelota
    private int tipoMovimiento; //direccion del movimiento de la pelota
    

    public Pelota() {

        x = 247;
        y = 427;
        dx = 1;
        dy = -1;
        width = 8;
        height = 8;
        tipoMovimiento = 1;

    }

    public void move(Game game) { //mover la pelota en las siguientes posiciones

        x += dx;
        y += dy;
        if (x < 0) {   //si da a la izquierda de la pantalla
            x = 0;
            if (tipoMovimiento == 3) {
                tipoMovimiento = 2;
                dx = -dx;
                y+=3;
            } else if (tipoMovimiento == 4) {
                tipoMovimiento = 1;
                dx = -dx;
                y-=2;
            }
        }
        if (x > (500-this.width)) {   //si da a la derecha
            x = 500-this.width;
            if (tipoMovimiento == 1) {
                tipoMovimiento = 4;
                dx = -dx;
                y-=3;
            } else if (tipoMovimiento == 2) {
                tipoMovimiento = 3;
                dx = -dx;
                y+=2;
            }
        }
        if (y < 0) {  //si da en la parte de arriba
            y = 0;
            if (tipoMovimiento == 1) {
                tipoMovimiento = 2;
                x+=2;
                dy = -dy;
            } else if (tipoMovimiento == 4) {
                tipoMovimiento = 3;
                dy = -dy;
                x-=3;
            }
        }
        if (y >= 439) { //si da debajo, y chequea las vidas para saber si has perdido.
            int lives = game.getVidasRestantes();
            lives -= 1;
            if (lives == 0) {
                Game.setStringVictoria("Has perdido");
                game.endGame();
            } else {
                game.setVidasRestantes(lives);

                dx = 1;
                dy = -1;
                tipoMovimiento = 1;
                game.resetPosicion();

            }

        }
    }

    public void rebotePelota(int px, int py, String collisionType,Pad pad) {
    //Este metodo cambia el dx y el dy dependiendo del tipo de colision que ocurra 
        if (collisionType.equals("Pad")) { //si la colision es en el pad
            if (this.x <= (px + (pad.getWidth()/3))) { //si es la 1/3 del pad
                if (tipoMovimiento == 2 || tipoMovimiento == 3) {
                    //dx= -2;
                    x -= 5;
                    dx = -1;
                    dy = -1;
                    tipoMovimiento = 4;
                }

            } else if (this.x > px + (pad.getWidth()/3) && x <= px + ((pad.getWidth()*2)/3)) {  //si es la segunda parte 2/3 del pad
                if (tipoMovimiento == 2) {
                    dx = 1;
                    dy = -1;
                    tipoMovimiento = 1;
                } else if (tipoMovimiento == 3) {
                    dx = -1;
                    dy = -1;
                    tipoMovimiento = 4;
                }

            } else if (x > px + ((pad.getWidth()*2)/3) && x <= px + pad.getWidth()) {  //si es la tercera parte 3/3 del pad
                if (tipoMovimiento == 2 || tipoMovimiento == 3) {
                    x += 5;
                    dx = 1;
                    dy = -1;
                    tipoMovimiento = 1;
                }


            }
        } else if (collisionType.equals("Ladrillo")) { //Si la colision es en un ladrillo
            if (y < py) { //encima del ladrillo
                if (tipoMovimiento == 2) {
                    tipoMovimiento = 1;
                    dy = -dy;
                } else if (tipoMovimiento == 3) {
                    tipoMovimiento=4;
                    dy=-dy;
                }
            }
            else if(x <= px)  //a su izquierda
            {
                if (tipoMovimiento == 2) {
                    tipoMovimiento = 3;
                    dx = -dx;
                } else if (tipoMovimiento == 1) {
                    tipoMovimiento=4;
                    dx=-dx;
                    
                }
            }
            else if( y>= py+7) //por debajo
            {
                if (tipoMovimiento == 1) {
                    tipoMovimiento = 2;
                    dy = -dy;
                } else if (tipoMovimiento == 4) {
                    tipoMovimiento=3;
                    dy=-dy;
                    
                }
            }
            else if(x >= px+42)  //a su derecha
            {
                if (tipoMovimiento == 4) {
                    tipoMovimiento = 1;
                    dx = -dx;
                } else if (tipoMovimiento == 3) {
                    tipoMovimiento=2;
                    dx=-dx;

                }
            }


        }
 
    }

    public int getX() { //devuelve x de la pelota
        return x;
    }

    public int getY() {  //devuelve y de la pelota
        return y;
    }

    public int getWidth() { //devuelve ancho de la pelota
        return width;
    }

    public void setX(int newX) { //envia nueva posicion X
        x = newX;
    }

    public void setY(int newY) { //envia nueva posicion Y
        y = newY;
    }

    public int getHeight() { //devuelve altura 
        return height;
    }

    public Rectangle getBounds() { 
        return new Rectangle(x, y, width, height);
    }

    public void pintaPelota(Graphics g) {
    	  g.fillArc(this.x,this.y, this.width,this.height, 0, 360);
    	  g.setColor(Color.white);
    }

}