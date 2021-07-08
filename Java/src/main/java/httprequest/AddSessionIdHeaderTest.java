package httprequest;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class AddSessionIdHeaderTest {

    public static void main(String[] args) {
        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("AMBARISESSIONID", "19r8gq55q70lxaed5l11jwg83"));
        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultHeaders(headers).build();
        HttpGet httpGet = new HttpGet("http://localhost:8080/api/v1/users");
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
//            Header[] respHeaders = response.getHeaders("Set-Cookie");
//            for(int i = 0 ; i < respHeaders.length; i++) {
//                System.out.println(respHeaders[i]);
//            }
            System.out.println("code: " + response.getStatusLine().getStatusCode());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
