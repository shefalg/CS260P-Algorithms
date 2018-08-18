import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class DSelect {
	public static void main(String args[])throws Exception
	{
//		Scanner obj=new Scanner(System.in);
//		System.out.println("Enter number of elements in array.");
//		int num=Integer.parseInt(obj.nextLine());
//		System.out.println("Enter number of groups for DSelect");
//		int n=Integer.parseInt(obj.nextLine());
		
		int kValues[]=new int[20];
		Random r =new Random();
		for(int loop=0;loop<20;loop++){
		kValues[loop]=r.nextInt(5000-1)+1;
		}
		int g=5; //Number of groups to be divided in
		for(int n=5000;n<=1000000;n+=5000){ //Working for 200 values in range 5000-10000000 
		int ar[]=new int[n];
		Random rand=new Random();
		for(int i=0; i<n; i++){
			ar[i]=rand.nextInt(1000000)+1;
		}
		
		long timesum=0;
		for(int c=0;c<20;c++){ 
		//Averaging 20 values of elapsed time.
		long startTime=Time.getCpuTime();
		int result=DSelect.DeterministicSelect(ar,kValues[c]-1, g);
		long finishTime=Time.getCpuTime();
		timesum+=(finishTime-startTime);
		//System.out.println("Result: "+result);
		}
		System.out.println(timesum/20);
//		System.out.println("Number of groups, Time in nano seconds:"+ g+", "+ (timesum/20));
		
		}
		
	}
	static int DeterministicSelect(int ar[],int k,int d)
	{
		int arraySize=ar.length;
		//If single element in array, return the element
		if(arraySize==1)
			return ar[0];
		//Divide the array into [arraySize/d] groups
		int numOfGroups=(int)Math.ceil((double)arraySize/d);
		//Create an array for baby medians of size [arraySize/d].
		int medians[]=new int[numOfGroups];
		int mediansArrayIndex=0;
		int c=0;
		int i=0;
		int median=0;
		//Call insertion sort for each group
		for(i=0;i<arraySize;i++)
		{
		if(c<d)
		{ //Add elements to subArray until d.
			c++;
		}
		else	
		{  //Call sort for this group and find median and append to medians array.
			DSelect.Sort(ar,(i-c),i-1);
			median=DSelect.findMedianIndex(i-c,i);
			medians[mediansArrayIndex]=ar[median];
			mediansArrayIndex++;
			c=1;
		}
		}
		if(c>0) //For the last group having less than d elements. (In case arraySize%d !=0 )
		{
		DSelect.Sort(ar,(i-c),i-1);
		median=DSelect.findMedianIndex(i-c,i);
		medians[mediansArrayIndex]=ar[median];
		mediansArrayIndex++;
		}
		//Find median of each sorted group, which is equal of middle element.
		median=DSelect.findMedianOfMedians(medians,mediansArrayIndex);
		int medianIndexInInputArray=DSelect.findMedianIndexInInputArray(ar, 0, ar.length, median);
		//Partition array around median, into 3 parts, lesser, equal to and greater than elements
		int partition=DSelect.partition(ar, 0,ar.length-1, medianIndexInInputArray);
		// now, depending on value of k either return median value, or recursively call DeterministicSelect in one of the partitions
		if(k==partition)
			return ar[partition];
		else if(k<partition)
			return DeterministicSelect(DSelect.getSubArray(ar,0,partition-1),k,d);
		else
			return DeterministicSelect(DSelect.getSubArray(ar, partition+1,arraySize-1),(k-partition-1),d);
	}
	static int findMedianIndexInInputArray(int[] ar,int low, int high,int element)
	{ 
		for(int i=low;i<=high;i++)
		{
			if(ar[i]==element)
				return i;
		}
		return 0;
	}
	static int findMedianOfMedians(int[] medians,int length)
	{
		//#TODO
		//Code change //DSelect.Sort(medians, 0, length-1);
		Arrays.sort(medians);
		int medianIndex=DSelect.findMedianIndex(0, length-1);
		return medians[medianIndex];
	}
	static int[] getSubArray(int ar[],int low,int high)
	{
		int subArray[]=new int[high-low+1];
		int k=0;
		for(int i=low;i<=high;i++)
			subArray[k++]=ar[i];
		return subArray;
	}
	static int findMedianIndexInArray(int medianIndex,int d)
	{
		if(d%2==0)
			return (medianIndex)*d+(d/2)-1;
		else
			return (medianIndex)*d+(d/2);
	}
	static int partition(int ar[],int low,int high,int pivot) //Here pivot is the median element index in input array.
	{
		int temp=0;
		//Swap pivot element and last element of array
		int i=low-1;
		temp=ar[pivot];
		ar[pivot]=ar[high];
		ar[high]=temp;		
		for(int j=low;j<high;j++)
		{
			if(ar[j]<=ar[high])
			{
				i++;
				temp=ar[i];
				ar[i]=ar[j];
				ar[j]=temp;
			}
		}
		temp=ar[i+1];
		ar[i+1]=ar[high];
		ar[high]=temp;
		return i+1;
	}
	static void printArray(int ar[],int n)
	{
		for(int i=0;i<n;i++)
		{
			if(i!=(n-1))
				System.out.print(ar[i]+",");
			else
				System.out.println(ar[i]);
		}
		
	}
	static int findMedianIndex(int startIndex,int endIndex)
	{
		int n=endIndex-startIndex+1;
		if(n==1)
			return startIndex;
		if(n%2==0)
			return startIndex+(n/2-1);
		return startIndex+(n/2);
	}
	static void Sort(int ar[],int lowerIndex,int higherIndex) //Insertion sort
	{
		int i,key,j=0;
		for(i=lowerIndex+1;i<=higherIndex;i++)
		{
			key=ar[i];
			j=i-1;
			while(j>=lowerIndex && ar[j]>key)
			{
				ar[j+1]=ar[j];
				j=j-1;	
			}
			ar[j+1]=key;		
		}	
	}
}
