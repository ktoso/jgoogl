package pl.project13.jgoogl.request.v1;

import com.google.common.base.Joiner;
import com.google.gson.Gson;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.AsyncHttpClient.BoundRequestBuilder;
import com.ning.http.client.Response;
import org.apache.log4j.Logger;
import pl.project13.jgoogl.conf.GooGlProjection;
import pl.project13.jgoogl.conf.GooGlVersion;
import pl.project13.jgoogl.exceptions.GooGlResponseFailureException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static com.google.common.collect.Maps.newHashMap;
import static pl.project13.jgoogl.utils.StringUtils.hasText;

/**
 * Date: 1/16/11
 *
 * @author Konrad Malawski
 */
public class RequestBuilder {

  private Logger log = Logger.getLogger(getClass());

  private AsyncHttpClient asyncHttpClient;
  private Gson            gson;

  private final String APP_JSON   = "application/json";
  private final String API_KEY    = "key";
  private final String SHORT_URL  = "shortUrl";
  private final String LONG_URL   = "longUrl";
  private final String PROJECTION = "projection";

  private GooGlVersion        version    = GooGlVersion.V1;
  private GooGlProjection     projection = GooGlProjection.ANALYTICS_CLICKS;
  private Map<String, String> params     = newHashMap();

  public RequestBuilder(AsyncHttpClient asyncHttpClient, Gson gson) {
    this.asyncHttpClient = asyncHttpClient;
    this.gson = gson;
  }

  public RequestBuilder apiKey(String apiKey) {
    if (!hasText(apiKey)) {
      params.remove(API_KEY);
    } else {
      params.put(API_KEY, apiKey);
    }
    return this;
  }

  public RequestBuilder shorten(String longUrl) {
    params.remove(SHORT_URL);
    params.put(LONG_URL, urlEncode(longUrl));
    return this;
  }

  public RequestBuilder expand(String shortUrl) {
    params.remove(LONG_URL);
    params.put(SHORT_URL, urlEncode(shortUrl));

    return this;
  }

  public RequestBuilder projection(GooGlProjection projection) {
    this.projection = projection;
    return this;
  }

  public RequestBuilder apiVersion(GooGlVersion apiVersion) {
    this.version = apiVersion;
    return this;
  }

  public String execute() throws IOException, ExecutionException, InterruptedException {
    BoundRequestBuilder builder = prepareBuilder();

    log.info("Calling: " + builder.build().toString());
    Future<Response> futureResponse = builder.execute();
    Response response = futureResponse.get();

    String responseBody = response.getResponseBody();
    if (!hasText(responseBody)) {
      throw new GooGlResponseFailureException("Got empty response", response);
    }
    return responseBody;
  }

  private BoundRequestBuilder prepareBuilder() {
    BoundRequestBuilder builder;

    if (shouldPost()) {
      builder = asyncHttpClient.preparePost(version.serviceUrl)
                               .setBody(gson.toJson(params));
    } else {
      builder = asyncHttpClient.prepareGet(prepareGetUrl(version.serviceUrl));
    }

    builder.addHeader("Content-type", APP_JSON)
           .addHeader("Accept", APP_JSON);

    return builder;
  }

  private String prepareGetUrl(String serviceUrl) {
    params.put(PROJECTION, projection.urlParamValue);

    String queryUrl = serviceUrl + "?" + Joiner.on("&").withKeyValueSeparator("=").join(params);

    params = newHashMap();

    return queryUrl;
  }


  private String urlEncode(String value) {
    String encode = value;
    try {
      encode = URLEncoder.encode(value, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      log.error("Could not urlEncode parameter", e);
    }
    return encode;
  }

  private boolean shouldPost() {
    return params.containsKey(LONG_URL); //todo not clean intention, make this better code
  }
}
