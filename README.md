jGooGl - Fluent goo.gl API Java Wrapper 
=======================================
Quick info - This is yet **under construction** and it's API is not yet stable, if you want to used if in a stable project, wait one week or be sure tu update the project next week :-)

General information
-------------------
**jGooGl** is an simple, yet pleasant to use, wrapper around Google's goo.gl service, and it's API,
which is documented here: http://code.google.com/apis/urlshortener/overview.html

Currently there only is one version out - v1, but if something new should be added, be sure I'll update this project :-)

Maven repository
----------------
The library is in maven central (though I implemented it around 3 years ago, I released in 2013 into there ;-)) 
Just add it to your dependencies:

```xml
<dependency>
  <groupId>pl.project13.jgoogl</groupId>
  <artifactId>jgoogl</artifactId>
  <version>1.0</version>
</dependency>


Usage guide
-----------
Using **jGooGl** is really easy, take a look at these few examples.

## Instance creation ##
### Simple instance creation ###
There are two ways of creating an jGooGl instance, the first one does not give you major tweaking
abilities but it's super easy to use:

```java
  // you may use jGooGl without an API key
  JGooGl jGooGl = JGooGl.withoutKey();

  // or with (THIS IS THE BETTER CHOICE)
  JGooGl jGooGl = JGooGl.withKey("mySuperAwesomeKey");
```

### Creating an instance using the Builder ###
As you may want to create an jGooGl instance that would use a mocked out httpclient or specify what version etc it should use.
That's what the JGooGlBuilder (or JGooGl.Builder) is here for, here's a few examples how you may use it:

```java
  // a simple example of JGooGlBuilder
  jGooGl = new JGooGlBuilder().useKey("my-secret-key").get();

  // and another example showcasing all methods:
  jGooGl = new JGooGl.Builder()
    .useSupplied(new Gson())
    .useSupplied(new AsyncHttpClient())
    .useKey(apiKey)
    .useVersion(GooGlVersion.V1)
    .useProjection(GooGlProjection.ANALYTICS_FULL)
    .get();
```

## Querying ##
### Shorten() ###
Here's how simple you can shorten an URL:

```java
  // the one-liner style is preferred as it can give you better Typing for expand requests
  String shortUrl = jGooGl.shorten("http://project13.pl").getShortUrl();

  // you could of course write it like this (and get a few more things out of ShortenResponse)
  ShortenResponse response = jGooGl.shorten("http://goo.gl/3X4m913");
  shortUrl = response.getShortUrl();
```

Shortening is really easy and there's not much you can change during using it.
But please be aware of the "small catch" while using on* methods, it's explained in detail bellow.

### Expand() ###
Using this method you can both expand an shortened goo.gl url and query for it's statistics.
To just query for the ExpandResponse use such code:

```java
  ExpandResponse expandResponse = jGooGl.expand(shortenedLongUrl); // this would be a url like: goo.gl/GOOGLSH
```

### Expand() and Analytics ###

Before we go into how expand() can and is used to fetch an AnalyticsResponse let's use the two dedicated methods firstL

```java
  // using the default projection (or the one specified during your GooGlBuilder phase)
  AnalyticsResponse response = jGooGl.analyticsFor(shortenedLongUrl);

  // using the specialized projections
  AnalyticsResponse analyticsResponse = jGooGl.analyticsFor(shortenedLongUrl, GooGlProjection.ANALYTICS_CLICKS);

  AnalyticsResponse analyticsResponse = jGooGl.analyticsFor(shortenedLongUrl, GooGlProjection.ANALYTICS_TOP_STRINGS);

  AnalyticsResponse analyticsResponse = jGooGl.analyticsFor(shortenedLongUrl, GooGlProjection.ANALYTICS_FULL);
```

That's quite nice, but in case you'd like to stick to your expand() method calls here's a nice **type-safe** way of doing so.
(By type-safe I mean that even if you had analyticsMode set in the builder, and call expands on the jGooGl instance, it will have the details in it,
in fact you could try to cast ExpandResponse to AnalyticsResponse "sometimes", the bellow apparoach takes away the headache of casting stuff by hand... :-))

```java
  AnalyticsResponse analyticsResponse = jGooGl.withAnalytics(ANALYTICS_FULL).expand(myShortUrl);
```

This is possible because withAnalytics creates a JGooGlWithAnalytics instance which will delegate all calls to the Real JGooGl instance,
but take care of the analytics casting where applicable.

### Chaining in-line JGooGl configuration changes ###
As you've already seen, there's a `JGooGlBuilder` there for you to easily create a JGooGl instance working on some specific parameters etc.
But there is a nice way of calling some shortens without using the "default" ("set at builder time").

The values set during construction will always be used if you don't use any of the bellow methods,
also the methods bellow only set the variable "for the current execution". Let's see some examples:

```java
  JGooGl jGooGl = JGooGl.withoutKey();

  jGooGl.shorten(url); // this calls without any key

  jGooGl.onKey(myKey).shorten(url); // this calls using the myKey as key

  // but the next execution will still be using the "default key"
  jGooGl.shorten(url); // this calls without any key
```

This way you're free to assume that the defaults stay defaults during your apps life-time,
and no-one will break it by setting some weird value in it.

The same can be done with all other parameters, like this:

```java
  jGooGl.onKey(myKey).shorten();
  jGooGl.onNoKey().shorten();
  jGooGl.onVersion(GooGlVersion.V1).shorten();

  // and
  jGooGl.onProjection(GooGlProjection.ANALYTICS_FULL).expand(longUrl);
```

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
I've written this code years ago... Beerware license!
