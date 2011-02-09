package pl.project13.jgoogl.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * Date: 1/17/11
 *
 * @author Konrad Malawski
 */
public class ISO8601DateDeserializer implements JsonDeserializer<Date> {

  DateTimeFormatter parser = ISODateTimeFormat.dateTimeNoMillis();

  public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
      throws JsonParseException {

    String string = json.getAsJsonPrimitive().getAsString().replaceAll("\\.\\d{1,3}\\+", "+");
    DateTime date = parser.parseDateTime(string);

    return date.toDate();
  }

}
