jGooGl - Fluent goo.gl API Java Wrapper
=======================================
General information
-------------------
**jGooGl** is an simple, yet pleasant to use, wrapper around Google's goo.gl service, and it's API,
which is documented here: http://code.google.com/apis/urlshortener/overview.html

Currently there only is one version out - v1, but if something new should be added, be sure I'll update this project :-)

Usage examples
--------------
Using **jGooGl** is really easy, take a look at these few examples.

1. Instance creation:
     // you may use jGooGl without an API key
     JGooGl jGooGl = JGooGl.withoutKey();

     // or with (THIS IS THE BETTER CHOICE)
     JGooGl jGooGl = JGooGl.withKey("mySuperAwesomeKey");

     // at any time you may switch back and forth between those two:
     jGooGl= jGooGl.addKey("theKeyOfTwilight").removeKey();

2. Querying the service
     ShortenResponse response = jGooGl.shorten("http://goo.gl/3X4m913");

     String url = jGooGl.withKey("myapikey").short("http://goo.gl/3X4m913").url();

     jGooGl.shorten("http://project13.pl");

3. The "one-liner" apparoach
     String short = JGooGl.withKey("toHyruleCastle").expand("shorten").url();

Frequently Asked Questions
--------------------------
<ul>
<li>***Q: Why would I need an API key if it works without one?***<br/>
A: Sure it **seems** like it's working as good with an API key,
that is, until you reach Google's query limits. The limits imposed on users with an api key
are fare less restrictive than anonymous users. So if your building this feature into an website
that has quite a few users - dont hesitate and go for an api key right away, it's explained how to get one here: http://code.google.com/apis/urlshortener/v1/authentication.html#key

Maven repo
----------
You may use **jGooGl** with your maven projects, just add:

  //
  //todo will add repo soon
  //

I will be adding jGooGl to Sonatype Maven repository when it's finished - please look forward to it! :-)

