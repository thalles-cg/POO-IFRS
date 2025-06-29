import javax.swing.JFrame;
import view.Tela;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Jogo");
        Tela tela = new Tela();

        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(tela);
        frame.addKeyListener(tela.getControle());
        frame.setVisible(true);

        new Thread(tela).start();
    }
}
