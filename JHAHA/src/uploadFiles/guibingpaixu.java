package test;

import java.util.Scanner;

public class guibingpaixu {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] sort = new int[10];
        inputArr(sort);

        // ���ù鲢���򷽷�
        mergeSort(sort,0,sort.length-1);
        System.out.println("����������Ϊ��");
        printArr(sort);
    }

    //�鲢����ʼ
    public static void mergeSort(int[] sort,int low,int high){
        int mid=(low+high)/2;
        if(low<high){
            //���
            mergeSort(sort,low,mid);
            //�ұ�
            mergeSort(sort,mid+1,high);
            //���ҹ鲢
            Merge(sort,low,mid,high);
        }
    }

    //һ�ι鲢
    public static void Merge(int[] sort,int low,int mid,int high){
        int i=low;
        int j=mid+1;
        int k=0;
        int[] result=new int[high-low+1];

        //����������������Сֵȡ����
        while(i<=mid&&j<=high){
            if(sort[i]<sort[j]){
                result[k++]=sort[i++];
            }else{
                result[k++]=sort[j++];
            }
        }
        // �����ʣ�������������
        while(i<=mid){
            result[k++]=sort[i++];
        }
        // ���ұ�ʣ�������������
        while(j<=high){
            result[k++]=sort[j++];
        }

        // ���������е�������sort����
        for(int n=0;n<result.length;n++){
            sort[n+low]=result[n];
        }
    }

    // �Ӽ�����������
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
        System.out.println();
    }
}