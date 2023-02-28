package queuemulti.pojo;

import java.util.concurrent.atomic.AtomicInteger;

import lombok.Getter;
import queuemulti.publicFacer.ISubscriber;

@Getter
public class TopicSubscriber {

	public TopicSubscriber(ConcreteSubscriber s1) {
			offset.set(0);
			subscriber = s1;
	}
	private AtomicInteger offset = new AtomicInteger(0);
	private ISubscriber subscriber;
	void setOffSet(int v) {
		this.offset.set(v);;
	}
}
