//package astar;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Comparator;


public class Astar{
  Node[][] area;

  PriorityQueue<Node> open;
  PriorityQueue<Node> closed;

  Node start;
  Node goal;
  Node[] keys;

  public Astar() {
    Comparator<Node> NodeCostComparator = new Comparator<Node>() {
      @Override
      public int compare(Node n1, Node n2) {
        return n1.f - n2.f;
      }
    };
    open = new PriorityQueue<Node>(NodeCostComparator);
    System.out.println(open.size());
    closed = new PriorityQueue<Node>(NodeCostComparator);
  }

  public void initArea(int r, int c){
    area = new Node[r][c];
  }

  public ArrayList<Node> findAdjacents(Node node) {
    ArrayList<Node> ret = new ArrayList<>();

    if ( area[node.row][node.col -1] != null && !closed.contains(area[node.row][node.col -1]) ) {
      area[node.row][node.col -1].parent=area[node.row][node.col];
      area[node.row][node.col -1].g=area[node.row][node.col -1].parent.g+2;
      ret.add(area[node.row][node.col -1]);

    } else if ( area[node.row][node.col +1] != null && !closed.contains(area[node.row][node.col +1]) ) {
      area[node.row][node.col +1].parent=area[node.row][node.col];
      area[node.row][node.col +1].g=area[node.row][node.col +1].parent.g+2;
      ret.add(area[node.row][node.col +1]);

    } else if ( area[node.row -1][node.col] != null && !closed.contains(area[node.row][node.col -1]) ) {
      area[node.row -1][node.col].parent=area[node.row][node.col];
      area[node.row -1][node.col].g=area[node.row][node.col +1].parent.g+2;
      ret.add(area[node.row -1][node.col]);

    } else if ( area[node.row +1][node.col] != null && !closed.contains(area[node.row][node.col +1]) ) {
      area[node.row +1][node.col].parent=area[node.row][node.col];
      area[node.row +1][node.col].g=area[node.row][node.col +1].parent.g+2;
      ret.add(area[node.row +1][node.col]);
    }
    return ret;
  }

  public boolean isEmpty() {
    boolean notNull = false;
    for(Node[] array : area) {
        for(Node val : array) {
          if(val != null) {
            notNull = true;
            break;
          }
        }
    }
    return notNull;
  }

}
