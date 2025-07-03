import javax.swing.*;
import controller.ControleTeclado;
import view.Fase;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "true");

        JFrame frame = new JFrame("Jogo");
        ControleTeclado controle = new ControleTeclado();
        Fase fase = new Fase(controle);

        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
    
        frame.add(fase);
        fase.addKeyListener(controle);
        fase.setFocusable(true);

        gd.setFullScreenWindow(frame);
        frame.setVisible(true);

        fase.requestFocusInWindow();
    }
}
