package server.core.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
import server.core.Updatable;
import server.core.Updater;

public class SimpleUpdater implements Updater {

	protected static final Logger			log				= LoggerFactory.getLogger(SimpleUpdater.class);

	protected List<Updatable>				asyncUpdatables	= new ArrayList<Updatable>();
	protected List<Updatable>				syncUpdatables	= new ArrayList<Updatable>();
	protected ArrayBlockingQueue<Runnable>	syncRunnables	= new ArrayBlockingQueue<Runnable>(2048);

	protected int							lastAsyncSize	= 0;

	protected CountDownLatch				latch			= null;

	@Override
	public void addAsyncUpdatable(Updatable updatable) {
		asyncUpdatables.add(updatable);
	}

	@Override
	public void addSyncUpdatable(Updatable updatable) {
		syncUpdatables.add(updatable);
	}

	/**
	 * 加入一个Runnable对象在主线程队列中，此方法是 线程安全的，可以在任意线程添加
	 * 
	 * @param runnable
	 */
	public void addSyncRunnable(Runnable runnable) {
		syncRunnables.offer(runnable);
	}

	@Override
	public void update() {
		long t1 = System.nanoTime();
		int size = asyncUpdatables.size();
		if (size > 0) {
			latch = new CountDownLatch(size);
			for (int i = 0; i < size; i++) {
				asyncUpdatables.get(i).update();
				latch.countDown();
			}
			try {
				latch.await();
			} catch (InterruptedException e1) {
				log.error(e1.toString(), e1);
				return;
			}
		}
		long t2 = System.nanoTime();
		long el1 = (t2 - t1) / 1000000;
		if (el1 > 100) {
			log.info("ConCycle[" + el1 + "]");
		}
		for (Updatable u : syncUpdatables) {
			try {
				u.update();
			} catch (Exception e) {
				log.error(e.toString(), e);
			}
		}
		long t3 = System.nanoTime();
		long el2 = (t3 - t2) / 1000000;
		if (el2 > 100) {
			log.info("SynCycle[" + el2 + "]");
		}
		Runnable runnable = null;
		while ((runnable = syncRunnables.poll()) != null) {
			try {
				runnable.run();
			} catch (Exception e) {
				log.error(e.toString(), e);
			}
		}
		long t4 = System.nanoTime();
		long el3 = (t4 - t2) / 1000000;
		if (el3 > 100) {
			log.info("SynCycle1[" + el3 + "]");
		}
	}

}
