package pl.project13.jgoogl.response.v1.error;

import java.util.List;

/**
 * <pre>
 * {
 *   "error": {
 *     "errors": [
 *       {
 *         "domain": "global",
 *         "reason": "internalError",
 *         "message": "Internal Error"
 *       }
 *     ],
 *     "code": 500,
 *     "message": "Internal Error"
 *   }
 * }
 * </pre>
 * Date: 1/16/11
 *
 * @author Konrad Malawski
 */
public class Error {
  List<ErrorDetails> errors;
  Integer            code;
  String             message;

  public Error() {
  }

  public List<ErrorDetails> getErrors() {
    return errors;
  }

  public void setErrors(List<ErrorDetails> errors) {
    this.errors = errors;
  }

  /**
   * code is the HTTP status code of this response.
   *
   * @return the HTTP code of the response
   */
  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "Error{" +
        "errors=" + errors +
        ", code=" + code +
        ", message='" + message + "'" +
        '}';
  }
}
