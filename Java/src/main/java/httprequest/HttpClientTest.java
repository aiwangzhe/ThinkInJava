package httprequest;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class HttpClientTest {

    public static void main(String[] args) {
        List<Header> headers = new ArrayList<>();
        Base64.Encoder encoder = Base64.getEncoder();
        String auth = encoder.encodeToString("admin:admin".getBytes());
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJsZW5vdm8iLCJyb2xlIjoxLCJpc3MiOiJodHRwOlwvXC93d3cubGVub3ZvLmNvbSIsImV4cCI6MTYxOTQxODc5OSwidXNlcm5hbWUiOiJhZG1pbiIsInN0YXR1cyI6MSwicGxhdGZvcm1zIjoiMCJ9.NQLjmR4RpYdbAyVWFi7Xl60AyHq0TOe42pZIeuvOAVh6JRdDiqeGub11wFvDv49KabLrhUF553BodDeGNni48UN3kqwRjnl0hzsjr_oF2Y-mKwKlmrqiq2acbF3XRqrR43vqQxwbMTVheXZycv2zi1XyWxWqVPX3GwDwYewJGta6MpiENqlnbuuvmfZ2xk87k2KE-OM-SqGe4mfSvzKWhHWJWuOW0kwjfF-WdnHSZRYDjsc-Viwi1u-1dkj16NrxbFp0cj3C5xu8J26th8tsqVKTAnUJqKh2CclTlZCpKnarorZlcqeuYCOvesJ2JbXvPKZbKEkPeG5kCbFuMtFM3A";
        headers.add(new BasicHeader("cluster-jwt", token));
        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultHeaders(headers).build();
        HttpGet httpGet = new HttpGet("http://localhost:8080/api/v1/users");
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            Header[] respHeaders = response.getHeaders("AMBARISESSIONID");
            for(int i = 0 ; i < respHeaders.length; i++) {
                System.out.println(respHeaders[i]);
            }
            System.out.println("code: " + response.getStatusLine().getStatusCode());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
