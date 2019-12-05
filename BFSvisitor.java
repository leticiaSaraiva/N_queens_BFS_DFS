
import java.util.ArrayList;

public class BFSvisitor {
    //BFSvisitor realiza a busca em largura dado um nó inicial

    private Node objectiveNode;            //nó objetivo que o algorítmo encontrou
    private Node initial;                  //nó onde será iniciada a busca
    private String treePath;               //guarda String com todos os nós visitados em ordem e seus vizinhos
    private int treePathSize;


    public BFSvisitor(Node initial){
        this.initial = initial;
        this.objectiveNode = null;
        this.treePath = "empty!";
        this.treePathSize = 0;
    }

    public Node runBfs(){ //executa a busca em largura de fato
        _bfs();
        return objectiveNode;
    }

    private void _bfs(){

        Queue queue = new Queue();           //cria a fila
        queue.push(this.initial);           //insere o nó inicial no começo da fila

        this.treePath = "Percurso na arvore:\n";
        while(!queue.empty()){       //testa se a fila está vazia
                                    //caso contrário, pegamos o nó V que está na frente da fila.
            Node v = queue.front();
            queue.pop();

            v.generateNeighborhoods();                       //gera todos o vizinhos de V
            this.treePath += v.toStringNeighbors() + "\n";  //salva o nó V e seus vizinhos na String treePath
            this.treePathSize++;

            if(v.isObjective()){            //testa se o nó V é um objetivo
                objectiveNode = v;        //se for, guarda como nó objetivo e encerra a busca
                this.treePath += "Busca em Largura realizada com sucesso! Numero de nos percorridos: " + this.treePathSize + ".\n";
                return;
            }
            for(Node u : v.getNeighbors()){    //percorre todos os vizinhos U dos vértices atual V
                queue.push(u);                //adiciona o vizinho U na fila
            }
            //O fato do espaço de busca gerado ser um Grafo Direcionado Acíclico
            //indica que não precisamos controlar redundância
        }
        this.treePath = "empty!";
    }

    public String getTreePath() {
        return treePath;
    }
}











