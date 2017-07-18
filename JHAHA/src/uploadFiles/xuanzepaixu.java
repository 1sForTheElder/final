package test;

import java.util.Arrays;
import java.util.Scanner;

public class xuanzepaixu {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] sort=new int[6];
        inputArr(sort);
        selectSort(sort);
        System.out.println("排序后的序列为：");
        printArr(sort);
    }

    //选择排序开始
    public static void selectSort(int[] sort){
        //进行length-1趟排序
        for(int i=0;i<sort.length-1;i++){
            int index=i;
            //排序开始
            for(int j=i+1;j<sort.length;j++){
            	System.out.println(Arrays.toString(sort));
                if(sort[index]>sort[j]){
                    index=j;
                }
            }
            if(index!=i){
                int temp=sort[index];
                sort[index]=sort[i];
                sort[i]=temp;
            }
            
        }
    }

    //从键盘输入待排序序列的值
    public static void inputArr(int[] sort){
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入待排序序列：");
        for(int i=0;i<sort.length;i++){
            sort[i]=sc.nextInt();
        }
    }

    //输出数组
    public static void printArr(int[] sort){
        for(int i=0;i<sort.length;i++){
            System.out.print(sort[i]+"  ");
        }
    }

}