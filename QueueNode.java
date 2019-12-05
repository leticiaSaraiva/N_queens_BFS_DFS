
class QueueNode{
    //classe auxiliar para fila
    protected Node key;
    protected QueueNode next;

    public QueueNode(Node key){

        this.key = key;
        this.next = null;
    }
    public QueueNode(){
        this.key = null;
        this.next = null;
    }
}
