package pl.project13.jgoogl;

import org.apache.log4j.Logger;
import org.junit.Test;
import pl.project13.jgoogl.conf.GooGlProjection;
import pl.project13.jgoogl.response.v1.AnalyticsResponse;
import pl.project13.jgoogl.response.v1.ExpandResponse;
import pl.project13.jgoogl.response.v1.enums.GooGlStatus;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Date: 1/16/11
 *
 * @author Konrad Malawski
 */
public class AnalyticsTest {

  Logger log = Logger.getLogger(getClass());

  JGooGl jGooGl = JGooGl.withoutKey();

  // test data
  String longUrl          = "http://www.project13.pl/"; // note the trailing slash, Google will add it in their response anyways
  String shortenedLongUrl = "http://goo.gl/ZA9Yx"; // the above should be shortened to this url

  public static final String EXPECTED_SHORTENER_KIND = "urlshortener#url";

  @Test
  public void shouldExpandProperly() throws Exception {
    AnalyticsResponse analyticsResponse = jGooGl.analyticsFor(shortenedLongUrl);

    log.info("Deserialized response: " + analyticsResponse);

    assertThat(analyticsResponse).isNotNull();
    assertThat(analyticsResponse.hasErrors()).isFalse();
    assertThat(analyticsResponse.getShortUrl()).isEqualTo(shortenedLongUrl);
    assertThat(analyticsResponse.getStatus()).isEqualTo(GooGlStatus.OK);
    assertThat(analyticsResponse.getLongUrl()).isEqualTo(longUrl);
    assertThat(analyticsResponse.getKind()).isEqualTo(EXPECTED_SHORTENER_KIND);
  }

  @Test
  public void queryFluentAnalytics() throws Exception {
    ExpandResponse expand = jGooGl.withAnalytics(GooGlProjection.ANALYTICS_FULL).expand(shortenedLongUrl);

    log.info(expand);

    assertThat(expand).isInstanceOf(AnalyticsResponse.class);
    AnalyticsResponse analyticsResponse = (AnalyticsResponse) expand;

    assertThat(analyticsResponse.getAnalytics()).isNotNull();
    assertThat(analyticsResponse.getLongUrl()).isEqualTo(longUrl);
  }
}
