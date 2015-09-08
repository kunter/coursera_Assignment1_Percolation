package org.hakb.connect;

public class FastFind {
    int[] array;

    public FastFind(int[] array) {
        this.array = array.clone();

    }

    public void union(int p, int q) {
        int prevValue=array[p];
        for (int i = 0; i < array.length; i++) {
            if (array[i] == prevValue) {
                array[i] = array[q];
            }
        }
    }

    public boolean connected(int p, int q) {
        if (array[p] == array[q]) {
            return true;
        }
        return false;
    }

    public int[] getArray() {
        return array;
    }

    public static  void test(){
        int[] arr={0,1,2,3,4,5,6,7,8,9};

        FastFind fastFind=new FastFind(arr);
        fastFind.union(5,1);
        fastFind.union(4,5);
        fastFind.union(5,9);
        fastFind.union(7,9);
        fastFind.union(5, 8);
        fastFind.union(1,0);

        int[] expectedArray={0,0,2,3,0,0,6,0,0,0};

        for (int i = 0; i < expectedArray.length; i++) {
            System.out.println(String.format("%b e= %d , a=%d", expectedArray[i]== fastFind.getArray()[i],expectedArray[i], fastFind.getArray()[i]));
        }

    }

    public static void main(String[] args){
        test();
    }


}
