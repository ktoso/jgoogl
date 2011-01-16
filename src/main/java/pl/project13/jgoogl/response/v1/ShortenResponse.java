package pl.project13.jgoogl.response.v1;

import com.google.gson.annotations.SerializedName;

/**
 * <pre>
 * {
 *   "kind": "urlshortener#url",
 *   "id": "http://goo.gl/fbsS",
 *   "longUrl": "http://www.google.com/"
 * }
 * </pre>
 *
 * @author Konrad Malawski
 */
public class ShortenResponse extends GooGlResponse {

  @SerializedName("id")
  private String shortUrl = "";
  private String longUrl = "";
  private String kind    = "";

  public ShortenResponse() {
  }

  public String getKind() {
    return kind;
  }

  public void setKind(String kind) {
    this.kind = kind;
  }

  /**
   * Id is the short URL that expands to the long URL you provided. If your request includes an auth token,
   * then this URL will be unique. If not, then it might be reused from a previous request to shorten the same URL.
   * @return the shortUrl, described above
   */
  public String getShortUrl() {
    return shortUrl;
  }

  public void setShortUrl(String shortUrl) {
    this.shortUrl = shortUrl;
  }

  /**
   * longUrl is the long URL to which it expands. In most cases, this will be the same as the URL you provided.
   * In some cases, the server may canonicalize the URL. For instance, if you pass http://www.google.com,
   * the server will add a trailing slash.
   *
   * @return the longUrl, described above
   */
  public String getLongUrl() {
    return longUrl;
  }

  public void setLongUrl(String longUrl) {
    this.longUrl = longUrl;
  }

  @Override
  public String toString() {
    return "ShortenResponse{" +
        "kind='" + kind + '\'' +
        ", shortUrl='" + shortUrl + '\'' +
        ", longUrl='" + longUrl + '\'' +
        ", error=" + error +
        '}';
  }
}