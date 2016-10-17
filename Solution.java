import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class TrieNode {
    TrieNode[] arr;
    int leafCount;
    boolean childless;
    
    public TrieNode() {
        arr = new TrieNode[26];
        leafCount = 0;
    }
}

class Trie {
    TrieNode root;
    
    public Trie() {
        root = new TrieNode();
    }
    
    public void insert(String word) {
        TrieNode p = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (p.arr[index] == null) {
                p.arr[index] = new TrieNode();
                if (!(p.childless)) {
                    p.childless = true;
                }
            }
            p = p.arr[index];
        }
        p.leafCount += 1;
    }
    
    public int countAllChildren(TrieNode node) {
        int childCount = 0;
        if (node != null) {
            childCount += node.leafCount;
            if (!(p.childless)) {
                for (int i = 0; i < 26; i++) {
                    if (node.arr[i] != null) {
                        if (node.arr[i].childless) {
                            childCount += node.arr[i].leafCount;
                        }
                        else {
                            childCount += countAllChildren(node.arr[i]);
                        }
                    }
                }
            }
        }
        return childCount;
    }
    
    
    public int numberOfWordsThatStartWith(String prefix) {
        TrieNode p = searchNode(prefix);
        return countAllChildren(p);
    }
    
    public TrieNode searchNode(String s) {
        TrieNode p = root;
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            if (p.arr[index] != null) {
                p = p.arr[index];
            }
            else {
                return null;
            }
        }
        
        return p == root ? null : p;
    }
}

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Trie names = new Trie();
        boolean timed = false;
        if (args.length != 0) {
            timed = args[0].compareTo("timed") == 0;
        }
        long startTime = System.currentTimeMillis();
        for(int a0 = 0; a0 < n; a0++){
            String op = in.next();
            String contact = in.next();
            if (op.compareTo("add") == 0) {
                names.insert(contact);
            }
            else if (op.compareTo("find") == 0) {
                System.out.println(names.numberOfWordsThatStartWith(contact));
            }
        }
        long endTime = System.currentTimeMillis();
        if (timed) {
            System.out.println("Total execution time: " + (endTime - startTime) / 1000.0 + " seconds.");
        }
    }
}