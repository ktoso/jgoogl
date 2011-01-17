package pl.project13.jgoogl.response.v1;

/**
 * Date: 1/16/11
 *
 * @author Konrad Malawski
 */
public class CountAndId {
  private Long count;
  private String id;

  public CountAndId() {
  }

  public Long getCount() {
    return count;
  }

  public void setCount(Long count) {
    this.count = count;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
