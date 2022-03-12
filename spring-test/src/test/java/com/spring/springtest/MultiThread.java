package com.spring.springtest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
public class MultiThread {

  private static volatile long count = 0;
  private static AtomicInteger count2 = new AtomicInteger();

  @Test
  void threadNotSafe() throws Exception {
    int max = 10000;

    for (int i = 0; i < max; i++) {
      new Thread(() -> count++).start();
    }

    Thread.sleep(300); // 모든 스레드가 종료될 때까지 잠깐 대기
    Assertions.assertThat(count).isEqualTo(max);
  }

  public synchronized void plus() {
    count++;
  }

  public synchronized void synchronizedTest() {
    // 메소드 전체에 적용
  }

  public void synchronizedUnitTest() {
    synchronized (MultiThread.class) {
      // 블럭에만 작성
    }
  }

  private void run() {
    plus();
    System.out.println(count);
  }

  @Test
  void codingTest() {
    int[] arrays = {1, 2, 3, 4};
    int[][] value = new int[arrays.length][2];
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < arrays.length; i++) {
      String temp = Integer.toBinaryString(arrays[i]);
      value[i][0] = i;
      value[i][1] = (int) temp.chars().filter(c -> c == '1').count();
    }
  }

  @Test
  void codingTest2() {
    int[] arrays2 = {1, 2, 3, 4};
    int[] arrays = {31, 15, 7, 3, 2};

    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < arrays.length; i++) {
      String temp = Integer.toBinaryString(arrays[i]);
      map.put(arrays[i], (int) temp.chars().filter(c -> c == '1').count());
    }
    List<Entry<Integer, Integer>> list = new ArrayList(map.entrySet());
    list.sort(Entry.comparingByKey());
    list.sort(Entry.comparingByValue());

    list.stream().map(Entry::getKey).forEach(System.out::println);

    System.out.println(Integer.toBinaryString(4));
  }

  @Test
  void mapSortedTest() {
    Map<Integer, Integer> map = new HashMap<>();
    map.put(5, 500);
    map.put(1, 1200);
    map.put(2, 30);
    map.put(6, 550);
    map.put(4, 1300);
    System.out.println("정렬 전");
    map.forEach((k,v) -> System.out.println(k + "-"+ v));
    System.out.println();
    System.out.println("정렬 후");
    List<Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
    list.sort(Entry.comparingByValue());
    list.forEach(System.out::println);
  }

}
