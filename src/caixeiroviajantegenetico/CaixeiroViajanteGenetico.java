/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caixeiroviajantegenetico;

import caixeiroviajantegenetico.controller.FileManager;
import caixeiroviajantegenetico.controller.ManipulaIndividuo;
import caixeiroviajantegenetico.model.Graph;
import caixeiroviajantegenetico.model.Individuo;
import java.util.ArrayList;

/**
 *
 * @author Caue Castello Ferreira
 */
public class CaixeiroViajanteGenetico {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {               

        // Melhor configuração observada nIndividuos = 200, nGens = 200, nMutacoes = 30, geracoes = 10000, taxa = 30
        
        int nIndividuos = 1000, nGens = 200, nMutacoes = 30, geracoes = 10000, i = 0;
        double taxa = 20;

        boolean correcao = false;
        
        taxa = nIndividuos * (taxa / 100);
        nMutacoes = nGens/nMutacoes;

        if(nMutacoes%2 != 0){
            nMutacoes += 1;
            correcao = true;
        }
        
        /*
        
        GRAFO
        
         */
        FileManager fm = new FileManager();
        Graph grafo = fm.carregaGrafo(nGens);

        /* 
        
        POPULAÇÃO
        
         */
        ManipulaIndividuo mp = new ManipulaIndividuo();
        ArrayList<Individuo> populacao = new ArrayList<>();
        ArrayList<Individuo> top = new ArrayList<>();

        int dataSize = 1024 * 1024;
        Runtime runtime = Runtime.getRuntime();
        
        long startTime = System.currentTimeMillis();  
        
        do {
            if (i == 0) {
                mp.criarPopulacao(populacao, nIndividuos, nGens);
            } else {
                populacao.clear();
                populacao.addAll(top);
                mp.criarPopulacao(populacao, nIndividuos-top.size(), nGens);
            }
            
            /*
        
        CRUZAMENTO
        
             */
            mp.cruzaPopulacao(populacao);

            /*
        
        MUTAÇÃO
        
             */
            //System.out.println("-------- Antes da mutação --------");        
            //mp.printarPopulacao(populacao);
            mp.mutarPopulacao(populacao, nGens, nMutacoes, 0);

            //System.out.println("\n\n-------- Depois da mutação --------");        
            //mp.printarPopulacao(populacao);
            /*
        
        SELEÇÃO
        
             */
            top = mp.selecionaPopulacao(populacao, grafo, nGens, taxa);

            i++;
        } while (i < geracoes);
        
        
        
        
        Individuo bao = top.get(0);
        
        for(Individuo ind: top){
            if(ind.getFitness()<= bao.getFitness()){
                bao = ind;
            }
        }                
        
        if(correcao){
            nMutacoes -= 1;
        }
        
        nMutacoes = nGens/nMutacoes;
        
        //Estatísticas
        System.out.println("Número de indivíduos: "+nIndividuos+"\nNúmero de gerações: "+geracoes+
                "\nTaxa de mutação: "+ nMutacoes+"%"+ "\nTaxa de sobrevivência: " +(taxa*100/nIndividuos)+"%");
        System.out.println("Tempo utilizado: "+ (System.currentTimeMillis()- startTime)+"ms");
        System.out.println ("Memoria usada: " + (runtime.totalMemory() - runtime.freeMemory()) / dataSize + "MB");
        
        System.out.println("O melhor é: "+ bao.printaIndividuo());
        
    }

}
