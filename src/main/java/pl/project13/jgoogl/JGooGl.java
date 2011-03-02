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

  private class JGooGlContext {
    String          apiKey     = null;
    GooGlVersion    apiVersion = GooGlVersion.V1;
    GooGlProjection projection = GooGlProjection.ANALYTICS_CLICKS;
  }

  private JGooGlContext defaultContext = new JGooGlContext();
  private JGooGlContext flowContext    = new JGooGlContext();

  /* CONSTRUCTORS */

  private JGooGl(Gson gson, AsyncHttpClient asyncHttpClient) {
    this.gson = gson;
    this.asyncHttpClient = asyncHttpClient;
  }

  private JGooGl() {
    this(GooGlGsonProvider.get(), new AsyncHttpClient());
    requestBuilder = new RequestBuilder(asyncHttpClient, gson);
  }

  private JGooGl(String apiKey) {
    this();
    defaultContext.apiKey = apiKey;
  }

/* FACTORY METHODS */

  public static JGooGl withKey(String key) {
    return new JGooGl(key);
  }

  public static JGooGl withoutKey() {
    return new JGooGl();
  }

  /* TEMPORARY INSTANCE MODIFICATION METHODS */

  public JGooGl onKey(String key) {
    defaultContext.apiKey = key;
    return this;
  }

  public JGooGl onNoKey() {
    defaultContext.apiKey = null;
    return this;
  }

  public JGooGl onVersion(GooGlVersion version) {
    defaultContext.apiVersion = version;
    return this;
  }

  /* INSTANCE METHODS */

  public ShortenResponse shorten(String longUrl) throws IOException, ExecutionException, InterruptedException {
    try {
      RequestBuilder builder = requestBuilder.apiKey(flowContext.apiKey)
                                             .apiVersion(flowContext.apiVersion)
                                             .projection(flowContext.projection)
                                             .shorten(longUrl);
      String responseBody = builder.execute();

      return gson.fromJson(responseBody, ShortenResponse.class);
    } finally {
      clearFlowContext();
    }
  }

  public ExpandResponse expand(String shortUrl) throws IOException, ExecutionException, InterruptedException {
    try {
      throwIfNotGooGlUrl(shortUrl);

      RequestBuilder builder = requestBuilder.apiKey(flowContext.apiKey)
                                             .projection(flowContext.projection)
                                             .expand(shortUrl);
      String responseBody = builder.execute();

      return parseExpandResponse(responseBody);
    } finally {
      clearFlowContext();
    }
  }

  private void clearFlowContext() {
    flowContext.apiKey = defaultContext.apiKey;
    flowContext.apiVersion = defaultContext.apiVersion;
    flowContext.projection = defaultContext.projection;
  }

  private ExpandResponse parseExpandResponse(String responseBody) {
    if (flowContext.projection == GooGlProjection.ANALYTICS_CLICKS) {
      return gson.fromJson(responseBody, ExpandResponse.class);
    } else {
      return gson.fromJson(responseBody, AnalyticsResponse.class);
    }
  }

  public JGooGl withAnalytics(GooGlProjection projection) {
    flowContext.projection = projection;
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
      throw new InvalidGooGlUrlException("It seems that the url: '" + url + "' is invalid...");
    }
  }

  public static class Builder {
    private JGooGl instance = new JGooGl();

    public JGooGl.Builder useSupplied(Gson gson) {
      instance.gson = gson;
      return this;
    }

    public JGooGl.Builder useSupplied(AsyncHttpClient asyncHttpClient) {
      instance.asyncHttpClient = asyncHttpClient;
      return this;
    }

    public JGooGl.Builder useKey(String apiKey) {
      instance.defaultContext.apiKey = apiKey;
      return this;
    }

    public JGooGl.Builder useVersion(GooGlVersion version) {
      instance.defaultContext.apiVersion = version;
      return this;
    }

    public JGooGl.Builder useProjection(GooGlProjection projection) {
      instance.defaultContext.projection = projection;
      return this;
    }

    public JGooGl get() {
      return instance;
    }
  }
}
