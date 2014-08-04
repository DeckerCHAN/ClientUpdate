package com.decker.clientupdate.core;

import com.decker.clientupdate.core.client.ClientProxy;
import com.decker.clientupdate.core.client.ClientType;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author decker
 */
public class Executor {

    File workFolder;

    public Executor() {

    }

    public String execute(String instruction) throws Exception {

        //parameter 0:command
        //parameter 1:target file
        //parameter 2:url
        String[] parameters = instruction.split(" ");
        File targetFile = UpdateCore.getInstance().getCurrentFolderPath().resolve(parameters[1]).toFile();
        switch (parameters[0]) {
            case "get": {
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
            }
            case "remove": {
                if (targetFile.exists()) {
                    targetFile.delete();
                } else {
                    throw new FileNotFoundException("Can not find file " + targetFile.getName());
                }
                return "[Success] " + instruction;
            }
            default: {
                return String.format("[Fault]Cant find command\"%s\"", parameters[0]);
            }
        }
    }
}
