package pl.project13.jgoogl.response.v1;

import com.google.gson.annotations.SerializedName;

/**
 * <pre>
 * {
 *   "kind":    "urlshortener#url",
 *   "id":      "http://goo.gl/fbsS",
 *   "longUrl": "http://www.google.com/",
 *   "status":  "OK"
 * }
 * </pre>
 * @author Konrad Malawski
 */
public class ExpandResponse extends GooGlResponse {


  @SerializedName("status")
  private ResponseStatus responseStatus = ResponseStatus.OK;

  private String shortUrl = "";


  public ExpandResponse() {
  }

  public String getShortUrl() {
    return shortUrl;
  }

  public void setShortUrl(String shortUrl) {
    this.shortUrl = shortUrl;
  }

  public ResponseStatus getResponseStatus() {
    return responseStatus;
  }

  public void setResponseStatus(ResponseStatus responseStatus) {
    this.responseStatus = responseStatus;
  }

  @Override
  public String toString() {
    return "ShortenResponse{" +
        "shortUrl='" + shortUrl + '\'' +
        ", responseStatus=" + responseStatus +
        ", error=" + error +
        '}';
  }

  /**
   * Field used to inform the user about the status of
   * the requested expand operation -
   *
   * @author Konrad Malawski
   */
  public static enum ResponseStatus {
    OK,
    ERROR
  }
}
