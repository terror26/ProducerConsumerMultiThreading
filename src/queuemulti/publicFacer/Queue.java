package queuemulti.publicFacer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import queuemulti.pojo.ConcreteSubscriber;
import queuemulti.pojo.Topic;
import queuemulti.pojo.TopicHandler;
import queuemulti.pojo.TopicSubscriber;

public class Queue {

	Map<Integer, TopicHandler> topicHandlers = new HashMap<>();
	
	public void subscribe(Topic t1,ConcreteSubscriber s1) {
		t1.getSubscribers().add(new TopicSubscriber(s1));
		System.out.println("added subscriber " + s1.getSubscriberName() );
	}
	public Topic createTopic(String topicName, int topicId) {
		Topic t1 = new Topic(topicName,topicId);
		TopicHandler topicHandler = new TopicHandler(t1);
		topicHandlers.put(t1.getTopicId(), topicHandler); // puts which handler to call for topic
		System.out.println("Created topic " + topicName);
		return t1;
	}
	public void publish(String msg, Topic t1) {
		t1.getMsgs().add(msg);
		System.out.println("published msg = " + msg + " to topic" + t1.getTopicName());
		new Thread(() -> topicHandlers.get(t1.getTopicId()).publish()).start();;
		
	}
	
}
