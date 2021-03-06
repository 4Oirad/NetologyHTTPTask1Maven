import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.List;

public class Main {

    public static ObjectMapper mapper = new ObjectMapper();
    public static final String REMOTE_SERVICE_URI =
            "https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats";

    public static void main(String[] args) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()){
            HttpUriRequest httGet = new HttpGet(REMOTE_SERVICE_URI);
            try (CloseableHttpResponse response = httpClient.execute(httGet)){
                List<Post> posts = mapper.readValue(response.getEntity().getContent(), new TypeReference<List<Post>>() {});
                posts.stream()
                    .filter(x -> x.getUpvotes() != null && x.getUpvotes() > 0)
                    .forEach(System.out::println);
            }
        }
    }
}