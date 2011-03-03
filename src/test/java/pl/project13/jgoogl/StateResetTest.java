package pl.project13.jgoogl;

import com.ning.http.client.AsyncHttpClient;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import pl.project13.jgoogl.request.v1.RequestBuilder;

import static org.mockito.Mockito.*;

/**
 * Date: 1/16/11
 *
 * @author Konrad Malawski
 */
public class StateResetTest {

  Logger log = Logger.getLogger(getClass());

  AsyncHttpClient asyncHttpClientMock = mock(AsyncHttpClient.class);
  JGooGl          jGooGl              = null;
  RequestBuilder  requestBuilderSpy   = null;

  // test data
  String longUrl   = "http://www.project13.pl/"; // note the trailing slash, Google will add it in their response anyways
  String sampleKey = "sample-api-key";

  @Before
  public void setUp() throws Exception {
    jGooGl = new JGooGl.Builder().useSupplied(asyncHttpClientMock).get();
  }

  @Test
  public void shouldUseKeyJustOnce() throws Exception {
    // given
    requestBuilderSpy = spy(jGooGl.getRequestBuilder());
    jGooGl.setRequestBuilder(requestBuilderSpy);

    // when
    log.info("calling with key...");
    jGooGl.onKey(sampleKey).shorten(longUrl);
    log.info("calling without key...");
    jGooGl.shorten(longUrl);

    // then should only use key once, in first query
    InOrder inOrder = inOrder(requestBuilderSpy);
    inOrder.verify(requestBuilderSpy).apiKey(sampleKey);
    inOrder.verify(requestBuilderSpy).apiKey(jGooGl.defaultContext.apiKey);
  }

  @Test
  public void shouldNotUseKeyAfterNoKeyCall() throws Exception {
    // given
    jGooGl = JGooGl.withKey(sampleKey);
    requestBuilderSpy = spy(jGooGl.getRequestBuilder());
    jGooGl.setRequestBuilder(requestBuilderSpy);

    // when
    jGooGl.onNoKey().shorten(longUrl);
    jGooGl.shorten(longUrl);

    // then should not use any key
    InOrder inOrder = inOrder(requestBuilderSpy);
    inOrder.verify(requestBuilderSpy).apiKey(null);
    inOrder.verify(requestBuilderSpy).apiKey(sampleKey);
  }
}
