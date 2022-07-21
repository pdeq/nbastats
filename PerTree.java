import java.util.ArrayList;
import java.util.NoSuchElementException;

public class PerTree {

  private Node<Player> root; // root node
  
  
  
  public boolean addPlayer (Player p) {
    
    if (p == null) {
      throw new NoSuchElementException();
    }
    
    if (root == null) {
      root = new Node<Player>(p);
    }
    else {
      if (addPlayerHelper(p, root)) {
        return true;
      }
    }
    return false;
    
  }
  
  protected static boolean addPlayerHelper(Player p, Node<Player> cur) {
    
    if (p.comparePer(cur.getCont()) <= 0){
      
      if (cur.getLeft() == null) {
        cur.setLeft(new Node<Player>(p));
        return true;
      }
      else {
        return addPlayerHelper(p, cur.getLeft());
      }
      
    }
    
    else if (p.comparePer(cur.getCont()) > 0) {
      
      if (cur.getRight() == null) {
        cur.setRight(new Node<Player>(p));
        return true;
      }
      else {
        return addPlayerHelper(p, cur.getRight());
      }
    }
    
    return false;
    
  }
  
  public ArrayList<Player> lookupAll(double minP, double maxP){
    return lookupAllHelper(minP, maxP, root);
  }
  
  //TODO
  public ArrayList<Player> lookupAllHelper(double minP, double maxP, Node<Player> cur){
    
    ArrayList<Player> matches = new ArrayList<Player>();
    
    if (cur != null) {
      
      if (cur.getCont().getPer() <= maxP && cur.getCont().getPer() >= minP) {
        matches.add(cur.getCont());
        matches.addAll(lookupAllHelper(minP, maxP, cur.getLeft()));
        matches.addAll(lookupAllHelper(minP, maxP, cur.getRight()));
      }
      
      else if (cur.getCont().getPer() > maxP) {
        if (cur.getLeft() != null) {
          return lookupAllHelper(minP, maxP, cur.getLeft());
        }
      }
      
      else if (cur.getCont().getPer() < minP) {
        if (cur.getRight() != null) {
          return lookupAllHelper(minP, maxP, cur.getRight());
        }
      }
      
      
      
      
    }
    
    return matches;
  }
  
}
