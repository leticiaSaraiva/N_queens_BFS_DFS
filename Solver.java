

import java.util.ArrayList;
import java.util.Scanner;

/*

Equipe:
Claro Henrique    -   400552
Letícia Saraiva   -   402120

 */

public class Solver {
    public static void main (String[] args){
        //implementação da interação com o usuário
        System.out.println("hello");
        int n = 0;


        while(true) {
            //lê quantidade de rainhas
            Scanner scan = new Scanner(System.in);
            System.out.println("\n\nBem-vindx ao incrivel programa que resolve o problema das N rainhas!");
            System.out.print("Digite o numero de rainhas:");

            while(true) {
                //le a quantidade de rainhas
                try {
                    n = Integer.parseInt(scan.nextLine());
                    break;
                } catch (Exception e) {
                    System.out.println("Desculpe, nao entendi!\nPoderia por gentileza digitar de novo?");
                }
            }

            Node initial = new Node(n);
            Node result;
            String showTree = "";

            while(true){
                //seleciona a busca em profundidade (dfs) ou busca em largura (bfs)
                System.out.println("Deseja realizar uma dfs ou bfs? [dfs|bfs]");
                String op = scan.nextLine().toLowerCase();

                if(op.contains("dfs")) {
                    DFSvisitor dfs_visitor = new DFSvisitor(initial); //realiza a DFS
                    result = dfs_visitor.runDfs();         //salva o nó objetivo encontrado ou null se não há solução
                    showTree = dfs_visitor.getTreePath(); //salva o espaço de busca em string
                    break;
                }else if(op.contains("bfs")){
                    BFSvisitor bfs_visitor = new BFSvisitor(initial); //realiza a BFS
                    result = bfs_visitor.runBfs();         //salva o nó objetivo encontrado ou null se não há solução
                    showTree = bfs_visitor.getTreePath(); //salva o espaço de busca em string
                    break;
                }
            }

            if(result == null){
                //não há solução
                System.out.println("Desculpe, nao ha solucao para n = " + n);
            }else{
                //mostra o caminho do nó inicial até o nó objetivo, mostrando também os vizinhos de cada nó
                System.out.println(result.showPathNeighbors());

                System.out.println("Pressione ENTER para ver a solucao um como tabuleiro.");
                scan.nextLine().toLowerCase();
                
                //mostra o caminho do nó inicial até o nó objetivo, porém usando a representação de tabuleiro n x n
                System.out.println(result.showPath(true));
                
                System.out.println("Pressione ENTER para ver toda a arvore gerada.");
                scan.nextLine().toLowerCase();
                    
                //mostra todos os nós gerados durante a busca, mostrando também os vizinhos de cada nó
                System.out.println(showTree);

            }
            System.out.println("Pressione ENTER para a iniciar de novo");
            scan.nextLine();
        }
    }
}
