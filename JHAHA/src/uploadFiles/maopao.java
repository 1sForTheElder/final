package test;

import java.util.Scanner;
import java.lang.reflect.Array;
import java.util.Arrays;
public class maopao {
    public static void main(String[] args) {
        int temp=0;
        System.out.println("����������ǰ��Ԫ�أ�");
        Scanner sc=new Scanner(System.in);    
        int[] sort=new int[6];
        //����sort����洢�Ӽ������������
        for(int i=0;i<sort.length;i++){
            sort[i]=sc.nextInt();
        }
//        System.out.print(sort[1]);
        //ð�ݿ�ʼ
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
        //�������������
        for(int i=0;i<sort.length;i++){
            System.out.print(sort[i]+"  ");
        }
    }
}