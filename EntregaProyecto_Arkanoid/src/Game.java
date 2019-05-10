import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.Random;

public final class Game extends JPanel implements ActionListener {

    private Timer timer;  
    private int nivel = 3; //indica los niveles
    private static String victoriaStr = "Has ganado!!";
    private int vidasRestantes = 4;  //vidas del jugador
    private String vidasStr = "Vidas Restantes";
    private String puntuacionStr = "Puntos : ";
    private String nivelStr = "Nivel : ";
    private int[][] vidas = {{5, 485}, {25, 485}, {45, 485}, {65, 485}}; //posicion de las vidas dibujadas en pantalla
    private boolean gamePlay = true;  //te dice si el juego sigue o se ha acabado
    private int nivelJugador = 1;     //nivel en el que esta el jugador
    private ArrayList<Ladrillo> ListaLadrillos;   //lista de los ladrillos en pantalla
    private int puntuacion = 0;                //puntuacion del jugador
    private Pad pad;                      
    private Pelota pelota;                   
    private int[][] stage = { //Aqui se definen el array de los 3 niveles que tiene el juego.
        //nivel == 1

        {1, 1, 55, 100}, {1, 1, 100, 100}, {1, 1, 145, 100}, {1, 1, 190, 100}, {1, 1, 235, 100}, {1, 1, 280, 100}, {1, 1, 325, 100}, {1, 1, 370, 100},
        {1, 1, 55, 110},					{1, 1, 145, 110},					 {1, 1, 235, 110}, 					 {1, 1, 325, 110}, 
        {1, 1, 55, 120}, {1, 1, 100, 120}, {1, 1, 145, 120}, {1, 1, 190, 120}, {1, 1, 235, 120}, {1, 1, 280, 120}, {1, 1, 325, 120}, {1, 1, 370, 120},
        				{1, 1, 100, 130}, 					{1, 1, 190, 130}, 					 {1, 1, 280, 130}, 						 {1, 1, 370, 130},
        {1, 1, 55, 140}, 				 {1, 1, 145, 140}, 					 {1, 1, 235, 140}, 						 {1, 1, 325, 140},
        {1, 1, 55, 150}, {1, 1, 100, 150}, {1, 1, 145, 150}, {1, 1, 190, 150}, {1, 1, 235, 150}, {1, 1, 280, 150}, {1, 1, 325, 150}, {1, 1, 370, 150},
        //nivel == 2

        {2, 3, 55, 100}, {2, 1, 145, 100}, {2, 2, 235, 100}, {2, 1, 325, 100}, {2, 3, 415, 100},
        {2, 3, 55, 110}, {2, 1, 145, 110}, {2, 2, 235, 110}, {2, 1, 325, 110}, {2, 3, 415, 110},
        {2, 3, 55, 120}, {2, 1, 145, 120}, {2, 2, 235, 120}, {2, 1, 325, 120}, {2, 3, 415, 120},
        {2, 3, 55, 130}, {2, 1, 145, 130}, {2, 2, 235, 130}, {2, 1, 325, 130}, {2, 3, 415, 130},
        {2, 3, 55, 140}, {2, 1, 145, 140}, {2, 2, 235, 140}, {2, 1, 325, 140}, {2, 3, 415, 140},
        {2, 3, 55, 150}, {2, 1, 145, 150}, {2, 2, 235, 150}, {2, 1, 325, 150}, {2, 3, 415, 150},
        {2, 3, 55, 100}, {2, 1, 145, 100}, {2, 2, 235, 100}, {2, 1, 325, 100}, {2, 3, 415, 100},
        //nivel == 3

       {3, 3, 55, 120}, {3, 1, 100, 120}, {3, 1, 145, 120}, {3, 1, 190, 120}, {3, 1, 235, 120}, {3, 1, 280, 120}, {3, 1, 325, 120}, {3, 3, 370, 120},
        {3, 3, 55, 130}, {3, 2, 100, 130}, {3, 2, 145, 130}, {3, 2, 190, 130}, {3, 2, 235, 130}, {3, 2, 280, 130}, {3, 2, 325, 130}, {3, 3, 370, 130},
        {3, 3, 55, 140}, {3, 1, 100, 140}, {3, 1, 145, 140}, {3, 1, 190, 140}, {3, 1, 235, 140}, {3, 1, 280, 140}, {3, 1, 325, 140}, {3, 3, 370, 140},
        {3, 3, 55, 150}, {3, 2, 100, 150}, {3, 2, 145, 150}, {3, 2, 190, 150}, {3, 2, 235, 150}, {3, 2, 280, 150}, {3, 2, 325, 150}, {3, 3, 370, 150},
        {3, 3, 55, 160}, {3, 1, 100, 160}, {3, 1, 145, 160}, {3, 1, 190, 160}, {3, 1, 235, 160}, {3, 1, 280, 160}, {3, 1, 325, 160}, {3, 3, 370, 160},
        {3, 3, 55, 170}, {3, 3, 100, 170}, {3, 3, 145, 170}, {3, 3, 190, 170}, {3, 3, 235, 170}, {3, 3, 280, 170}, {3, 3, 325, 170}, {3, 3, 370, 170}
    };

