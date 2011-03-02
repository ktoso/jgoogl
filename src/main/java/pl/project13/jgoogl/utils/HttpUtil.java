package pl.project13.jgoogl.utils;

import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.AsyncHttpClient.BoundRequestBuilder;
import com.ning.http.client.Response;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static pl.project13.jgoogl.utils.StringUtils.hasText;

/**
 * Date: 1/16/11
 *
 * @author Konrad Malawski
 */
public class HttpUtil {

  private Logger log = Logger.getLogger(getClass());

  private AsyncHttpClient asyncHttpClient = new AsyncHttpClient();

  private final String APP_JSON = "application/json";

  public String POST(String queryUrl, Map<String, String> data) throws IOException, ExecutionException, InterruptedException {

    BoundRequestBuilder requestBuilder = asyncHttpClient.preparePost(queryUrl)
                                                        .addHeader("Content-type", APP_JSON)
                                                        .addHeader("Accept", APP_JSON);

    putParameters(requestBuilder, data);

    log.info(requestBuilder.build().toString());

    Future<Response> responseFuture = requestBuilder.execute();
    Response response = responseFuture.get();

    log.info("Response bbody was: " + response.getResponseBody());

    return response.getResponseBody();
  }

  private void putParameters(BoundRequestBuilder requestBuilder, Map<String, String> data) {
    for (String key : data.keySet()) {
      String value = data.get(key);
      if (hasText(value)) {
        requestBuilder.addParameter(key, value);
      }
    }
  }
}
