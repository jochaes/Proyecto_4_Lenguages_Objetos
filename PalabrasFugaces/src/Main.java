import palabrasfugaces.Start;

import javax.swing.*;

public class Main  extends JFrame {

    private JDesktopPane desktopPane;

    public Main() {

            super("Palabras Fugaces");
            this.desktopPane = new JDesktopPane();
            this.setContentPane(this.desktopPane);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setSize(800, 600);
            this.setVisible(true);

            addGame();
        }
        public void addGame(){
            Start game = new Start();
            this.desktopPane.add(game);
            game.setVisible(true);
        }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });

        System.out.println("Hello world!");
    }
}