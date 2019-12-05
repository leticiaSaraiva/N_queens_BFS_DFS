
import java.util.ArrayList;

public class DFSvisitor {
    //DFSvisitor realiza a busca em profundidade dado um nó inicial

    private Node objectiveNode;     //todos os nós objetivos que o algorítmo encontrou
    private Node initial;           //nó onde será iniciada a busca
    private String treePath;        //guarda String com todos os nós visitados em ordem e seus vizinhos
    private int treePathSize;       //quantidade de nós visitados durante a busca

    public DFSvisitor(Node inicial){
        this.initial = inicial;
        this.objectiveNode = null;
        this.treePath = "";
        this.treePathSize = 0;
    }

    public Node runDfs(){    //executa a busca em profundidade de fato
        _dfs(initial);                 //a pilha é utilizada implicitamente através de chamadas recursivas
        return objectiveNode;
    }

    private boolean _dfs(Node v){
        v.generateNeighborhoods();                      //gera todos os vizinhos do nó atual
        this.treePath += v.toStringNeighbors() + "\n"; //salva o nó V e seus vizinhos na String treePath
        this.treePathSize++;

        if(v.isObjective()){            //testa se o nó atual é objetivo
            objectiveNode = v;         //se for, ele é adicionado no nós objetivos
            this.treePath += "\nBusca em Profundidade realizada com sucesso! Numero de nos percorridos: " + this.treePathSize + ".\n";
            return true;
        }else{
            for(Node u : v.getNeighbors()){       //percorre todos os vizinhos U do nó atual
                boolean foundSolution = _dfs(u); //chama a função recursiva para o vizinho
                if(foundSolution)
                    return true;
            }
            return false;
        }
        //O fato do espaço de busca gerado ser um Grafo Direcionado Acíclico
        //indica que não precisamos controlar redundância
    }

    public String getTreePath() {
        return treePath;
    }
}









