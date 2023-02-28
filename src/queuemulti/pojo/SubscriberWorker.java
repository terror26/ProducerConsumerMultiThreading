package queuemulti.pojo;

import java.util.concurrent.atomic.AtomicInteger;

import lombok.SneakyThrows;
import queuemulti.publicFacer.ISubscriber;

public class SubscriberWorker implements Runnable {
	private Topic topic;
	private TopicSubscriber topicSubscriber;
	
	public SubscriberWorker(Topic topic, TopicSubscriber topicSubscriber) {
		this.topic = topic;
		this.topicSubscriber = topicSubscriber;
		
		
	}

	@SneakyThrows
	@Override
	public void run() {
		do {
			ISubscriber subscriber = topicSubscriber.getSubscriber();
			synchronized (subscriber) {
				int offset = topicSubscriber.getOffset().get();
				while(offset >= topic.getMsgs().size()) {
					subscriber.wait();
				}
				String msg = topic.getMsgs().get(offset);
				topicSubscriber.getSubscriber().consume(msg);
				topicSubscriber.getOffset().compareAndSet(offset, offset + 1);	
			}
		} while(true);
		
	}	
	

	synchronized public void wakeUpIfNeeded() {
        synchronized (topicSubscriber) {
        	topicSubscriber.notify();
        }
    }
	
	
}
