package test;

import java.util.Arrays;
import java.util.Scanner;

public class charupaixu {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int temp=0;
        System.out.println("����������ǰ��Ԫ�أ�");
        Scanner sc=new Scanner(System.in);    
        int[] sort=new int[6];
        //����sort����洢�Ӽ������������
        for(int i=0;i<sort.length;i++){
            sort[i]=sc.nextInt();
        }
        //���뿪ʼ
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
        //�������������
        for(int i=0;i<sort.length;i++){
            System.out.print(sort[i]+"  ");
        }
    }

}