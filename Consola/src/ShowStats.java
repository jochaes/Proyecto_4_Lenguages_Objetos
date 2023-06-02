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

    public JInternalFrame getInternalFrame(){
        return mainFrame;
    }

    public JButton getActualizarStats_btn() {
        return actualizarStats_btn;
    }

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



}
