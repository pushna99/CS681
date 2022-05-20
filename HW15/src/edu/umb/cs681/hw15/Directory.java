package edu.umb.cs681.hw15;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.LinkedList;
import java.time.LocalDateTime;


public class Directory extends FSElement {

    private ConcurrentLinkedQueue<FSElement> childNode;
    LinkedList<Directory> subDirectories = new LinkedList<Directory>();
    LinkedList<File> files = new LinkedList<File>();
    
    public Directory(Directory parent, String name, int size, LocalDateTime creationTime) {
        super(parent, name, size, creationTime);
        if(parent != null)
            parent.addChild(this);
    }

    public ConcurrentLinkedQueue<FSElement> getChildren() {
        lock.lock();
        try {
            return this.childNode;
        } finally {
            lock.unlock();
        }
    }

    public void addChild(FSElement child) {
        if(this.childNode == null) {
            this.childNode = new ConcurrentLinkedQueue<FSElement>();
        }
        this.childNode.add(child);
    }

    public int countChildren() {
        lock.lock();
        try {
            return this.childNode.size();
        } 
        finally {
            lock.unlock();
        }

    }

    public LinkedList<Directory> getSubDirectories() {
        lock.lock();
        try {
            for (FSElement element : getChildren()) {
                if (element.isDirectory())
                	subDirectories.add((Directory) element);
            }
            return subDirectories;
        } finally {
            lock.unlock();
        }
    }

    public LinkedList<File> getFiles() {
        lock.lock();
        try {
            for (FSElement element : getChildren()) {
                if (element instanceof File)
                    files.add((File) element);
            }
            return files;
        } finally {
            lock.unlock();
        }

    }

    public int getTotalSize() {
        lock.lock();
        try {
            int totalSize = 0;
            for(FSElement element : getChildren()) {
                if(element instanceof Directory)
                	totalSize += ((Directory) element).getTotalSize();
                else
                	totalSize += element.getSize();
            }
            return totalSize;
        }finally {
            lock.unlock();
        }

    }

    @Override
    public boolean isDirectory() {
        lock.lock();
        try {
            return true;
        } finally {
            lock.unlock();
        }

    }

}