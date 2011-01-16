package pl.project13.jgoogl.exceptions;

/**
 * Date: 1/16/11
 *
 * @author Konrad Malawski
 */
public class InvalidGooGlUrlException extends RuntimeException {
  public InvalidGooGlUrlException() {
  }

  public InvalidGooGlUrlException(String message) {
    super(message);
  }

  public InvalidGooGlUrlException(String message, Throwable cause) {
    super(message, cause);
  }

  public InvalidGooGlUrlException(Throwable cause) {
    super(cause);
  }
}
