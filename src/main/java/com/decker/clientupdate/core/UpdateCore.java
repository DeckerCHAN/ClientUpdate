package com.decker.clientupdate.core;

import com.decker.clientupdate.core.client.ClientProxy;
import com.decker.clientupdate.core.client.ClientType;
import com.decker.clientupdate.interactiveUI.ConfigFrame;
import com.decker.clientupdate.interactiveUI.ProgressFrame;
import com.decker.clientupdate.util.GUILauncher;
import java.awt.Color;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author decker
 */
public class UpdateCore {

    public static UpdateCore instance;

    public static UpdateCore getInstance() throws IOException {
        if (instance == null) {
            instance = new UpdateCore();
        }
        return instance;
    }

    private File tempFolder;
    ProgressFrame progressFrame;

    public File getTempFolder() {
        return this.tempFolder;
    }

    private UpdateCore() throws IOException {
        //TODO:Generate temp folder
        tempFolder = new File("temp");
        if ((!tempFolder.exists())) {
            tempFolder.mkdir();
        }
    }

    public void process() throws Exception {

        try {
            //Receive config if ilist is empty 
            if (Config.haveSuchConfig("ilist")) {
                ConfigFrame configFrame = new ConfigFrame();
                GUILauncher.Run(configFrame);
                System.out.println("Form finished! Result is" + configFrame.getIListUrl());
                Config.setConfig("ilist", configFrame.getIListUrl());
                Config.saveAllConfigToFile();
                configFrame.dispose();
            }
            progressFrame = new ProgressFrame();
            GUILauncher.Launch(progressFrame);
            String instructionList[] = new ClientProxy(ClientType.HttpClient).receiveToString(Config.getConfig("ilist")).split("\r");
            for (int i = 0; i < instructionList.length; i++) {
                String instruction = instructionList[i];
                this.ExecuteInstruction(instruction);
                this.progressFrame.setProgress((i/instruction.length()));
            }
        } catch (Exception e) {
            this.progressFrame.setStatus(e.getMessage());
        }

    }

    public String ExecuteInstruction(String instruction) {

        try {
            return new Executor().execute(instruction);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public void ClearWorkspace() {
        if (this.tempFolder.exists()) {
            this.tempFolder.delete();
        }
    }

}
