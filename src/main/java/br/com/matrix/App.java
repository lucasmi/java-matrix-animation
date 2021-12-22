package br.com.matrix;

import java.awt.FontFormatException;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * @author Lucas iorio (www.byiorio.com.br)
 *
 *         Animacao do Matrix
 */
public class App {
    public static void main(String[] args) throws FontFormatException, IOException {
        JFrame frame = new JFrame("Matrix - www.byiorio.com.br");
        frame.add(new Chuva());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}