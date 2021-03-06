package lab9;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * COMP 3021
 * 
This is a class that prints the maximum value of a given array of 90 elements

This is a single threaded version.

Create a multi-thread version with 3 threads:

one thread finds the max among the cells [0,29] 
another thread the max among the cells [30,59] 
another thread the max among the cells [60,89]

Compare the results of the three threads and print at console the max value.

 * 
 * @author valerio
 *
 */
public class FindMax{
	// this is an array of 90 elements
	// the max value of this array is 9999
	static int[] array = { 1, 34, 5, 6, 343, 5, 63, 5, 34, 2, 78, 2, 3, 4, 5, 234, 678, 543, 45, 67, 43, 2, 3, 4543,
			234, 3, 454, 1, 2, 3, 1, 9999, 34, 5, 6, 343, 5, 63, 5, 34, 2, 78, 2, 3, 4, 5, 234, 678, 543, 45, 67, 43, 2,
			3, 4543, 234, 3, 454, 1, 2, 3, 1, 34, 5, 6, 5, 63, 5, 34, 2, 78, 2, 3, 4, 5, 234, 678, 543, 45, 67, 43, 2,
			3, 4543, 234, 3, 454, 1, 2, 3 };

	public static void main(String[] args) {
		new FindMax().printMax();
	}

	public void printMax() {
		// this is a single threaded version
		MyTask task1 = new MyTask(0,29);
		MyTask task2 = new MyTask(30,59);
		MyTask task3 = new MyTask(60,89);
		Thread t1 = new Thread(task1);
		Thread t2 = new Thread(task2);
		Thread t3 = new Thread(task3);
		ExecutorService executor = Executors.newFixedThreadPool(3);
		executor.execute(t1);
		executor.execute(t2);
		executor.execute(t3);
		executor.shutdown();
		
		int max = task1.getOutput();
		if (task2.getOutput()>max)
			max = task2.getOutput();
		if (task3.getOutput()>max)
			max = task3.getOutput();
		System.out.println("the max value is " + max);
	}
	
	class MyTask implements Runnable {
		private int begin;
		private int end;
		private int output;
		public MyTask(int begin, int end) {
			this.begin = begin;
			this.end = end;
		}
		public int getOutput() {
			return output;
		}
		
		@Override
		public void run() {
			output = findMax(begin,end);
		}
	}

	/**
	 * returns the max value in the array within a give range [begin,range]
	 * 
	 * @param begin
	 * @param end
	 * @return
	 */
	private int findMax(int begin, int end)  {
		// you should NOT change this function
		int max = array[begin];
		for (int i = begin + 1; i <= end; i++) {
			if (array[i] > max) {
				max = array[i];
			}
		}
		return max;
	}
}
