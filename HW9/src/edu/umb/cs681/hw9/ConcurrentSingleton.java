package edu.umb.cs681.hw9;
import java.util.concurrent.atomic.AtomicReference;

public class ConcurrentSingleton {
	
	private ConcurrentSingleton() {};
	private static AtomicReference<ConcurrentSingleton> instance = 
			new AtomicReference<>();
	
	public static AtomicReference<ConcurrentSingleton> getInstance(){
		ConcurrentSingleton singleton_obj = new ConcurrentSingleton();
		if (instance.compareAndSet(null,singleton_obj)) {
			instance.set(singleton_obj);
        }        
        return instance;
    }
}
