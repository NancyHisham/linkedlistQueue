import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;



class isEmpty {

	@Test
	void test() {
		LinkedListQueue test = new LinkedListQueue();
		test.enqueue(1);
		test.enqueue(2);
		test.enqueue(3);
		test.enqueue(4);
		test.enqueue(5);
		test.dequeue();
		test.dequeue();
		test.dequeue();
		test.dequeue();
		test.dequeue();
		assertEquals(true, test.isEmpty());

	}

}
