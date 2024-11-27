package org.example;

public class Heap {

    static int MXN = 1 << 18;// 2^18
    static int NODE_LEN = 2 * MXN;

    static Node[] nodes = new Node[NODE_LEN];

    int size;

    public Heap(int size) {
        this.size = size + 1;
        MXN = nearestPowerOfTwo(size);
        NODE_LEN = 2 * MXN;
        nodes = new Node[NODE_LEN];
    }

    private int nearestPowerOfTwo(int x) {
        double log = Math.log(x) / Math.log(2);
        Double pow = Math.pow(2, Math.ceil(log));
        return pow.intValue();
    }

    public int maxPos;

    void init(int pos, int lo, int hi) {

        if (hi - lo == 1) {
            Node node = new Node(pos);
            if (lo >= size) {
                node.left = 0;
                node.right = 0;
                node.max = 0;
            } else {
                node.left = 1;
                node.right = 1;
                node.max = 1;
                //System.out.println("pos = " + pos + " x = " + lo);
            }
            node.len = 1;
            nodes[pos] = node;
            return;
        }

        init(2 * pos, lo, (lo + hi) / 2);
        init(2 * pos + 1, (lo + hi) / 2, hi);

        nodes[pos] = new Node(pos, true);
    }

    public int change(final int switchPos) {
        int pos = switchPos + MXN;

        Node node = nodes[pos];

        node.alternate();

        for (int i = pos / 2; i > 0; i /= 2) {
            nodes[i].merge();
        }

        return nodes[1].max;
    }


    static class Node {

        public int left;
        public int right;
        public int max;
        public int len;

        boolean start;
        boolean end;

        int pos;

        public Node(int pos) {
            this(pos, false);
        }

        public Node(int pos, boolean isMerge) {
            this.pos = pos;
            if (isMerge) {
                merge();
            }
        }

        public void merge() {
            Node a = getLeft();
            Node b = getRight();
            start = a.start;
            end = b.end;
            len = a.len + b.len;
            left = a.left;
            if (a.len == a.left && a.end != b.start) {
                left += b.left;
            }

            right = b.right;
            if (b.len == b.right && a.end != b.start) {
                right += a.right;
            }
            max = Math.max(Math.max(a.max, b.max), Math.max(left, right));
            if (a.end != b.start) {
                max = Math.max(max, a.right + b.left);
            }
        }


        private Node getLeft() {
            return nodes[pos * 2];
        }

        private Node getRight() {
            return nodes[pos * 2 + 1];
        }

        public void alternate() {
            start = !start;
            end = start;
        }
    }


}

