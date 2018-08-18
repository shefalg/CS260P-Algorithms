import java.util.*;
/*
 *Linear space approach based Dynamic programming based algorithm for finding longest common subsequence, given by Professor Hirschberg.
 * */
public class LCS_DH {
	public static void main(String ar[])
	{
		for(int i=5;i<=20000;i+=50)
//		for(int i=100000;i<=100000;i+=3)
		{
		String words[]=new String[2];
		words=generateRandomWords(i);		
		System.out.println("Length of LCS  : "+getLongestCommonSubsequence(words[0],words[1]));
		long startTime=Time.getCpuTime();
//		int res=getLongestCommonSubsequence(words[0],words[1]);
		long finishTime=Time.getCpuTime();
		System.out.println("Time:"+(finishTime-startTime));
		}
	}
	public static int getLongestCommonSubsequence(String a,String b)
	{
		int m=a.length();
		int n=b.length();
		int dp[][]=new int[2][n+1];
		for(int i=0;i<m;i++)
		{
			reinitializeArray(dp,n);
			for(int j=0;j<n;j++)
			{
				if(a.charAt(i)==b.charAt(j))
					dp[1][j+1]=1+dp[0][j];
				else
					dp[1][j+1]=Math.max(dp[0][j+1],dp[1][j]);		
			}
		}
		return dp[1][n];
	}
	static void reinitializeArray(int[][] ar,int col)
	{
		for(int i=0;i<col;i++)
		{
			ar[0][i]=ar[1][i];
		}
	}
	public static String[] generateRandomWords(int length)
	{
	    String[] randomStrings = new String[2];
	    Random random = new Random();
	    for(int i = 0; i < 2; i++)
	    {
	    	char[] word = new char[length];
	        for(int j = 0; j < word.length; j++)
	        {
	            word[j] = (char)('a' + random.nextInt(26));
	        }
	        randomStrings[i] = new String(word);
	    }
	    return randomStrings;
	}
}
