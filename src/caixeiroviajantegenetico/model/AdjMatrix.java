/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package caixeiroviajantegenetico.model;

/**
 *
 * @author Cauê Castello Ferreira
 */
import java.util.ArrayList;

public class AdjMatrix implements Graph{

    private int[][] matrix;

    public AdjMatrix(int nVertex) {
        this.matrix = new int[nVertex][nVertex];
    }
    
    
    
    @Override
    public void setEdge(int ori, int target, int weight) {
        this.matrix[ori][target] = weight;
    }

    @Override
    public ArrayList<Integer> getAdj(int node) {
        
        ArrayList<Integer> adj = new ArrayList<>();
        
        for(int j = 0; j<this.matrix.length; j++){
            if(node != j && this.matrix[node][j] != 0){
             adj.add(j);
            }                
        }
        
        return adj;
    }

    @Override
    public void printGraph() {
        
        int i =0, j;
        for (int[] matrix1 : this.matrix) {  
            j=0;
            for(int matrix2: matrix1){
                if(matrix2>0){
                    System.out.println("Vertex: "+ i + " Para: "+ j+" custo "+matrix2);
                }
                j++;
            }
            i++;
        }
    }

    @Override
    public int getWeight(int fromVertex, int toVertex) {
        return this.matrix[fromVertex][toVertex];
    }
    
    @Override
    public int getVertexNum(){
        return this.matrix.length;
    }
    
}
