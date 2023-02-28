package queuemulti.pojo;

import lombok.Getter;
import queuemulti.publicFacer.ISubscriber;

@Getter
public class ConcreteSubscriber implements ISubscriber {
	
	String subscriberName;
	int subscriberId;
	
	
	public ConcreteSubscriber(String sname, int subId) {
		subscriberId = subId;
		subscriberName = sname;
	}


	@Override
	public void consume(String msg) throws InterruptedException {
		System.out.println("Consuming msg = " +msg + " for subscriberName = " + subscriberName);
		Thread.sleep(2000);
		System.out.println("Done Consuming msg = " +msg+ " for subscriberName = " + subscriberName);
		
	}


	@Override
	public int getId() {
		return subscriberId;
	}


	public void resetIndex(Topic t1, ConcreteSubscriber s1, int i) {
		for(TopicSubscriber ts : t1.getSubscribers()) {
			if(s1.getId() == ts.getSubscriber().getId()) {
				ts.setOffSet(i);
				System.out.println("offset set to " + i + " for topic =" +t1.getTopicId());
			}
		}
		
	}
	
}
