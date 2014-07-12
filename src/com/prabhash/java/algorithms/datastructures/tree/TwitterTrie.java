/*
 * Given a list of names. Find whether a particular name occurs inside a given tweet or not. If found return true otherwise
 * false Time complexity should be less than O(n).
 * 
 * Ex: "Katy Perry","Ronan Keating" given as a list of string.
 * List<String> names;
 * bool findName(String tweet)
 * {
 * 
 * }
 */
package com.prabhash.java.algorithms.datastructures.tree;

/**
 * Load list of names into trie
 * Then compare the suffixes of given tweet with trie if found then return true 
 */
public class TwitterTrie {
    public TwitterTrie() {
        super();
    }

    public static void main(String[] args) {
        TwitterTrie twitterTrie = new TwitterTrie();
        TwitterTrie.Trie trie = twitterTrie.new Trie();
        String[] strs = { "Prakash", "Swathi", "Rama" };
        for (String str : strs) {
            trie.insert(str);
        }
        trie.print();
        System.out.println(twitterTrie.found("This is ramakrishna again", trie));
        System.out.println(twitterTrie.found("This is gopi again", trie));

    }

    public boolean found(String tweet, TwitterTrie.Trie root) {
        boolean b = false;
        for (int i = 0; i < tweet.length(); i++) {
            b = root.search(tweet.substring(i));
            if (b)
                break;
        }
        return b;
    }

    class Trie {
        private static final int ALPHA_SIZE = 26;
        private static final int BUF_SIZE = 1024;
        private Trie root;
        private Trie[] trieArray;
        private char data;
        private boolean word;

        public Trie() {
            super();
        }

        public void insert(String data) {
            Trie root = this.getRoot();
            if (root == null) {
                root = new Trie(' ');
                this.setRoot(root);
            }
            for (int i = 0; i < data.length(); i++) {
                if (root.getTrieArray()[atoi(data.charAt(i))] == null) {
                    root.getTrieArray()[atoi(data.charAt(i))] = new Trie(data.charAt(i));
                }
                root = root.getTrieArray()[atoi(data.charAt(i))];
            }
            root.setWord(true);
        }

        public void print() {
            Trie root = this.getRoot();
            if (root == null) {
                return;
            }
            char[] buffer = new char[BUF_SIZE];
            int index = 0;
            traverse(root, index, buffer);
        }

        public void traverse(Trie root, int index, char[] buffer) {
            if (root == null)
                return;
            if (root.isWord())
                System.out.println(new String(buffer, 0, index));
            for (int i = 0; i < ALPHA_SIZE; i++) {
                if (root.getTrieArray()[i] != null) {
                    buffer[index] = root.getTrieArray()[i].getData();
                    traverse(root.getTrieArray()[i], index + 1, buffer);
                }

            }
        }

        public boolean search(String pattern) {
            Trie root = this.getRoot();
            if (root == null)
                return false;
            int x = 0;
            for (int i = 0; i < pattern.length(); i++) {
                if (atoi(pattern.charAt(i)) > 26) {
                    continue;
                } else if (root.getTrieArray()[atoi(pattern.charAt(i))] != null) {
                    root = root.getTrieArray()[atoi(pattern.charAt(i))];
                } else {
                    break;
                }
            }
            if (root.isWord()) {
                return root.isWord();
            }
            return false;
        }

        public int atoi(char ch) {
            int i = 0;
            if (ch >= 65 && ch <= 90)
                i = ch - 65;
            else if (ch >= 97 && ch <= 122)
                i = ch - 97;
            else
                i = (int)ch;

            return i;

        }

        private Trie(char data) {
            this.setData(data);
            this.setTrieArray(new Trie[ALPHA_SIZE]);
        }

        public void setRoot(Trie root) {
            this.root = root;
        }

        public Trie getRoot() {
            return root;
        }

        public void setTrieArray(Trie[] trieArray) {
            this.trieArray = trieArray;
        }

        public Trie[] getTrieArray() {
            return trieArray;
        }

        public void setData(char data) {
            this.data = data;
        }

        public char getData() {
            return data;
        }

        public void setWord(boolean word) {
            this.word = word;
        }

        public boolean isWord() {
            return word;
        }
    }
}