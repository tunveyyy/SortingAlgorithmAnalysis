import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class LogSort {
    public List<Log> bubbleSort(List<Log> logs){
        for(int i = 0; i < logs.size(); i++){
            for(int j = 0; j < logs.size(); j++){
                 if(logs.get(i).compareTo(logs.get(j)) < 0)
                     swap(logs,i,j);
            }
        }

        return logs;
    }

    public List<Log> quickSort(List<Log> logs){
        Collections.sort(logs, (log1, log2) -> log1.compareTo(log2));
        return logs;
    }

    private int partition(List<Log> logs, int low, int high)
    {
        int middle = (high + low)/2;
        Log pivot = logs.get(middle);
        int i = (low-1); // index of smaller element
        for (int j=low; j<high; j++)
        {
            // If current element is smaller than the pivot
            if (logs.get(j).compareTo(pivot) < 0)
            {
                i++;
                // swap arr[i] and arr[j]
                swap(logs, i, j);

            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        swap(logs, i+1, high);

        return i+1;
    }
    private void sort(List<Log> logs, int low, int high)
    {
        if (low < high)
        {
            /* pi is partitioning index, arr[pi] is
              now at right place */
            int pi = partition(logs, low, high);

            // Recursively sort elements before
            // partition and after partition
            sort(logs, low, pi-1);
            sort(logs, pi+1, high);
        }
    }
    public List<Log> mergeSort(List<Log> logs){
        divide(logs, 0 , logs.size() - 1);
        return logs;
    }

    private void swap(List<Log> logs, int i, int j){
        Log temp = logs.get(i);
        logs.set(i,logs.get(j));
        logs.set(j, temp);

    }

    private void merge(List<Log> logs, int start, int mid, int end){

        List<Log> mergedList = new ArrayList<>();
        int left = start;
        int right = mid + 1;

        while(left <= mid && right <= end){
            if(logs.get(right).compareTo(logs.get(left)) < 0){
                mergedList.add(logs.get(right));
                right ++;
            }
            else{
                mergedList.add(logs.get(left));
                left++;
            }
        }
        while(left <= mid){
            mergedList.add(logs.get(left));
            left++;
        }

        while(right <= end){
            mergedList.add(logs.get(right));
            right++;
        }
        int i = 0;
        int j = start;
        while( i < mergedList.size()){
            logs.set(j, mergedList.get(i++));
            j++;
        }

    }

    private void divide(List<Log> logs, int start, int end){
        if((end - start) > 0 ){
            int mid = (end + start)/2;
            divide(logs,start,mid);
            divide(logs, mid + 1, end);
            merge(logs, start, mid, end );
        }
    }

}


