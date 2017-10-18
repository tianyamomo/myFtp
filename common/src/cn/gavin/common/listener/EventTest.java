/**
 * 
 *EventTest.java 
 *
 *@author gavin.jiang
 *@email 820367229@qq.com
 *@date 2017-5-8
 *@version 0.1
 */
package cn.gavin.common.listener;

import static org.junit.Assert.fail;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

/**
 * @author gavin.jiang
 *
 */
public class EventTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void eventTest(){
		ExecutorService executor = Executors.newFixedThreadPool(3);
		
		for(int i = 0; i < 3; i++){
			EventProducer producer = new EventProducer();
			producer.setValue(i*2+2);
			CreateProducer create = new CreateProducer();
			create.setVal(i*2+2);
			Future<String> ft = executor.submit(create);
			System.out.println("创建：  " );
			/*try {
				System.out.println("创建：  " + ft.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}*/
		}
        System.out.println("main : "+new Date().getTime());
	}
	
}
