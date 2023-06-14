/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caixeiroviajantegenetico.controller;

import caixeiroviajantegenetico.model.Graph;
import caixeiroviajantegenetico.model.Individuo;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Cauê Castello Ferreira
 */
public class ManipulaIndividuo {

    Random aleatorio;

    public ManipulaIndividuo() {

        aleatorio = new Random(System.currentTimeMillis());

    }

    public void printarIndividuo(Individuo ind) {
        for (Integer gene : ind.getGenes()) {
            System.out.print(gene + "\t");
        }
        System.out.print("\n");
    }

    public void printarPopulacao(ArrayList<Individuo> populacao) {

        int i = 0;
        for (Individuo ind : populacao) {
            System.out.println("Invididuo: " + i);
            printarIndividuo(ind);
            i++;
        }

    }

    //Gera um indivíduio
    public void criarIdividuoAleatorio(Individuo ind) {

        for (int i = 0; i < ind.getTamanho(); i++) {

            int posicao = 0;

            do {
                posicao = aleatorio.nextInt(ind.getTamanho());

            } while (ind.getGenes()[posicao] != -1);

            ind.setGenes(posicao, i);

        }

    }

    public void criarPopulacao(ArrayList<Individuo> populacao, int nIndividuos, int nGens) {

        for (int i = 0; i <= nIndividuos; i++) {
            Individuo individuo = new Individuo(nGens);

            criarIdividuoAleatorio(individuo);

            /* System.out.println("Individuo: "+ i);
            individuo.printaIndividuo();
            System.out.println("\n");*/
            populacao.add(individuo);

        }

    }

    public void mutarIndividuoSwap(Individuo ind, int nGens, int mutacoes) {
        
        int[] mutate = aleatorio.ints(mutacoes, 0, (nGens - 1)).toArray();        
        
        int i = 0;

        int aux = 0;

        do {

            aux = ind.getGenes()[mutate[i]];
            ind.setGenes(mutate[i], ind.getGenes()[mutate[i + 1]]);
            ind.setGenes(mutate[i + 1], aux);

            i = i + 2;

        } while (i < mutate.length);

    }

    public void mutarPopulacao(ArrayList<Individuo> populacao, int nGens, int nMutacoes, int tipo) {

        switch (tipo) {
            case 0:
                populacao.forEach((ind) -> {
                    mutarIndividuoSwap(ind, nGens, nMutacoes);
                });
                break;
            default:
                System.out.println("Não houve mutação");
                break;
        }
    }

    public ArrayList<Individuo> selecionaPopulacao(ArrayList<Individuo> populacao, Graph grafo, int nGens, double taxa) {

        ArrayList<Individuo> selecao = new ArrayList<>();

        boolean visitado[] = new boolean[nGens];
                        
        populacao.forEach((ind) -> {            
            int peso = grafo.getWeight(ind.getGenes()[ind.getTamanho() - 1], ind.getGenes()[0]);

            for(int num = 0; num < visitado.length; num++){
                visitado[num] = false;
            }                        
            
            visitado[ind.getGenes()[0]] = true;

            for (int i = 0; i <= ind.getGenes().length - 2; i++) {

                if (grafo.getWeight(ind.getGenes()[i], ind.getGenes()[i + 1]) > 0) {
                    visitado[ind.getGenes()[i + 1]] = true;
                    peso += grafo.getWeight(ind.getGenes()[i], ind.getGenes()[i + 1]);
                }
            }

            ind.setFitness(peso);

            if (ind.getTamanho() == chegou(visitado)) {
                if (selecao.size() < taxa) {

                    selecao.add(ind);
                } else {

                    for (int l = 0; l < selecao.size(); l++) {

                        if (selecao.get(l).getFitness() > ind.getFitness()) {

                            selecao.remove(l);
                            selecao.add(ind);
                            break;
                        }
                    }
                }
            }

        });

        return selecao;
    }

    public Individuo cruzamento(Individuo pai, Individuo mae) {
        Individuo filho = new Individuo(pai.getTamanho());

        for (int i = 0; i < pai.getGenes().length; i++) {

            if (i == pai.getGenes().length / 2) {

                filho.setGenes(i, pai.getGenes()[i]);
            } else {

                filho.setGenes(i, mae.getGenes()[i]);
            }
        }

        return filho;
    }

    public void cruzaPopulacao(ArrayList<Individuo> populacao) {

        int popInicial = populacao.size();

        for (int i = 0; i < popInicial; i = i + 2) {

            populacao.add(cruzamento(populacao.get(i), populacao.get(+1)));

        }
    }

    private static int chegou(boolean v[]) {

        int i = 0;

        for (boolean n : v) {
            if (n) {
                i++;
            }
        }
        return i;
    }

}
