package pl.project13.jgoogl.conf;

/**
 * Date: 1/16/11
 *
 * Can be used to perform analytics lookup queries on urls.
 * Each method is described in details bellow.
 *
 * @author Konrad Malawski
 */
public enum GooGlProjection {

  /**
   * Przekaż stałą do metody expandShortcut jeśli interesują cię statystyki kliknięć
   */
  ANALYTICS_CLICKS("ANALYTICS_CLICKS"),
  /**
   * Przekaż stałą do metody expandShortcut jeśli interesują cię statystyki
   * odnośnie refererów, przeglądarek i platform systemowych
   */
  ANALYTICS_TOP_STRINGS("ANALYTICS_TOP_STRINGS"),
  /**
   * Przekaż stałą do metody expandShortcut jeśli interesują cię
   * wszystkie statystyki powiązane ze skrótem
   */
  ANALYTICS_FULL("FULL");

  public final String urlParamValue;

  GooGlProjection(String urlParamValue) {
    this.urlParamValue = urlParamValue;
  }
}
