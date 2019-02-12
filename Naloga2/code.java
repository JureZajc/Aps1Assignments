import java.util.Scanner;

public class Naloga2 {
	static int swaps;
	static int compare;
	static int swapsMin;
	static int compareMin;
	static int swapsMax;
	static int compareMax;
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String string = scanner.next();
		String method = scanner.next();
		String direction = scanner.next();
		
		//scanner.useDelimiter("\r\n");
		
		int count = 0;
		//int something = 1;
		int[] arr = new int[100000];
		while (scanner.hasNextLine()) {
			while (scanner.hasNextInt()){
				String nextIntString = scanner.next();
				int nextInt = Integer.parseInt(nextIntString);
				arr[count] = nextInt;
				//something++;
				
				count++;
			}int justAnotherVariable = 0;
			
			for (int i = 0; i < arr.length; i++) {
				if (arr[i] != 0){
					justAnotherVariable++;
				}
				
			}
			int count22 = 0;
			int[] arr2 = new int[justAnotherVariable];
			for (int i = 0; i < arr.length; i++) {
				if(arr[i] != 0){
					arr2[count22] = arr[i];
					count22++;
				}
			}				
			runCommand(string, method, direction, arr2);
			System.exit(0);
		}			
		//scanner.close();
		
		}	
	
	static void runCommand(String string, String method, String direction, int[] arr2){
		if (string.equals("trace")){
			if(method.equals("select")){
				if(direction.equals("up")){
					selectUpWith(arr2);
				}
				else if (direction.equals("down")) {
					selectDownWith(arr2);
				}
			}
			else if (method.equals("insert")) {
				if (direction.equals("up")){
					insertUpWith(arr2);
				}
				else if (direction.equals("down")) {
					insertDownWith(arr2);
				}
			}
			else if (method.equals("heap")) {
				if(direction.equals("down")){
					sortUp(arr2);
				}
				else if (direction.equals("up")) {
					heapSort(arr2);
				}
			}
			else if (method.equals("bubble")) {
				if(direction.equals("down")){
					bubbleSortDown(arr2);
				}
				else if (direction.equals("up")) {
					bubbleSort(arr2);
				}
			}
			else if (method.equals("merge")) {
				if(direction.equals("up")) {
					for (int i = 0; i < arr2.length; i++) {
						System.out.print(arr2[i] + " ");
					}
					System.out.println();
					mergeUp(arr2, 0, arr2.length-1);
				}
				else {
					for (int i = 0; i < arr2.length; i++) {
						System.out.print(arr2[i] + " ");
					}
					System.out.println();
					mergeDown(arr2, 0, arr2.length-1);
				}
				
			}
			
		}
		else if (string.equals("count")) {
			if(method.equals("insert")){
				if(direction.equals("up")){
					insertDownWO(arr2);
				}
			}else if (method.equals("select")) {
				if(direction.equals("up")){
					selectionCount(arr2);
				}
				else if (direction.equals("down")) {
					selectionCount(arr2);
				}
			}
			else if (method.equals("bubble")) {
				if(direction.equals("up")){
					bubbleSortCount(arr2);
				}
				else if (direction.equals("down")) {
					bubbleSortCountDown(arr2);
				}
			}
			else if (method.equals("radix")) {
				if(direction.equals("up")){
					radixCount(arr2);
				}
				else if (direction.equals("down")) {
					radixCount(arr2);
				}
			}
		}
	}
	//left is for left index, and right for the right index of the sub-array of arr to be sorted
	 private static void mergeDown(int[] arr, int left, int right) {
		// TODO Auto-generated method stub	
		 if (left < right) {
			 //middle point
			int m = (left + right) / 2;			
			for (int i = left; i <= m; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.print("| ");
			for (int i = m + 1; i <= right; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
			//sort first and second half
			mergeDown(arr, left, m);
			mergeDown(arr, m + 1, right);
			//merge the sorted halves
			mergeDown2(arr, left, right, m);			
			for (int i = left; i <= right; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();			
		}	
	}
    
	private static void mergeDown2(int[] arr, int left, int right, int m) {
		// TODO Auto-generated method stub
		//sizes of two subarrays to be merged
		int n = m - left + 1;
		int n2 = right - m;
		//temp arrays
		int[] leftArr = new int[n];
		int[] rightArr = new int[n2];
		//copy data to temp arrays, leftArr and rightArr
		for (int i = 0; i < n; i++) {
			swaps++;
			leftArr[i] = arr[left + i];
		}
		for (int i = 0; i < n2; i++) {
			swaps++;
			rightArr[i] = arr[m + 1 + i];
		}
		//start with merging , initial indexes are 0
		int i = 0; 
		int j = 0; 
		int k = left;
		while (i < n && j < n2) {
			compare++;
			swaps++;	//this swap is probabbly wrong. 
			if (leftArr[i] >= rightArr[j]) {
				arr[k] = leftArr[i];
				i++;
			} else {
				arr[k] = rightArr[j];
				j++;
			}	
			k++;
		}
		//copy the remaining elements of leftArr, if there are any
		while (i < n) {
			arr[k] = leftArr[i];
			i++;k++;swaps++;
		}
		//copy the remaining elements of rightArr, if there are any
		while (j < n2) {
			arr[k] = rightArr[j];
			j++;k++;swaps++;
		}		
	}

	private static void radixCount(int[] arr) {
		// TODO Auto-generated method stub
		 int max = arr[0];
		 for (int i = 0; i < arr.length; i++) {
	            if (max < arr[i]) {
	                max = arr[i];
	            }
	        }
		int length = String.valueOf(max).length();
		int n = arr.length;
		int result =  length*2*n;
		System.out.printf("%d %d | %d %d | %d %d", result, result, result, result, result, result);
		
	}

	private static void mergeUp(int[] arr, int left, int right) {
		// TODO Auto-generated method stub
		if (left < right) {
			int m = (left + right) / 2;			
				for (int i = left; i <= m; i++) {
					System.out.print(arr[i] + " ");
				}
				System.out.print("| ");
				for (int i = m + 1; i <= right; i++) {
					System.out.print(arr[i]+ " ");
				}
				System.out.println();
			
			mergeUp(arr, left, m);
			mergeUp(arr, m + 1, right);
			merge(arr, left, right, m);
			
				for (int i = left; i <= right; i++) {
					System.out.print(arr[i] + " ");
				}
				System.out.println();			
		}
	}
	
	 
	private static void merge(int[] arr, int left, int right, int m) {
		int n = m - left + 1;
		int n2 = right - m;
		int[] leftArr = new int[n];
		int[] rightArr = new int[n2];
		for (int i = 0; i < n; i++) {
			swaps++;
			leftArr[i] = arr[left + i];
		}
		for (int i = 0; i < n2; i++) {
			swaps++;
			rightArr[i] = arr[m + 1 + i];
		}
		int i = 0; 
		int j = 0; 
		int k = left;
		while (i < n && j < n2) {
			compare++;
			swaps++;	
				if (leftArr[i] <= rightArr[j]) {
					arr[k] = leftArr[i];
					i++;
				} else {
					arr[k] = rightArr[j];
					j++;
				}
			k++;
		}
		while (i < n) {
			arr[k] = leftArr[i];
			i++;k++;swaps++;
		}
		while (j < n2) {
			arr[k] = rightArr[j];
			j++;k++;swaps++;
		}
	}
	 //basic insertion sort from geeks for geeks. 
	static int[] insertUp(int[] arr) {
	        int n = arr.length; 
	        for (int i=1; i<n; ++i) 
	        { 
	            int key = arr[i]; 
	            int j = i-1; 
	  
	            /* Move elements of arr[0..i-1], that are 
	               greater than key, to one position ahead 
	               of their current position */
	            while (j>=0 && arr[j] > key) 
	            { 
	                arr[j+1] = arr[j]; 
	                j = j-1; 
	            } 
	            arr[j+1] = key; 
	        }
			return arr; 
	}
	
	static int[] insertDown(int[] arr) {
		int n = arr.length; 
        for (int i=1; i<n; ++i) 
        { 
            int key = arr[i]; 
            int j = i-1; 
  
            /* Move elements of arr[0..i-1], that are 
               greater than key, to one position ahead 
               of their current position */
            while (j>=0 && arr[j] < key) 
            { 
                arr[j+1] = arr[j]; 
                j = j-1; 
            } 
            arr[j+1] = key; 
        }
		return arr; 
	}
	
	static void selectionCount(int[] arr){
		   int count22 = 0;
		   int[] minArr = insertUp(arr);
		   int[] maxArr = insertDown(arr);
		   for (int i = 0; i < arr.length - 1; i++)
	        {
	           
	            int index = i;
	            for (int j = i + 1; j < arr.length; j++)
	                if (arr[j] < arr[index]) {
	                    index = j;
	            		
	                }
	            int smallerNumber = arr[index];  
	            arr[index] = arr[i];
	            arr[i] = smallerNumber;
	            count22+=3;
	        }
		   for (int i = 0; i < minArr.length - 1; i++)
	        {
	           
	            int index = i;
	            for (int j = i + 1; j < minArr.length; j++)
	                if (minArr[j] < minArr[index]) {
	                    index = j;
	            		
	                }
	            int smallerNumber = minArr[index];  
	            minArr[index] = minArr[i];
	            minArr[i] = smallerNumber;
	            swapsMin+=3;
	        }
		   for (int i = 0; i < maxArr.length - 1; i++)
	        {
	           
	            int index = i;
	            for (int j = i + 1; j < maxArr.length; j++)
	                if (maxArr[j] < maxArr[index]) {
	                    index = j;
	            		
	                }
	            int smallerNumber = maxArr[index];  
	            maxArr[index] = maxArr[i];
	            maxArr[i] = smallerNumber;
	            swapsMax+=3;
	        }
		   int n = arr.length;
		   compare = (n*(n-1))/2;
		   swaps = (n*(n-1))/4;
		   
		   System.out.printf("%d %d | %d %d | %d %d", count22, compare, swapsMin, compare, swapsMax, compare);
		}
	
	static void bubbleSortCountDown(int arr[]) {
		int temp = 0;
		int n = arr.length;
		int swaps = 0;
		int comp = 0;
		int number = 0;
		int index = 0;
		boolean swap = true;
		while(swap && number < n-1) {
			swap = false;
			for (int i = n - 1; i > number; i--) {
				comp++;
				if (arr[i - 1] < arr[i]) {
					temp = arr[i];
					arr[i] = arr[i - 1];
					arr[i - 1] = temp;
					index = i;
					swap = true;
					swaps += 3;
				}
			}
			if (swap) number = index;
			else number = arr.length - 1;
		}
		System.out.printf("%d %d | %d %d | %d %d", swaps, comp, 0, n-1 , 3*n*(n-1)/2, n*(n-1)/2); //for count
	}
		   
	static void bubbleSortCount(int arr[]){
		int temp = 0;
		int n = arr.length;
		int swaps = 0;
		int comp = 0;
		int number = 0;
		int index = 0;
		boolean swap = true;
		while(swap && number < n-1) {
			swap = false;
			for (int i = n - 1; i > number; i--) {
				comp++;
				if (arr[i - 1] > arr[i]) {
					temp = arr[i];
					arr[i] = arr[i - 1];
					arr[i - 1] = temp;
					index = i;
					swap = true;
					swaps += 3;
				}
			}
			if (swap) number = index;
			else number = arr.length - 1;
		}		
		System.out.printf("%d %d | %d %d | %d %d", swaps, comp, 0, n-1 , 3*n*(n-1)/2, n*(n-1)/2);		      
	} 
	
	
	static void bubbleSort(int[] arr){
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		int temp = 0;
		int n = arr.length;		
		int number = 0;
		int index = 0;
		boolean swap = true;
		while(swap && number < n-1) {
			swap = false;
			for (int i = n - 1; i > number; i--) {
				compare++;
				if (arr[i - 1] > arr[i]) {
					temp = arr[i];
					arr[i] = arr[i - 1];
					arr[i - 1] = temp;
					index = i;
					swap = true;
					swaps += 3;
					
				}

			}
			if (swap) number = index;
			else number = arr.length - 1;	
			for (int i1 = 0; i1 < number; i1++) {
				System.out.print(arr[i1] + " ");
			}				
			System.out.print("|");
			for (int i1 = number; i1 < arr.length; i1++) {
				System.out.print(" " + arr[i1]);
			}
			System.out.println();
		}	
	}
	
	
	static void bubbleSortDown(int[] arr){
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		int temp = 0;
		int n = arr.length;
		int number = 0;
		int index = 0;
		boolean swap = true;
		while(swap && number < n-1) {
			swap = false;
			for (int i = n - 1; i > number; i--) {
				compare++;
				if (arr[i - 1] < arr[i]) {
					temp = arr[i];
					arr[i] = arr[i - 1];
					arr[i - 1] = temp;
					index = i;
					swap = true;
					swaps += 3;
				}
				
			}
			if (swap) number = index; 
			else number = arr.length - 1;
			
			for (int i1 = 0; i1 < number; i1++) {
				System.out.print(arr[i1] + " ");
			}
			System.out.print("|");
			for (int i1 = number; i1 < arr.length; i1++) {
				System.out.print(" " + arr[i1]);
			}
			System.out.println();
		}
		
		/*for (int i = 0; i < number; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.print("|");
		for (int i = number; i < arr.length; i++) {
			System.out.print(" " + arr[i]);
		}
		System.out.println();	*/
		//System.out.printf("%d %d | %d %d | %d %d", swaps, comp, 0, n-1 , n/2, n*(n-1)/2); for count
	}

	static void insertDownWith(int[] arr){
		int n = arr.length;
		int count = 1;
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
        for (int i=1; i<n; ++i)
        {
            int key = arr[i];
            int j = i-1;
            while (j>=0 && arr[j] < key)
            {
                arr[j+1] = arr[j];
                j = j-1;
            }
            arr[j+1] = key;
            for (int j1 = 0; j1 <= count; j1++) {
				System.out.print(arr[j1] + " ");
			}
            count++;
            System.out.print("| ");
            for (int h = count; h < arr.length; h++){
            	System.out.print(arr[h]+ " ");
            }
            System.out.println();
        }
	}
	
	static void insertDownWO(int[] arr){
		int n = arr.length;
		@SuppressWarnings("unused")
		int swapsMax = 0;
		int key;
        for (int i=1; i<n; i++)
        {
           key = arr[i];
           swaps++;
           for (int j = i; j >0 ; j--) {
        	   compare++;
        	   if (arr[j-1]> key) {
        		    arr[j] = arr[j - 1];
					arr[j - 1] = key;
					if (j > 1) swaps++;
				    else swaps += 2;
					
			} 
        	   	else {
        	   		arr[j] = key;
        	   		swaps++;
        	   		break;	
			}
        }
	}                             
        System.out.printf("%d %d | %d %d | %d %d", swaps, compare, 2*(n-1), n-1,  ((n-2) * (n+1)), (n*n - n)/2);
}
	
	static void insertCount(int[] arr){
		int n = arr.length;
		int swaps = 0, comp = 0;
		for (int i = 1; i < arr.length; i++) {
			int j = i;
			comp++;
			while((j>0) && (arr[j-1] > arr[j])){
				if(arr[j-1]>arr[j]){
					comp++;
				}
			}
			int temp = arr[j-1];
			arr[j-1] = arr[j];
			arr[j] = temp;
			j--;
			swaps+=3;
		}
		System.out.printf("%d %d | %d %d | %d %d", swaps, comp, 2*(n-1), n-1, swaps, comp);
	}
	
	 static void swapBubble(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
	
	static void bubbleSortWith(int[] arr){
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		int count = 0;
		int start = 0, end = arr.length;
		while (end - start > 1) {
			for (int j1 = 0; j1 <= count; j1++) {
				System.out.print(arr[j1] + " ");
			}
	        count++;
	        System.out.print("| ");
	        for (int h = count; h < arr.length; h++){
	        	System.out.print(arr[h]+ " ");
	        }
	        System.out.println();
		    int lastSwap = 0;
		    
		    
		    for (int i = start + 1; i < end; i++) {
		        if (arr[i - 1] > arr[i]) {
		            swapBubble(arr, i - 1, i);
		            lastSwap = i;

		        }
		    }
		    
		    for (int i = end - 1; i > start; i--) {
		        if (arr[i - 1] > arr[i]) {
		            swapBubble(arr, i - 1, i);
		            lastSwap = i;

		        }
		    }
		    end = lastSwap;
		    start = lastSwap;
		}
	}
				
	static void selectUpWith(int[] arr){
		   int count = 0;
			for (int i = 0; i < arr.length; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
		   for (int i = 0; i < arr.length - 1; i++)
	        {
	           
	            int index = i;
	            for (int j = i + 1; j < arr.length; j++)
	                if (arr[j] < arr[index]) 
	                    index = j;

	            int smallerNumber = arr[index];  
	            arr[index] = arr[i];
	            arr[i] = smallerNumber;
	            for (int j = 0; j <= count; j++) {
					System.out.print(arr[j] + " ");
				}
	            count++;
	            System.out.print("| ");
	            for (int h = count; h < arr.length; h++){
	            	System.out.print(arr[h]+ " ");
	            }
	            System.out.println();
	        }
		}
	
	static void selectDownWith(int[] arr){
		   int count = 0;
			for (int i = 0; i < arr.length; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
		   for (int i = 0; i < arr.length - 1; i++)
	        {
	            int index = i;
	            for (int j = i + 1; j < arr.length; j++)
	                if (arr[j] > arr[index]) 
	                    index = j;

	            int smallerNumber = arr[index];  
	            arr[index] = arr[i];
	            arr[i] = smallerNumber;
	            for (int j = 0; j <= count; j++) {
					System.out.print(arr[j] + " ");
				}
	            count++;
	            System.out.print("| ");
	            for (int h = count; h < arr.length; h++){
	            	System.out.print(arr[h]+ " ");
	            }
	            System.out.println();
	        }
		}
	
	static void insertUpWith(int[] arr){
		int n = arr.length;
		int count = 1;
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
        for (int i=1; i<n; ++i)
        {
            int key = arr[i];
            int j = i-1;
            while (j>=0 && arr[j] > key)
            {
                arr[j+1] = arr[j];
                j = j-1;
            }
            arr[j+1] = key;
            for (int j1 = 0; j1 <= count; j1++) {
				System.out.print(arr[j1] + " ");
			}
            count++;
            System.out.print("| ");
            for (int h = count; h < arr.length; h++){
            	System.out.print(arr[h]+ " ");
            }
            System.out.println();
        }
	}
	
	static void heapSortCount(int arr[])
    {

        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--)
            heapifyCount(arr, n, i);
        for (int i=n-1; i>=0; i--)
        {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapifyCount(arr, i, 0);          
        }        
    }

    static void heapifyCount(int arr[], int n, int i)
    {
    	int largest = i;  
        int l = 2*i + 1;  
        int r = 2*i + 2;  

        if (l < n && arr[l] > arr[largest])
            largest = l;

        if (r < n && arr[r] > arr[largest])
            largest = r;

        if (largest != i)
        {
            int swap = arr[i];           
            arr[i] = arr[largest];        	
            arr[largest] = swap;
            heapifyCount(arr, n, largest);                        
            }        
    }
	
	static void heapSort(int arr[])
    {
    	for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
    	System.out.println();
        int n = arr.length;
        int count = n-1;

        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        for (int i=n-1; i>=0; i--)
        {

            int temp = arr[0];
            for (int j1 = 0; j1 <= count; j1++) {
    			System.out.print(arr[j1] + " ");
    		}
            count--;
            System.out.print("| ");
            for (int h = count + 2; h < arr.length; h++){
            	System.out.print(arr[h]+ " ");
            }
            System.out.println();
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);          
        }        
    }

    static void heapify(int arr[], int n, int i)
    {
    	int largest = i;  
        int l = 2*i + 1;  
        int r = 2*i + 2;  

        if (l < n && arr[l] > arr[largest])
            largest = l;

        if (r < n && arr[r] > arr[largest])
            largest = r;

        if (largest != i)
        {
            int swap = arr[i];            
            arr[i] = arr[largest];       	
            arr[largest] = swap;
            heapify(arr, n, largest);                        
            }       
    }
    
    static void sortUp(int arr[])
    {
    	for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
    	System.out.println();
        int n = arr.length;
        int count = n-1;

        for (int i = n / 2 - 1; i >= 0; i--)
            heapifyUp(arr, n, i);
        
        for (int i=n-1; i>=0; i--)
        {

            int temp = arr[0];
            for (int j1 = 0; j1 <= count; j1++) {
    			System.out.print(arr[j1] + " ");
    		}
            count--;
            System.out.print("| ");
            for (int h = count + 2; h < arr.length; h++){
            	System.out.print(arr[h]+ " ");
            }
            System.out.println();
            arr[0] = arr[i];
            arr[i] = temp;
            heapifyUp(arr, i, 0);          
        }      
    }

    static void heapifyUp(int arr[], int n, int i)
    {
    	int largest = i;  
        int l = 2*i + 1;  
        int r = 2*i + 2;  

        if (l < n && arr[l] < arr[largest])
            largest = l;

        if (r < n && arr[r] < arr[largest])
            largest = r;

        if (largest != i)
        {
            int swap = arr[i];           
            arr[i] = arr[largest];        	
            arr[largest] = swap;
            heapifyUp(arr, n, largest);
            }       
    }
    
    static void sortUpCount(int arr[])
    {
    	
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--)
            heapifyCount1(arr, n, i);
        
        for (int i=n-1; i>=0; i--)
        {
            int temp = arr[0];            
            arr[0] = arr[i];
            arr[i] = temp;
            heapifyCount1(arr, i, 0);          
        }        
    }

    static void heapifyCount1(int arr[], int n, int i)
    {
    	int largest = i;  
        int l = 2*i + 1;  
        int r = 2*i + 2;  

        if (l < n && arr[l] < arr[largest])
            largest = l;       

        if (r < n && arr[r] < arr[largest])
            largest = r;

        if (largest != i)
        {
            int swap = arr[i];            
            arr[i] = arr[largest];        	
            arr[largest] = swap;
            heapifyCount1(arr, n, largest);
            }        
    }
}


