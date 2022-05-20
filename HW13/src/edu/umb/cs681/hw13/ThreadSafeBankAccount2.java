package edu.umb.cs681.hw13;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafeBankAccount2 {
	
	private final ReentrantLock lock = new ReentrantLock();
	
	Condition sufficientFundsCondition = lock.newCondition();
    Condition belowUpperLimitFundsCondition = lock.newCondition();
	
    private double balance = 0;
    
    
    public void withdraw(double amount){
        lock.lock();
        System.out.println(Thread.currentThread().getName() + "\t Current Balance is :" + balance);
        
        //balance to exceed 0
        while (balance <= 0) {
        	try {
        		System.out.println(Thread.currentThread().getName() + "\t Balance below limit - Await Deposit");
                sufficientFundsCondition.await();
        	}
        	catch (InterruptedException e) {
        		e.printStackTrace();
        	}
        }

        balance -= amount;
        System.out.println(Thread.currentThread().getName() + "\t Balance after withdrawal : " + balance);
        belowUpperLimitFundsCondition.signalAll();
        lock.unlock();
        
    }
    public void deposit(double amt){
        lock.lock();
        System.out.println(Thread.currentThread().getName() + "\t Current Balance is : " + balance);
        
        //balance to go below 300
        while (balance >= 300) {
        	try {
        		System.out.println(Thread.currentThread().getName() + "\t Balance exceeded limit : Await Withdrawal");
                belowUpperLimitFundsCondition.await();
        	}
        	catch (InterruptedException e) {
        		e.printStackTrace();
        	}
        }

        balance += amt;
        System.out.println(Thread.currentThread().getName() + "\t Balance after deposit	: " + balance);
        sufficientFundsCondition.signalAll();
        lock.unlock();
        
    }

}
