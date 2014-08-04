package com.decker.clientupdate.core.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author decker
 */
public interface Client {


     InputStream download(String url) throws IOException;

}
