package pl.project13.jgoogl.response.v1;

import pl.project13.jgoogl.response.v1.enums.GooGlStatus;

/**
 * <pre>
 * {
 *   "kind":    "urlshortener#url",
 *   "id":      "http://goo.gl/fbsS",
 *   "longUrl": "http://www.google.com/",
 *   "status":  "OK"
 * }
 * </pre>
 *
 * @author Konrad Malawski
 */
public class ExpandResponse extends GooGlResponse {

  private String      id      = "";
  private String      longUrl = "";
  private GooGlStatus status  = GooGlStatus.OK;
  private String      kind    = "";

  public ExpandResponse() {
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  /**
   * longUrl is the long URL to which it expands. Note that longUrl may
   * not be present in the response, for example, if status is "REMOVED".
   *
   * @return
   */
  public String getLongUrl() {
    return longUrl;
  }

  public void setLongUrl(String longUrl) {
    this.longUrl = longUrl;
  }

  /**
   * status is "OK" for most URLs. If Google believes that the URL is fishy,
   * status may be something else, such as "MALWARE". Another value this can take is "REMOVED".
   *
   * @return the status of this url, as described above
   */
  public GooGlStatus getStatus() {
    return status;
  }

  public void setStatus(GooGlStatus status) {
    this.status = status;
  }

  public String getKind() {
    return kind;
  }

  public void setKind(String kind) {
    this.kind = kind;
  }

  @Override
  public String toString() {
    return "ExpandResponse{" +
        "id='" + id + '\'' +
        ", longUrl='" + longUrl + '\'' +
        ", status='" + status + '\'' +
        ", error=" + error +
        '}';
  }
}
