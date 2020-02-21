import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Diagonal_Difference extends Frame implements ActionListener {
    int[] matrizCuadrada;
    String respuestaSize;
    Integer matrizSize, counter = 0;
    Frame ventanaDefinir = new Frame();
    Frame ventanaIngresar = new Frame();
    Frame ventanaMatriz = new Frame();
    Button bYes, bNo, ingresar, addNumber;
    TextField size, valueNumer;
    Label p3, p4, p5;

    Diagonal_Difference() {
        Label p1 = new Label();
        this.bYes = new Button("Yes");
        this.bNo = new Button("No");
        this.ventanaDefinir.setSize(600, 200);

        p1.setBounds(125,40,350,30);
        p1.setText("¿La matriz a ingresar tendrá un tamaño definido?");
        bYes.setBounds(255,120,30,30);
        bNo.setBounds(315,120,30,30);

        this.ventanaDefinir.add(p1);
        this.ventanaDefinir.add(bYes);
        this.ventanaDefinir.add(bNo);
        this.ventanaDefinir.setLayout(null);
        this.ventanaDefinir.setVisible(true);

        bYes.addActionListener(this);
        bNo.addActionListener(this);
    }

    public void solidSize() {
        Label p2 = new Label();
        this.size = new TextField();
        this.ingresar = new Button("Ingresar tamaño");
        this.ventanaIngresar.setSize(600, 200);

        p2.setBounds(125,40,350,30);
        p2.setText("¿Cuál será el tamaño de la nueva matriz?");
        this.ingresar.setBounds(200,140,200,30);
        this.size.setBounds(250,90,100,30);

        this.ventanaIngresar.add(p2);
        this.ventanaIngresar.add(ingresar);
        this.ventanaIngresar.add(size);
        this.ventanaIngresar.setLayout(null);
        this.ventanaIngresar.setVisible(true);

        ingresar.addActionListener(this);
    }

    public void generateMatrix() {
        this.p3 = new Label();
        this.p4 = new Label();
        this.p5 = new Label();
        this.valueNumer = new TextField();
        this.addNumber = new Button("Ingresar valor");
        this.ventanaMatriz.setSize(600, 350);

        this.p3.setBounds(125,40,350,30);
        this.p3.setText("Por favor ingrese los valores de la matriz:");
        this.p4.setBounds(125,190,350,30);
        this.p4.setText("Valores ingresados: " + this.counter.toString());
        this.p5.setBounds(125,240,350,30);
        this.p5.setText("Valores esperados: " + ((Integer)(this.matrizSize*this.matrizSize)).toString());
        this.addNumber.setBounds(200,140,200,30);
        this.valueNumer.setBounds(250,90,100,30);

        this.ventanaMatriz.add(p3);
        this.ventanaMatriz.add(p4);
        this.ventanaMatriz.add(p5);
        this.ventanaMatriz.add(addNumber);
        this.ventanaMatriz.add(valueNumer);
        this.ventanaMatriz.setLayout(null);
        this.ventanaMatriz.setVisible(true);

        addNumber.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        String auxSize;
        String auxValue;
        Integer numeroMatriz;

        if (ae.getActionCommand().equals("Yes")) {
            this.ventanaDefinir.setVisible(false);
            this.solidSize();
        }

        if (ae.getActionCommand().equals("No")) {
            JOptionPane.showMessageDialog(null, "Esta opción no fue implementada, cuestiones de tiempo...", "Seguimos programando para su satisfacción.", JOptionPane.INFORMATION_MESSAGE);
        }

        if (ae.getActionCommand().equals("Ingresar tamaño")) {
            auxSize = this.size.getText();
            try {
                this.matrizSize = Integer.parseInt(auxSize);
                this.ventanaIngresar.setVisible(false);
                this.matrizCuadrada = new int[this.matrizSize * this.matrizSize];
                this.generateMatrix();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ingrese un número por favor", "El tamaño debe ser un número", JOptionPane.INFORMATION_MESSAGE);
            }
        }

        if (ae.getActionCommand().equals("Ingresar valor")) {
            auxValue = this.valueNumer.getText();
            try {
                numeroMatriz = Integer.parseInt(auxValue);
                this.matrizCuadrada[this.counter] = numeroMatriz;
                this.counter++;
                this.p4.setText("Valores ingresados: " + this.counter.toString());
                this.valueNumer.setText("");
                if (this.counter == this.matrizCuadrada.length) {
                    this.calculate();
                }
            } catch (Exception e) {
                this.valueNumer.setText("");
                JOptionPane.showMessageDialog(null, "Solo se pueden ingresar números", "Valor numérico esperado", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public void calculate() {
        int fila = 0;
        int dDerecha = 0, dIzquierda = 0;
        for (int i = 0; i < this.matrizSize; i++) {
            dIzquierda += this.matrizCuadrada[(fila + 1) * this.matrizSize - (i + 1)];
            dDerecha += this.matrizCuadrada[fila * this.matrizSize + i];
            fila++;
        }
        JOptionPane.showMessageDialog(null,"La diferencia absoluta de las regiones es: " + ((Integer)(Math.abs(dDerecha-dIzquierda))).toString(),"Diferencia Absoluta",JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    public static void main(String[] args) {
        Diagonal_Difference matriz = new Diagonal_Difference();
    }
    
}