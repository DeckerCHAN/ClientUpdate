package com.decker.clientupdate.core.client;

import com.decker.clientupdate.core.Config;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.IOException;
import java.io.InputStream;

class FtpClient
        extends FTPClient
        implements Client {
    private static FtpClient instance;
    FTPClient client;
    String server;
    String port;
    String path;

    public static FtpClient getInstance()
            throws IOException {
        if (instance == null) {
            instance = new FtpClient();
        }
        return instance;
    }

    FtpClient()
            throws IOException {
        this.client = new FTPClient();
        this.server = Config.getConfig(this.server);
        this.port = "21";
        this.path = Config.getConfig(this.path);
    }

    private void connectToServer()
            throws IOException {
        this.client.connect(this.server, Integer.valueOf(this.port).intValue());
        this.client.login("anonymous", "");
        this.client.enterLocalPassiveMode();
        this.client.setFileType(2);
        if (this.client.isConnected()) {
            System.out.println(String.format("Successful connected to %s", new Object[]{Config.getConfig("Server")}));
        } else {
            System.out.println(String.format("Unsuccessful connected to %s.....Terminated!", new Object[]{Config.getConfig("Server")}));


            return;
        }
        System.out.println("Remote system is " + this.client.getSystemType());
        this.client.changeWorkingDirectory(this.path);
        System.out.println("Changing remote folder.......Current directory is " + this.client.printWorkingDirectory());
    }

    public Boolean checkRemoteExists(String fileName)
            throws IOException {
        if (!this.client.isConnected()) {
            connectToServer();
        }
        FTPFile[] ftpFiles = this.client.listFiles();
        for (FTPFile fTPFile : ftpFiles) {
            if (fTPFile.getName().equals(fileName)) {
                return true;
            }
        }
        return false;
    }

    public Boolean downloadFile(String url, String targetPath)
            throws IOException {
        return false;
    }

    public InputStream download(String url)
            throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}