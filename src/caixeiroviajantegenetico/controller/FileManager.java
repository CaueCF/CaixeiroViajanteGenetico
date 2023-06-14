/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caixeiroviajantegenetico.controller;

import caixeiroviajantegenetico.model.AdjMatrix;
import caixeiroviajantegenetico.model.Graph;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Douglas
 */
public class FileManager {
    
    public ArrayList<String> stringReader (String path){ 
        BufferedReader buffRead = null;
        try {
            buffRead = new BufferedReader(new FileReader(path));
            ArrayList<String> text = new ArrayList<>();
            String line = buffRead.readLine();
            while (line != null) {
                text.add(line);
                line = buffRead.readLine();
            }   buffRead.close();
            return text;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            try {
                buffRead.close();
            } catch (IOException ex) {
                Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }   
    
    public ArrayList<String> stringReaderWithoutHeader (String path){ 
        BufferedReader buffRead = null;
        try {
            buffRead = new BufferedReader(new FileReader(path));
            ArrayList<String> text = new ArrayList<>();
            String line = buffRead.readLine();
            line = buffRead.readLine();
            while (line != null) {
                text.add(line);
                line = buffRead.readLine();
            }   buffRead.close();
            return text;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            try {
                buffRead.close();
            } catch (IOException ex) {
                Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }    
    
    public void writer (String path, String text){ 
        BufferedWriter buffWrite = null; 
        try {
            buffWrite = new BufferedWriter(new FileWriter(path));
            buffWrite.append(text);
            buffWrite.close();
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                buffWrite.close();
            } catch (IOException ex) {
                Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    } 
    
    public void writerAppend (String path, ArrayList<StringBuilder> data) {
        
        BufferedWriter buffWrite = null;
        try {
            StringBuilder temp = new StringBuilder();
            for (StringBuilder line : data) {
                temp.append(line).append("\n");
            }   buffWrite = new BufferedWriter(new FileWriter(path, true));
            buffWrite.append(temp.toString());
            buffWrite.close();
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                buffWrite.close();
            } catch (IOException ex) {
                Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    }
    
    public void writerAppend (String path, String text){ 
        BufferedWriter buffWrite = null; 
        try {
            buffWrite = new BufferedWriter(new FileWriter(path, true));
            buffWrite.append(text);
            buffWrite.close();
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                buffWrite.close();
            } catch (IOException ex) {
                Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }    
    
    public Graph carregaGrafo(int tamanho){
        FileManager fileManager = new FileManager();
        ArrayList<String> text = fileManager.stringReader("./data/Avaliacao.txt");

        int nVertex;
        Graph graph = new AdjMatrix(0);

        //int pesoTotal = 0;
        nVertex = tamanho;//Integer.parseInt(JOptionPane.showInputDialog("Insira o número de nós"));

        for (int i = 0; i < nVertex; i++) {

            String line = text.get(i);

            if (i == 0) {

                //Integer.parseInt(line.trim());
                graph = new AdjMatrix(nVertex);
            } else {

                int oriVertex = Integer.parseInt(line.split(" ")[0]);
                String splits[] = line.substring(line.indexOf(" "), line.length()).split(";");

                int y = 0;
               
                
                for (String part : splits) {

                    if (y < nVertex - 1) {
                        
                        if(i>178){
                            if(y>140){
                                y = y;
                            }                            
                        }
                        
                        String edgeData[] = part.split("-");
                        int targetVertex = Integer.parseInt(edgeData[0].trim());
                        int weight = Integer.parseInt(edgeData[1]);

                        /*
                            ADICIONAR A ARESTA À REPRESENTAÇÃO
                         */
                        graph.setEdge(oriVertex, targetVertex, weight);
                        graph.setEdge(targetVertex, oriVertex, weight);
                    } else {
                        break;
                    }
                    y++;
                }
            }
        }
        
        return graph;
    }
    
}
