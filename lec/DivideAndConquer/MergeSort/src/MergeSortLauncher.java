public class MergeSortLauncher {
    public static void printArray(int[] arr) {
        int size = arr.length;
        if(size == 0){
            System.out.println("The array is null");
        }
        else {
            for (int i = 0; i < size; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }

    static void main(String[] args) {
        int toBeSorted[] = new int[]{10, 2, 5, 3, 7, 1, 6};
        int expected[] = MergeSort.MergeSort(toBeSorted);

        System.out.println("The original array is:");
        MergeSortLauncher.printArray(toBeSorted);
        System.out.println("The sorted array is:");
        MergeSortLauncher.printArray(expected);
    }
}
