package test;

import java.util.Scanner;

public class xierpaixu {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] sort = new int[6];
        inputArr(sort);
        shellSort(sort);
        printArr(sort);
    }

    public static void shellSort(int[] sort) {
        int j = 0;
        int temp = 0;
        //设置间隔d
        for (int d = sort.length / 2; d > 0; d /= 2) {
            //排序开始
            for (int i = d; i < sort.length; i++) {
                temp = sort[i];
                for (j = i - d; j >= 0; j -= d) {
                    if (temp < sort[j]) {
                        sort[j + d] = sort[j];
                    } else {
                        break;
                    }
                }
                sort[j + d] = temp;
            }

        }
    }

    public static void inputArr(int[] sort){
        System.out.println("请输入排序前的元素：");
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < sort.length; i++) {
            sort[i] = sc.nextInt();
        }
    }

    public static void printArr(int[] sort){
        for(int i=0;i<sort.length;i++){
            System.out.print(sort[i]+"  ");
        }
    }
}