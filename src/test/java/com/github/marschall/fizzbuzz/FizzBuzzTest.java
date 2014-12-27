package com.github.marschall.fizzbuzz;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.junit.Test;

import com.github.marschall.fizzbuzz.FizzBuzz;
import com.github.marschall.fizzbuzz.FizzBuzz.FizzBuzzSpliterator;

public class FizzBuzzTest {

  @Test
  public void stream() {
    List<String> list = FizzBuzz.stream()
      .skip(9L)
      .limit(6L)
      .collect(Collectors.toList());
    assertEquals(Arrays.asList("Buzz", "11", "Fizz", "13", "14", "FizzBuzz"), list);
  }

  @Test
  public void trySplit() {
    Spliterator<String> spliterator = new FizzBuzzSpliterator(10L, 19L);
    Spliterator<String> prefix = spliterator.trySplit();
    assertEquals(Arrays.asList("Buzz", "11", "Fizz", "13", "14"),
        StreamSupport.stream(prefix, false).collect(Collectors.toList()));
    assertEquals(Arrays.asList("FizzBuzz", "16", "17", "Fizz", "19"),
        StreamSupport.stream(spliterator, false).collect(Collectors.toList()));
  }
  
}
