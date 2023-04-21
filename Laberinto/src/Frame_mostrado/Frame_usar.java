package Frame_mostrado;

import Resolucion.SolucionarLaberinto;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import static javax.swing.LayoutStyle.ComponentPlacement.RELATED;
import static javax.swing.LayoutStyle.ComponentPlacement.UNRELATED;
import static javax.swing.SwingConstants.VERTICAL;
import static javax.swing.BorderFactory.createLineBorder;
import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.Alignment.LEADING;
import static javax.swing.GroupLayout.Alignment.TRAILING;
import static javax.swing.GroupLayout.DEFAULT_SIZE;
import static javax.swing.GroupLayout.PREFERRED_SIZE;

/**
 * Clase que crea un laberinto de forma aleatoria y lo resueve con backtracking
 *
 * @author Jonathan de Jesus Perez Becerra
 */
public class Frame_usar extends JFrame {

    private SolucionarLaberinto Solucion;
    
    private JButton btnCrear; //Creamos una variable tipo JButton
    private JButton btnResolver; //Creamos una variable tipo JButton
    private JButton btnBorrar; //Creamos una variable tipo JButton
    private JSeparator jSeparator1; //Creamos una variable tipo JSeparator
    private JLabel lblDimX; //Creamos una variable tipo JLabel
    private JLabel lblDimY; //Creamos una variable tipo JLabel
    private JPanel pnlMenu; //Creamos una variable tipo JPanel
    private JPanel pnlColores; //Creamos una variable tipo JPanel
    private JPanel pnlTablero; //Creamos una variable tipo JPanel
    public static JTextField tbxDimX; //Creamos una variable tipo JTextField
    public static JTextField tbxDimY; //Creamos una variable tipo JTextField

    public Frame_usar(int x, int y) {
        super("Laberinto");
        AcomodarComponentes();
    }

