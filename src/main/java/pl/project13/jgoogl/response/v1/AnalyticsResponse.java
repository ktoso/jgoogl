package pl.project13.jgoogl.response.v1;

import com.google.gson.annotations.SerializedName;
import pl.project13.jgoogl.response.v1.GooGlAnalytics;
import pl.project13.jgoogl.response.v1.GooGlResponse;
import pl.project13.jgoogl.response.v1.enums.GooGlStatus;

import java.util.Date;

/**
 * Date: 1/16/11
 * <p/>
 * <pre>
 * {
 *   "kind": "urlshortener#url",
 *   "id": "http://goo.gl/fbsS",
 *   "longUrl": "http://www.google.com/",
 *   "status": "OK",
 *   "created": "2009-12-13T07:22:55.000+00:00",
 *   "analytics": {
 *    "allTime": {
 *     "shortUrlClicks": "3227",
 *     "longUrlClicks": "9358",
 *     "referrers": [ { "count": "2160", "id": "Unknown/empty" }, ... ],
 *     "countries": [ { "count": "1022", "id": "US" } , ... ],
 *     "browsers": [ { "count": "1025", "id": "Firefox" }, ... ],
 *     "platforms": [ { "count": "2278", "id": "Windows" }, ... ]
 *    },
 *    "month": { ... },
 *    "week": { ... },
 *    "day": { ... },
 *    "twoHours": { ... }
 *   }
 * }
 * </pre>
 *
 * @author Konrad Malawski
 */
public class AnalyticsResponse extends GooGlResponse {
  @SerializedName("id")
  private String      shortUrl;
  private String      longUrl;
  private GooGlStatus status;
  private String      kind;
  @SerializedName("created")
  private Date        createdAt;

  private GooGlAnalytics analytics;

  public AnalyticsResponse() {
  }


}
