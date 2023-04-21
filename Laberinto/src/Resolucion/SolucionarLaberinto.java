/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Resolucion;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Random;
import java.util.Stack;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Jonathan de Jesus Perez Becerra
 */
public class SolucionarLaberinto {

    //Variables globales del formulario
    public static int Filas = 0;
    public static int Columnas = 0;
    private static int TamX = 0;
    private static int TamY = 0;
    private boolean resolverFlag = true;
    private static int entradaFilas, entradaColumnas, xant, yant, salidax, saliday;
    private static final int TableroX = 550;
    private static final int TableroY = 530;
    private JPanel pnlTablero;
    private JTextField tbxDimX;
    private JTextField tbxDimY;

    //Matriz de botones
    public static JButton[][] MatrizBotones;

    private Frame_mostrado.Frame_usar panel;//instanciamos la clase

    public void btnCrearActionPerformed(ActionEvent evt, JPanel pnlTablero) {
        if (HayElementos()) {
            JOptionPane.showMessageDialog(null, "Primero debes borrar el laberinto actual!");
        } //Fin if HayElementos
        else {

            //Se verifica que las entradas sean validas
            if (ValidarDimensiones()) {
                //Se settea el tamaño de la matriz de botones
                MatrizBotones = new JButton[Filas][Columnas];
                //Se settea el tamaño de gridLayout de nuestro panel del tablero
                pnlTablero.setLayout(new GridLayout(Filas, Columnas));
                //Se obtiene el tamaño de los botones acorde a su cantidad
                ObtenerTamObjetos(Filas, Columnas);
                //Se declaran los contadores a utilizar
                int entradaAleatoria;
                entradaAleatoria = (int) (Math.random() * 4);
                if (entradaAleatoria == 0) {
                    entradaFilas = 0;
                    entradaColumnas = (int) (Math.random() * Columnas - 1);
                    if (entradaColumnas == 0) {
                        entradaColumnas = 1;
                    }
                }
                if (entradaAleatoria == 1) {
                    entradaColumnas = Columnas - 1;
                    entradaFilas = (int) (Math.random() * Filas - 1);
                    if (entradaFilas == 0) {
                        entradaFilas = 1;
                    }
                }
                if (entradaAleatoria == 2) {
                    entradaFilas = Filas - 1;
                    entradaColumnas = (int) (Math.random() * Columnas - 1);
                    if (entradaColumnas == 0) {
                        entradaColumnas = 1;
                    }
                }
                if (entradaAleatoria == 3) {
                    entradaColumnas = 0;
                    entradaFilas = (int) (Math.random() * Filas - 1);
                    if (entradaFilas == 0) {
                        entradaFilas = 1;
                    }
                }

                //Creamos un algoritmo que indique la entrada
                for (int filas = 0; filas < Filas; filas++) {//Se recorre la dimension X desde 0 hasta DimensionX
                    for (int columnas = 0; columnas < Columnas; columnas++) {//Se recorre la dimension Y desde 0 hasta DimensionY
                        JButton btnNuevo = new JButton();//Se crea un nuevo objeto de tipo JButton
                        if ((columnas == 0 | columnas == Columnas - 1) | (filas == 0 | filas == Filas - 1)) {
                            btnNuevo.setText(1 + "");//le asignamos un 1 para simular un muro
                        } else {
                            btnNuevo.setText("");
                        }
                        btnNuevo.setSize(TamX, TamY);//Se le asignan sus dimensiones (ancho, alto)
                        if (filas == entradaFilas && columnas == entradaColumnas) {//Si estamos en la posicion de entrada
                            btnNuevo.setBackground(Color.blue);//Coloreamos la entrada de AZUL
                            btnNuevo.setText("*");
                        }
                        MatrizBotones[filas][columnas] = btnNuevo;//Se agrega a la matriz el botón recien creado
                        pnlTablero.add(MatrizBotones[filas][columnas]);//Se agrega al panel 
                        RedibujarTablero();//Se redibuja el panel
                    }//Fin For - Y
                }//Fin For - X
                crearCamino(entradaFilas, entradaColumnas);
                generarAleatorios();
                pintar();

            }//Fin If - ValidaDimensiones
            else {
                JOptionPane.showMessageDialog(null, "Las Dimensiones a ingresar deben ser númericas y en un rango de entre 3 y 50");
            }//Fin if - ValidaDimensiones - false
        }
    }