    public Game() {

        //creamos el inicio del juego con el constructor.

        addKeyListener(new TAdapter()); //adaptador del teclado para mover el pad.
        setFocusable(true);
        setBackground(new Color(0,110,255));
        setDoubleBuffered(true);
        setSize(500, 550);
        pad = new Pad();
        pelota = new Pelota();
        ListaLadrillos = new ArrayList<Ladrillo>();
        setNivel();
        puntuacion=0; 
        timer = new Timer(5, this); 
        timer.start();
    }


    public void paint(Graphics g) {
        super.paint(g);

        if (gamePlay) { //chequea si esta el juego activo


            //Draw Below score card
            g.setColor(Color.black);
            g.fillRect(0, 450, 500, 3);
            g.drawString(vidasStr, 5, 475);
            g.drawString(puntuacionStr + puntuacion, 350, 475);
            g.drawString(nivelStr + nivelJugador, 350, 500);
            for (int i = 0; i < vidasRestantes - 1; i++) {
                g.fillRect(vidas[i][0], vidas[i][1], 15, 5);

            }
            //Dibuja el pad
            pad.pintaPad(g);
            //dibuja la pelota
            pelota.pintaPelota(g);
            //dibuja los ladrillos
            for (int i = 0; i < ListaLadrillos.size(); i++) {
            	Ladrillo b = ListaLadrillos.get(i);
                g.setColor(b.getColor());
                g.fillRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
            }
           // g.setColor(Color.white);




        } else { //si el juego se acaba
        	g.setColor(Color.white);
            g.drawString(victoriaStr, 200, 195);
            g.drawString("Puntuacion final: "+puntuacion, 200, 210);

        }
        Toolkit.getDefaultToolkit().sync();
    }

    public void actionPerformed(ActionEvent e) { //mueve los objetos creados
        if (gamePlay) { //chequea que el juego esta activo
     

            if (ListaLadrillos.size() > 0) {  //si queda ladrillos en pantalla.
                pad.move();
                pelota.move(this);
                chequeaColision();  

            } else {  //cuando ya no hay ladrillos en pantalla.
                nivelJugador++;
                if (nivelJugador > nivel) {
                    gamePlay = false;  //fin del juego
                } else {
                    setNivel();
                        if(nivelJugador == 2) {
                    	pad.setWidth(50);
                    }
                    if(nivelJugador == 3) {
                    	pad.setWidth(40);	
                    }
                    resetPosicion();
                }
            }

            repaint();  //repaint despues de los cambios

        }
    }

    public void chequeaColision() {
        if (gamePlay) {

            //recibe la posicion de la pelota
            Rectangle rb = pelota.getBounds();
            //recibe la posicion del pad.
            Rectangle rp = pad.getBounds();
            //detecta la colision de la pelota con el Pad.


            if (rb.intersects(rp)) {
                pelota.rebotePelota(pad.getX(), pad.getY(), "Pad", pad); //cambia la direccion de la pelota

            }
            //detecta la colision con los tipos de ladrillos
            for (int i = 0; i < ListaLadrillos.size(); i++) {
            	Ladrillo b = ListaLadrillos.get(i);
                Rectangle rbrick = b.getBounds();
                if (rbrick.intersects(rb)) {
                    if (b.getTipoLadrillo() == 1) {  //si el ladrillo es normal

                        Toolkit.getDefaultToolkit().beep(); //sonido al tocar un ladrillo
                        puntuacion += 10;  //incrementa puntuacion
                        pelota.rebotePelota(b.getX(), b.getY(), "Ladrillo", pad);
                        ListaLadrillos.remove(i); //borra el ladrillo a tocarlo
                        i--;
                    } else if (b.getTipoLadrillo() == 2) {  //si es un ladrillo duro

                        puntuacion += 10;
                        Toolkit.getDefaultToolkit().beep();
                        b.cambiaLadrillo();
                        pelota.rebotePelota(b.getX(), b.getY(), "Ladrillo", pad);
                        
                    } else if (b.getTipoLadrillo() == 3) { //Si es un super ladrillo
                        b.cambiaLadrillo2();
                    	pelota.rebotePelota(b.getX(), b.getY(), "Ladrillo", pad);
                        Toolkit.getDefaultToolkit().beep();
                    }
                }

            }
        }
    }

    public void setNivel() {  //inicia el nuevo nivel
        ListaLadrillos = null;
        ListaLadrillos = new ArrayList<Ladrillo>();

        for (int i = 0; i < stage.length; i++) {

            if (stage[i][0] == nivelJugador) {
            	Ladrillo b = new Ladrillo(stage[i][1], stage[i][2], stage[i][3]);  //crea ladrillo
                ListaLadrillos.add(b); //añade ladrillo a la lista
            } else {
                if (stage[i][0] > nivelJugador) {
                    break;
                }
            }
        }

    }

    public void setVidasRestantes(int vidas) { //cambia las vidas restantes
        this.vidasRestantes = vidas;
    }

    public int getVidasRestantes() { //devuelve vidas restantes
        return this.vidasRestantes;
    }

    public void endGame() { //acaba el juego
        gamePlay = false;
    }

    public Pad getPad() {  //devuelve el pad
        return this.pad;
    }

    public void resetPosicion() { //reseta el pad y la pelot a la posicion inicial.
        pad.resetPad();

    }

    private class TAdapter extends KeyAdapter { //adaptador del teclado

        public void keyReleased(KeyEvent e) {
            pad.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) {
            pad.keyPressed(e);
        }
    }

    public static void setStringVictoria(String str) { //envia el string con la victoria o derrota
        victoriaStr = str;
    }
}