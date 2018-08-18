import java.util.*;
/*
 * Regular Dynamic programming based algorithm for finding longest common subsequence
 * */
public class LCS {
	public static void main(String ar[])
	{
//		for(int i=5;i<=21275;i+=50)
		for(int i=5;i<=20000;i+=50)
		{
		String words[]=new String[2];
		words=LCS_DH.generateRandomWords(i);
		
		long startTime=Time.getCpuTime();
		System.out.println("Length of LCS: "+getLongestCommonSubsequence(words[0],words[1]));
//		int result=getLongestCommonSubsequence(words[0],words[1]);
		long finishTime=Time.getCpuTime();
		System.out.println("Time:"+(finishTime-startTime));
		}
	
	}
	
	public static int getLongestCommonSubsequence(String a, String b){
		int m = a.length();
		int n = b.length();
		int[][] dp = new int[m+1][n+1];
	 
		for(int i=0; i<=m; i++){
			for(int j=0; j<=n; j++){
				if(i==0 || j==0){
					dp[i][j]=0;
				}else if(a.charAt(i-1)==b.charAt(j-1)){
					dp[i][j] = 1 + dp[i-1][j-1];
				}else{
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		return dp[m][n];
	}
}
