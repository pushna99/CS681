package edu.umb.cs681.hw15;

import java.time.LocalDateTime;

public class File extends FSElement {

    @Override
    public boolean isDirectory() {
        lock.lock();
        try {
            return false;
        } finally {
            lock.unlock();
        }

    }
    public File(Directory parent, String name, int size, LocalDateTime time) {
        super(parent, name, size, time);
        parent.addChild(this);
    }

}