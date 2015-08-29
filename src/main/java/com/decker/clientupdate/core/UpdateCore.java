package com.decker.clientupdate.core;

import com.decker.clientupdate.core.client.ClientProxy;
import com.decker.clientupdate.core.client.ClientType;
import com.decker.clientupdate.interactiveUI.ConfigFrame;
import com.decker.clientupdate.interactiveUI.ProgressFrame;
import com.decker.clientupdate.interactiveUI.ReportFrame;
import com.decker.clientupdate.util.GUILauncher;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Path;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.exception.ExceptionUtils;

/**
 *
 * @author decker
 */
public final class UpdateCore {

    public static UpdateCore instance;

    public static UpdateCore getInstance() throws IOException {
        if (instance == null) {
            instance = new UpdateCore();
        }
        return instance;
    }

    private final File tempFolder;
    private final File currentFolder;
    private ProgressFrame progressFrame;

    public File getTempFolder() {
        return this.tempFolder;
    }

    public Path getCurrentFolderPath() {
        return this.currentFolder.toPath();
    }

    private UpdateCore() throws IOException {
        //Get current work folder
        this.currentFolder = new File(new File(URLDecoder.decode(UpdateCore.class.getProtectionDomain().getCodeSource().getLocation().getPath(), "UTF-8")).getParentFile().getCanonicalPath());
        //Generate temp folder
        tempFolder = this.currentFolder.toPath().resolve("temp").toFile();
        if (tempFolder.exists()) {
            this.removeTempFolder();
            tempFolder.mkdir();
        } else {
            tempFolder.mkdir();

        }
    }

    public void process() throws Exception {
        String updateResultReport = null;
        try {
            //Receive config if ilist is empty 
            ConfigFrame configFrame = new ConfigFrame();
            if (Config.haveSuchConfig("ilist")) {
                configFrame.setIListUrl(Config.getConfig("ilist"));
            }
            GUILauncher.Run(configFrame);
            if (configFrame.getIListUrl() == null || configFrame.getIListUrl().length() <= 0) {
                throw new IllegalArgumentException("Empty input");
            } else {
                Config.setConfig("ilist", configFrame.getIListUrl());
            }
            Config.saveAllConfigToFile();
            configFrame.dispose();

            //pop up progress window
            progressFrame = new ProgressFrame();
            GUILauncher.Launch(progressFrame);
            String[] instructionList = new ClientProxy(ClientType.HttpClient).receiveToString(Config.getConfig("ilist")).split("\n");
            for (int i = 0; i < instructionList.length; i++) {
                String instruction = instructionList[i];
                String executeResult = this.executeInstruction(instruction);
                this.progressFrame.setStatus(executeResult);
                this.progressFrame.setProgress(((double) (i + 1) / (double) instructionList.length));
            }
            this.removeTempFolder();
            this.progressFrame.dispose();
            updateResultReport = String.format("All update finished! %s instructions executed.", String.valueOf(instructionList.length));
        } catch (Exception e) {
            updateResultReport = String.format("At least one deadly error encountered! Please connect to server manager to report this:%s", ExceptionUtils.getStackTrace(e));

        }

        ReportFrame reportFrame = new ReportFrame();
        reportFrame.setReport(updateResultReport);
        GUILauncher.Run(reportFrame);

    }

    public String executeInstruction(String instruction) {

        try {
            return new Executor().execute(instruction);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public void removeTempFolder() throws IOException {
        if (this.tempFolder.exists()) {
            FileUtils.deleteQuietly(this.tempFolder);
        }
    }

}