    /**
     * Metodo que redibuja el elemto pnlTablero
     */
    public void RedibujarTablero() {
        //Se valida los componentes del elemento pnlTablero
        pnlTablero.validate();
        //Se redibuja el elemento pnlTablero y sus componentes hijos
        pnlTablero.repaint();
    }

    /**
     * Metodo que resuelve el Laberinto
     *
     * @param evt evento que viene del clic del boton Resolver
     */
    public void btnResolverActionPerformed(ActionEvent evt) {
        //Se valida que hayan elementos en el panel
        if (HayElementos()) {
            if (resolverFlag) {
                resolverLaberinto(MatrizBotones, entradaFilas, entradaColumnas);
                resolverFlag = false;
            } else {
                JOptionPane.showMessageDialog(null, "El laberinto ya fue resuelto!");
            }
        }//Fin if HayElementos
        else {
            JOptionPane.showMessageDialog(null, "Primero debes crear el laberinto!");
        }
    }

    /**
     * Metodo que borra los componentes del panel
     *
     * @param evt evento que viene del clic del boton Borrar
     */
    public void btnBorrarActionPerformed(ActionEvent evt) {
        if (HayElementos()) {
            //Se recorre la matriz de botones y se elimina cada elemento de la matríz
            for (int x = 0; x < Filas; x++) {
                for (int y = 0; y < Columnas; y++) {
                    MatrizBotones[x][y] = null;
                }//Fin For - y
            }//Fin For - x
            //Se remueven todos los elementos hijos del JPanel pnlTablero
            pnlTablero.removeAll();
            //Se redibuja el panel
            RedibujarTablero();
            resolverFlag = true;
        } //Fin if HayElementos
        else {
            JOptionPane.showMessageDialog(null, "Primero Debes crear el laberinto!");
        }
    }

    /**
     * Función que verifica si hay elementos creados en el panel
     */
    private boolean HayElementos() {
        //Si hay elementos en el panel retorna true, caso contrario retorna false
        return pnlTablero.getComponentCount() > 0;
    }

    /**
     * Función que verifica si el valor String es de tipo númerico
     */
    private static boolean esNumerico(String valorString) {
        try {
            Integer.parseInt(valorString);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    /**
     * Función que valida que las dimensiones ingresadas por el usuario sean
     * validas y entre los rangos de 3 a 50
     */
    private boolean ValidarDimensiones() {
        boolean valido = false;//definición inicial de variable de retorno
        if (esNumerico(tbxDimX.getText())) {//Se obtienen las entradas de texto de las dimensiones ingresadas
            if (esNumerico(tbxDimY.getText())) {
                Filas = Integer.parseInt(tbxDimX.getText());//Se obtiene el tamaño en X (ancho) que debe tener el boton
                Columnas = Integer.parseInt(tbxDimY.getText());//Se obtiene el tamaño en Y (alto) que debe tener el boton
                if (((Filas < 51) && (Columnas < 51)) && ((Filas > 2) && (Columnas > 2))) {//Se verifican que las dimensiones ingresadas por el usuario esten entre 1 y 50
                    return true;
                }
            }
        }
        return valido;
    }

    /**
     * Metodo que calcula el tamaño de ancho y alto de los botones acorde a la
     * cantidad de elementos en la matriz
     */
    private void ObtenerTamObjetos(int cantX, int cantY) {
        TamX = TableroX / cantX;
        TamY = TableroY / cantY;
    }

    /**
     * Metodo que asigna el Layout al panel
     */
    public void asignarLayoutPnl() {
        // Se crea un nuevo objeto GroupLayout que se utilizará para establecer la disposición del panel pnlTablero
        GroupLayout pnlTableroLayout = new GroupLayout(pnlTablero);

        // Se establece la disposición horizontal del panel pnlTablero
        pnlTablero.setLayout(pnlTableroLayout);
        pnlTableroLayout.setHorizontalGroup(
                pnlTableroLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 585, Short.MAX_VALUE)
        );

        // Se establece la disposición vertical del panel pnlTablero
        pnlTableroLayout.setVerticalGroup(
                pnlTableroLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 500, Short.MAX_VALUE)
        );
    }

