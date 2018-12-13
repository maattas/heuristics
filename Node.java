//package astar;

public class Node{
int g;
int h;
int f;

int row;
int col;

Node parent;
//Node adjacents;

  public Node(int r, int c){
    row=r;
    col=c;
  }

  public void calculateHeuristic(Node goal, String heur) {
    if ( heur == "DISTANCE" ) {
      h = Math.abs(goal.row - row) + Math.abs(goal.col - col);
    } else if ( heur == "SOMETHING" ) {
      System.out.println("SOMETHING called");
    }
  }

}
