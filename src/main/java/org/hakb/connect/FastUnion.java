package org.hakb.connect;

public class FastUnion {
    int array[];

    public FastUnion(int[] array) {
        this.array = array;
    }

    public void union(int p, int q) {
        int rootP = findRoot(array[p]);
        int rootQ = findRoot(array[q]);
        array[rootP] = rootQ;
    }

    public boolean connected(int p, int q) {
        if (findRoot(p) == findRoot(q)) {
            return true;
        }

        return false;
    }

    private int findRoot(int p) {
        if (array[p] != p) {
            return findRoot(array[p]);
        } else {
            return p;
        }

    }

    public int[] getArray() {
        return array;
    }

    public static void test() {
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        FastUnion fastUnion = new FastUnion(arr);
        fastUnion.union(4, 3);
        fastUnion.union(3, 8);
        fastUnion.union(6, 5);
        fastUnion.union(9, 4);
        fastUnion.union(2, 1);
        fastUnion.union(5, 0);
        fastUnion.union(7, 2);
        fastUnion.union(6, 1);
        fastUnion.union(7, 3);

        int[] expectedArray = {1, 8, 1, 8, 3, 0, 5, 1, 8, 8};

        for (int i = 0; i < expectedArray.length; i++) {
            System.out.println(String.format("%b e= %d , a=%d", expectedArray[i] == fastUnion.getArray()[i], expectedArray[i], fastUnion.getArray()[i]));
        }

    }

    public static void main(String[] args) {
        test();
    }

}
