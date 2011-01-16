package pl.project13.jgoogl;

import com.google.common.annotations.VisibleForTesting;
import com.google.gson.Gson;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;
import pl.project13.jgoogl.conf.GooGlProjection;
import pl.project13.jgoogl.conf.GooGlVersion;
import pl.project13.jgoogl.request.v1.RequestBuilder;
import pl.project13.jgoogl.response.v1.GooGlResponse;
import pl.project13.jgoogl.response.v1.ShortenResponse;

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
public class JGooGl {

  private Gson            gson            = new Gson();
  private AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
  private RequestBuilder  requestBuilder  = new RequestBuilder(asyncHttpClient);

  private GooGlVersion    apiVersion = GooGlVersion.V1;
  private String          apiKey     = "";
  private GooGlProjection projection = GooGlProjection.ANALYTICS_NONE;


  public JGooGl() {
  }

  public JGooGl(String apiKey) {
    this.apiKey = apiKey;
  }

  public JGooGl(GooGlVersion apiVersion) {
    this.apiVersion = apiVersion;
  }

  public JGooGl(GooGlVersion apiVersion, String apiKey) {
    this.apiVersion = apiVersion;
    this.apiKey = apiKey;
  }

  public ShortenResponse shorten(String url) throws IOException, ExecutionException, InterruptedException {


    Future<Response> futureResponse =     requestBuilder.apiKey(apiKey)
                  .shorten(url)
                  .execute();

    Response response = futureResponse.get();
    ShortenResponse gooGlResponse = parseResponse(response.getResponseBody());

    return gooGlResponse;
  }

  @VisibleForTesting
  ShortenResponse parseResponse(String responseString) {
    ShortenResponse response = gson.fromJson(responseString, ShortenResponse.class);
    return response;
  }

  // noise

  public GooGlVersion getApiVersion() {
    return apiVersion;
  }

  public void setApiVersion(GooGlVersion apiVersion) {
    this.apiVersion = apiVersion;
  }

  public String getApiKey() {
    return apiKey;
  }

  public void setApiKey(String apiKey) {
    this.apiKey = apiKey;
  }

  public GooGlProjection getProjection() {
    return projection;
  }

  public void setProjection(GooGlProjection projection) {
    this.projection = projection;
  }
}
