package pl.project13.jgoogl;

import com.google.gson.Gson;
import com.ning.http.client.AsyncHttpClient;
import org.junit.Test;
import pl.project13.jgoogl.conf.GooGlProjection;
import pl.project13.jgoogl.conf.GooGlVersion;

/**
 * Date: 2/28/11
 *
 * @author Konrad Malawski
 */
public class BuilderTest {

  public String apiKey = "my-secret-key";

  @Test
  public void shouldUseSuppliedAsyncHttpClient() throws Exception {
    AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
  }

  @Test
  public void testAllMethods() throws Exception {
    JGooGl jGooGl = new JGooGl.Builder()
        .useSupplied(new Gson())
        .useSupplied(new AsyncHttpClient())
        .useKey(apiKey)
        .useVersion(GooGlVersion.V1)
        .useProjection(GooGlProjection.ANALYTICS_FULL)
        .get();
  }

  @Test
  public void shouldUseApiKey() throws Exception {
    JGooGl jGooGl = new JGooGl.Builder()
        .useKey(apiKey)
        .get();
  }

  @Test
  public void testStaticFactories() throws Exception {
    JGooGl.withKey("random-key");
    JGooGl.withoutKey();
  }
}
