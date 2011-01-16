package pl.project13.jgoogl.request.v1;

import com.google.gson.Gson;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.AsyncHttpClient.BoundRequestBuilder;
import com.ning.http.client.Response;
import pl.project13.jgoogl.conf.GooGlVersion;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

import static pl.project13.jgoogl.utils.StringUtils.hasText;

/**
 * Date: 1/16/11
 *
 * @author Konrad Malawski
 */
public class RequestBuilder {
  private final String APP_JSON = "application/json";

  private Map<String, String> params  = new HashMap<String, String>();
  private GooGlVersion        version = GooGlVersion.V1;
  private AsyncHttpClient asyncHttpClient;

  public RequestBuilder(AsyncHttpClient asyncHttpClient) {
    this.asyncHttpClient = asyncHttpClient;
  }

  public RequestBuilder apiKey(String apiKey) {
    params.put("key", apiKey);
    return this;
  }

  public RequestBuilder shorten(String url) {
    params.put("longUrl", url);
    return this;
  }

  public Future<Response> execute() throws IOException {
    BoundRequestBuilder builder = asyncHttpClient.preparePost(version.serviceUrl)
                                                 .addHeader("Content-type", APP_JSON)
                                                 .addHeader("Accept", APP_JSON);

    Gson gson = new Gson();
    builder.setBody(gson.toJson(params));

//    builder.setBody("{");
//    for (String key : params.keySet()) {
//      String value = params.get(key);
//      if (hasText(value)) {
//        new Gson().toJson()
//      }
//    }

    return builder.execute();
  }
}
