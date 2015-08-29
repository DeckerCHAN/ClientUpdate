package com.decker.clientupdate.core.client;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

public class ClientProxy {
    Client client;

    public ClientProxy(ClientType type)
            throws Exception {
        switch (type) {
            case FtpClient:
                this.client = new FtpClient();
                break;
            case HttpClient:
                this.client = new HttpClient();
                break;
            default:
                throw new ClassNotFoundException("Cant find cilent " + type.toString());
        }
    }

    public String receiveToString(String url)
            throws IOException {
        InputStream sourceStream = this.client.download(url);
        StringWriter writer = new StringWriter();
        IOUtils.copy(sourceStream, writer);
        return writer.toString();
    }

    public InputStream receiveToInputStream(String url)
            throws IOException {
        return this.client.download(url);
    }

    public File reveiveToTempFile(String url) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

