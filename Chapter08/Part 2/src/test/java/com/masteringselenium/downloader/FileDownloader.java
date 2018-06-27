package com.masteringselenium.downloader;

import com.masteringselenium.downloader.RequestType;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.protocol.BasicHttpContext;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;
import java.util.Set;

public class FileDownloader {

    private RequestType httpRequestMethod = RequestType.GET;
    private URI fileURI;
    private List<NameValuePair> urlParameters;
private RemoteWebDriver driver;

public FileDownloader(RemoteWebDriver driverObject) {
    this.driver = driverObject;
}

    public void setHTTPRequestMethod(RequestType requestType) {
        httpRequestMethod = requestType;
    }

    public void setURLParameters(List<NameValuePair> urlParameters) {
        this.urlParameters = urlParameters;
    }

    public void setURI(URI linkToFile) throws MalformedURLException {
        fileURI = linkToFile;
    }

private String getWebDriverUserAgent() {
    return driver.executeScript("return navigator.userAgent").toString();
}

    private BasicCookieStore getWebDriverCookies(Set<Cookie> seleniumCookieSet) {
        BasicCookieStore copyOfWebDriverCookieStore = new BasicCookieStore();
        for (Cookie seleniumCookie : seleniumCookieSet) {
            BasicClientCookie duplicateCookie = new BasicClientCookie(seleniumCookie.getName(), seleniumCookie.getValue());
            duplicateCookie.setDomain(seleniumCookie.getDomain());
            duplicateCookie.setSecure(seleniumCookie.isSecure());
            duplicateCookie.setExpiryDate(seleniumCookie.getExpiry());
            duplicateCookie.setPath(seleniumCookie.getPath());
            copyOfWebDriverCookieStore.addCookie(duplicateCookie);
        }

        return copyOfWebDriverCookieStore;
    }

    private HttpResponse makeHTTPConnection() throws IOException, NullPointerException {
        if (fileURI == null) throw new NullPointerException("No file URI specified");

        HttpClient client = HttpClientBuilder.create().build();

        HttpRequestBase requestMethod = httpRequestMethod.getRequestMethod();
        requestMethod.setURI(fileURI);

        BasicHttpContext localContext = new BasicHttpContext();

        localContext.setAttribute(HttpClientContext.COOKIE_STORE, getWebDriverCookies(driver.manage().getCookies()));
        requestMethod.setHeader("User-Agent", getWebDriverUserAgent());

        if (null != urlParameters && (
                httpRequestMethod.equals(RequestType.PATCH) ||
                        httpRequestMethod.equals(RequestType.POST) ||
                        httpRequestMethod.equals(RequestType.PUT))
                ) {
            ((HttpEntityEnclosingRequestBase) requestMethod).setEntity(new UrlEncodedFormEntity(urlParameters));
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