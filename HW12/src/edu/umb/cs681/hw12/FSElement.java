package edu.umb.cs681.hw12;

import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;

public abstract class FSElement {

	private LocalDateTime time;
    private Directory parent;
    protected ReentrantLock lock = new ReentrantLock();
    private String name;
    private int size;

    public FSElement(Directory parent, String name, int size, LocalDateTime time) {
        this.parent = parent;
        this.name = name;
        this.size = size;
        this.time = time;
    }


    public Directory getParent() {
        lock.lock();
        try {
            return this.parent;
        } finally {
            lock.unlock();
        }
    }

    public String getName() {
        lock.lock();
        try {
            return this.name;
        } finally {
            lock.unlock();
        }
    }
    
    public void setName(String name) {
		lock.lock();
		try {
			this.name=name;
		}finally {
			lock.unlock();
		}
	}
    
    public void setSize(int size) {
		lock.lock();
		try {
			this.size = size;
		}finally {
			lock.unlock();
		}
	}
    
    public int getSize() {
		lock.lock();
		try {
			return this.size;
		}finally {
			lock.unlock();
		}
	}
	
	public LocalDateTime getCreationTime() {
        lock.lock();
        try {
            return this.time;
        } finally {
            lock.unlock();
        }
    }
	
    public abstract boolean isDirectory();

}