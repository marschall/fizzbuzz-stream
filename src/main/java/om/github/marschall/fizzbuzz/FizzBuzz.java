package om.github.marschall.fizzbuzz;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public final class FizzBuzz {
  
  public static void main(String[] args) {
    stream().limit(100L).forEach(System.out::println);
  }

  public static Stream<String> stream() {
    return StreamSupport.stream(new FizzBuzzSpliterator(), false);
  }
  
  static final class FizzBuzzSpliterator implements Spliterator<String> {
    
    /**
     * Position of the next read.
     */
    private long index;
    private long end;
    
    
    FizzBuzzSpliterator(long index, long end) {
      this.index = index;
      this.end = end;
    }

    FizzBuzzSpliterator() {
      this(1, Long.MAX_VALUE);
    }

    @Override
    public void forEachRemaining(Consumer<? super String> action) {
      while (index <= end) {
        action.accept(this.generateValue());
        index += 1;
      }
    }
    
    private String generateValue() {
      boolean divisibleByThree = index % 3L == 0L;
      boolean divisibleByFive = index % 5L == 0L;
      if (divisibleByThree && divisibleByFive) {
        return "FizzBuzz";
      } else if (divisibleByThree) {
        return "Fizz";
      } else if (divisibleByFive) {
        return "Buzz";
      } else {
        return Long.toString(index);
      }
    }
    
    @Override
    public boolean tryAdvance(Consumer<? super String> action) {
      if (this.index > this.end) {
        return false;
      }
      action.accept(this.generateValue());
      index += 1;
      return true;
    }
    @Override
    public Spliterator<String> trySplit() {
      if (this.index >= this.end) {
        // empty or size 1 => null
        return null;
      }
      long half = (this.end - this.index + 1L) / 2L;
      Spliterator<String> result = new FizzBuzzSpliterator(this.index, this.index + half - 1L);
      this.index += half;
      return result;
    }
    @Override
    public long estimateSize() {
      return this.end - this.index + 1;
    }
    
    public long getExactSizeIfKnown() {
      return estimateSize();
    }
    
    @Override
    public int characteristics() {
      return Spliterator.SUBSIZED;
    }
    
  }

}
