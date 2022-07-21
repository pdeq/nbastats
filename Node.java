
public class Node<T extends Comparable<T>>{
  
  private T cont; // Data in each node
  private Node<T> left; // left node
  private Node<T> right; // right node
  
  public Node(T cont, Node<T> left, Node<T> right) {
    this.cont = cont;
    this.left = left;
    this.right = right;
  }
  
  public Node(T cont) {
    this.cont = cont;
  }
  
  public T getCont() {
    return cont;
  }
  
  public Node<T> getLeft(){
    return left;
  }
  
  public Node<T> getRight(){
    return right;
  }
  
  public void setLeft(Node<T> left) {
    this.left = left;
  }
  
  public  void setRight(Node<T> right) {
    this.right = right;
  }
  
  

}
