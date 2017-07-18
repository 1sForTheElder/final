package test;

import java.util.Scanner;

public class guibingpaixu {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] sort = new int[10];
        inputArr(sort);

        // 调用归并排序方法
        mergeSort(sort,0,sort.length-1);
        System.out.println("排序后的序列为：");
        printArr(sort);
    }

    //归并排序开始
    public static void mergeSort(int[] sort,int low,int high){
        int mid=(low+high)/2;
        if(low<high){
            //左边
            mergeSort(sort,low,mid);
            //右边
            mergeSort(sort,mid+1,high);
            //左右归并
            Merge(sort,low,mid,high);
        }
    }

    //一次归并
    public static void Merge(int[] sort,int low,int mid,int high){
        int i=low;
        int j=mid+1;
        int k=0;
        int[] result=new int[high-low+1];

        //将两个子序列中最小值取出来
        while(i<=mid&&j<=high){
            if(sort[i]<sort[j]){
                result[k++]=sort[i++];
            }else{
                result[k++]=sort[j++];
            }
        }
        // 把左边剩余的数移入数组
        while(i<=mid){
            result[k++]=sort[i++];
        }
        // 把右边剩余的数移入数组
        while(j<=high){
            result[k++]=sort[j++];
        }

        // 把新数组中的数覆盖sort数组
        for(int n=0;n<result.length;n++){
            sort[n+low]=result[n];
        }
    }

    // 从键盘输入数据
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
        System.out.println();
    }
}