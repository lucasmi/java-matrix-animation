package br.com.matrix;

import java.util.Random;

/**
 * @author Lucas iorio (www.byiorio.com.br)
 *
 *         Utilidades
 */
public class Utilidade {
    public static final int TAMANHO_FONTE = 16;
    private static Random randomico = new Random();

    private Utilidade() {
    }

    public static char[] getLetraRandomica() {
        int[] letrasExistentes = { 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113,
                114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 36, 43, 45, 42, 47,
                61, 37, 34, 39, 35, 38, 40 };

        // Escolhe um indice do array aleatorio
        int indiceAleatorioLetra = getNumeroRandomico(letrasExistentes.length);

        // cria um array para o componente que desenha a letra
        char[] definirLetra = new char[1];

        // Pega a letra atraves do indice aleatorio escilho
        definirLetra[0] = (char) letrasExistentes[indiceAleatorioLetra];
        return definirLetra;
    }

    public static int getNumeroRandomico(int max) {
        return randomico.nextInt(max);
    }

    public static int getNumeroRandomico(int min, int max) {
        return randomico.nextInt(max - min + 1) + min;
    }

    public static int getNumeroRandomicoMultiplo(int maximo, int multiplo) {
        int valor = randomico.nextInt(maximo / multiplo) * multiplo;
        return valor == 0 ? 1 : valor;
    }

    public static int getAlturaFonte() {
        return TAMANHO_FONTE; // -2 // caso queira mudar o espacamento
    }

    public static int getLarguraFonte() {
        return TAMANHO_FONTE;// -3 // caso queira mudar o espacamento
    }

    /**
     * Pega uma velocidade dentro dos multiplos do tamanho da fonte
     * 
     * @return
     */
    public static int getVelocidadeRandomica() {
        int[] multiplos = { 2, 4, 8, 16 };
        int multiploEscolhido = randomico.nextInt(multiplos.length);
        return multiplos[multiploEscolhido];
    }
}