    /**
     * Método que acomoda los Componentes y los coloca en su posicion
     */
    private void AcomodarComponentes() {
        this.Solucion = new Resolucion.SolucionarLaberinto();
        pnlMenu = new JPanel(); //Creamos un Objeto tipo JPanel
        pnlColores = new JPanel(); //Creamos un Objeto tipo JPanel
        pnlTablero = new JPanel(); //Creamos un Objeto tipo JPanel
        lblDimX = new JLabel(); //Creamos un Objeto tipo JLabel
        lblDimY = new JLabel(); //Creamos un Objeto tipo JLabel
        tbxDimX = new JTextField(); //Creamos un Objeto tipo JTexField
        tbxDimY = new JTextField(); //Creamos un Objeto tipo JTextField
        btnResolver = new JButton(); //Creamos un Objeto tipo JButton
        btnCrear = new JButton(); //Creamos un Objeto tipo JButton
        btnBorrar = new JButton(); //Creamos un Objeto tipo JButton
        jSeparator1 = new JSeparator(); //Creamos un Objeto tipo JSeparator

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Asignamos el sistema de cierre
        setResizable(false);//Bloqueamos la opcion de hacer grande 
        this.setSize(new Dimension(550, 530));//le asignamos la dimencion al Frame

        lblDimX.setText("Elementos en X:");//Asignamos Texto a la primer etiqueta

        tbxDimX.setPreferredSize(new Dimension(50, 24));//Asignamos el tamaño

        lblDimY.setText("Elementos en Y:");//Asignamos Texto a la segunda etiqueta

        tbxDimY.setPreferredSize(new Dimension(50, 24));//Asignamos el tamaño

        btnResolver.setText("Resolver");////Asignamos Texto al boton de Resolver
        btnResolver.setToolTipText("Botón que resuelve el laberinto");//Texto que aparece si dejas el cursor sobre el boton
        btnResolver.addActionListener(new ActionListener() {//asignamos que el Botón tendra una accion
            public void actionPerformed(ActionEvent evt) {//creamos el evento
                Solucion.btnResolverActionPerformed(evt);//mandamos llamar el método que contiene el evento de resolver
            }//cerramos el método
        });//cerramos la accion

        btnCrear.setText("Crear");
        btnResolver.setToolTipText("Botón que crea el laberinto");//Texto que aparece s dejas el cursor sobre el boton
        btnCrear.addActionListener(new ActionListener() {//asignamos que el Botón tendra una accion 
            public void actionPerformed(ActionEvent evt) {//creamos el método que contendra el evento
                Solucion.btnCrearActionPerformed(evt, pnlTablero);//mandamos llamar el método que contiene el evento de resolver
            }//cerramos el método
        });//cerramos la accion

        btnBorrar.setText("Borrar");
        btnResolver.setToolTipText("Botón que borra los componentes del laberinto");//Texto que aparece s dejas el cursor sobre el boton
        btnBorrar.addActionListener(new ActionListener() {//asignamos que el Botón tendra una accion
            public void actionPerformed(ActionEvent evt) {//creamos el método que contendra el evento
                Solucion.btnBorrarActionPerformed(evt);//mandamos llamar el método que contiene el evento de resolver
            }//cerramos el método
        });//cerramos la accion

        jSeparator1.setOrientation(VERTICAL);//asignamos que el separador sera vertical

        GroupLayout pnlMenuLayout = new GroupLayout(pnlMenu);//creamos un grupo que tendra como host a pnlMenu
        pnlMenu.setLayout(pnlMenuLayout);//asignamos que pnlMenu tendra un layout tipo GroupLayout
        pnlMenuLayout.setHorizontalGroup(//ponemos que el grupo se guiara de forma horizontal
                pnlMenuLayout.createParallelGroup(LEADING)//asignamos que los componentes se alinearan hacia
                        //el origen y de izquierda a derecha
                        .addGroup(pnlMenuLayout.createSequentialGroup()//asignamos que el grupo se alineara como vaya llegando
                                .addComponent(lblDimX)//añadimos la primer etiiqueta
                                .addPreferredGap(UNRELATED)//asignamos una distancia para el proximo Bóton
                                .addComponent(tbxDimX, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)//Añadimos el JtextField
                                .addGap(18, 18, 18)//le asignamos las separaciones y el tamaño
                                .addComponent(lblDimY)//añadimos el JTextField del eje Y
                                .addPreferredGap(UNRELATED)//separamos de la etiqueta
                                .addComponent(tbxDimY, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)//asignamos las dimensiones
                                .addPreferredGap(RELATED)//marcamos la separacion
                                .addComponent(jSeparator1, PREFERRED_SIZE, 2, PREFERRED_SIZE)//Añadimos el separador y le asignamos tamaño
                                .addPreferredGap(RELATED)//lo relacionamos
                                .addComponent(btnCrear)//añadimos el boton de crear
                                .addPreferredGap(RELATED)//lo relacionamos para la separacion
                                .addComponent(btnResolver)//añadimos el boton de resolver
                                .addPreferredGap(RELATED)//lo relacionamos para la separacion
                                .addComponent(btnBorrar)//añadimos el boton de borrar
                                .addGap(0, 0, Short.MAX_VALUE))//asignamos la separacion del final
        );
        pnlMenuLayout.setVerticalGroup(//aignamos  el grupo como vertical para asignar la altura entre todos
                pnlMenuLayout.createParallelGroup(LEADING)//asignamos el menu paralelo como principal
                        .addGroup(TRAILING, pnlMenuLayout.createSequentialGroup()//añadimos los componentes como van añadiendose
                                .addContainerGap(DEFAULT_SIZE, Short.MAX_VALUE)//asignamos las separasiones automaticas
                                .addGroup(pnlMenuLayout.createParallelGroup(LEADING)//añadimos el grupo a principal
                                        .addComponent(jSeparator1, PREFERRED_SIZE, 32, PREFERRED_SIZE)//añadimos el separador
                                        .addGroup(pnlMenuLayout.createParallelGroup(BASELINE)//añadimos el prupo con un alineamiento base
                                                .addComponent(lblDimX)//añadimos la etiqueta en X
                                                .addComponent(tbxDimX, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)//asignamos su separacion
                                                .addComponent(lblDimY)//añadimos la etiqueta en Y 
                                                .addComponent(tbxDimY, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)//asignamos su separacion
                                                .addComponent(btnCrear)//añadomps el boton de crear
                                                .addComponent(btnResolver)//añadimos el boton de resolver
                                                .addComponent(btnBorrar))))//añadimos el boton de borrar
        );

        pnlTablero.setBackground(new Color(204, 204, 204));//asignamos el color al panel
        pnlTablero.setBorder(createLineBorder(new Color(0, 0, 0)));//le creamos bordes negros

//        Solucion.asignarLayoutPnl(this);//Mandamos llamar el metodo de asignal Layout al panel

        GroupLayout layout = new GroupLayout(getContentPane());//creamos un objeto tipo GroupLayout
        getContentPane().setLayout(layout);//asignamos el layout al contenido del panel
        layout.setHorizontalGroup(//asignamos el layout de horizontal
                layout.createParallelGroup(LEADING)//creamos el grupo principal
                        .addGroup(layout.createSequentialGroup()//añadimos el grupo
                                .addContainerGap()//representamos los espacios entre el borde izquierdo
                                .addGroup(layout.createParallelGroup(LEADING)//creamos otro grupo como principal
                                        .addGroup(layout.createSequentialGroup()//añadimos los elementos secuencialmente
                                                .addComponent(pnlTablero, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)//añadimos el tablero
                                                .addGap(0, 0, Short.MAX_VALUE))//asignamos las separaciones
                                        .addComponent(pnlMenu, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE))//asignamos los tamaños
                                .addContainerGap())//representamos los espacios entre el borde derecho
        );
        layout.setVerticalGroup(//ahora lo hacemos de vertical
                layout.createParallelGroup(LEADING)//creamos el grupo principal
                        .addGroup(layout.createSequentialGroup()//añadimos el grupo de forma secuencial
                                .addContainerGap()//asignamos el espacio entre el borde superior
                                .addComponent(pnlMenu, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)//añadimos el menu
                                .addPreferredGap(RELATED)//creamos la separacion del panel con el menu en 0
                                .addComponent(pnlTablero, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)//añadimos el tablero
                                .addContainerGap(0, Short.MAX_VALUE))//asignamos la separacion del borde inferior
        );

        pnlColores.setLayout(new GridLayout(0, 4)); // 0 filas y 8 columnas para acomodar los colores y etiquetas

        //añadimos un nuevo panel y lo pintamos
        pnlColores.add(new JPanel() {
            {
                setBackground(Color.blue);
                JLabel entrada = new JLabel("Entrada");
                add(entrada);
                entrada.setForeground(Color.white);
            }
        });

        //añadimos un nuevo panel y lo pintamos
        pnlColores.add(new JPanel() {
            {
                setBackground(Color.red);
                JLabel salida = new JLabel("Salida");
                add(salida);
                salida.setForeground(Color.white);
            }
        });

        //añadimos un nuevo panel y lo pintamos
        pnlColores.add(new JPanel() {
            {
                setBackground(Color.green);
                JLabel camino = new JLabel("Camino");
                camino.setForeground(Color.white);
                add(camino);
            }
        });

        //añadimos un nuevo panel y lo pintamos
        pnlColores.add(new JPanel() {
            {
                setBackground(Color.cyan);
                JLabel BackTracking = new JLabel("BackTracking");
                BackTracking.setForeground(Color.white);
                add(BackTracking);
            }
        });

        // Definición del layout general del formulario
        layout.setHorizontalGroup(
                layout.createParallelGroup(LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(LEADING)
                                        .addComponent(pnlTablero, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                        .addComponent(pnlMenu, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(pnlColores, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
                                )
                                .addContainerGap()
                        )
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(pnlMenu, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                .addPreferredGap(RELATED)
                                .addComponent(pnlTablero, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                .addPreferredGap(RELATED)
                                .addComponent(pnlColores, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                .addContainerGap(0, Short.MAX_VALUE))
        );
        // Definición del layout del panel de tablero
        GroupLayout pnlTableroLayout = new GroupLayout(pnlTablero);
        pnlTablero.setLayout(pnlTableroLayout);
        pnlTableroLayout.setHorizontalGroup(
                pnlTableroLayout.createParallelGroup(LEADING)
                        .addGap(0, 585, Short.MAX_VALUE)
        );
        pnlTableroLayout.setVerticalGroup(
                pnlTableroLayout.createParallelGroup(LEADING)
                        .addGap(0, 500, Short.MAX_VALUE)
        );
        Solucion.setPnlTablero(pnlTablero);//mandamos el tablero a Solucionar laberinto
        Solucion.setTbxDimX(tbxDimX);//mandamos el cuadro de texto de X a Solucionar laberinto
        Solucion.setTbxDimY(tbxDimY);//mandamos el cuadro de texto de Y a Solucionar laberinto

        pack();//asignamos el tamaño de los componentes por si solos
    }
    
    
}
