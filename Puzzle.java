package puzzle;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 *
 * @author Marinoca
 */
public class Puzzle {
    Queue<String> lista = new LinkedList<String>();    //Fila implementado usando lista vinculada para armazenar todos os nós .
    Map<String, Integer> nosRepetidos = new HashMap<String, Integer>(); // HashMap é usado para ignorar os nós repetidos
    Map<String, String> posAntecessor = new HashMap<String, String>(); // relaciona cada posição de seu antecessor

    public static void main(String args[]) {

        //numeros de entrada para iniciar jogo
        String numerosDoJogo = "123567048";

        Puzzle jogoPuzzle = new Puzzle(); 
        jogoPuzzle.add(numerosDoJogo, null); 

        while (!jogoPuzzle.lista.isEmpty()) {

            String estadoAtual = jogoPuzzle.lista.remove();
            jogoPuzzle.subir(estadoAtual);  // Mova o espaço em branco e adicionar novo estado de fila
            jogoPuzzle.descer(estadoAtual);  // Mova o espaço em branco para baixo
            jogoPuzzle.esquerda(estadoAtual); // Move esquerda
            jogoPuzzle.direita(estadoAtual); // Mover para a direita e remova o nó atual da fila
        }

        System.out.println("Não existe solução para este jogo!");
    }

    //Adicionar método para adicionar a nova cadeia ao mapa e fila
    void add(String novoEstado, String antigoEstado) {
        if (!nosRepetidos.containsKey(novoEstado)) { // se não tiver um novo estado repetindo
            int novoValor = antigoEstado == null ? 0 : nosRepetidos.get(antigoEstado) + 1; //se antigoEstado for null, entao novoValor recebe 0 senão novoValor recebe nosRepetidos.get(antigoEstado) + 1
            nosRepetidos.put(novoEstado, novoValor); //atualiza novoEstado e valor
            lista.add(novoEstado);
            posAntecessor.put(novoEstado, antigoEstado); //atualiza novo e antigo estado
        }
    }
//* Funções que realizam possiveis movimentos

    void subir(String estadoAtual) {
        int indexZero = estadoAtual.indexOf("0");
        if (indexZero > 2) {
            String proximoEstado = estadoAtual.substring(0, indexZero - 3) + "0" + estadoAtual.substring(indexZero - 2, indexZero) + estadoAtual.charAt(indexZero - 3) + estadoAtual.substring(indexZero + 1);
            verificarConclusao(estadoAtual, proximoEstado);
        }
    }

    void descer(String estadoAtual) {
        int indexZero = estadoAtual.indexOf("0");
        if (indexZero < 6) {
            String proximoEstado = estadoAtual.substring(0, indexZero) + estadoAtual.substring(indexZero + 3, indexZero + 4) + estadoAtual.substring(indexZero + 1, indexZero + 3) + "0" + estadoAtual.substring(indexZero + 4);
            verificarConclusao(estadoAtual, proximoEstado);
        }
    }

    void esquerda(String estadoAtual) {
        int indexZero = estadoAtual.indexOf("0");
        if (indexZero != 0 && indexZero != 3 && indexZero != 6) {
            String proximoEstado = estadoAtual.substring(0, indexZero - 1) + "0" + estadoAtual.charAt(indexZero - 1) + estadoAtual.substring(indexZero + 1);
            verificarConclusao(estadoAtual, proximoEstado);
        }
    }

    void direita(String estadoAtual) {
        int indexZero = estadoAtual.indexOf("0");
        if (indexZero != 2 && indexZero != 5 && indexZero != 8) {
            String proximoEstado = estadoAtual.substring(0, indexZero) + estadoAtual.charAt(indexZero + 1) + "0" + estadoAtual.substring(indexZero + 2);
            verificarConclusao(estadoAtual, proximoEstado);
        }
    }


    private void verificarConclusao(String antigoEstado, String novoEstado) {
        add(novoEstado, antigoEstado);
        if (novoEstado.equals("123804765")) {
            System.out.println("Foram necessários " + nosRepetidos.get(novoEstado) + " movimentos para resolução do jogo.");
            String ultimoEstado = novoEstado;

            while (ultimoEstado != null) {

                for (int i = 0; i < 9; i++) {
                    String numeros = ultimoEstado;
                    char[] vetorNumeros = numeros.toCharArray();
                    System.out.print(vetorNumeros[i]);
                    if (i == 2) {
                        System.out.println("");
                    }
                    if (i == 5) {
                        System.out.println("");
                    }
                }
                System.out.println("");
                System.out.println("movimento " + nosRepetidos.get(ultimoEstado));
                System.out.println("");
                ultimoEstado = posAntecessor.get(ultimoEstado);

            }
            System.exit(0);
        }
    }
}


 