    /**
     * Método que crea un camino entre la entrada y la salida para que siempre
     * exista solucion
     *
     * @param fil valor de la entrada en x
     * @param col valor de la entrada en y
     * @return utilizamos la llamada recursiva o true en caso de haber acabado
     * el camino
     */
    private boolean crearCamino(int fil, int col) {
        asignarLayoutPnl();
        //si la entrada esta a la izquierda
        if (col == 0) {
            MatrizBotones[fil][col + 1].setText("*");
            MatrizBotones[fil][col + 1].setBackground(Color.cyan);
            return crearCamino(fil, col + 1);
        }
        //si la entrada esta a la derecha
        if (col == Columnas - 1) {
            MatrizBotones[fil][col - 1].setText("*");
            MatrizBotones[fil][col - 1].setBackground(Color.cyan);
            return crearCamino(fil, col - 1);
        }
        //si la entrada esta arriba
        if (fil == 0) {
            MatrizBotones[fil + 1][col].setText("*");
            MatrizBotones[fil + 1][col].setBackground(Color.cyan);
            return crearCamino(fil + 1, col);
        }
        //si la estrada esta abajo
        if (fil == Filas - 1) {
            MatrizBotones[fil - 1][col].setText("*");
            MatrizBotones[fil - 1][col].setBackground(Color.cyan);
            return crearCamino(fil - 1, col);
        }

        //----------------------------------------------------------------------
        Random aleatorio = new Random();
        int eleccion = aleatorio.nextInt(4);

        //generamos el camino hacia arriba
        if (eleccion == 0 && !MatrizBotones[fil - 1][col].getText().equals("*") && fil - 1 != xant && fil - 1 != entradaFilas) {//arriba
            if (MatrizBotones[fil - 1][col].getText().equals(1 + "")) {
                MatrizBotones[fil - 1][col].setText("*");
                MatrizBotones[fil - 1][col].setBackground(Color.red);
                salidax = fil - 1;
                saliday = col;
                return true;
            } else {
                xant = fil;
                yant = col;
                MatrizBotones[fil - 1][col].setText("*");
                return crearCamino(fil - 1, col);
            }
        }
        //generamos el camino hacia la derecha
        if (eleccion == 1 && !MatrizBotones[fil][col + 1].getText().equals("*") && col + 1 != yant && col + 1 != entradaColumnas) {//derecha
            if (MatrizBotones[fil][col + 1].getText().equals(1 + "")) {
                MatrizBotones[fil][col + 1].setText("*");
                MatrizBotones[fil][col + 1].setBackground(Color.red);
                salidax = fil;
                saliday = col + 1;
                return true;
            } else {
                xant = fil;
                yant = col;
                MatrizBotones[fil][col + 1].setText("*");
                return crearCamino(fil, col + 1);
            }
        }
        //generamos el camino hacia abajo
        if (eleccion == 2 && !MatrizBotones[fil + 1][col].getText().equals("*") && fil + 1 != xant && fil + 1 != entradaFilas) {//abajo
            if (MatrizBotones[fil + 1][col].getText().equals(1 + "")) {
                MatrizBotones[fil + 1][col].setText("*");
                MatrizBotones[fil + 1][col].setBackground(Color.red);
                salidax = fil + 1;
                saliday = col;
                return true;
            } else {
                xant = fil;
                yant = col;
                MatrizBotones[fil + 1][col].setText("*");
                return crearCamino(fil + 1, col);
            }
        }
        //generamos el camino hacia la izquieda
        if (eleccion == 3 && !MatrizBotones[fil][col - 1].getText().equals("*") && col - 1 != yant && col - 1 != entradaColumnas) {//izquierda
            if (MatrizBotones[fil][col - 1].getText().equals(1 + "")) {
                MatrizBotones[fil][col - 1].setText("*");
                MatrizBotones[fil][col - 1].setBackground(Color.red);
                salidax = fil;
                saliday = col - 1;
                return true;
            } else {
                xant = fil;
                yant = col;
                MatrizBotones[fil][col - 1].setText("*");
                return crearCamino(fil, col);
            }
        }

        return crearCamino(fil, col);
    }

