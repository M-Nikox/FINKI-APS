/*
 * Ваша задача е да креирате неориентиран нетежински граф со листа на соседство, каде темињата како информација содржат број. Графот го креирате според наредбите кои се добиваат. Ќе ви биде дадена низа од команди што можат да бидат од следните типови:

CREATE - треба да креирате нов граф. 

ADDEDGE [број1] [број2] - треба да креирате ребро меѓу темињата со реден број број1 и реден број број2. 

DELETEEDGE [број1] [број2] - треба да го избришете реброто меѓу темињата со реден број број1 и реден број број2. 

ADЈACENT [број1] [број2] - треба да испечатите true доколку темињата со реден број број1 и реден број број2 се соседни, во спротивно false.

PRINTGRAPH - Треба да ја испечатите листата на соседство

Во првата линија на влезот е даден бројот на команди кои ќе следуваат.
/

Your task is to create an unoriented unweighted graph by using an adjacency list, where each vertex information is аn integer. You create the graph according to the received commands. You will be given an array of commands that can be one of the following:

CREATE - you should create a new graph.

ADDEDGE [number1] [number2] - you should create an edge between the vertices with ordinal number number1 and ordinal number number2. 

DELETEEDGE [number1] [number2] - you should remove the edge between the vertices with ordinal number number1 and ordinal number number2.

ADЈACENT [number1] [number2] - you should print true if the vertices with ordinal number number1 and ordinal number number2 are adjacent, otherwise print false.

PRINTGRAPH - you should print the adjacency list. 

The number of commands is given in the first input line.
 */

 import java.util.*;

class AdjacencyListGraph<T> {
    private Map<T, Set<T>> adjacencyList;
    public AdjacencyListGraph() {
        this.adjacencyList = new HashMap<>();
    }

    public void addVertex(T vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            adjacencyList.put(vertex, new HashSet<>());
        }
    }

    public void removeVertex(T vertex) {

        for (Set<T> neighbors : adjacencyList.values()) {
            neighbors.remove(vertex);
        }

        adjacencyList.remove(vertex);
    }

    public void addEdge(T source, T destination) {
        addVertex(source);
        addVertex(destination);
        adjacencyList.get(source).add(destination);
        adjacencyList.get(destination).add(source);
    }

    public void removeEdge(T source, T destination) {
        if (adjacencyList.containsKey(source)) {
            adjacencyList.get(source).remove(destination);
        }
        if (adjacencyList.containsKey(destination)) {
            adjacencyList.get(destination).remove(source);
        }
    }

    public Set<T> getNeighbors (T vertex) {
        return adjacencyList.getOrDefault(vertex, new HashSet<>());
    }

    public void print()
    {
        adjacencyList.forEach((x,y) ->
                System.out.printf("%s: %s\n", x.toString(), new ArrayList<T>(y)));
    }

}

public class Test {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String line, parts[];
        int n = sc.nextInt();
        line=sc.nextLine();
        AdjacencyListGraph<Integer> graph = new AdjacencyListGraph<Integer>();
        for(int i=0; i<n; i++)
        {
            line = sc.nextLine();
            parts=line.split(" ");
            switch(parts[0])
            {
                case "CREATE" : graph = new AdjacencyListGraph<Integer>(); 
                break;
                case "PRINTGRAPH" : graph.print(); System.out.println(); 
                break;
                case "ADDEDGE" : graph.addEdge(Integer.parseInt(parts[1]),Integer.parseInt(parts[2])); 
                break;
                case "DELETEEDGE" : graph.removeEdge(Integer.parseInt(parts[1]),Integer.parseInt(parts[2])); 
                break;
                
                default:
                    System.out.println(graph
                            .getNeighbors(Integer.parseInt(parts[1]) )
                            .contains(Integer.parseInt(parts[2])) ); 
                break;
            }
        }
    }
}