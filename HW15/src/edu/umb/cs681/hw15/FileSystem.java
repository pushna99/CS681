package edu.umb.cs681.hw15;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class FileSystem implements Runnable {

    private LinkedList<Directory> dirRoot;
    private static FileSystem instanceObj = null;

    void appendRootDir(Directory RootDirectory) {
    	dirRoot = new LinkedList<Directory>();
    	dirRoot.add(RootDirectory);
    }
    private FileSystem() {};

    public static FileSystem getFileSystem() {
        if(instanceObj==null)
        	instanceObj = new FileSystem ();
        return instanceObj;
    }

    public LinkedList<Directory> getRootDirs() {
        return this.dirRoot;
    }

    @Override
    public void run() {

        System.out.println("\nThread no. "+Thread.currentThread().getId()+" is running currently");

    }

    public static void main(String[] args) {

        LocalDateTime curTime = LocalDateTime.now();

        FileSystem fs = new FileSystem();

        Directory root = new Directory(null, "root", 0, curTime );

        Directory applications = new Directory(root, "applications", 0, curTime );
        File first = new File(applications, "first", 1800, curTime );
        File second = new File(applications, "second", 1000, curTime );
        Thread t1 = new Thread(fs);
        t1.start();
        try {
            t1.join();
        } 
        catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        System.out.println("Directory "+root.getName()+" is added.");
        System.out.println(first.getName()+" File is added to the directory "+root.getName()+".");
        System.out.println(second.getName()+" File is added to the directory "+root.getName()+".");


        Directory home = new Directory(root, "home", 0, curTime );
        File third = new File(home, "third", 700, curTime );
        File fourth = new File(home, "fourth", 400, curTime );
        Thread t2 = new Thread(fs);
        t2.start();
        try {
            t2.join();
        } 
        catch (InterruptedException e2) {
            e2.printStackTrace();
        }
        System.out.println("Directory "+home.getName()+" is added.");
        System.out.println(third.getName()+" File is added to the directory "+root.getName()+".");
        System.out.println(fourth.getName()+" File is added to the directory "+root.getName()+".");

        Directory code = new Directory(home, "code", 0, curTime);
        File fifth = new File(code, "fifth", 100, curTime);
        File sixth = new File(code, "sixth", 200, curTime);
        Thread t3 = new Thread(fs);
        t3.start();
        try {
            t3.join();
        } 
        catch (InterruptedException e3) {
            e3.printStackTrace();
        }
        System.out.println("Directory "+code.getName()+" is added.");
        System.out.println(fifth.getName()+" File is added to the directory "+root.getName()+".");
        System.out.println(sixth.getName()+" File is added to the directory "+root.getName()+".");


    }

}
