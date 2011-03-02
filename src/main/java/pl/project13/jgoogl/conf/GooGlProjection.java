package pl.project13.jgoogl.conf;

/**
 * Date: 1/16/11
 * <p/>
 * Can be used to perform analytics lookup queries on urls.
 * Each method is described in details bellow.
 *
 * @author Konrad Malawski
 */
public enum GooGlProjection {

  /**
   * Use this projection if you want click statistics
   */
  ANALYTICS_CLICKS("ANALYTICS_CLICKS"),

  /**
   * Use this projection if you want stats about referers, browsers and operating systems
   */
  ANALYTICS_TOP_STRINGS("ANALYTICS_TOP_STRINGS"),

  /**
   * Use this projection to get all statistics  (CLICKS + TOP_STRINGS)
   */
  ANALYTICS_FULL("FULL");

  public final String urlParamValue;

  GooGlProjection(String urlParamValue) {
    this.urlParamValue = urlParamValue;
  }
}
