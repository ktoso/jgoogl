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
public class JGooGlTest {

  Logger log = Logger.getLogger(this.getClass());

  JGooGl jGooGl = new JGooGl();

  // test data
  String longUrl          = "http://www.project13.pl/"; // note the trailing slash, Google will add it in their response anyways
  String shortenedLongUrl = "http://goo.gl/ZA9Yx"; // the above should be shortened to this url

  @Test
  public void shouldShortenProperly() throws Exception {
    ShortenResponse shortResponse = jGooGl.shorten(longUrl);

    log.info("Unserialized response: " + shortResponse);

    assertThat(shortResponse).isNotNull();
    assertThat(shortResponse.hasErrors()).isFalse();
    assertThat(shortResponse.getLongUrl()).isEqualTo(longUrl);
    assertThat(shortResponse.getKind()).isEqualTo("urlshortener#url");
    assertThat(shortResponse.getId()).isEqualTo(shortenedLongUrl);
  }

  @Test
  public void shouldExpandProperly() throws Exception {
    ShortenResponse shortResponse = jGooGl.expand(longUrl);

    log.info("Unserialized response: " + shortResponse);

    assertThat(shortResponse).isNotNull();
    assertThat(shortResponse.hasErrors()).isFalse();
    assertThat(shortResponse.getLongUrl()).isEqualTo(longUrl);
    assertThat(shortResponse.getKind()).isEqualTo("urlshortener#url");
    assertThat(shortResponse.getId()).isEqualTo(shortenedLongUrl);
  }

}