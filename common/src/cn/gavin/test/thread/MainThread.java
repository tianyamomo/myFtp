/**
 * 
 *MainThread.java 
 *
 *@author gavin.jiang
 *@email 820367229@qq.com
 *@date 2017-5-18
 *@version 0.1
 */
package cn.gavin.test.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author gavin.jiang
 *
 */
public class MainThread implements Runnable{
	private static ExecutorService executor = Executors.newFixedThreadPool(10);
	private static ExecutorService executor2 = Executors.newFixedThreadPool(5);
	public void start() {
		for(int i = 0; i < 8; i++){
			FirstThreadProcess first = new FirstThreadProcess("i",i+2);
			Future<String> ft1 = executor.submit(first);
			try {
				String str = ft1.get();
				if(null != str){
					ChildThreadOFFirstThread child = new ChildThreadOFFirstThread(str.substring(str.lastIndexOf("_"), str.length()),i+1);
					Future<String> ft = executor2.submit(child);
					if(null != ft.get()){
						System.out.println(ft.get());
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
	}
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		this.start();
	}
	
}