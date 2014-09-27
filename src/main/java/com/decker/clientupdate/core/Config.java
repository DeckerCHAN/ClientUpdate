package com.decker.clientupdate.core;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import org.apache.commons.io.FileUtils;
import org.yaml.snakeyaml.Yaml;

/**
 *
 * @author decker
 */
public class Config {

    private static Config instance;

    public static Config getInstance() {
        if (instance == null) {
            try {
                instance = new Config();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return null;
            }
        }
        return instance;
    }

    
    public static String getConfig(String configName) {
        return getInstance().config.get(configName);
    }

    public static void setConfig(String configName, String value) {
        getInstance().config.put(configName, value);
    }

    public static void saveAllConfigToFile() throws IOException {
        getInstance().saveTofFile();
    }
    
    public static Boolean haveSuchConfig(String configName)
    {
       String val= getInstance().config.get(configName);
       return !(val==null||val.length()<=0);
    }
    
    private HashMap<String, String> config;
    private File ymlFile;

    private void saveTofFile() throws IOException {
        String stringToFile = (String) (new Yaml().dump(this.config));
        FileUtils.writeStringToFile(ymlFile, stringToFile);
    }
    
    public  Boolean checkComplete()
    {
        for (String conf : this.config.values()) {
            if(conf==null||conf.length()<=0)
            {
                return false;
            }
        }
        return true;
    }
    
    private Config() throws Exception {
        this.config = new HashMap<>();
        this.ymlFile = UpdateCore.getInstance().getCurrentFolderPath().resolve("config.yml").toFile();
        if (!ymlFile.exists()) {
            ymlFile.createNewFile();
        } else {
            String fileContent = FileUtils.readFileToString(ymlFile);
            this.config = (HashMap<String, String>) (new Yaml().load(fileContent));
            if(this.config==null)
            {
                this.config=new HashMap<>();
            }
        }

    }
}
