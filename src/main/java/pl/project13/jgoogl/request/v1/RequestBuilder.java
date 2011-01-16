package pl.project13.jgoogl.request.v1;

import com.google.gson.Gson;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.AsyncHttpClient.BoundRequestBuilder;
import com.ning.http.client.Response;
import pl.project13.jgoogl.conf.GooGlVersion;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static com.google.common.collect.Maps.newHashMap;

/**
 * Date: 1/16/11
 *
 * @author Konrad Malawski
 */
public class RequestBuilder {

  private AsyncHttpClient asyncHttpClient;
  private Gson            gson;

  private final String APP_JSON  = "application/json";
  private final String API_KEY   = "key";
  private final String SHORT_URL = "shortUrl";
  private final String LONG_URL  = "longUrl";

  private GooGlVersion        version = GooGlVersion.V1;
  private Map<String, String> params  = newHashMap();

  public RequestBuilder(AsyncHttpClient asyncHttpClient, Gson gson) {
    this.asyncHttpClient = asyncHttpClient;
    this.gson = gson;
  }

  public RequestBuilder apiKey(String apiKey) {
    params.put(API_KEY, apiKey);
    return this;
  }

  public RequestBuilder shorten(String url) {
    params.remove(SHORT_URL);
    params.put(LONG_URL, url);
    return this;
  }

  public String execute() throws IOException, ExecutionException, InterruptedException {
    BoundRequestBuilder builder = prepareBuilder();

    Future<Response> futureResponse = builder.execute();
    Response response = futureResponse.get();

    return response.getResponseBody();
  }

  private BoundRequestBuilder prepareBuilder() {
    BoundRequestBuilder builder;

    if (shouldPost()) {
      builder = asyncHttpClient.preparePost(version.serviceUrl)
                               .setBody(gson.toJson(params));
    } else {
      builder = asyncHttpClient.prepareGet(version.serviceUrl);
      addQueryParams(builder);
    }

    builder.addHeader("Content-type", APP_JSON)
           .addHeader("Accept", APP_JSON);

    return builder;
  }

  private void addQueryParams(BoundRequestBuilder builder) {
    for (String key : params.keySet()) {
      String value = params.get(key);
      builder.addQueryParameter(key, value);
    }
  }

  private boolean shouldPost() {
    return params.containsKey(LONG_URL); //todo not clean intention, make this better code
  }


  public RequestBuilder expand(String shortUrl) {
    params.remove(LONG_URL);
    params.put(SHORT_URL, shortUrl);

    return this;
  }
}
