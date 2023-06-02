import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;
import java.util.jar.Attributes;
import java.util.jar.JarInputStream;
import java.util.jar.Manifest;

public class Consola extends JFrame {

    private JPanel panel1;
    private JDesktopPane jdp;
    private JMenuBar menubar1;
    private JuegoManagerJC juegoManager;

    private ShowStats showStats;

    private JMenu juegosMenu;

    public Consola(){



        //Inicializar la interfaz
        this.juegoManager = new JuegoManagerJC();
        setTitle("Consola de Juegos");
        setSize(800,800);
        setContentPane(jdp);

        this.showStats = new ShowStats();
        this.jdp.add(showStats.getInternalFrame());

        setActualizarStatsBtn(this.showStats.getActualizarStats_btn());
        //Crea el Drop Dowm Menu
        this.setJMenuBar(menubar1);
        JMenu archivo = new JMenu("Archivo");
        JMenuItem opcJAR = new JMenuItem("Cargar JAR");
        JMenuItem opcScores = new JMenuItem("Mostrar Mejores Resultados");
        JMenuItem opcExit = new JMenuItem("Salir");

        setCargarJar(opcJAR);
        setCargarScores(opcScores);
        opcExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        archivo.add(opcJAR);
        archivo.add(opcScores);
        archivo.add(opcExit);
        menubar1.add(archivo);

        //Menu de jars cargados
        juegosMenu = new JMenu("Juegos");
        menubar1.add(juegosMenu);


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void setCargarJar( JMenuItem cargarJarMenuItem ){

        cargarJarMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    File file = showSaveDialog();


                    if (file != null && !juegoManager.yaExiste(file.getName())) {
                        JarInputStream jarStream = new JarInputStream(new FileInputStream(file));
                        Manifest mf = jarStream.getManifest();
                        Attributes mainAttribs = mf.getMainAttributes();
                        String mainClass = mainAttribs.getValue("Main-Class");
                        //se carga de forma dinámica el JAR
                        URLClassLoader child = new URLClassLoader(
                                new URL[]{file.toURI().toURL()},
                                Main.class.getClassLoader()
                        );
                        Class classToLoad = Class.forName(mainClass, true, child);

                        //no debe declararse la instancia del juego cargado si esta ya ha sido cargada previamente
                        JInternalFrame instance = (JInternalFrame) classToLoad.getDeclaredConstructor().newInstance();
                        instance.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                        juegoManager.addJuego( new JuegoConsolaJC(instance, file.getName()) );
                        jdp.add(instance);
                        ((Component) instance).setVisible(true);

                        //Anadir juego al menu
                        JMenuItem juego = new JMenuItem(file.getName());
                        setCargarJuegoMenuItem(juego);
                        juegosMenu.add(juego);

                    }
                } catch (MalformedURLException | InstantiationException | IllegalAccessException | ClassNotFoundException | InvocationTargetException |
                         NoSuchMethodException ex) {
                    ex.printStackTrace();
                }catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private void setCargarJuegoMenuItem( JMenuItem juego ){
        juego.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                JMenuItem item = (JMenuItem) e.getSource();
                juegoManager.yaExiste(item.getText());
                System.out.println("Cargando juego: " + item.getText());
            }
        });
    }

    private void setCargarScores( JMenuItem cargarScoresMenuItem ) {
        cargarScoresMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                showStats.getInternalFrame().setVisible(true);
                showStats.fillTable();
//                String stats = juegoManager.getEstadisticas();
//                System.out.println(stats);

            }
        });
    }

    private void setActualizarStatsBtn( JButton actualizarStats_btn ) {
        actualizarStats_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showStats.getInternalFrame().setVisible(true);
                List<String[]> stats = juegoManager.getEstadisticas();

                String[][] statsTable = stats.toArray(new String[0][0]);


                String[] columns = {"Juego", "Jugador","Clave", "Puntaje"};
                if( !stats.isEmpty()){
                    showStats.actualizarStatsTable(statsTable ,columns);
                }
                System.out.println(stats);
            }
        });
    }

    private File showSaveDialog() {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        if (this.theOutString != null)
            chooser.setCurrentDirectory(new File(this.theOutString));
        int result = chooser.showOpenDialog(jdp);
        if (JFileChooser.APPROVE_OPTION == result) {
            this.theOutString = chooser.getSelectedFile().getPath();
            return chooser.getSelectedFile();
        } else {
            return null;
        }
    }

    private String theOutString;
}
