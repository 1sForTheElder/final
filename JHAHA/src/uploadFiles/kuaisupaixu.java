package test;

import java.util.Scanner;

public class kuaisupaixu {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] sort=new int[10];
        //从键盘录入元素
        inputArr(sort);

        quickSort(sort,0,sort.length-1);

        System.out.println("排序后的序列：");
        printArr(sort);
    }

    //快速排序一次划分算法
    public static int Partition(int[] sort,int low,int high){
        int key=sort[low];
        while(low<high){
            //从右侧开始扫描
            while(key<sort[high]){
                high--;
            }
            if(key>sort[high]){
                int temp=key;
                key=sort[high];
                sort[high]=temp;
            }

            //从左侧开始扫描
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

    //分治法
    public static void quickSort(int[] sort,int low,int high){
        if(low<high){
            int middle=Partition(sort,low,high);
            quickSort(sort,low,middle-1);//对左边子序列进行排序
            quickSort(sort,middle+1,high);//对右边子序列进行排序
        }
    }

    //定义一个方法从键盘输入带排序的元素
    public static void inputArr(int[] sort){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入待排序的元素:");
        for(int i=0;i<sort.length;i++){
            sort[i]=sc.nextInt();
        }
    }

    //定义一个方法输出数组元素
    public static void printArr(int[] sort){
        for(int i=0;i<sort.length;i++){
            System.out.print(sort[i]+"  ");
        }
    }

}