package queuemulti.pojo;

import java.util.HashMap;
import java.util.Map;

public class TopicHandler {
	Topic topic;
	Map<Integer, SubscriberWorker> sws = new HashMap<>();
	
	
	public TopicHandler(Topic topic) {
		this.topic = topic;
	}


	public void publish() {
		for(TopicSubscriber subscriber: topic.getSubscribers()) {
			startParrallely(subscriber);
		}
	}
	

	private void startParrallely(TopicSubscriber topicSubscriber) {
		int id = topicSubscriber.getSubscriber().getId();
		if(sws.get(id) == null) { // lazy load
			SubscriberWorker sw = new SubscriberWorker(topic,topicSubscriber);
			sws.put(id, sw);
		}
		
		new Thread(sws.get(id)).start();	 // Running parrallelly 	
		sws.get(id).wakeUpIfNeeded();;
	}
	
}
