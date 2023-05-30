import gamesInterface.GameFunction;

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
import java.util.LinkedList;
import java.util.jar.Attributes;
import java.util.jar.JarInputStream;
import java.util.jar.Manifest;

public class Consola extends JFrame {

    private JPanel panel1;
    private JDesktopPane jdp;
    private JMenuBar menubar1;

    private LinkedList<JInternalFrame> loadedGames;

    public Consola(){

        this.loadedGames = new LinkedList<>();

        setTitle("Consola de Juegos");
        setSize(800,800);
        setContentPane(jdp);


        this.setJMenuBar(menubar1);
        JMenu archivo = new JMenu("Archivo");
        JMenuItem opcJAR = new JMenuItem("Cargar JAR");
        opcJAR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    File file = showSaveDialog();
                    if (file!= null) {
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

                        loadedGames.add(instance);
                        jdp.add(instance);
                        ((Component) instance).setVisible(true);
                    }
                } catch (MalformedURLException ex) {
                    ex.printStackTrace();
                } catch (InstantiationException ex) {
                    ex.printStackTrace();
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (InvocationTargetException ex) {
                    throw new RuntimeException(ex);
                } catch (NoSuchMethodException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        JMenuItem opcScores = new JMenuItem("Mostrar Mejores Resultados");
        opcScores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for (JInternalFrame game : loadedGames
                     ) {
                    Object result = ((GameFunction)game).getStats();
                    System.out.println(result.toString());
                }

            }
        });

        JMenuItem opcExit = new JMenuItem("Salir");
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

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
