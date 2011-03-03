package pl.project13.jgoogl;

import org.apache.log4j.Logger;
import org.junit.Test;
import pl.project13.jgoogl.response.v1.AnalyticsResponse;
import pl.project13.jgoogl.response.v1.enums.GooGlStatus;

import static org.fest.assertions.Assertions.assertThat;
import static pl.project13.jgoogl.conf.GooGlProjection.ANALYTICS_CLICKS;
import static pl.project13.jgoogl.conf.GooGlProjection.ANALYTICS_FULL;

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
    AnalyticsResponse expand = jGooGl.withAnalytics(ANALYTICS_FULL).expand(shortenedLongUrl);

    log.info(expand);

    assertThat(expand).isInstanceOf(AnalyticsResponse.class);
    assertThat(expand.getAnalytics()).isNotNull();
    assertThat(expand.getLongUrl()).isEqualTo(longUrl);
  }

  @Test
  public void shouldGetOnlyClicksAnalytics() throws Exception {
    AnalyticsResponse expand = jGooGl.withAnalytics(ANALYTICS_CLICKS).expand(shortenedLongUrl);

    log.info(expand);

    assertThat(expand).isInstanceOf(AnalyticsResponse.class);
    assertThat(expand.getAnalytics()).isNotNull();
    assertThat(expand.getLongUrl()).isEqualTo(longUrl);
  }
}
