package org.hakb.connect;

public class QuickWeightUnion implements Connectable {
    int[] array;
    int[] arrayHeight;

    public QuickWeightUnion(int[] array) {
        this.array = array.clone();
        arrayHeight = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            arrayHeight[i] = 1;

        }
    }

    @Override
    public void union(int p, int q) {
        int rootP = findRoot(array[p]);
        int rootQ = findRoot(array[q]);
        if (arrayHeight[rootP] >= arrayHeight[rootQ]) {
            array[rootQ] = rootP;
            arrayHeight[rootP] = arrayHeight[rootP] + arrayHeight[rootQ];
        } else {
            array[rootP] = rootQ;
            arrayHeight[rootQ] = arrayHeight[rootQ] + arrayHeight[rootP];
        }

    }


    @Override
    public boolean connected(int p, int q) {
        if (findRoot(p) == findRoot(q)) {
            return true;
        }

        return false;
    }

    private int findRoot(int p) {
        int height = 0;
        if (array[p] != p) {
            return findRoot(height, array[p]);
        } else {
            return p;
        }

    }

    private int findRoot(int weight, int p) {
        if (array[p] != p) {
            return findRoot(weight + 1, array[p]);
        } else {
            return p;
        }

    }

    public int[] getArray() {
        return array;
    }

    public static void test() {
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        QuickWeightUnion quickWeightUnion = new QuickWeightUnion(arr);
        quickWeightUnion.union(9, 8);
        quickWeightUnion.union(3, 4);
        quickWeightUnion.union(6, 8);
        quickWeightUnion.union(0, 1);
        quickWeightUnion.union(2, 7);
        quickWeightUnion.union(2, 1);
        quickWeightUnion.union(3, 9);
        quickWeightUnion.union(6, 5);
        quickWeightUnion.union(5, 1);


        int[] expectedArray = {2, 0, 9, 9, 3, 9, 9, 2, 9, 9};

        for (int i = 0; i < expectedArray.length; i++) {
            System.out.println(String.format("i:%d %b e= %d , a=%d", i, expectedArray[i] == quickWeightUnion.getArray()[i], expectedArray[i], quickWeightUnion.getArray()[i]));
        }
        System.out.println(String.format("is connected 4-5 : %b", quickWeightUnion.connected(4, 5)));

    }

    public static void main(String[] args) {
        test();

    }

}
