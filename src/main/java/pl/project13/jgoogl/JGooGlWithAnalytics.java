package pl.project13.jgoogl;

import pl.project13.jgoogl.response.v1.AnalyticsResponse;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Date: 3/3/11
 *
 * @author Konrad Malawski
 */
public class JGooGlWithAnalytics {

  private JGooGl jGooGlDelegate;

  JGooGlWithAnalytics(JGooGl jGooGlDelegate) {
    this.jGooGlDelegate = jGooGlDelegate;
  }

  AnalyticsResponse expand(String shortUrl) throws IOException, ExecutionException, InterruptedException {
    return (AnalyticsResponse) jGooGlDelegate.expand(shortUrl);
  }

}