    /**
     * Metodo que genera numeros aleatorios entre 0 y 1 respetando el camino
     * creado anteriormente
     */
    private void generarAleatorios() {
        /**
         * Recorre la matriz de botones y asigna valores aleatorios de 0 o 1 a
         * los botones vacíos y un valor de 0 a los botones con asterisco.
         */
        for (int i = 0; i < MatrizBotones.length; i++) {
            for (int j = 0; j < MatrizBotones[0].length; j++) {
                // Verificar si el botón está vacío
                if (MatrizBotones[i][j].getText().equals("")) {
                    // Asignar un valor aleatorio de 0 o 1 al botón vacío
                    MatrizBotones[i][j].setText((int) (Math.random() * 2) + "");
                }
                // Verificar si el botón es la entrada del laberinto
                if (MatrizBotones[i][j].getText().equals("*")) {
                    // Asignar un valor de 0 al botón con asterisco
                    MatrizBotones[i][j].setText(0 + "");
                }
            }
        }
    }

    /**
     * Método que pinta las casillas para que sea mas facil visualizar los
     * caminos de las paredes, asi mismo distinguir la entrada de la salida
     */
    private void pintar() {
        for (int i = 0; i < MatrizBotones.length; i++) {
            for (int j = 0; j < MatrizBotones[0].length; j++) {
                if (MatrizBotones[i][j].getText().equals("1")) {
                    MatrizBotones[i][j].setBackground(Color.lightGray);
                }
                if (MatrizBotones[i][j].getText().equals("0")) {
                    MatrizBotones[i][j].setBackground(Color.white);
                }
                if (i == entradaFilas && j == entradaColumnas) {
                    MatrizBotones[i][j].setBackground(Color.blue);
                    MatrizBotones[i][j].setText("E");
                }
                if (i == salidax && j == saliday) {
                    MatrizBotones[i][j].setBackground(Color.red);
                    MatrizBotones[i][j].setText(0 + "");
                }

            }
        }
    }

    private boolean resolverLaberinto(JButton[][] laberinto, int filaActual, int colActual) {
        Stack<int[]> camino = new Stack<>(); // Pila para almacenar las coordenadas del camino actual
        camino.push(new int[]{filaActual, colActual});

        // Chequear si estamos en el objetivo
        if (laberinto[filaActual][colActual].getText().equals(0 + "") && (colActual == 0 | colActual == Columnas - 1
                | filaActual == 0 | filaActual == Filas - 1)) {
            JOptionPane.showMessageDialog(null, "Logre salir!");
            return true;
        }

        // Chequear si la casilla actual es una pared o ya ha sido visitada
        if (laberinto[filaActual][colActual].getText().equals(1 + "") || laberinto[filaActual][colActual].getText().equals("")) {
            camino.pop(); // Desapilar las coordenadas de la casilla actual
            return false;
        }

        // Marcar la casilla actual como visitada
        if (!laberinto[filaActual][colActual].getText().equals("E")) {
            laberinto[filaActual][colActual].setText("");
            laberinto[filaActual][colActual].setBackground(Color.green);
        }

        // Chequear las casillas vecinas
        if (filaActual > 0 && resolverLaberinto(laberinto, filaActual - 1, colActual)) { // arriba
            return true;
        }
        if (colActual < laberinto[0].length - 1 && resolverLaberinto(laberinto, filaActual, colActual + 1)) { // derecha
            return true;
        }
        if (filaActual < laberinto.length - 1 && resolverLaberinto(laberinto, filaActual + 1, colActual)) { // abajo
            return true;
        }
        if (colActual > 0 && resolverLaberinto(laberinto, filaActual, colActual - 1)) { // izquierda
            return true;
        }

        // Si ninguna casilla vecina lleva al objetivo, desmarcar la casilla actual y retroceder
        if (!laberinto[filaActual][colActual].getText().equals("E")) {
            laberinto[filaActual][colActual].setText(0 + "");
            laberinto[filaActual][colActual].setBackground(Color.CYAN);
            camino.pop(); // Desapilar las coordenadas de la casilla actual
        }
        return false;
    }

    
    public void setPnlTablero(JPanel pnlTablero) {
        this.pnlTablero = pnlTablero;
    }

    public void setTbxDimX(JTextField tbxDimX) {
        this.tbxDimX = tbxDimX;
    }

    public void setTbxDimY(JTextField tbxDimY) {
        this.tbxDimY = tbxDimY;
    }

}
