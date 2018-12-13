//package astar;
import java.util.PriorityQueue;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Comparator;

public class Main {
    public static void main(String args[]) throws IOException {

    if (args.length != 2) {
      throw new IllegalArgumentException("Invalid amount of arguments found");
    }

    // Load the file
    String string_map = args[0];
    BufferedReader br = new BufferedReader(new FileReader(string_map));

    try {

        StringBuilder sb = new StringBuilder();
        String line = br.readLine();

        while (line != null) {
            sb.append(line);
            sb.append(System.lineSeparator());
            line = br.readLine();
        }
        string_map = sb.toString();
    } finally {
        br.close();
    }

    String[] map_matrice = string_map.split(System.lineSeparator());
    int rows = map_matrice.length;
    int columns = map_matrice[0].length();
    Astar astar = new Astar();

    astar.initArea(rows,columns);

    for(int row=0; row<rows; row++) {
      for(int column=0; column<columns; column++) {
        if ( map_matrice[row].charAt(column) == 'E' ) { // Find exit from maze
          Node node = new Node(row, column);
          astar.goal = node;
          // calculateHeuristic == 0
        }
      }
    }

    String HEURISTICS = args[1];

    for(int row=0; row<rows; row++) {
      for(int column=0; column<columns; column++) {
        if( map_matrice[row].charAt(column) == 'A' ) { // Find Al from maze
          Node node = new Node(row,column);
          node.g = 0;
          node.calculateHeuristic(astar.goal, HEURISTICS);
          astar.start= node;

        } else if ( map_matrice[row].charAt(column) == ' ' ) {
          Node node = new Node(row,column);
          node.calculateHeuristic(astar.goal, HEURISTICS);
          astar.area[row][column] = node;
        }
      }
    }

    // Add start node to open list
    astar.open.offer(astar.start);
    //astar.addStart(astar.start);

    String p = "";

    while ( !astar.isEmpty() ) {
      p += Integer.toString(astar.open.peek().row);
      p += Integer.toString(astar.open.peek().col);
      System.out.println(p);

      Node expandedNode = astar.open.peek();
      astar.open.remove(expandedNode);
      astar.closed.add(expandedNode);
      ArrayList<Node> list = new ArrayList<>();
      list = astar.findAdjacents(expandedNode);

      // found goal
      if ( list.contains(astar.goal) ) {
        break;
      } else {
        for (int i = 0; i < list.size(); i++) {
          astar.open.add(list.get(i));
        }
      }
    }
  }
} // Main







//https://www.baeldung.com/java-dijkstra
