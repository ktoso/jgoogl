jGooGl - Fluent goo.gl API Java Wrapper
=======================================

General information
-------------------
**jGooGl** is an simple, yet pleasant to use, wrapper around Google's goo.gl service, and it's API,
which is documented here: http://code.google.com/apis/urlshortener/overview.html

Currently there only is one version out - v1, but if something new should be added, be sure I'll update this project :-)

Maven repository
----------------
Add this repository for **releases** of this plugin:

        <repository>
            <id>sonatype-releases</id>
            <name>Sonatype Releases</name>
            <url>https://oss.sonatype.org/content/repositories/releases/</url>
        </repository>

**or** use this one for it's **snapshots**:

        <repository>
            <id>sonatype-snapshots</id>
            <name>Sonatype Snapshots</name>
            <url>https://oss.sonatype.org/content/repositories/snapshots/</url>
        </repository>


Then just add it to your dependencies:

        <dependencies>
            <dependency>
                <groupId>pl.project13.jgoogl</groupId>
                <artifactId>jgoogl</artifactId>
                <version>1.0</version>
            </dependency>
        </dependencies>

I also hope to make it available from Maven Central, we'll se... ;-)


Usage guide
-----------
Using **jGooGl** is really easy, take a look at these few examples.

## Instance creation ##
### Simple instance creation ###

        // you may use jGooGl without an API key
        JGooGl jGooGl = JGooGl.withoutKey();

        // or with (THIS IS THE BETTER CHOICE)
        JGooGl jGooGl = JGooGl.withKey("mySuperAwesomeKey");

### Creating an instance using the Builder ###
You may want to create an jGooGl instance that would use a mocked out httpclient or specify what version etc it should use.
That's what the JGooGlBuilder (or JGooGl.Builder) is here for, here's a few examples how you may use it:

        // a simple example of JGooGlBuilder
        jGooGl = new JGooGlBuilder().useKey("my-secret-key").get();

        // and another example showcasing all methods:
        jGooGl = new JGooGl.Builder().useSupplied(new Gson())
                                     .useSupplied(new AsyncHttpClient())
                                     .useKey(apiKey)
                                     .useVersion(GooGlVersion.V1)
                                     .useProjection(GooGlProjection.ANALYTICS_FULL)
                                     .get();
## Querying ##
### Shorten() ###
Here's how simple you can shorten an URL:

        // the one-liner style is preferred as it can give you better Typing for expand requests
        String shortUrl = jGooGl.shorten("http://project13.pl").getShortUrl();

        // you could of course write it like this (and get a few more things out of ShortenResponse)
        ShortenResponse response = jGooGl.shorten("http://goo.gl/3X4m913");
        shortUrl = response.getShortUrl();

Shortening is really easy and there's not much you can change during using it.
But please be aware of the "small catch" while using on* methods, it's explained in detail bellow.

### Expand() ###
Using this method you can both expand an shortened goo.gl url and query for it's statistics.
To just query for the ExpandResponse use such code:

       ExpandResponse expandResponse = jGooGl.expand(shortenedLongUrl); // this would be a url like: goo.gl/GOOGLSH

### Expand() and statistics ###

Before we go into how expand() can and is used to fetch an AnalyticsResponse let's use the two dedicated methods firstL

      // using the default projection (or the one specified during your GooGlBuilder phase)
      AnalyticsResponse response = jGooGl.analyticsFor(shortenedLongUrl);

      // using the specialized projections
      AnalyticsResponse analyticsResponse = jGooGl.analyticsFor(shortenedLongUrl, GooGlProjection.ANALYTICS_CLICKS);

      AnalyticsResponse analyticsResponse = jGooGl.analyticsFor(shortenedLongUrl, GooGlProjection.ANALYTICS_TOP_STRINGS);

      AnalyticsResponse analyticsResponse = jGooGl.analyticsFor(shortenedLongUrl, GooGlProjection.ANALYTICS_FULL);



As AnalyticsResponse is just a ExpandResponse with more details packed into it (that's how the GooGl API returns it anyways)
there's a small trick here for you to still have **type-safe** Analytics calls. In order to explain

There is a small catch there, although: `.withAnalytics().expand()` does return `AnalyticsResponse`
and no casting is needed if you'd use it like above - explicitly on jGooGl you'd have to do an explicite cast.
This obviously sux, so here's how to avoid it:
       
        // As no analytics mode is given, it will re-use the last used mode, or fallback to NONE
        AnalyticsResponse response = jGooGl.expandWithAnalytics(url);

Instead of the alwaysWith method, there's also an with\* method family that will only use the setting on the following query.

        // the 'just this time' version would look like this
        jGooGl.withAnalytics(ALL).expand(url)

The "one-liner" approach would be:

        String short = JGooGl.withKey("toHyruleCastle").expand("shorten").url();

Frequently Asked Questions
--------------------------
- **Q: Why would I need an API key if it works without one? <br/>**
  A: Sure it **seems** like it's working as good with an API key,
  that is, until you reach Google's query limits. The limits imposed on users with an api key
  are fare less restrictive than anonymous users. So if your building this feature into an website
  that has quite a few users - dont hesitate and go for an api key right away, it's explained how to 
  get one here: http://code.google.com/apis/urlshortener/v1/authentication.html#key

License
-------
I'm releasing this under the ..... //TODO
