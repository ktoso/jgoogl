package pl.project13.jgoogl;

import org.apache.log4j.Logger;
import org.junit.Test;
import pl.project13.jgoogl.response.v1.ShortenResponse;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Date: 1/16/11
 *
 * @author Konrad Malawski
 */
public class ShortenTest {

  Logger log = Logger.getLogger(getClass());

  JGooGl jGooGl = JGooGl.withoutKey();

  // test data
  String longUrl          = "http://www.project13.pl/"; // note the trailing slash, Google will add it in their response anyways
  String shortenedLongUrl = "http://goo.gl/ZA9Yx"; // the above should be shortened to this url

  public static final String EXPECTED_SHORTENER_KIND = "urlshortener#url";

  @Test
  public void shouldShortenProperly() throws Exception {
    ShortenResponse shortResponse = jGooGl.shorten(longUrl);

    log.info("Deserialized response: " + shortResponse);

    assertThat(shortResponse).isNotNull();
    assertThat(shortResponse.hasErrors()).isFalse();
    assertThat(shortResponse.getLongUrl()).isEqualTo(longUrl);
    assertThat(shortResponse.getKind()).isEqualTo(EXPECTED_SHORTENER_KIND);
    assertThat(shortResponse.getShortUrl()).isEqualTo(shortenedLongUrl);
  }
}
