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
public class Individuo {

    private int[] genes;
    
    private final int tamanho;

    private int fitness;
    
    public Individuo(int tamanho) {
        this.fitness = 0;
        this.tamanho = tamanho;
        this.genes = new int[tamanho];
        
        for(int i =0; i<tamanho; i++){
            genes[i] = -1;
        }
        
    }

    public int[] getGenes() {
        return genes;
    }

    public int getTamanho() {
        return tamanho;
    }                    

    public void setGenes(int pos, int gen) {
        this.genes[pos] = gen;
    }
    
    public void setFitness(int n){
        this.fitness = n;
    }
    
    public int getFitness(){
        return fitness;
    }
    
    public String printaIndividuo(){
        String saida = "caminho [";
        
        for(int i: getGenes()){
            saida += i+" ";
        }
        
        saida+= "] \nCom peso: "+ getFitness();
        
        return saida;
    }
        
}
