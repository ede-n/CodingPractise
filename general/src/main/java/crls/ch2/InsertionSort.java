package crls.ch2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.Objects.requireNonNull;

public class InsertionSort {
  private static final Logger log = LoggerFactory.getLogger(InsertionSort.class);

  public void inPlaceSorting(int[] nums) {
    requireNonNull(nums, "nums must be non null");

    // if there is only one element, there is nothing to sort
    if (nums.length == 1) {
      return;
    }

    // Loop Invariant:
    // nums[0..j] is a sorted array
    for(int i = 0; i < nums.length; i++) {
      int key = nums[i];
      int j = i - 1;
      while (j >= 0 && nums[j] > key) {
          nums[j + 1] = nums[j];
          j--;
      }
      nums[j+1] = key;
    }
  }

  public static void main(String[] args) {
    log.info("{} Invoked!", InsertionSort.class.getSimpleName());

    InsertionSort ins = new InsertionSort();

    int[] a = {6,5,4,3,2,1};
    ins.inPlaceSorting(a);

    log.info("{}", a);
  }
}
