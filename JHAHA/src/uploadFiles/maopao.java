package test;

import java.util.Scanner;
import java.lang.reflect.Array;
import java.util.Arrays;
public class maopao {
    public static void main(String[] args) {
        int temp=0;
        System.out.println("请输入排序前的元素：");
        Scanner sc=new Scanner(System.in);    
        int[] sort=new int[6];
        //利用sort数组存储从键盘输入的数字
        for(int i=0;i<sort.length;i++){
            sort[i]=sc.nextInt();
        }
//        System.out.print(sort[1]);
        //冒泡开始
        for(int i=0;i<sort.length-1;i++){
            for(int j=0;j<sort.length-i-1;j++){
            	
                if(sort[j]>sort[j+1]){
                    temp=sort[j];
                    sort[j]=sort[j+1];
                    sort[j+1]=temp;                    
                }
                System.out.println(Arrays.toString(sort));
            }
        }
        //输出排序后的数组
        for(int i=0;i<sort.length;i++){
            System.out.print(sort[i]+"  ");
        }
    }
}