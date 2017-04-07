package io.priesnit;

import java.util.Stack;

/**
 * Created by vince on 4/6/2017.
 * holds up to capacity many different unsigned integers.
 * constant time put, remove and minimum element retrival
 */
class TrieElementCounter {

    private final Node tree;
    private Stack<Node> nodePool;

    public TrieElementCounter(int capacity) {
        this.tree = new Node();
        this.nodePool = new Stack<>();
        for (int i = 0; i < capacity * 32; i++) {
            nodePool.push(new Node());
        }
    }

    private void freeUnused(Node n) {
        if (n.Counter == 0 && n.zero == null && n.one == null) {
            if (n == n.parent.one) {
                log('1');
                n.parent.one = null;
            } else if (n == n.parent.zero) {
                log('0');
                n.parent.zero = null;
            }

            freeUnused(n.parent);

            n.one = null;
            n.zero = null;
            n.parent = null;
            n.Counter = 0;
            nodePool.push(n);
        }
    }

    // returns the number of v's
    public long put(int v) {
        Node n = this.tree;
        log("Putting " + v + ": ");

        for (char c : toBinary(v)) {
            if (c == '0') {
                if (n.zero == null) {
                    n.zero = nodePool.pop();
                    n.zero.parent = n;
                }
                log('0');
                n = n.zero;
            } else if (c == '1') {
                if (n.one == null) {
                    n.one = nodePool.pop();
                    n.one.parent = n;
                }
                log('1');
                n = n.one;
            } else {
                throw new IllegalStateException("Unexpected char in binarystring: " + c);
            }
        }
        n.Counter++;
        log(", count is now " + n.Counter + "\n");
        return n.Counter;
    }

    private char[] toBinary(int v) {
        String string = Integer.toBinaryString(v);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 32 - string.length(); i++) {
            sb.append('0');
        }
        return sb.append(string).toString().toCharArray();
    }

    public long remove(int v) {
        Node n = this.tree;
        log("Removing " + v + ": ");
        for (char c : toBinary(v)) {
            if (c == '0') {
                log('0');
                n = n.zero;
            } else if (c == '1') {
                log('1');
                n = n.one;
            } else {
                throw new IllegalStateException("Unexpected char in binarystring: " + c);
            }
        }

        int result = --n.Counter;
        log(", count is now " + result + "\n");

        freeUnused(n);
        return result;
    }

    private void log(char c) {
        log("" + c);
    }

    private void log(String s) {
        //System.out.print(s);
    }


    public int getMin() {
        Node n = this.tree;
        log("Finding min: ");
        int min = 0;
        for (int i = 0; i < 32; i++) {
            min = 2 * min;
            if (n.zero != null) {
                log('0');
                n = n.zero;
            } else {
                log('1');
                n = n.one;
                min++;
            }
        }
        log(", got " + min + ", count is " + n.Counter + "\n");
        return min;
    }

    private static class Node {
        Node parent;
        Node zero;
        Node one;
        int Counter;
    }

}

