package pl.project13.jgoogl;

import org.apache.log4j.Logger;
import org.junit.Test;
import pl.project13.jgoogl.exceptions.InvalidGooGlUrlException;
import pl.project13.jgoogl.response.v1.ExpandResponse;
import pl.project13.jgoogl.response.v1.enums.GooGlStatus;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Date: 1/16/11
 *
 * @author Konrad Malawski
 */
public class ExpandTest {

  Logger log = Logger.getLogger(getClass());

  JGooGl jGooGl = JGooGl.withoutKey();

  // test data
  String longUrl          = "http://www.project13.pl/"; // note the trailing slash, Google will add it in their response anyways
  String shortenedLongUrl = "http://goo.gl/ZA9Yx"; // the above should be shortened to this url

  public static final String EXPECTED_SHORTENER_KIND = "urlshortener#url";

  @Test
  public void shouldExpandProperly() throws Exception {
    ExpandResponse expandResponse = jGooGl.expand(shortenedLongUrl);

    assertValidExpandResponse(expandResponse);
  }

  @Test(expected = InvalidGooGlUrlException.class)
  public void expandShouldThrowOnBadUrl() throws Exception {
    jGooGl.expand("ww.goo.gl/");
  }

  @Test(expected = InvalidGooGlUrlException.class)
  public void thisExpandShouldAlsoThrowOnBadUrl() throws Exception {
    jGooGl.expand("http://www.somesite.com/?ww.goo.gl/");
  }

  private void assertValidExpandResponse(ExpandResponse expandResponse) {
    log.info("Deserialized response: " + expandResponse);

    assertThat(expandResponse).isNotNull();
    assertThat(expandResponse.hasErrors()).isFalse();
    assertThat(expandResponse.getShortUrl()).isEqualTo(shortenedLongUrl);
    assertThat(expandResponse.getStatus()).isEqualTo(GooGlStatus.OK);
    assertThat(expandResponse.getLongUrl()).isEqualTo(longUrl);
    assertThat(expandResponse.getKind()).isEqualTo(EXPECTED_SHORTENER_KIND);
    assertThat(expandResponse.getShortUrl()).isEqualTo(shortenedLongUrl);
  }

}
