import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class MergeSortTest {

//    @Test
//    public void mergeTest() {
//        int[] arr1 = {1, 3, 5};
//        int[] arr2 = {2, 4, 6};
//
//        int[] result = MergeSort.Merge(arr1, arr2);
//
//        assertThat(result).asList().containsExactly(1, 2, 3, 4, 5, 6).inOrder();
//    }
//
//    @Test
//    public void mergeTest_arr1ExhaustedFirst() {
//        // arr1 全部小于 arr2，arr1 先填完，剩下的循环全靠 arr2 那个 else if 分支填
//        int[] arr1 = {1, 2, 3};
//        int[] arr2 = {4, 5, 6, 7, 8};
//
//        int[] result = MergeSort.Merge(arr1, arr2);
//
//        assertThat(result).asList().containsExactly(1, 2, 3, 4, 5, 6, 7, 8).inOrder();
//    }
//
//    @Test
//    public void mergeTest_arr2ExhaustedFirst() {
//        // 反过来，arr2 先填完，剩下的循环全靠 arr1 那个 else if 分支填
//        int[] arr1 = {3, 4, 5, 6, 7};
//        int[] arr2 = {1, 2};
//
//        int[] result = MergeSort.Merge(arr1, arr2);
//
//        assertThat(result).asList().containsExactly(1, 2, 3, 4, 5, 6, 7).inOrder();
//    }
//
//    @Test
//    public void mergeTest_arr1Empty() {
//        int[] arr1 = {};
//        int[] arr2 = {1, 2, 3};
//
//        int[] result = MergeSort.Merge(arr1, arr2);
//
//        assertThat(result).asList().containsExactly(1, 2, 3).inOrder();
//    }
//
//    @Test
//    public void mergeTest_arr2Empty() {
//        int[] arr1 = {1, 2, 3};
//        int[] arr2 = {};
//
//        int[] result = MergeSort.Merge(arr1, arr2);
//
//        assertThat(result).asList().containsExactly(1, 2, 3).inOrder();
//    }
//
//    @Test
//    public void mergeTest_bothEmpty() {
//        int[] arr1 = {};
//        int[] arr2 = {};
//
//        int[] result = MergeSort.Merge(arr1, arr2);
//
//        assertThat(result).isEmpty();
//    }
//
//    @Test
//    public void mergeTest_duplicateValuesAcrossArrays() {
//        // 两边有相等的元素，测 x1 <= x2 的分支（相等时优先取 arr1 的）
//        int[] arr1 = {1, 3, 3, 5};
//        int[] arr2 = {2, 3, 4};
//
//        int[] result = MergeSort.Merge(arr1, arr2);
//
//        assertThat(result).asList().containsExactly(1, 2, 3, 3, 3, 4, 5).inOrder();
//    }
//
//    @Test
//    public void mergeTest_negativeNumbers() {
//        int[] arr1 = {-5, -1, 3};
//        int[] arr2 = {-3, 0, 2};
//
//        int[] result = MergeSort.Merge(arr1, arr2);
//
//        assertThat(result).asList().containsExactly(-5, -3, -1, 0, 2, 3).inOrder();
//    }

    @Test
    public void MergeSortTest(){
        int toBeSorted[] = new int[]{10, 2, 5, 3, 7, 1, 6};
        int result[] = MergeSort.MergeSort(toBeSorted);
        int expected[] = new int[]{1, 2, 3, 5, 6, 7, 10};

        assertThat(result).isEqualTo(expected);
    }
}
