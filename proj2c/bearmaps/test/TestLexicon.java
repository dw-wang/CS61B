package bearmaps.test;

import sun.awt.image.ImageWatched;

import javax.swing.plaf.basic.BasicScrollPaneUI;
import java.util.*;
import java.util.stream.Collectors;

public class TestLexicon {
    private static class Lexicon {
        TrieNode root;

        public Lexicon() {
            root = new TrieNode();
        }

        public Lexicon(String[] words) {
            root = new TrieNode();
            for (String word: words) {
                this.add(word);
            }
        }

        public void add(String word) {
            char[] c_array = word.toCharArray();
            TrieNode curr = root;
            int pos = 0;
            for (char c: c_array) {
                pos++;
                if (!curr.suffixes.containsKey(c)) {
                    curr.suffixes.put(c, new TrieNode());
                }
                curr.suffixes.get(c).isWord = (pos == word.length());
                curr = curr.suffixes.get(c);
            }
        }

        private TrieNode find(String prefix) {
            char[] c_array = prefix.toCharArray();
            TrieNode curr = root;
            for (char c: c_array) {
                if (!curr.suffixes.containsKey(c)) {
                    return null;
                }
                curr = curr.suffixes.get(c);
            }
            return curr;
        }

        public boolean containsPrefix(String prefix) {
            return find(prefix) != null;
        }

        public boolean contains(String word) {
            TrieNode found = find(word);
            return found != null && found.isWord == true;
        }

        public List<String> getAllWordsToList() {
            List<String> words = new LinkedList<>();
            getWordsToListHelper(root, "", words);
            return words;
        }

        public List<String> getPrefixWordsToList(String prefix) {
            TrieNode start = find(prefix);
            List<String> words = new LinkedList<>();
            getWordsToListHelper(start, "", words);
            return words;
        }

        private void getWordsToListHelper(TrieNode root, String prefix, List<String> words) {
            for (char c: root.suffixes.keySet()) {
                prefix += String.valueOf(c);
                if (root.suffixes.get(c).isWord) {
                    words.add(prefix);
                }
                getWordsToListHelper(root.suffixes.get(c), prefix, words);
                prefix = prefix.substring(0,prefix.length()-1);
            }
        }


        private class TrieNode {
            public boolean isWord;
            Map<Character, TrieNode> suffixes;

            public TrieNode(){
                isWord = false;
                suffixes = new HashMap<>();
            }
        }

    }

    public static void testAdd() {
        Lexicon lex = new Lexicon();
        lex.add("Hi");
        lex.add("Hello");
        lex.add("He");
    }

    public static void testStringConstructor() {
        String[] words = {"Hi", "Hello", "He", "She", "It"};
        Lexicon lex = new Lexicon(words);
    }

    public static void testContains() {
        String[] words = {"Hi", "Hello", "He", "She", "It"};
        Lexicon lex = new Lexicon(words);
        assert(lex.contains("Hi")==true);
        assert(lex.contains("Hello")==true);
        assert(lex.contains("She")==true);
        assert(lex.contains("It")==true);
        assert(lex.contains("He")==true);
        assert(lex.contains("Dog")==false);
        assert(lex.contains("Him")==false);
        assert(lex.contains("Iter")==false);
        assert(lex.contains("H")==false);
    }

    public static void testGetWordsToList() {
        String[] words = {"Hi", "Hello", "He", "She", "Sell", "It", "High", "Hill"};
        Lexicon lex = new Lexicon(words);
        List<String> listWords = lex.getAllWordsToList();
        for (String w: listWords) {
            System.out.println(w);
        }
    }



    public static void main(String[] args) {
        //testAdd();
        //testStringConstructor();
        //testContains();
        testGetWordsToList();
    }
}
