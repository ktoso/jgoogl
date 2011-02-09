package pl.project13.jgoogl.response.v1;

import java.util.List;

/**
 * Date: 1/16/11
 * <pre>
 * "allTime": {
 *   "shortUrlClicks": "3227",
 *   "longUrlClicks": "9358",
 *   "referrers": [ { "count": "2160", "id": "Unknown/empty" }, ... ],
 *   "countries": [ { "count": "1022", "id": "US" } , ... ],
 *   "browsers": [ { "count": "1025", "id": "Firefox" }, ... ],
 *   "platforms": [ { "count": "2278", "id": "Windows" }, ... ]
 * }
 * </pre>
 *
 * @author Konrad Malawski
 */
public class GooGlAnalytics {
  Long shortUrlClicks;
  Long longUrlClicks;
  List<CountAndId> referrers;
  List<CountAndId> countries;
  List<CountAndId> browsers;
  List<CountAndId> platforms;

  public Long getShortUrlClicks() {
    return shortUrlClicks;
  }

  public void setShortUrlClicks(Long shortUrlClicks) {
    this.shortUrlClicks = shortUrlClicks;
  }

  public Long getLongUrlClicks() {
    return longUrlClicks;
  }

  public void setLongUrlClicks(Long longUrlClicks) {
    this.longUrlClicks = longUrlClicks;
  }

  public List<CountAndId> getReferrers() {
    return referrers;
  }

  public void setReferrers(List<CountAndId> referrers) {
    this.referrers = referrers;
  }

  public List<CountAndId> getCountries() {
    return countries;
  }

  public void setCountries(List<CountAndId> countries) {
    this.countries = countries;
  }

  public List<CountAndId> getBrowsers() {
    return browsers;
  }

  public void setBrowsers(List<CountAndId> browsers) {
    this.browsers = browsers;
  }

  public List<CountAndId> getPlatforms() {
    return platforms;
  }

  public void setPlatforms(List<CountAndId> platforms) {
    this.platforms = platforms;
  }
}
