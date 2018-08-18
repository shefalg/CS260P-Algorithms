import java.io.BufferedReader;
import java.text.*;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Random;
public class QuickSelect {
	public static void main(String[] args)throws Exception {
		Scanner obj=new Scanner(System.in);
//		System.out.println("Quick Select Algorithm. \n Enter which smallest number you want?");
//		int k=Integer.parseInt(obj.nextLine());
//		System.out.println("Quickselect: Enter number of elements in array.");
//		int num=Integer.parseInt(obj.nextLine());
		
		int valuesOfK[]=new int[20];
		Random rand1=new Random();
		for(int j=0;j<20;j++)
		{
			valuesOfK[j]=rand1.nextInt(5000-1)+1;
		}
		for(int l=5000;l<=1000000;l+=5000)
		{
		int ar[]=new int[l];
		Random rand=new Random();
		for(int i=0;i<l;i++)
		{
			ar[i]=rand.nextInt(l-1)+1;
		}
		long timeSum=0;
		for(int c=0;c<20;c++)
		{
		long startTime=Time.getCpuTime();
		int result=QuickSelect.QSelect(ar, valuesOfK[c]-1);
		long finishTime=Time.getCpuTime();
		timeSum+=(finishTime-startTime);
//		System.out.println("Result: "+result);
		}
		System.out.println(timeSum/20);
		}
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
	static int QSelect(int ar[],int k)
	{
		return recurseQSelect(ar,0,ar.length-1,k);
	}
	static int recurseQSelect(int ar[],int left,int right,int k)
	{
		if(left==right)
			return ar[left];
		int pivotIndex=RandomPivot(left, right);
		pivotIndex=partition(ar,left,right,pivotIndex);
		if(k==pivotIndex)
			return ar[k];
		else if(k<pivotIndex)
			return recurseQSelect(ar, left, pivotIndex-1, k);
		else
			return recurseQSelect(ar, pivotIndex+1, right, k);
	}
	static int RandomPivot(int left,int right)
	{
		return left+(int)Math.floor(Math.random()*(right-left+1));
	}
	static int partition(int ar[],int low,int high,int pivot) //Here pivot is random element of the array.
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
		//Swap ar[i+1] and pivot.....CHECK!
		temp=ar[i+1];
		ar[i+1]=ar[high];
		ar[high]=temp;
		return i+1;
	}

}
