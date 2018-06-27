package com.masteringselenium.downloader;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.BasicHttpContext;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;

public class FileDownloader {

    private RequestType httpRequestMethod = RequestType.GET;
    private URI fileURI;
    private List<NameValuePair> urlParameters;

    public void setHTTPRequestMethod(RequestType requestType) {
        httpRequestMethod = requestType;
    }

    public void setURLParameters(List<NameValuePair> urlParameters) {
        this.urlParameters = urlParameters;
    }

    public void setURI(URI linkToFile) throws MalformedURLException {
        fileURI = linkToFile;
    }

    private HttpResponse makeHTTPConnection() throws IOException, NullPointerException {
        if (fileURI == null) throw new NullPointerException("No file URI specified");

        HttpClient client = HttpClientBuilder.create().build();

        HttpRequestBase requestMethod = httpRequestMethod.getRequestMethod();
        requestMethod.setURI(fileURI);

        BasicHttpContext localContext = new BasicHttpContext();

        if (null != urlParameters && (
                httpRequestMethod.equals(RequestType.PATCH) ||
                        httpRequestMethod.equals(RequestType.POST) ||
                        httpRequestMethod.equals(RequestType.PUT)
        )) {
            ((HttpEntityEnclosingRequestBase) requestMethod)
                    .setEntity(new UrlEncodedFormEntity(urlParameters));
        }

        return client.execute(requestMethod, localContext);
    }

    public int getLinkHTTPStatus() throws Exception {
        HttpResponse downloadableFile = makeHTTPConnection();
        int httpStatusCode;
        try {
            httpStatusCode = downloadableFile.getStatusLine().getStatusCode();
        } finally {
            if (null != downloadableFile.getEntity()) {
                downloadableFile.getEntity().getContent().close();
            }
        }

        return httpStatusCode;
    }

}