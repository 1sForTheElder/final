package test;

import java.util.Scanner;

public class kuaisupaixu {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] sort=new int[10];
        //�Ӽ���¼��Ԫ��
        inputArr(sort);

        quickSort(sort,0,sort.length-1);

        System.out.println("���������У�");
        printArr(sort);
    }

    //��������һ�λ����㷨
    public static int Partition(int[] sort,int low,int high){
        int key=sort[low];
        while(low<high){
            //���Ҳ࿪ʼɨ��
            while(key<sort[high]){
                high--;
            }
            if(key>sort[high]){
                int temp=key;
                key=sort[high];
                sort[high]=temp;
            }

            //����࿪ʼɨ��
            while(sort[low]<key){
                low++;
            }
            if(sort[low]>key){
                int temp=key;
                key=sort[low];
                sort[low]=temp;
            }
        }
        return low;
    }

    //���η�
    public static void quickSort(int[] sort,int low,int high){
        if(low<high){
            int middle=Partition(sort,low,high);
            quickSort(sort,low,middle-1);//����������н�������
            quickSort(sort,middle+1,high);//���ұ������н�������
        }
    }

    //����һ�������Ӽ�������������Ԫ��
    public static void inputArr(int[] sort){
        Scanner sc = new Scanner(System.in);
        System.out.println("������������Ԫ��:");
        for(int i=0;i<sort.length;i++){
            sort[i]=sc.nextInt();
        }
    }

    //����һ�������������Ԫ��
    public static void printArr(int[] sort){
        for(int i=0;i<sort.length;i++){
            System.out.print(sort[i]+"  ");
        }
    }

}