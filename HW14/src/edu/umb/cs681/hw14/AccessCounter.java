package edu.umb.cs681.hw14;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.nio.file.Path;


public class AccessCounter {
	
	private ConcurrentHashMap<Path, AtomicInteger> mapHash = new ConcurrentHashMap<Path, AtomicInteger>();
	private static ReentrantLock staticLock = new ReentrantLock();
	private static AccessCounter instanceObj = null;
	private AccessCounter() {};	
	
	public static AccessCounter getInstance() {
		staticLock.lock();
		try {
			if (instanceObj == null)
				instanceObj = new AccessCounter();
			return instanceObj;
		}
		finally {
			staticLock.unlock();
		}
	}
	
	public void increment(Path path) {
		mapHash.compute(path, (Path p, AtomicInteger i) -> {
	        if(i == null) {
	            return new AtomicInteger(1);
	        } else {
	            return new AtomicInteger(i.incrementAndGet());
	        }
	    });
	}
	
	public int getCount(Path path) {
		return mapHash.compute(path, (Path p, AtomicInteger i) -> {
            if(i == null) {
                return new AtomicInteger(0);
            } else {
                return i;
            }
        }).get();
	}	
}