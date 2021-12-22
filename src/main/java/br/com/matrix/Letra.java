package br.com.matrix;

import java.awt.Color;
import java.awt.Toolkit;
import java.io.Serializable;

/**
 * @author Lucas iorio (www.byiorio.com.br)
 *
 * Controle da Letra
 */
public class Letra implements Serializable {
    private int x;
    private int y;
    private char[] caractere;
    private Color cor;

    public boolean isSumiu() {
        Integer h = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        return (this.y > h);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public char[] getLetra() {
        return caractere;
    }

    public void setLetra(char[] letra) {
        this.caractere = letra;
    }

    public Color getCor() {
        return cor;
    }

    public void setCor(Color cor) {
        this.cor = cor;
    }

}
