package pl.project13.jgoogl;

import com.google.gson.Gson;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;
import pl.project13.jgoogl.conf.GooGlProjection;
import pl.project13.jgoogl.conf.GooGlVersion;
import pl.project13.jgoogl.exceptions.InvalidGooGlUrlException;
import pl.project13.jgoogl.request.v1.RequestBuilder;
import pl.project13.jgoogl.response.v1.ExpandResponse;
import pl.project13.jgoogl.response.v1.ShortenResponse;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.regex.Pattern;

/**
 * Date: 1/16/11
 *
 * @author Konrad Malawski
 */
public class JGooGl {

  private Gson            gson;
  private AsyncHttpClient asyncHttpClient;
  private RequestBuilder  requestBuilder;

  private String          apiKey     = null;
  private GooGlVersion    apiVersion = GooGlVersion.V1;
  private GooGlProjection projection = GooGlProjection.ANALYTICS_NONE;

  public JGooGl() {
    this(new Gson(), new AsyncHttpClient());
    requestBuilder = new RequestBuilder(asyncHttpClient, gson);
  }

  public JGooGl(Gson gson, AsyncHttpClient asyncHttpClient) {
    this.gson = gson;
    this.asyncHttpClient = asyncHttpClient;
  }

  public JGooGl(GooGlVersion apiVersion) {
    this();
    this.apiVersion = apiVersion;
  }

  public JGooGl(String apiKey) {
    this();
    this.apiKey = apiKey;
  }

  public JGooGl(GooGlVersion apiVersion, String apiKey) {
    this();
    this.apiVersion = apiVersion;
    this.apiKey = apiKey;
  }

  public JGooGl(Gson gson, AsyncHttpClient asyncHttpClient, GooGlVersion apiVersion, String apiKey) {
    this(gson, asyncHttpClient);
    this.apiVersion = apiVersion;
    this.apiKey = apiKey;
  }

  public JGooGl withKey(String key) {
    return new JGooGl(gson, asyncHttpClient, apiVersion, key);
  }

  public JGooGl withoutKey() {
    return new JGooGl(gson, asyncHttpClient, apiVersion, null);
  }

  public ShortenResponse shorten(String longUrl) throws IOException, ExecutionException, InterruptedException {
    String responseBody = requestBuilder.apiKey(apiKey).shorten(longUrl).execute();

    return gson.fromJson(responseBody, ShortenResponse.class);
  }

  public ExpandResponse expand(String shortUrl) throws IOException, ExecutionException, InterruptedException {
    throwIfNotGooGlUrl(shortUrl);

    String responseBody = requestBuilder.apiKey(apiKey).expand(shortUrl).execute();

    return gson.fromJson(responseBody, ExpandResponse.class);
  }

  private void throwIfNotGooGlUrl(String url) {
    if(!(url.startsWith("goo.gl/") || url.startsWith("http://www.goo.gl/") || url.startsWith("http://goo.gl/"))){
      throw new InvalidGooGlUrlException("It seems that the url: '" + url + "");
    }
  }

}
