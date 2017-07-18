package test;

import java.util.Arrays;
import java.util.Scanner;

public class charupaixu {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int temp=0;
        System.out.println("请输入排序前的元素：");
        Scanner sc=new Scanner(System.in);    
        int[] sort=new int[6];
        //利用sort数组存储从键盘输入的数字
        for(int i=0;i<sort.length;i++){
            sort[i]=sc.nextInt();
        }
        //插入开始
        for(int i=0;i<sort.length-1;i++){
            for(int j=i+1;j>0;j--){
                if(sort[j]<sort[j-1]){
                    temp=sort[j-1];
                    sort[j-1]=sort[j];
                    sort[j]=temp;
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