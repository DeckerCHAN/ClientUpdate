package com.decker.clientupdate.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;

/**
 *
 * @author decker
 */
public class Executor {

    File workFolder;

    public Executor()  {

    }

    public String execute(String instruction) throws Exception {

        //parameter 0:command
        //parameter 1:target file
        //parameter 2:url
        String[] parameters = instruction.split(" ");
        File targetFile=new File(parameters[1]);
        switch (parameters[0]) {
            case "get": {

//                Boolean conclusion;
//                conclusion = NetworkClient.getInstance().downloadFileToTemp(parameters[1]);
//                if (conclusion) {
//                    Files.copy(UpdateCore.getInstance().getTempFolder().getAbsolutePath()+"\\"+parameters[1], null);
//                    return String.format("[Success] Download file %s and put to %s", parameters[1], parameters[2]);
//                } else {
//                    return String.format("[Fault]Cant download file %s and put to %s", parameters[1], parameters[2]);
//                }
                

            }
            case "rm": {
                if(targetFile.exists())
                {
                    Files.delete(targetFile.toPath());
                }
                else
                {
                    throw new FileNotFoundException("Can not find file "+targetFile.getName());
                }
                break;
            }
            default: {
                return String.format("[Fault]Cant find command\"%s\"", parameters[0]);
            }
        }
        return null;
    }
}
