package edu.umb.cs681.hw9;

public class MultiThread implements Runnable {
	
    public void run() {
        System.out.println(ConcurrentSingleton.getInstance());
    }
    
    public static void main(String[] args){
    	for(int i=0; i<10; i++){
    	new Thread(()->{System.out.println(ConcurrentSingleton.getInstance());}).start();
    	}
    }
}