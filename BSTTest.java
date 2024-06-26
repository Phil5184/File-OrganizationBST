import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.*;

public class BSTTest {
	
	/* TODO: Add your own tests */
	@Test
	public void containsTest1() {
		BST<String, String> toTest = new BST<>();
		toTest.put("testKey", "testValue");
		toTest.put("testKey1", "testValue1");
		toTest.put("testKey2", "testValue2");
		toTest.put("testKey3", "testValue3");
		toTest.put("testKey4", "testValue4");
		String s1 = "testKey";
		assertEquals(true, toTest.containsKey(s1));
	}
	@Test
	public void containsTest2() {
		BST<String, String> toTest = new BST<>();
		toTest.put("testKey", "testValue");
		toTest.put("testKey1", "testValue1");
		toTest.put("testKey2", "testValue2");
		toTest.put("testKey3", "testValue3");
		toTest.put("testKey4", "testValue4");
		String s1 = "testKey";
		assertEquals(false, toTest.containsKey("testKey5"));
	}
	
	@Test
	public void removeTest1() {
		BST<String, String> toTest = new BST<>();
		toTest.put("testKey", "testValue");
		toTest.put("testKey1", "testValue1");
		toTest.put("testKey2", "testValue2");
		toTest.put("testKey3", "testValue3");
		toTest.put("testKey4", "testValue4");
		toTest.remove("testKey");
		String s1 = "testKey";
		assertEquals(false, toTest.containsKey(s1));
	}
	
	@Test
	public void removeTest2() {
		BST<String, String> toTest = new BST<>();
		toTest.put("testKey", "testValue");
		//toTest.remove("testKey");
		String s1 = "testKey";
		assertEquals(true, toTest.remove(s1));
	}
	@Test
	public void emptyTest1() {
		BST<String, String> toTest = new BST<>();
		assertEquals(true, toTest.isEmpty());
	}
	@Test
	public void emptyTest2() {
		BST<String, String> toTest = new BST<>();
		toTest.put("testKey", "testVal");
		assertEquals(false, toTest.isEmpty());
	}
	@Test
	public void getTest1() {
		BST<String, String> toTest = new BST<>();
		toTest.put("testKey", "testVal");
		assertEquals("testVal", toTest.get("testKey"));
	}
	@Test
	public void getTest2() {
		BST<String, String> toTest = new BST<>();
		toTest.put("testKey", "testVal");
		assertEquals(null, toTest.get("testKey1"));
	}
	@Test
	public void putTest1() {
		BST<String, String> toTest = new BST<>();
		toTest.put("testKey", "testVal");
		assertEquals(false, toTest.put("testKey", "Value"));
	}
	@Test
	public void putTest2() {
		BST<String, String> toTest = new BST<>();
		assertEquals(true, toTest.put("testKey", "Value"));
	}
	@Test
	public void setTest1() {
		BST<String, String> toTest = new BST<>();
		toTest.set("testKey", "Value");
		assertEquals(true, toTest.containsKey("testKey"));
	}
	@Test
	public void setTest2() {
		BST<String, String> toTest = new BST<>();
		toTest.put("testKey", "Value");
		toTest.set("testKey", "Value1");
		assertEquals("Value1", toTest.get("testKey"));
	}
	@Test
	public void sizeTest1() {
		BST<String, String> toTest = new BST<>();
		toTest.put("testKey", "Value");
		toTest.set("testKey", "Value1");
		assertEquals(1, toTest.size());
	}
	@Test
	public void sizeTest2() {
		BST<String, String> toTest = new BST<>();
		toTest.put("testKey", "Value");
		toTest.remove("testKey");
		assertEquals(0, toTest.size());
	}
	@Test
	public void replaceTest1() {
		BST<String, String> toTest = new BST<>();
		toTest.put("testKey", "Value");
		assertEquals(true, toTest.replace("testKey", "new"));
	}
	@Test
	public void replaceTest2() {
		BST<String, String> toTest = new BST<>();
		//toTest.put("testKey", "Value");
		assertEquals(false, toTest.replace("testKey", "new"));
	}
	@Test
	public void keysTest1() {
		BST<String, String> toTest = new BST<>();
		ArrayList<String> keys = new ArrayList<>();
		toTest.put("testKey", "Value");
		keys.add("testKey");
		toTest.put("testKey1", "Value");
		keys.add("testKey1");
		toTest.put("testKey2", "Value");
		keys.add("testKey2");
		assertEquals(keys, toTest.keys());
	}
	@Test
	public void keysTest2() {
		BST<String, String> toTest = new BST<>();
		ArrayList<String> keys = new ArrayList<>();
		assertEquals(keys, toTest.keys());
	}
}
