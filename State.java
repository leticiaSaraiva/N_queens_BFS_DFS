

import java.util.ArrayList;



public class State {
    //classe State guarda a representação de um estado do tabuleiro
    //a representação consiste em um vetor de inteiros V
    //onde V[i] = posição da coluna da rainha que está na linha i

    private ArrayList<Integer> values;
    public State(ArrayList<Integer> nvalues){
        values = nvalues;
    }
    public ArrayList<Integer> getValues() {
        return values;
    }
}









