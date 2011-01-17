********
jGooGl - Fluent goo.gl API Java Wrapper
********
General information
===================
jGooGl is just an simple, yet pleasant to use, wrapper around Google's goo.gl service, and it's API,
which is documented here: http://code.google.com/apis/urlshortener/overview.html

Currently there only is one version out - v1, but if something new should be added, be sure I'll update this project :-)

Usage examples
==============
Using **jGooGl** is really easy, take a look at these few examples:
  //without api key
  JGooGl jGooGl = new JGooGl();

  ShortenResponse response = jGooGl.shorten("http://goo.gl/3X4m913");

  String url = jGooGl.withKey("myapikey").short("http://goo.gl/3X4m913").url();

  jGooGl.expand("http://project13.pl");

Maven repo
==========
You may use **jGooGl** with your maven projects, just add:

  //
  //todo will add repo soon
  // 
