package pl.project13.jgoogl.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import pl.project13.jgoogl.response.v1.enums.GooGlStatus;

/**
 * Date: 1/16/11
 *
 * @author Konrad Malawski
 */
public class GooGlGsonProvider {

  /**
   * This Gson provider registers custom deserializers needed for our code
   * to work properly, please use it
   *
   * @return an properly configured Gson instance
   */
  public static Gson get() {
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.setPrettyPrinting();
    gsonBuilder.registerTypeAdapter(GooGlStatus.class, new GooGlStatusDeserializer());

    return gsonBuilder.create();
  }
}
