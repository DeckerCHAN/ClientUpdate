package com.decker.clientupdate.core.client;

import java.io.IOException;
import java.io.InputStream;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 *
 * @author decker
 */
final class HttpClient  implements Client {
    public static CloseableHttpClient httpClient;
    public HttpClient() {
        super();
        if(httpClient==null)
        {
            httpClient=HttpClientBuilder.create().build();
        }
    }

    @Override
    public InputStream download(String url) throws IOException{
        
         HttpGet httpget = new HttpGet(url);
         HttpResponse response = httpClient.execute(httpget);
         InputStream is=response.getEntity().getContent();
         return is;
     }


}
