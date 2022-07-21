package org.concurrency.learn;

import java.util.concurrent.TimeUnit;

class Chef implements Runnable {
	private Restaurant restaurant;
	private int count = 0;

	public Chef(Restaurant r) {
		restaurant = r;
	}

	public void run() {
		try {
			while (!Thread.interrupted()) {
				synchronized (this) {
					while (restaurant.meal != null)
						wait(); // ... for the meal to be taken
				}
				if (++count == 10) {
					System.out.println("Out of food, closing");
					// shutdownNow() attempts to stop all actively executing tasks, 
					// halts the processing of waiting tasks, and returns a list of
					// the tasks that were awaiting execution.
					restaurant.exec.shutdownNow();
				}
				System.out.println("Order up! ");
				synchronized (restaurant.waitPerson) {
					restaurant.meal = new Meal(count);
					// The notifyAll() method of thread class is used to 
					// wake up all threads. This method gives the notification 
					// to all waiting threads of a particular object.
					restaurant.waitPerson.notifyAll();
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}
		} catch (InterruptedException e) {
			System.out.println("Chef interrupted");
		}
	}
}
