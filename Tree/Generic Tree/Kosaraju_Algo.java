import java.util.*;
public class Kosaraju_Algo {
    static class Edge{
        int v1;
        int v2;
        Edge(int v1, int v2){
            this.v1=v1;
            this.v2=v2;
        }
    }

    public static void addEdge(int v1, int v2,ArrayList<ArrayList<Edge>> graph){
        graph.get(v1).add(new Edge(v1, v2));
    }

    public static void display(ArrayList<ArrayList<Edge>> graph){
        for(int i=0;i<graph.size();i++){
            System.out.print(i+"-> ");
            ArrayList<Edge> mArrayList= graph.get(i);
            for(int j=0;j<mArrayList.size();j++){
                Edge edge=mArrayList.get(j);
                System.out.print(edge.v1+"->"+edge.v2+", ");
            }
            System.out.println();
        }
    }

    public static Stack<Integer> st= new Stack<>();

    public static void dfs(int src, ArrayList<ArrayList<Edge>> graph,boolean[] visited ){
        visited[src]=true;
        st.add(src);
        for(int i=0;i<graph.get(src).size();i++){
            if(visited[graph.get(src).get(i).v2]==false){
                dfs(graph.get(src).get(i).v2, graph, visited);
            }
        }
    }

    public static int kosaraju(ArrayList<ArrayList<Edge>> graph){
        boolean[] visited= new boolean [graph.size()];
        // call dfs
        for(int i=0;i<graph.size();i++){
            if(visited[i]==false){
               dfs(i, graph, visited);
            }
        }
        
        // reverse edges of the original graph in new graph
        ArrayList<ArrayList<Edge>> graph2= new ArrayList<>();
        for(int i=0;i<graph.size();i++){
            graph2.add(new ArrayList<>());
        }

        for(int i=0;i<graph.size();i++){
            for(int j=0;j<graph.get(i).size();j++){
                addEdge(graph.get(i).get(j).v2, graph.get(i).get(j).v1, graph2);
            }
        }

        // count dfs calls
        int count =0;
        visited= new boolean [graph.size()];
        for(int i=0;i<graph.size();i++){
            if(visited[i]==false){
                count++;
               dfs(i, graph2, visited);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Edge>> graph= new ArrayList<>();
        for(int i=0;i<3;i++){
            graph.add(new ArrayList<>());
        }

        // addEdge(0, 2, graph);
        // addEdge(2, 1, graph);
        // addEdge(1, 0, graph);
        // addEdge(0, 3, graph);
        // addEdge(3, 4, graph);

        addEdge(0, 1, graph);
        addEdge(1, 2, graph);
        addEdge(2, 0, graph);
        // display(graph);
        System.out.println(kosaraju(graph));
    }
}
