package pl.project13.jgoogl.response.v1;

import pl.project13.jgoogl.response.v1.error.Error;

/**
 * Date: 1/16/11
 *
 * @author Konrad Malawski
 */
public abstract class GooGlResponse {

  protected Error error;

  public Error getError() {
    return error;
  }

  public void setError(pl.project13.jgoogl.response.v1.error.Error error) {
    this.error = error;
  }

  public boolean hasErrors() {
    return error != null && (error.getErrors() != null && error.getErrors().size() > 0);
  }
}
