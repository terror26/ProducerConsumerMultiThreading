package queuemulti.publicFacer;

public interface ISubscriber {
	
    int getId();
	void consume(String msg) throws InterruptedException;	
}
