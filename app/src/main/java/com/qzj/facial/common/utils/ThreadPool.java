package com.qzj.facial.common.utils;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 线程池
 * @author qzj
 *
 */
@SuppressWarnings("unused")
public class ThreadPool {
	
	public static final int THREAD_COUNT = 3;
	public static final String FIXED_POOL = "fixedPool";
	public static final String SINGLE_POOL = "singlePool";
	public static final String CACHED_POOL = "cachedPool";
	private static ExecutorService fixedPool = null;
	private static ExecutorService singlePool = null;
	private static ExecutorService cachedPool = null;
	
	/**
	 * 返回固定大小的线程池实例
	 */
	public static ExecutorService getFixedInstance(){
		if(fixedPool == null){
			synchronized (ThreadPool.class){
				int cpuNum = Runtime.getRuntime().availableProcessors();
				fixedPool = Executors.newFixedThreadPool(cpuNum > 0?cpuNum:THREAD_COUNT);
			}
		}
		return fixedPool;
	}
	
	/**
	 * 返回单线程池
	 */
	public static ExecutorService getSingleInstance(){
		if(singlePool == null){
			synchronized (ThreadPool.class){
				if (singlePool == null)
					singlePool = Executors.newSingleThreadExecutor();
			}
		}
		return singlePool;
	}
	
	/**
	 * 返回无限大小线程�?
	 */
	public static ExecutorService getCachedInstance(){
		if(cachedPool == null){
			synchronized (ThreadPool.class){
				cachedPool = Executors.newCachedThreadPool();
			}
		}
		return cachedPool;
	}
	
	public void shutDown(String poolName){
		if(poolName != null){
			switch (poolName){
				case ThreadPool.FIXED_POOL :
					if(fixedPool != null){
						fixedPool.shutdown();
					}
					break;

				case ThreadPool.SINGLE_POOL :
					if(fixedPool != null){
						singlePool.shutdown();
					}
					break;

				case ThreadPool.CACHED_POOL :
					if(fixedPool != null){
						cachedPool.shutdown();
					}
					break;
			}
		}
	}
	
}
