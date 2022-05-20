package edu.umb.cs681.hw11;

import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.nio.file.Path;


public class AccessCounter {
	
	private AccessCounter() {};	
	
	private Map<java.nio.file.Path, Integer> mapHash = new HashMap<java.nio.file.Path, Integer>();
	private ReentrantLock lock = new ReentrantLock();
	private static ReentrantLock staticLock = new ReentrantLock();
	private static AccessCounter instanceObj = null;
	
	
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
		lock.lock();
		try {
			if (mapHash.get(path) == null) {
				mapHash.put(path, 1);
			}
			else 
				mapHash.put(path, mapHash.get(path) + 1);
		}
		finally {
			lock.unlock();
		}
	}
	
	public int getCount(Path path) {
		lock.lock();
		try {
			if (mapHash.get(path) == null)
				return 0;
			else
				return mapHash.get(path);
		}
		finally {
			lock.unlock();
		}
	}	
}