package edu.eci.arsw.primefinder;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

	public Boolean flag = false;

	public static Timer timer;


	static ArrayList<PrimeFinderThread> threads = new ArrayList<>();
	public static void main(String[] args) {

		PrimeFinderThread pft=new PrimeFinderThread(0, 30000000);
		PrimeFinderThread pft1=new PrimeFinderThread(0, 10000000);
		PrimeFinderThread pft2=new PrimeFinderThread(10000000, 20000000);
		PrimeFinderThread pft3=new PrimeFinderThread(20000000, 30000000);

		pft1.start();
		pft2.start();
		pft3.start();
		threads.add(pft1);
		threads.add(pft2);
		threads.add(pft3);

		Object obj = new Object();



		synchronized (obj) {


			timer = new Timer();
			timer.schedule(new StopTask(), 5000);
		}
	}

	class StopTask extends TimerTask {
		public void run() {
			System.out.println("Time Up!");
			try {
				threads.get(0).wait();
				threads.get(1).wait();
				threads.get(2).wait();
				System.out.println(threads.get(0).getPrimes().size() + threads.get(1).getPrimes().size() + threads.get(2).getPrimes().size() );
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}

			timer.cancel();
		}
	}


}
