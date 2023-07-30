/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.lutfi.ptjalintestapplication.alert;

import com.csvreader.CsvReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lutfi
 */
public class Alert {
    
    private final String PATH = "input";
    private final String[] ALLOWED_EXT = new String[]{".txt"};
    private final int INDEX_BANK =  0;
    private final int INDEX_ENV =  1;
    private final int INDEX_PORT =  2;
    private final int INDEX_BANK_NAME =  3;
    private final int INDEX_STATUS =  4;
    
    public void  execute(){
        try {
            perform();
        } catch (IOException ex) {
            Logger.getLogger(Alert.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void perform() throws IOException{
        List<String> tListFile = getListFile();
        if(tListFile.size() > 0){
            executeAlertFile(tListFile);
        }else{
            System.out.println("No status found");
        }
        System.out.println("Done!");
    }
    
    private List<String> getListFile(){
        List<String> litsFile = new ArrayList<>();
        File rootPath = new File(PATH);
        
        if(rootPath.exists() && rootPath.isDirectory()){
            File[] fileInRoot = rootPath.listFiles();
            for (File file : fileInRoot) {
                if(file.isFile()){
                    String tFileName = file.getAbsolutePath();
                    if(isAlloweExtention(tFileName)){
                        litsFile.add(tFileName);
                    }
                }
            }
            
            return litsFile;
        }
        
        throw new IllegalArgumentException("Cannot find root directory");
    }
    
    private boolean isAlloweExtention(String fileName){
        String tExt = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        for (String alloedExt : ALLOWED_EXT) {
            if(tExt.equals(alloedExt)){
                return true;
            }
        }
        
        return false;
    }
    
    private void executeAlertFile(List<String> pListFile) throws IOException{
        HashMap<String, List<String>> tListEnvStatus = new HashMap<>();
        for (String fileName : pListFile) {
            readFile(tListEnvStatus, fileName);
        }
        
        tListEnvStatus.keySet().forEach(bank -> {
            createOutput(bank, tListEnvStatus.get(bank));
        });
    }
    
    private void readFile(final HashMap<String, List<String>> pListStatusEnv, String pFileName) throws FileNotFoundException, IOException{
        CsvReader reader = new CsvReader(pFileName, ';', Charset.defaultCharset());
        while (reader.readRecord()) {            
            String tBank = reader.get(INDEX_BANK);
            String tEnv = reader.get(INDEX_ENV);
            String tPort = reader.get(INDEX_PORT);
            String tStatus = reader.get(INDEX_STATUS);
            
            if (tStatus.equalsIgnoreCase("offline")) {
                String tStatusValue = "Envi "+tEnv +" Port "+tPort +" terpantau "+ tStatus;
                addToList(pListStatusEnv, tBank, tStatusValue);
            }
        }
    }
    
    private void addToList(final HashMap<String, List<String>> pListStatusEnv, String pBank, String pValue ){
        if(pListStatusEnv.containsKey(pBank)){
            pListStatusEnv.get(pBank).add(pValue);
        }else{
            List<String> tListValue = new ArrayList<>();
            tListValue.add(pValue);
            pListStatusEnv.put(pBank, tListValue);
        }
    }
    
    private void createOutput(String pBank, List<String> pStatusList){
        System.out.println("Selamat Siang Rekan Bank "+ pBank+",");
        System.out.println();
        System.out.println("Mohon bantuan untuk sign on pada Envi berikut:");
        System.out.println();
        pStatusList.forEach(status -> {
            System.out.println(" -"+ status);
        });
        System.out.println();
        System.out.println("Terima Kasih");
        System.out.println("-----------------------------------------------");
    }
}
