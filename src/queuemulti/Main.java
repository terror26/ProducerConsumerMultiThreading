package queuemulti;

import queuemulti.pojo.ConcreteSubscriber;
import queuemulti.pojo.Topic;
import queuemulti.publicFacer.ISubscriber;
import queuemulti.publicFacer.Queue;

//package queuemulti;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		Queue qu = new Queue();
		// Steps , create qu
		// create topic in qu
		// create subsrcriber
		// subscribe to topic in queue
		// publish msg
		// resetOffset

		Topic t1 = qu.createTopic("KanishkTopic", 1);
		Topic t2 = qu.createTopic("VermaTopic", 2);

		ConcreteSubscriber s1 = new ConcreteSubscriber("sub1", 1);
		ConcreteSubscriber s2 = new ConcreteSubscriber("sub2", 2);

		ConcreteSubscriber s3 = new ConcreteSubscriber("sub3", 3);

		qu.subscribe(t1, s1);
		qu.subscribe(t1, s2);

		qu.subscribe(t2, s3);

		qu.publish("M1", t1);
		qu.publish("M2", t1);
		Thread.sleep(5000);

		qu.publish("M3", t1);
		qu.publish("M5", t1);

		qu.publish("M4", t2);

		Thread.sleep(5000);

		s1.resetIndex(t1, s1, 1); // replay M2, M3 M5, then M6

		qu.publish("M6", t1);

	}
}
