/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caixeiroviajantegenetico.model;

import java.util.ArrayList;

/**
 *
 * @author Caue Castello Ferreira
 */
public interface Graph {
    
    public void setEdge(int ori, int target, int weight);
    
    public ArrayList<Integer> getAdj(int node);
    
    public void printGraph();        
    
    public int getWeight(int fromVertex, int toVertex);
    
    public int getVertexNum();
}
