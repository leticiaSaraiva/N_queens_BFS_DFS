
import java.util.ArrayList;


public class Node {


    int n;
    private State state; //estado do nó atual
    private Node parent; //nó pai que gerou o nó atual
    private ArrayList<Node> neighbors; //todos os nós vizinhos

    public Node(State state, Node parent, int n){
        this.state = state;
        this.parent = parent;
        this.neighbors = new ArrayList<>();
        this.n = n;
    }

    public Node(int n){
        this.state = new State(new ArrayList<>());
        this.parent = null;
        this.neighbors = new ArrayList<>();
        this.n = n;
    }

    public ArrayList<Node> generateNeighborhoods(){
        // Gera todos os vizinhos do no atual
        // e guarda nessa lista 'neigh'
        ArrayList<Node> neighbors = new ArrayList<>();

        for(int i = 0; i < n; i++){
            // Se for possível colocar o inteiro i no final do vetor sem ferir as propriedades do jogo,
            // então ele é adicionado como vizinho
            if(isValidOperation(i)){
                ArrayList<Integer> newState = new ArrayList<>(state.getValues()); //criar cópia do estado atual
                newState.add(i);                                                 //adiciona o inteiro i no final
                Node u = new Node(new State(newState),this,n);          //cria o novo nó vizinho
                neighbors.add(u);                                              //adiciona o nó criado na vizinhança
            }
        }
        return this.neighbors = neighbors;  //retorna os vizinhos gerados do nó atual
    }

    private boolean isValidOperation(int x){
        // Testa se é possível acrescentar o x no final do vetor
        int lx = state.getValues().size(); //linha da peça x
        int cx = x;                       //coluna da peça x

        if(lx == n) return false; //tabuleiro já foi preenchido por N rainhas
        for(int i = 0; i < state.getValues().size(); i++){
            int li = i;
            int ci = state.getValues().get(i);
            if(ci == cx) return false;              //testa se já existe uma peça que ocupa a coluna da peça x
            if(ci - li == cx - lx) return false;    //testa se já existe uma peça na diagonal (principal) da peça x
            if(ci + li == cx + lx) return false;    //testa se já existe uma peça na diagonal (secundária) da peça x
        }
        return true;
    }

    public boolean isObjective(){
        return state.getValues().size() == n;
    }
    //funcao testa se o estado desse nó é um objetivo

    public ArrayList<Node> getNeighbors() {
        return neighbors;
    }
    public Node getParent() {
        return parent;
    }

    @Override
    public String toString() {
        //retorna String que representa o estado desse nó
        String res = "[";
        for(int i = 0; i < state.getValues().size(); i++){
            res += this.state.getValues().get(i);
            if(i != state.getValues().size()-1)
                res += ',';
        }
        return res + "]";
    }


    public String toString(boolean showBoard) {
        //se showBoard = true, retorna uma String que mostra um tabuleiro NxN para uma melhor visualização da solução
        //caso contrário retorna a representação do estado padrão
        if(!showBoard) return this.toString();
        String res = "";
        for(int i = 0; i < n; i++){
            String linha;
            if(i < state.getValues().size()){
                int col = state.getValues().get(i);
                linha = ". ".repeat(col) + "x " + ". ".repeat(n-col-1);
            }else{
                linha = ". ".repeat(n);
            }
            res += linha;
            if(i != n-1) res += '\n';
        }
        return res;
    }

    public String showPath(boolean showBoard){
        //retorna o toString do caminho entre o nó inicial (vazio) até o nó atual
        String res = "";
        Node atual = this;
        while(atual != null) {
            res = atual.toString(showBoard) + "\n" + "_".repeat(this.n*2+2) + "\n" + res;
            atual = atual.getParent();
        }
        return res;
    }

    public String showPathNeighbors(){
        //retorna o toString do caminho entre o nó inicial (vazio) até o nó atual
        String res = "Caminho do nó inicial para a solução: \n";
        res += "Estado      |     Vizinhos \n";
        Node atual = this;
        ArrayList<String> stack = new ArrayList<>();

        while(atual != null) {
            String v = atual.toString() + " = {";
            for(Node u : atual.neighbors){
                v += u + ",";
            }
            v = v + "}\n";
            stack.add(v);
            atual = atual.getParent();
        }

        for (int i = stack.size()-1; i >= 0; i--) {
            res += stack.get(i);
        }
        return res;
    }

    public String toStringNeighbors(){
        //retorna uma String com o estado do nó e seus vizinhos
        String res = this + " = {";
        for(Node u : this.neighbors){
            u.generateNeighborhoods();
            res += u + ",";
        }
        res = res + "}";
        return res;
    }

    public String showTree(){
        return "";
    }
    private String _showTree(int depth){
        return "";
    }
}








