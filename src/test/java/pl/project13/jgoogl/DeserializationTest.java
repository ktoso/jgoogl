package pl.project13.jgoogl;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.junit.Test;
import pl.project13.jgoogl.response.v1.ShortenResponse;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Date: 1/16/11
 *
 * @author Konrad Malawski
 */
public class DeserializationTest {

  Logger log = Logger.getLogger(this.getClass());

  @Test
  public void testErrorParse() throws Exception {
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

    ShortenResponse response = new Gson().fromJson(errorJson, ShortenResponse.class);

    log.info("Unserialized response: " + response);

    assertThat(response).isNotNull();
    assertThat(response.getError().getErrors()).hasSize(1);
    assertThat(response.hasErrors()).isTrue();
    assertThat(response.getError().getCode()).isEqualTo(500);
    assertThat(response.getError().getMessage()).isEqualTo("Internal Error");
  }

  @Test
  public void testMoreErrorsParse() throws Exception {
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

    ShortenResponse response = new Gson().fromJson(errorJson, ShortenResponse.class);

    log.info("Unserialized response: " + response);

    assertThat(response).isNotNull();
    assertThat(response.getError().getErrors()).hasSize(2);
    assertThat(response.hasErrors()).isTrue();
    assertThat(response.getError().getCode()).isEqualTo(500);
    assertThat(response.getError().getMessage()).isEqualTo("Internal Error");
  }
}
