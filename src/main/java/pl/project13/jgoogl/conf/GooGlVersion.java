package pl.project13.jgoogl.conf;

/**
 * Date: 1/16/11
 *
 * @author Konrad Malawski
 */
public enum GooGlVersion {
  V1("https://www.googleapis.com/urlshortener/v1/url");

  public final String serviceUrl;

  GooGlVersion(String serviceUrl) {
    this.serviceUrl = serviceUrl;
  }
}
