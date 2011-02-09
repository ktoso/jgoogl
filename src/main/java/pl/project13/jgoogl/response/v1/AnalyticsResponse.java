package pl.project13.jgoogl.response.v1;

import com.google.gson.annotations.SerializedName;

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
public class AnalyticsResponse extends ExpandResponse {
  @SerializedName("created")
  private Date         createdAt;
  private AnalyticsMap analytics;

  public AnalyticsResponse() {
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public AnalyticsMap getAnalytics() {
    return analytics;
  }

  public void setAnalytics(AnalyticsMap analytics) {
    this.analytics = analytics;
  }

  private static class AnalyticsMap {
    private GooGlAnalytics allTime;
    private GooGlAnalytics month;
    private GooGlAnalytics week;
    private GooGlAnalytics day;
    private GooGlAnalytics twoHours;

    private AnalyticsMap() {
    }

    public GooGlAnalytics getAllTime() {
      return allTime;
    }

    public void setAllTime(GooGlAnalytics allTime) {
      this.allTime = allTime;
    }

    public GooGlAnalytics getMonth() {
      return month;
    }

    public void setMonth(GooGlAnalytics month) {
      this.month = month;
    }

    public GooGlAnalytics getWeek() {
      return week;
    }

    public void setWeek(GooGlAnalytics week) {
      this.week = week;
    }

    public GooGlAnalytics getDay() {
      return day;
    }

    public void setDay(GooGlAnalytics day) {
      this.day = day;
    }

    public GooGlAnalytics getTwoHours() {
      return twoHours;
    }

    public void setTwoHours(GooGlAnalytics twoHours) {
      this.twoHours = twoHours;
    }

    @Override
    public String toString() {
      return "AnalyticsMap{" +
          "allTime=" + allTime +
          ", month=" + month +
          ", week=" + week +
          ", day=" + day +
          ", twoHours=" + twoHours +
          '}';
    }
  }

  @Override
  public String toString() {
    return "AnalyticsResponse{" +
        "createdAt=" + createdAt +
        ", analytics=" + analytics +
        ", shortUrl='" + shortUrl + '\'' +
        ", longUrl='" + longUrl + '\'' +
        ", status='" + status + '\'' +
        ", error=" + error +
        "} " ;
  }
}
