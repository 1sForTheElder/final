package test;

import java.util.Arrays;
import java.util.Scanner;

public class xuanzepaixu {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] sort=new int[6];
        inputArr(sort);
        selectSort(sort);
        System.out.println("����������Ϊ��");
        printArr(sort);
    }

    //ѡ������ʼ
    public static void selectSort(int[] sort){
        //����length-1������
        for(int i=0;i<sort.length-1;i++){
            int index=i;
            //����ʼ
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

    //�Ӽ���������������е�ֵ
    public static void inputArr(int[] sort){
        Scanner sc=new Scanner(System.in);
        System.out.println("��������������У�");
        for(int i=0;i<sort.length;i++){
            sort[i]=sc.nextInt();
        }
    }

    //�������
    public static void printArr(int[] sort){
        for(int i=0;i<sort.length;i++){
            System.out.print(sort[i]+"  ");
        }
    }

}