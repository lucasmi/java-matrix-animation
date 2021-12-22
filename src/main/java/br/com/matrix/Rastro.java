package br.com.matrix;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.io.Serializable;

public class Rastro implements Serializable {
    private Letra[] letras; // Listra de lestras do rastro
    private int velocidade; // Velocidade do Rastro
    private int posicaoAtualAnimacao; // Posicao da letra Branca
    private int tamanho; // Tamanho total do rastro
    private int posicaoLateral; // Posicao no Eixo X do rastro
    private boolean primeiraVez = true; // Variavel usada par bao comecar tudo na mesma posicao

    public Rastro(int posicaoLateral) {
        inicializar(posicaoLateral);
    }

    /***
     * Inicializa todas a variaveis do Rastro
     * 
     * @param posicaoLateralEscolhida
     */
    private void inicializar(int posicaoLateralEscolhida) {
        // Pega altura maxima da tela
        int alturaTela = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

        // Com a altura maxima da pra saber quantas letras vao caber
        int totalLetras = alturaTela / Utilidade.getAlturaFonte();

        // Limite do rastro, para o rastro nao ficar do tamanho da tela, ele pode ter o
        // tamanho total menos 2 posicoes
        int limiteLetras = 2;

        // PEga tamanho aleatorio do rastro para cada rastro ficar diferente
        this.tamanho = Utilidade.getNumeroRandomico(20, totalLetras - limiteLetras);

        // Pega a posicao do Eixo X escolhida no FOR da Chuva.java
        this.posicaoLateral = posicaoLateralEscolhida;

        // Pega uma velocidade aleatorio para o rastro
        this.velocidade = Utilidade.getVelocidadeRandomica();

        // Cria o rastro inteiro de letras
        this.letras = this.criarTodasLetras();

        // Caso seja a primeira vez que o rastro sera desenhado
        // pegar uma posicao inicial aleatoria, para nao comecar todo mundo no mesmo
        // lugar
        if (this.primeiraVez) {
            this.posicaoAtualAnimacao = Utilidade.getNumeroRandomicoMultiplo(20 *
                    Utilidade.getAlturaFonte(),
                    Utilidade.getAlturaFonte());
            this.primeiraVez = false;
        } else {
            this.posicaoAtualAnimacao = 0;
        }
    }

    /**
     * Preence toda a coluna com letras
     * 
     * @return
     */
    private Letra[] criarTodasLetras() {
        // Descobre quantas letras cabem na vertical
        Integer alturaTela = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        Integer totalLetras = alturaTela / Utilidade.getAlturaFonte();

        // Cria o tamanho maximo de letras
        Letra[] listaLetras = new Letra[totalLetras];

        // Cria randomicamente as letras
        for (int i = 0; i < totalLetras; i++) {

            // Inicia uma letra
            Letra letra = new Letra();
            letra.setY(i * Utilidade.getAlturaFonte());
            letra.setX(posicaoLateral * Utilidade.getAlturaFonte());
            letra.setLetra(Utilidade.getLetraRandomica());
            letra.setCor(Color.BLACK);
            listaLetras[i] = letra;
        }

        return listaLetras;
    }

    /**
     * Desenha a letra na tela
     * 
     * @param g2
     */
    public void desenhar(Graphics2D g2) {

        // Verifica todas as letras
        for (int i = 0; i < this.letras.length; i++) {
            // se a posicao for multiplo do tamanho da fonte, faz as verificacoes
            if (posicaoAtualAnimacao % Utilidade.getAlturaFonte() == 0) {

                // Escolhe a cor da letra
                this.letras[i].setCor(selecionaCorLetra(i));

                // Verifica se o acaso vai mudar a letra
                // Em uma chance de 1 para 20
                if (Utilidade.getNumeroRandomico(20) == 1) {
                    this.letras[i].setLetra(Utilidade.getLetraRandomica());
                }
            }

            // Nao desenhar se a cor for preta
            if (!Color.BLACK.equals(this.letras[i].getCor())) {
                g2.setColor(this.letras[i].getCor());
                g2.drawChars(this.letras[i].getLetra(), 0, 1, this.letras[i].getX(), this.letras[i].getY());
            }
        }

        avancarPosicao();
    }

    /**
     * Verificar se a letra atual está na posicao que gostaria de saber
     * 
     * @param index
     * @param posicaoVerificar
     * @return
     */
    private boolean isLetraNaPosicaoSolicitada(int indexLetra, int posicaoVerificar) {
        int verificaPosicao = posicaoVerificar * Utilidade.getAlturaFonte();

        return (posicaoAtualAnimacao - this.letras[indexLetra].getY() == verificaPosicao);
    }

    /**
     * Seleciona uma cor para a Letra
     * 
     * @param indexLetra
     * @return
     */
    private Color selecionaCorLetra(int indexLetra) {
        int tamanhoRastroCorreto = (tamanho * Utilidade.getAlturaFonte());

        // Se a letra atual está dentro do tamanho do rastro
        boolean letraDentroLimiteRastro = posicaoAtualAnimacao > this.letras[indexLetra].getY()
                && this.letras[indexLetra].getY() >= posicaoAtualAnimacao - tamanhoRastroCorreto;

        // Verifica se a letra atual do index esta junto com a posicao atual da queda
        // 0 = posicao atual
        if (isLetraNaPosicaoSolicitada(indexLetra, 0)) {
            // Cor da primeira Letra
            return Color.WHITE;

        } else if (letraDentroLimiteRastro) {
            // Cor da segunda letra
            if (isLetraNaPosicaoSolicitada(indexLetra, 1)) {
                return new Color(146, 229, 161);
                // return Color.RED;

                // Cor da terceira letra
            } else if (isLetraNaPosicaoSolicitada(indexLetra, 2)) {
                return new Color(128, 206, 135);
                // return Color.YELLOW;

            } else if (isLetraNaPosicaoSolicitada(indexLetra, this.tamanho - 1)) {
                // return Color.YELLOW;
                return new Color(21, 130, 59);

            } else if (isLetraNaPosicaoSolicitada(indexLetra, this.tamanho)) {
                // return Color.RED;
                return new Color(13, 90, 40);

            } else {
                // Cor normal
                return new Color(34, 180, 85);
                // return new Color(0, 255, 65); //verde claro
            }

        } else {
            return Color.BLACK;
        }
    }

    /**
     * Faz a animacao andar, no caso a letra branca descer
     */
    private void avancarPosicao() {
        // Avanca posicao
        this.posicaoAtualAnimacao += this.velocidade;

        // Se ultrapassou o limite volta pro começo
        Integer alturaTela = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        int tamanhoTrilha = (tamanho * Utilidade.getAlturaFonte());

        // Se sair da tela reinicia o rastro
        if (this.posicaoAtualAnimacao > alturaTela + tamanhoTrilha) {
            inicializar(this.posicaoLateral);
        }
    }
}
