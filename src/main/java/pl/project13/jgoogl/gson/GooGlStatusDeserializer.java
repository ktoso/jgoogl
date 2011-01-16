package pl.project13.jgoogl.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import pl.project13.jgoogl.response.v1.enums.GooGlStatus;

import java.lang.reflect.Type;

/**
 * Date: 1/16/11
 *
 * @author Konrad Malawski
 */
public class GooGlStatusDeserializer implements JsonDeserializer<GooGlStatus> {

  public GooGlStatus deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
      throws JsonParseException {
    String string = json.getAsJsonPrimitive().getAsString();
    return GooGlStatus.byName(string);
  }
}
