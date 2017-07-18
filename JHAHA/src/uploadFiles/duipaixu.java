public class test{
public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] sort = new int[10];
        inputArr(sort);
        // 调用堆排序方法
        heapSort(sort);
        System.out.println("排序后的序列为：");
        printArr(sort);
    }
