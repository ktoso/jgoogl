package pl.project13.jgoogl.utils;

/**
 * Date: 1/16/11
 *
 * @author Konrad Malawski
 */
public class StringUtils {

  public static boolean hasErrors(String responseString) {
    return responseString.contains("\"error\": {");
  }

  public static boolean hasText(String value) {
    return value != null && value.length() > 0;
  }
}
