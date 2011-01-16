package pl.project13.jgoogl.response.v1.enums;

/**
 * Date: 1/16/11
 *
 * @author Konrad Malawski
 */
public enum GooGlStatus {
  OK,
  MALWARE,
  PHISHING,
  REMOVED;

  public static GooGlStatus byName(String string) {
    for (GooGlStatus glStatus : values()) {
      if (glStatus.name().equals(string)) {
        return glStatus;
      }
    }
    return null;
  }
}
