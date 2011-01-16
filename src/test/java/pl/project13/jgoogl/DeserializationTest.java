package pl.project13.jgoogl;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.junit.Test;
import pl.project13.jgoogl.gson.GooGlGsonProvider;
import pl.project13.jgoogl.response.v1.ExpandResponse;
import pl.project13.jgoogl.response.v1.ShortenResponse;
import pl.project13.jgoogl.response.v1.enums.GooGlStatus;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Date: 1/16/11
 *
 * @author Konrad Malawski
 */
public class DeserializationTest {

  Logger log = Logger.getLogger(this.getClass());

  Gson gson = GooGlGsonProvider.get();

  @Test
  public void shouldParseErrorProperly() throws Exception {
    String errorJson = "{\n" +
        " \"error\": {\n" +
        "  \"errors\": [\n" +
        "   {\n" +
        "    \"domain\": \"global\",\n" +
        "    \"reason\": \"internalError\",\n" +
        "    \"message\": \"Internal Error\"\n" +
        "   }\n" +
        "  ],\n" +
        "  \"code\": 500,\n" +
        "  \"message\": \"Internal Error\"\n" +
        " }\n" +
        "}";

    ShortenResponse response = gson.fromJson(errorJson, ShortenResponse.class);

    log.info("Unserialized response: " + response);

    assertThat(response).isNotNull();
    assertThat(response.getError().getErrors()).hasSize(1);
    assertThat(response.hasErrors()).isTrue();
    assertThat(response.getError().getCode()).isEqualTo(500);
    assertThat(response.getError().getMessage()).isEqualTo("Internal Error");
  }

  @Test
  public void shouldHaveMoreErrors() throws Exception {
    String errorJson = "{\n" +
        " \"error\": {\n" +
        "  \"errors\": [\n" +
        "   {\n" +
        "    \"domain\": \"global\",\n" +
        "    \"reason\": \"internalError\",\n" +
        "    \"message\": \"Internal Error\"\n" +
        "   },\n" +
        "   {\n" +
        "    \"domain\": \"global\",\n" +
        "    \"reason\": \"internalError\",\n" +
        "    \"message\": \"Internal Error\"\n" +
        "   }\n" +
        "  ],\n" +
        "  \"code\": 500,\n" +
        "  \"message\": \"Internal Error\"\n" +
        " }\n" +
        "}";

    ShortenResponse response = gson.fromJson(errorJson, ShortenResponse.class);

    log.info("Unserialized response: " + response);

    assertThat(response).isNotNull();
    assertThat(response.getError().getErrors()).hasSize(2);
    assertThat(response.hasErrors()).isTrue();
    assertThat(response.getError().getCode()).isEqualTo(500);
    assertThat(response.getError().getMessage()).isEqualTo("Internal Error");
  }

  @Test
  public void shouldDeserializeStatuses() throws Exception {
    String test = "{\n" +
        " \"kind\": \"urlshortener#url\",\n" +
        " \"id\": \"http://goo.gl/fbsS\",\n" +
        " \"longUrl\": \"http://www.google.com/\",\n" +
        " \"status\": \"REMOVED\"\n" +
        "}";

    ExpandResponse response = gson.fromJson(test, ExpandResponse.class);

    assertThat(response).isNotNull();
    assertThat(response.hasErrors()).isFalse();
    assertThat(response.getStatus()).isEqualTo(GooGlStatus.REMOVED);
  }
}
