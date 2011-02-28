package pl.project13.jgoogl.conf;

/**
 * Determine which GooGl version to use, they might have different URLs and API
 *
 * @author Konrad Malawski
 */
public enum GooGlVersion {
  /**
   * First and currently only version of GooGl
   */
  V1("https://www.googleapis.com/urlshortener/v1/url");

  public final String serviceUrl;

  GooGlVersion(String serviceUrl) {
    this.serviceUrl = serviceUrl;
  }
}
