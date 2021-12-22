package br.com.matrix;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JPanel;

/**
 * @author Lucas iorio (www.byiorio.com.br)
 *
 *         Controle da chuva
 */
public class Chuva extends JPanel {

    private Rastro[] rastros;
    private Font fonteMatrix;

    public Chuva() throws FontFormatException, IOException {

        // Carrega fonte do Matrix do arquivo
        this.fonteMatrix = Font.createFont(Font.TRUETYPE_FONT,
                this.getClass().getResourceAsStream("/fonts/matrix.ttf"));
        fonteMatrix = fonteMatrix.deriveFont(Font.PLAIN, Utilidade.TAMANHO_FONTE);

        // Bora chover
        carregaChuva();

    }

    /**
     * Coloca um rastro para cada coluna no Eixo X disponivel
     */
    private void carregaChuva() {
        Integer larguraTela = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        Integer totalLetras = larguraTela / Utilidade.getLarguraFonte();
        this.rastros = new Rastro[totalLetras];

        for (int i = 0; i < totalLetras; i++) {
            this.rastros[i] = new Rastro(i);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        try {
            // Adiciona um quadrado com a cor preta para ficar de fundo
            Integer larguraTela = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
            Integer alturaTela = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
            g.setColor(new Color(13, 2, 8));
            g.fillRect(0, 0, larguraTela, alturaTela);

            // Gera grafico 2d para desenhar a letra
            Graphics2D g2 = (Graphics2D) g;
            g2.setFont(this.fonteMatrix);
            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            // FAz um loop para cada letra de cada rastro
            for (int i = 0; i < this.rastros.length; i++) {
                this.rastros[i].desenhar(g2);
            }

            // da uma folga pro processador
            Thread.sleep(10);

            repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
