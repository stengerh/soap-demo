package com.github.stengerh.demo.soap.cxf.client.configuration;

import org.springframework.core.io.UrlResource;
import org.springframework.util.Base64Utils;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

class AuthenticatingUrlResource extends UrlResource {
    private final String username;
    private final String password;

    public AuthenticatingUrlResource(URL wsdlUrl, String username, String password) throws MalformedURLException {
        super(wsdlUrl.toString());
        this.username = username;
        this.password = password;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        URLConnection con = this.getURL().openConnection();
        ResourceUtils.useCachesIfNecessary(con);
        addAuthorizationHeader(con);
        try {
            return con.getInputStream();
        } catch (IOException ex) {
            // Close the HTTP connection (if applicable).
            if (con instanceof HttpURLConnection) {
                ((HttpURLConnection) con).disconnect();
            }
            throw ex;
        }
    }

    @Override
    protected void customizeConnection(HttpURLConnection con) throws IOException {
        // Avoid the super method call: it uses HEAD requests against the WSDL which CXF does not support
        //super.customizeConnection(con);
        addAuthorizationHeader(con);
    }

    private void addAuthorizationHeader(URLConnection con) {
        if (con instanceof HttpURLConnection) {
            addAuthorizationHeader((HttpURLConnection) con);
        }
    }

    private void addAuthorizationHeader(HttpURLConnection con) {
        con.setRequestProperty("Authorization", "Basic " + Base64Utils.encodeToString((username + ":" + password).getBytes(StandardCharsets.UTF_8)));
    }
}
