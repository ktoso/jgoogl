package pl.project13.jgoogl.exceptions;

import com.ning.http.client.Response;

/**
 * Date: 1/16/11
 *
 * @author Konrad Malawski
 */
public class GooGlResponseFailureException extends RuntimeException {
  public GooGlResponseFailureException() {
  }

  public GooGlResponseFailureException(String message) {
    super(message);
  }

  public GooGlResponseFailureException(String message, Throwable cause) {
    super(message, cause);
  }

  public GooGlResponseFailureException(Throwable cause) {
    super(cause);
  }

  public GooGlResponseFailureException(String message, Response response) {
    super(message + " Status: " + response.getStatusCode() + " - " + response.getStatusText());
  }
}
