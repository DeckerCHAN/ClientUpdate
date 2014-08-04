package com.decker.clientupdate.core.client;

import com.decker.clientupdate.core.Config;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

/**
 *
 * @author decker
 */
  class FtpClient extends FTPClient implements Client{

    private static FtpClient instance;

    public static FtpClient getInstance() throws IOException {
        if (instance == null) {
            instance = new FtpClient();
        }
        return instance;
    }

    FTPClient client;
    String server;
    String port;
    String path;

    FtpClient() throws IOException {
        this.client = new FTPClient();
        this.server = Config.getConfig(server);
        this.port = "21";
        this.path = Config.getConfig(path);

    }

    private void connectToServer() throws IOException {
        this.client.connect(server, Integer.valueOf(port));
        this.client.login("anonymous", "");
        this.client.enterLocalPassiveMode();
        this.client.setFileType(FTP.BINARY_FILE_TYPE);
        if (this.client.isConnected()) {
            System.out.println(String.format("Successful connected to %s",
                    Config.getConfig("Server")));
        } else {
            System.out.println(String.format(
                    "Unsuccessful connected to %s.....Terminated!",
                    Config.getConfig("Server")));
            return;
        }
        System.out.println("Remote system is " + this.client.getSystemType());
        this.client.changeWorkingDirectory(this.path);
        System.out.println("Changing remote folder.......Current directory is " + this.client.printWorkingDirectory());
    }

    public Boolean checkRemoteExists(String fileName) throws IOException {
        if (!this.client.isConnected()) {
            this.connectToServer();
        }
        FTPFile[] ftpFiles = this.client.listFiles();
        for (FTPFile fTPFile : ftpFiles) {
            if (fTPFile.getName().equals(fileName)) {
                return true;
            }
        }
        return false;
    }

    public Boolean downloadFile(String url,String targetPath) throws IOException {
        //Check target file exists
//        File tempFile=new File(putFolder.getAbsolutePath()+"\\"+fileName);
//        if(tempFile.exists()){
//            Files.delete(targetFile.getAbsolutePath());
//        }
//        targetFile.createNewFile();
//        //Check is connect
//        if (!this.client.isConnected()) {
//            this.connectToServer();
//        }
//        //Check remote file exists
//        if (this.checkRemoteExists(fileName)) {
//            OutputStream output = new FileOutputStream(tempFile);
//            this.client.retrieveFile(fileName, output);
//            return true;
//        }
        
        return false;

    }

    @Override
    public InputStream download(String url) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
