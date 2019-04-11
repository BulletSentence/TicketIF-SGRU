/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifma.ticketif.core;

import java.io.File;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Array;
import java.util.Calendar;
import java.util.Formatter;
import java.util.List;

/**
 *
 * @author gerson
 */
public class GerenteLog {
    
    private final String path;
    private File arquivo;
    
    public GerenteLog () {
        
        this.path = "log";
        
        //Logpath
        File diretorioLog = new File(path);
        if(!diretorioLog.exists()) {
            System.out.println("[ERRO] Diretório de Log não encontrado");   
            if(diretorioLog.mkdir()){
                System.out.println("[INFO] Diretório de Log criado");
            }
        }
    }
    
    public String registrarExecao(Exception _execao) {
          
        java.util.Date data = new java.util.Date();
        String nomeClasse = _execao.getClass().getName();
        String errorExcecao = _execao.toString();        
        String dataExecao = Calendar.getInstance().getTime().toString();
        
        String output = "[DATA]: "+dataExecao+"\n"
                        + "[NOME]: "+nomeClasse+"\n"
                        + "[ERRO]: "+errorExcecao+"\n";
        
        
        String pathArquivo = path+"/"+nomeClasse.toUpperCase();
        
        try {
            File arquivoLog = new File(pathArquivo);
            arquivoLog.createNewFile();
            arquivoLog.setWritable(true);
            
            Formatter outputFile = new Formatter(pathArquivo);
            outputFile.format("%s",output);
            outputFile.close();
            
            return pathArquivo;
            
        } catch (Exception e) {
            return null;
        }
    }
}
