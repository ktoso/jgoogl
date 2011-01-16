package pl.project13.jgoogl.response.v1.error;

/**
 * <pre>
 * {
 *   "domain": "global",
 *   "reason": "internalError",
 *   "message": "Internal Error"
 * }
 * </pre>
 * Date: 1/16/11
 *
 * @author Konrad Malawski
 */
public class ErrorDetails {
  String domain;
  String reason;
  String message;
  String locationType;
  String location;

  public ErrorDetails() {
  }

  public ErrorDetails(String domain, String reason, String message) {
    this.domain = domain;
    this.reason = reason;
    this.message = message;
  }

  public String getDomain() {
    return domain;
  }

  public void setDomain(String domain) {
    this.domain = domain;
  }

  /**
   * reason specifies the error reason.
   * For example, reason will be "required" or "invalid" if some field was missing or malformed.
   *
   * @return the reason hinting at why this request failed
   */
  public String getReason() {
    return reason;
  }

  /**
   * reason specifies the error reason.
   * For example, reason will be "required" or "invalid" if some field was missing or malformed.
   */
  public void setReason(String reason) {
    this.reason = reason;
  }

  /**
   * message is a human readable explanation of the error.
   *
   * @return human readable error explanation
   */
  public String getMessage() {
    return message;
  }

  /**
   * message is a human readable explanation of the error.
   */
  public void setMessage(String message) {
    this.message = message;
  }


  /**
   * locationType, if present, specifies what part of the request has an error.
   * Possible values include "parameter" and "header".
   *
   * @return the locationType, as described above
   */
  public String getLocationType() {
    return locationType;
  }

  /**
   * locationType, if present, specifies what part of the request has an error.
   * Possible values include "parameter" and "header".
   */
  public void setLocationType(String locationType) {
    this.locationType = locationType;
  }

  /**
   * location, if present, specifies the location in the request that caused the error,
   * e.g. the name of a missing or malformed parameter
   *
   * @return the location, described above
   */
  public String getLocation() {
    return location;
  }


  /**
   * location, if present, specifies the location in the request that caused the error,
   * e.g. the name of a missing or malformed parameter
   */
  public void setLocation(String location) {
    this.location = location;
  }

  @Override
  public String toString() {
    return "ErrorDetails{" +
        "domain='" + domain + '\'' +
        ", reason='" + reason + '\'' +
        ", message='" + message + '\'' +
        ", locationType='" + locationType + '\'' +
        ", location='" + location + '\'' +
        '}';
  }
}
