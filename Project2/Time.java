

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.text.DecimalFormat;

public class Time {
	
	/** Get CPU time in nanoseconds. */
	public static long getCpuTime( ) {
	    ThreadMXBean bean = ManagementFactory.getThreadMXBean( );
	    return bean.isCurrentThreadCpuTimeSupported( ) ?
	        bean.getCurrentThreadCpuTime( ) : 0L;
	}
	 
	/** Get user time in nanoseconds. */
	public static long getUserTime( ) {
	    ThreadMXBean bean = ManagementFactory.getThreadMXBean( );
	    return bean.isCurrentThreadCpuTimeSupported( ) ?
	        bean.getCurrentThreadUserTime( ) : 0L;
	}

	
	public static void main(String[] args) {
		 int e = 0;
		
		 double startcpuTimeNano = getCpuTime( );
		 double startUserTimeNano   = getUserTime( );
		
		 for(int i =0;i<1000000000;i++)
			 for(int j =0;j<10000000;j++)
			      e = e + i; 

		 double taskUserTimeNano    = getUserTime( ) - startUserTimeNano;
		 double cpuTimeNano  = getCpuTime( ) - startcpuTimeNano;
	
		 System.out.println("User time: "+taskUserTimeNano/1000000000+ "  seconds");
		 System.out.println("CPU Time: "+cpuTimeNano/1000000000+ "  seconds");
	
		 
	}
}
// Reference: http://nadeausoftware.com/articles/2008/03/java_tip_how_get_cpu_and_user_time_benchmarking#UsingaSuninternalclasstogetJVMCPUtime
