package com.decker.clientupdate.core;

import com.decker.clientupdate.core.client.ClientProxy;
import com.decker.clientupdate.core.client.ClientType;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;

public class Executor {

    public String execute(String instruction)
            throws Exception {
        String[] parameters = instruction.split(" ");
        File targetFile = UpdateCore.getInstance().getCurrentFolderPath().resolve(java.net.URLDecoder.decode(parameters[1], "UTF-8")).toFile();
        switch (parameters[0]) {
            case "get":
                try {
                    File tempFile = UpdateCore.getInstance().getTempFolder().toPath().resolve(targetFile.getName()).toFile();
                    if (targetFile.exists()) {
                        targetFile.delete();
                    }
                    InputStream fileIS = new ClientProxy(ClientType.HttpClient).receiveToInputStream(parameters[2]);
                    OutputStream fileOS = new FileOutputStream(tempFile);
                    IOUtils.copy(fileIS, fileOS);
                    fileIS.close();
                    fileOS.close();
                    FileUtils.moveFile(tempFile, targetFile);
                    FileUtils.deleteQuietly(tempFile);
                    return "[Success] " + instruction;
                } catch (Exception e) {
                    e.printStackTrace();
                    return "[Fault]" + e.getMessage();
                }
            case "remove":
                if (targetFile.exists()) {
                    targetFile.delete();
                } else {
                    throw new FileNotFoundException("Can not find file " + targetFile.getName());
                }
                return "[Success] " + instruction;
        }
        return String.format("[Fault]Cant find command\"%s\"", new Object[]{parameters[0]});
    }
}

