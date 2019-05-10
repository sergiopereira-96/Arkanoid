import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Arkanoid_Principal extends JFrame implements ActionListener {

    public Arkanoid_Principal() {
        JLabel titulo = new JLabel("ARKANOID");
        titulo.setBounds(220, 50, 300, 50);
        titulo.setForeground(Color.white);
    	JButton button = new JButton("Start Game");
        button.addActionListener(this);
        button.setBounds(180, 100, 150, 50);
        add(button, BorderLayout.CENTER);
        add(titulo);
        setBackground(new Color(0,110,255));
        PantallaInicio start = new PantallaInicio();
        start.setBounds(0, 100, 10, 10);
        this.add(start);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 550);
        setLocationRelativeTo(null);
        setTitle("ARKANOID 1.0");
        setResizable(false);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String str = ae.getActionCommand();
        //Crea la instancia del juego cuando pulsas el boton start
        if (str.equals("Start Game")) {
            JFrame gameWindow = new JFrame();
            gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gameWindow.setSize(510, 550);
            gameWindow.setLocationRelativeTo(null);
            gameWindow.setTitle("ARKANOID 1.0");
            gameWindow.setVisible(true);
            gameWindow.setResizable(false);
            gameWindow.add(new Game());
           
            



        }
    }
}

class PantallaInicio extends JPanel {  //esta clase la crearemos para enviar la informacion del juego al panel principal

    public PantallaInicio() {

        //envia propiedades al panel
        setFocusable(true);
        setBackground(Color.black);
        setDoubleBuffered(true);
        setSize(500, 400);
        this.setLayout(null);




    }
//Escribe la informacion del juego
    public void paint(Graphics g) {

        int x, y;
        g.setColor(Color.white);

        x = 120;
        y = 200;
 
        g.drawString("Informacion del juego", x + 70, y);
        x = 0;

        g.drawString("Tipos de ladrillos", x, y + 50);
        g.setColor(Color.lightGray);
        g.fillRect(x + 5, y + 70, 40, 7);
        g.setColor(Color.white);
        g.drawString("Ladrillo normal, ser rompe con un solo toque de pelota", x + 70, y + 75);
        g.setColor(Color.gray);
        g.fillRect(x + 5, y + 85, 40, 7);
        g.setColor(Color.white);
        g.drawString("Ladrillo duro, se rompe con dos toques de pelota", x + 70, y + 90);
        g.setColor(Color.black);
        g.fillRect(x + 5, y + 100, 40, 7);
        g.setColor(Color.white);
        g.drawString("Super ladrillo, se rompe con 3 toques de pelota", x + 70, y + 105);
       

    }
}