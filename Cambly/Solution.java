package com.company.Cambly;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import javafx.util.Pair;

public class Solution {

  public static void main(String[] args) throws IOException {
    String str1 =
        "From the moment the first immigrants arrived on these shores, generations of parents have worked hard\n"
            + "\n"
            + "and sacrificed whatever is necessary so that their children could have the same chances they had; or\n"
            + "\n"
            + "the chances they never had. Because while we could never ensure that our children would be rich or\n"
            + "\n"
            + "successful; while we could never be positive that they would do better than their parents, America is\n"
            + "\n"
            + "about making it possible to give them the chance. To give every child the opportunity to try. Education\n"
            + "\n"
            + "is still the foundation of this opportunity. And the most basic building block that holds that\n"
            + "\n"
            + "foundation together is still reading. At the dawn of the 21st century, in a world where knowledge truly\n"
            + "\n"
            + "is power and literacy is the skill that unlocks the gates of opportunity and success, we all have a\n"
            + "\n"
            + "responsibility as parents and librarians, educators and citizens, to instill in our children a love of\n"
            + "\n"
            + "reading so that we can give them the chance to fulfill their dreams.";
    helper(str1);
  }


  private static void helper(String input) throws IOException {
    // validate the input
    if (input == null || input.length() == 0) {
      return;
    }
    // map to keep track of words and frequency
    HashMap<String, Integer> freq = new HashMap<>();
    // add all possible symbols
    HashSet<Character> symbols = new HashSet<>();
    symbols.add(' ');
    symbols.add('!');
    symbols.add('?');
    symbols.add(',');
    symbols.add(':');
    symbols.add('.');
    symbols.add(';');
    symbols.add('\n');
    StringBuilder sb = new StringBuilder();
    int len = input.length();
    // start to process the input string
    char[] chars = input.toCharArray();
    for (int i = 0; i <= len; i++) {
      if (i == len || symbols.contains(chars[i])) {
        if (sb.length() > 0) {
          // if encounter a space or new line, add the current string to the map and reset the StringBuilder
          String key = sb.toString().toLowerCase();
          freq.put(key, freq.getOrDefault(key, 0) + 1);
          sb = new StringBuilder();
        }
      } else if (Character.isLetter(chars[i])) {
        // if encounter a letter, append it to the StringBuilder
        sb.append(chars[i]);
      }
    }
    // sort the list by the frequency first and next the letter order
    Comparator<Pair<String, Integer>> comp = new Comparator<Pair<String, Integer>>() {
      @Override
      public int compare(Pair<String, Integer> o1, Pair<String, Integer> o2) {
        if (o1.getValue().equals(o2.getValue())) {
          return o1.getKey().compareTo(o2.getKey());
        }
        return o2.getValue() - o1.getValue();
      }
    };
    List<Pair<String, Integer>> res = new LinkedList<>();
    for (String key : freq.keySet()) {
      res.add(new Pair<>(key, freq.get(key)));
    }
    res.sort(comp);

    BufferedWriter writer = new BufferedWriter(new FileWriter("result1.text"));
    for (Pair pair : res) {
      System.out.println(pair.getKey() + " " + pair.getValue());
      writer.write(pair.getKey() + " " + pair.getValue());
      writer.newLine();
    }

    writer.close();
  }

}
