FizzBuzz Java 8 Stream Edition [![Build Status](https://travis-ci.org/marschall/fizzbuzz-stream.svg?branch=master)](https://travis-ci.org/marschall/fizzbuzz-stream)
==============================

A [Java 8 Stream API](http://www.oracle.com/technetwork/articles/java/ma14-java-se-8-streams-2177646.html) based implementation of [FizzBuzz](http://c2.com/cgi/wiki?FizzBuzzTest).

```java
    FizzBuzz.stream().limit(100L).forEach(System.out::println);
```

