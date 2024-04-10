package multiThread;

class ThreadCount extends Thread{
	ThreadCount(){
		super("Overriding new Thread");
		System.out.println("Create new Thread" + this);
		start();
	}
	public void run() {
		try {
			for(int i=0;i<10;i++) {
				System.out.println("Create new Thread" +this);
				Thread.sleep(1500);
			}
		}catch(InterruptedException e) {
			System.out.println("");
		}
		System.out.println("Thread child run is intergrupted");
	}
}

public class MultiThreading{

public static void main(String args[]){
		ThreadCount C = new ThreadCount();
		try {
			while(C.isAlive()) {
				System.out.println("Thread main will be alive until Thread child is alive");
				Thread.sleep(2500);
			}
		}catch(InterruptedException e) {
			System.out.println("");
		}
		System.out.println("Thread main run is interupted");
		
	}

}