package queuemulti.pojo;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import queuemulti.publicFacer.ISubscriber;

@Getter
public class Topic {
	String topicName;
	int topicId;
	List<String > msgs;
	List<TopicSubscriber> subscribers;
	
	public Topic(String topicName, int topicId) {
		this.topicName = topicName;
		this.topicId = topicId;	
		subscribers = new ArrayList<>();
		msgs = new ArrayList<>();
	}
	
	
}
