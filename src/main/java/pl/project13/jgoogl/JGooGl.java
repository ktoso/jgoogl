package pl.project13.jgoogl;

import com.google.gson.Gson;
import com.ning.http.client.AsyncHttpClient;
import pl.project13.jgoogl.conf.GooGlProjection;
import pl.project13.jgoogl.conf.GooGlVersion;
import pl.project13.jgoogl.exceptions.InvalidGooGlUrlException;
import pl.project13.jgoogl.gson.GooGlGsonProvider;
import pl.project13.jgoogl.request.v1.RequestBuilder;
import pl.project13.jgoogl.response.v1.AnalyticsResponse;
import pl.project13.jgoogl.response.v1.ExpandResponse;
import pl.project13.jgoogl.response.v1.ShortenResponse;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

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
  private GooGlProjection projection = GooGlProjection.ANALYTICS_CLICKS;

  private JGooGl() {
    this(GooGlGsonProvider.get(), new AsyncHttpClient());
    requestBuilder = new RequestBuilder(asyncHttpClient, gson);
  }

  private JGooGl(Gson gson, AsyncHttpClient asyncHttpClient) {
    this.gson = gson;
    this.asyncHttpClient = asyncHttpClient;
  }

  private JGooGl(String apiKey) {
    this();
    this.apiKey = apiKey;
  }

  public JGooGl addKey(String key) {
    this.apiKey = key;
    return this;
  }

  public JGooGl removeKey() {
    this.apiKey = null;
    return this;
  }

  public JGooGl onVersion(GooGlVersion version) {
    this.apiVersion = version;
    return this;
  }

  public static JGooGl withKey(String key) {
    return new JGooGl(key);
  }

  public static JGooGl withoutKey() {
    return new JGooGl();
  }

  public ShortenResponse shorten(String longUrl) throws IOException, ExecutionException, InterruptedException {
    String responseBody = requestBuilder.apiKey(apiKey)
                                        .apiVersion(apiVersion)
                                        .projection(projection)
                                        .shorten(longUrl)
                                        .execute();

    return gson.fromJson(responseBody, ShortenResponse.class);
  }

  public ExpandResponse expand(String shortUrl) throws IOException, ExecutionException, InterruptedException {
    throwIfNotGooGlUrl(shortUrl);

    String responseBody = requestBuilder.apiKey(apiKey)
                                        .projection(projection)
                                        .expand(shortUrl)
                                        .execute();

    return parseExpandResponse(responseBody);
  }

  private ExpandResponse parseExpandResponse(String responseBody) {
    if (projection == GooGlProjection.ANALYTICS_CLICKS) {
      return gson.fromJson(responseBody, ExpandResponse.class);
    } else {
      return gson.fromJson(responseBody, AnalyticsResponse.class);
    }
  }

  public JGooGl withAnalytics(GooGlProjection projection) {
    this.projection = projection;
    return this;
  }

  public AnalyticsResponse analyticsFor(String shortUrl) throws IOException, ExecutionException, InterruptedException {
    return analyticsFor(shortUrl, GooGlProjection.ANALYTICS_FULL);
  }

  public AnalyticsResponse analyticsFor(String shortUrl, GooGlProjection projection) throws IOException, ExecutionException, InterruptedException {
    withAnalytics(projection);
    return (AnalyticsResponse) expand(shortUrl);
  }

  private void throwIfNotGooGlUrl(String url) {
    if (!(url.startsWith("goo.gl/") || url.startsWith("http://www.goo.gl/") || url.startsWith("http://goo.gl/"))) {
      throw new InvalidGooGlUrlException("It seems that the url: '" + url + "");
    }
  }

}
