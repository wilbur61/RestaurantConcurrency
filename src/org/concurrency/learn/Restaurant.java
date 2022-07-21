package org.concurrency.learn;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//
// https://github.com/vonzhou/Thinking-In-Java/blob/master/src/thinkinginjava/concurrency/Restaurant.java
//

//What is an ExecutorService?
//
// ExecutorService is a JDK API that simplifies running tasks in asynchronous mode. 
// Generally speaking, ExecutorService automatically provides a pool of threads and 
// an API for assigning tasks to it.
//
//
// ExecutorService newCachedThreadPool() Creates a thread pool that creates new threads 
// as needed, but will reuse previously constructed threads when they are available. 
// These pools will typically improve the performance of programs that execute many 
// short-lived asynchronous tasks.

public class Restaurant {
	Meal meal;
	ExecutorService exec = Executors.newCachedThreadPool();
	WaitPerson waitPerson = new WaitPerson(this);
	Chef chef = new Chef(this);

	public Restaurant() {
		exec.execute(chef);
		exec.execute(waitPerson);
	}

	public static void main(String[] args) {
		new Restaurant();
	}
} 