package br.com.matrix;

import java.awt.Color;
import java.awt.Toolkit;
import java.io.Serializable;

/**
 * @author Lucas iorio (www.byiorio.com.br)
 *
 *         Controle da Letra
 */
public class Letra implements Serializable {
    private int x; // posicao no eixo x
    private int y;// posicao no eixo y
    private char[] caractere; // Array de CHAR de uma posicao por causa do objeto que desenha, onde pega um
                              // array de char
    private Color cor; // Cor atual da letra

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
