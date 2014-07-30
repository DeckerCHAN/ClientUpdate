/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.decker.clientupdate.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
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
    private HashMap<String, String> config;
    private File ymlFile;

    private void saveTofFile() throws IOException {
        String stringToFile = (String) (new Yaml().dump(this.config));
        FileUtils.writeStringToFile(ymlFile, stringToFile);
    }

    private Config() throws Exception {
        this.config = new HashMap<>();
        this.ymlFile = new File("config.yml");
        if (!ymlFile.exists()) {
            ymlFile.createNewFile();
            this.config.put("server", null);
            this.config.put("path", null);
        } else {
            String fileContent = FileUtils.readFileToString(ymlFile);
            this.config = (HashMap<String, String>) (new Yaml().load(fileContent));
        }

    }
}
