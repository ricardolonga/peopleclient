package br.com.ricardolonga.peopleclient.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;

public class HttpHelper {

    public static String doGet(String url) {
        HttpClient httpclient = new DefaultHttpClient(/* httpParams */);

        try {
            HttpResponse response = httpclient.execute(new HttpGet(url));

            InputStream content = response.getEntity().getContent();

            return IOUtils.toString(content, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    public static void doPost(String url, String json) {
        HttpClient httpclient = new DefaultHttpClient();

        try {
            HttpPost post = new HttpPost(url);

            post.addHeader("Content-Type", "application/json");

            post.setEntity(new StringEntity(json, HTTP.UTF_8));

            httpclient.execute(post);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void doDelete(String url) {
        try {
            new DefaultHttpClient().execute(new HttpDelete(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
