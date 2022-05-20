package edu.umb.cs681.hw13;

import java.util.concurrent.atomic.AtomicBoolean;

public class WithdrawRunnable implements Runnable{

	    private ThreadSafeBankAccount2 acc = null;
	    public AtomicBoolean done = new AtomicBoolean(false);

	    public void setDone() {
	        done.getAndSet(true);
	    }
	    
	    public WithdrawRunnable(ThreadSafeBankAccount2 acc) {
	        this.acc = acc;
	    }

	    @Override
	    public void run() {

	        while (true) {
	            if(done.get()) {
	                System.out.println(Thread.currentThread().getName() +"\tWithdrawal Completed");
	                break;
	            }

	            System.out.println(Thread.currentThread().getName() +"\t Withdrawing money");
	            acc.withdraw(275);

	            try {
	                Thread.sleep(1000);
	            }
	            catch(InterruptedException e) {
	            	e.printStackTrace();
	            	continue;
	            }
	        }
	    }
	}