/**
 * Instituto Tecnológico de Costa Rica
 * Escuela de Computación
 * Curso de Lenguajes
 *
 * Proyecto 4: Consola de Juegos
 *
 * Josué Chaves Araya  - 2015094068
 */

//Clase que se encarga de mostrar las estadisticas de los juegos

import javax.swing.*;

public class ShowStats extends JInternalFrame{
    private JTable statsTable;
    private JButton actualizarStats_btn;
    private JPanel MainPanel;
    private JScrollPane tabla_sp;

    private JInternalFrame mainFrame;

    public ShowStats(){
        mainFrame = this;
        mainFrame.setTitle("Estadisticas");
        mainFrame.setSize(500, 200);
        mainFrame.setClosable(true);
        mainFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        mainFrame.setResizable(true);
        getContentPane().add(MainPanel);
        pack();
    }

    //Actualiza la tabla de estadisticas
    public void actualizarStatsTable(String[][] data, String[] columnNames){
        this.statsTable = new JTable(data, columnNames);
        this.tabla_sp.setViewportView(statsTable);
    }

    public void fillTable(){
        String[] columns = {"Juego", "Jugador","Clave", "Puntaje"};
        String[][] data = {{"---", "---", "---","---"}};

        this.statsTable = new JTable(data, columns);
        this.tabla_sp.setViewportView(statsTable);

    }

    public JInternalFrame getInternalFrame(){
        return mainFrame;
    }

    public JButton getActualizarStats_btn() {
        return actualizarStats_btn;
    }







}
