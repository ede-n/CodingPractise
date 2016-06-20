package crls.ch2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SelectionSort {

  private static final Logger log = LoggerFactory.getLogger(SelectionSort.class);

  public SelectionSort() {
    log.info("Initialized SelectionSort...");
  }

  public void sortInPlace(int[] arr) {
    log.info("Received arrary for sorting {}", arr);

    for (int i = 0; i < arr.length ; i++) {
      int smallValueIndex = i;
      for (int j = i; j < arr.length; j++ ) {
        if (arr[j] < arr[smallValueIndex]){
          smallValueIndex = j;
        }
      }
      int temp = arr[i];
      arr[i] = arr[smallValueIndex];
      arr[smallValueIndex] = temp;
    }

    log.info("Sorted array: {}", arr);
  }

  private int smallestValueIndex(int[] arr,
                                 int beginIndexInclusive,
                                 int endIndexInclusive) {
    log.info("beginIndexInclusive: {}; endIndexInclusive: {}; arr: {}", beginIndexInclusive, endIndexInclusive, arr);
    int smallestValue = arr[beginIndexInclusive];
    int smallestIndex = beginIndexInclusive;

    for (int i = beginIndexInclusive; i <= endIndexInclusive ; i++) {
      if (arr[i] < arr[smallestIndex]) {
        smallestIndex = i;
      }
    }

    return smallestIndex;
  }

  public static void main(String[] args) {
    int [] ints = {7, 9, 5, 11, 37, 48, 20};
    SelectionSort selectionSort = new SelectionSort();
    selectionSort.sortInPlace(ints);
  }
}
