/**
 * Merge sort for integer list.
 * sorted list is from small to big.
 */
public class MergeSort {
    public static int[] MergeSort(int[] arr){
        int[] returnList = new int[arr.length];
        if(arr.length == 1){
            returnList = arr;
        }
        else{

        }
        return returnList;
    }

    /**
     * Merge two arrays into a single sorted array.
     * @param arr1 first sorted array
     * @param arr2 second sorted array
     * @return a new sorted array containing all elements from arr1 and arr2
     */
    public static int[] Merge(int[] arr1, int[] arr2){
        int size = arr1.length + arr2.length;
        int[] returnArr = new int[size];

        int pointer = 0;
        int pointer1 = 0;
        int pointer2 = 0;

        while((pointer1 < arr1.length) || (pointer2 < arr2.length)){
            if(pointer1 < arr1.length && pointer2 < arr2.length){
                int x1 = arr1[pointer1];
                int x2 = arr2[pointer2];
                if(x1 <= x2){
                    returnArr[pointer] = x1;
                    pointer1 ++;
                    pointer ++;
                }
                else{
                    returnArr[pointer] = x2;
                    pointer2 ++;
                    pointer ++;
                }
            }
            else if(pointer1 == arr1.length){
                int x = arr2[pointer2];
                returnArr[pointer] = x;
                pointer ++;
                pointer2 ++;
            }
            else if(pointer2 == arr2.length){
                int x = arr1[pointer1];
                returnArr[pointer] = x;
                pointer ++;
                pointer1 ++;
            }
        }
        return returnArr;
    }
}
