import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.*;

public class FileSystemTest {
	@Test
	public void testAdd1() {
		FileSystem SecondFile = new FileSystem();
		SecondFile.add("mySample.txt", "/root", "2021/02/01");
		assertEquals(true, SecondFile.dateTree.containsKey("2021/02/01"));
	}
	@Test
	public void testAdd2() {
		FileSystem SecondFile = new FileSystem();
		SecondFile.add("mySample.txt", "/home", "2021/02/01");
		SecondFile.add("mySample1.txt", "/root", "2021/02/01");
		SecondFile.add("mySample2.txt", "/user", "2021/02/06");
		//System.out.println(SecondFile.dateTree.keys());
		assertEquals(3, SecondFile.dateTree.size());
	}
	@Test
	public void testOutputName1() {
		FileSystem File3 = new FileSystem("/Users/phil/documents/GitHub/cse12-pa7-BST/pa7-starter/input.txt");
		ArrayList<String> s1 = new ArrayList<>();
		s1.add("mySample.txt: {Name: mySample.txt, Directory: /home, Modified Date: 2021/02/01}");
		s1.add("mySample1.txt: {Name: mySample1.txt, Directory: /root, Modified Date: 2021/02/01}");
		s1.add("mySample2.txt: {Name: mySample2.txt, Directory: /user, Modified Date: 2021/02/06}");
		assertEquals(s1, File3.outputNameTree());
	}
	@Test
	public void testOutputName2() {
		FileSystem File3 = new FileSystem();
		ArrayList<String> s1 = new ArrayList<>();
		//s1.add("mySample.txt: {Name: mySample.txt, Directory: /home, Modified Date: 2021/02/01}");
		//s1.add("mySample1.txt: {Name: mySample1.txt, Directory: /root, Modified Date: 2021/02/01}");
		//s1.add("mySample2.txt: {Name: mySample2.txt, Directory: /user, Modified Date: 2021/02/06}");
		assertEquals(s1, File3.outputNameTree());
	}
	@Test
	public void testOutputDate1() {
		FileSystem File4 = new FileSystem("/Users/phil/documents/GitHub/cse12-pa7-BST/pa7-starter/input.txt");
		ArrayList<String> s1 = new ArrayList<>();
		s1.add("2021/02/06: {Name: mySample2.txt, Directory: /user, Modified Date: 2021/02/06}");
		s1.add("2021/02/01: {Name: mySample1.txt, Directory: /root, Modified Date: 2021/02/01}");
		s1.add("2021/02/01: {Name: mySample.txt, Directory: /home, Modified Date: 2021/02/01}");
		assertEquals(s1, File4.outputDateTree());
	}
	@Test
	public void testOutputDate2() {
		FileSystem File4 = new FileSystem();
		ArrayList<String> s1 = new ArrayList<>();
//		s1.add("2021/02/06: {Name: mySample2.txt, Directory: /user, Modified Date: 2021/02/06}");
//		s1.add("2021/02/01: {Name: mySample1.txt, Directory: /root, Modified Date: 2021/02/01}");
//		s1.add("2021/02/01: {Name: mySample.txt, Directory: /home, Modified Date: 2021/02/01}");
		assertEquals(s1, File4.outputDateTree());
	}
	@Test
	public void testFindNameByDate1() {
		FileSystem File3 = new FileSystem();
		ArrayList<String> s1 = new ArrayList<>();
		File3.add("mySample.txt", "/home", "2021/02/01");
		File3.add("mySample1.txt", "/root", "2021/02/01");
		File3.add("mySample2.txt", "/user", "2021/02/01");
		s1.add("mySample.txt");
		s1.add("mySample1.txt");
		s1.add("mySample2.txt");
		assertEquals(s1, File3.findFileNamesByDate("2021/02/01"));
		
	}
	@Test
	public void testFindNameByDate2() {
		FileSystem File3 = new FileSystem();
		ArrayList<String> s1 = new ArrayList<>();
//		File3.add("mySample.txt", "/home", "2021/02/01");
//		File3.add("mySample1.txt", "/root", "2021/02/01");
//		File3.add("mySample2.txt", "/user", "2021/02/01");
//		s1.add("mySample.txt");
//		s1.add("mySample1.txt");
//		s1.add("mySample2.txt");
		assertEquals(null, File3.findFileNamesByDate("2021/02/01"));
	}
	
	@Test
	public void testFilterDate1() {
		FileSystem File3 = new FileSystem();
		
		File3.add("mySample.txt", "/home", "2021/02/01");
		File3.add("mySample1.txt", "/root", "2021/02/03");
		File3.add("mySample2.txt", "/user", "2021/02/05");
		FileSystem testing = File3.filter("2021/02/01", "2021/02/05");
		assertEquals(true, testing.dateTree.containsKey("2021/02/01"));
		assertEquals(true, testing.dateTree.containsKey("2021/02/03"));
	
	}
	@Test
	public void testFilterDate2() {
		FileSystem File3 = new FileSystem();
		
		File3.add("mySample.txt", "/home", "2021/02/01");
		File3.add("mySample1.txt", "/root", "2021/02/03");
		File3.add("mySample2.txt", "/user", "2021/02/05");
		FileSystem testing = File3.filter("2021/02/06", "2021/02/10");
		assertEquals(0, testing.dateTree.size());
	
	}
	@Test
	public void testFilterWild1() {
		FileSystem File3 = new FileSystem();
		
		File3.add("mySample.txt", "/home", "2021/02/01");
		File3.add("mySample1.txt", "/root", "2021/02/03");
		File3.add("mySample2.txt", "/user", "2021/02/05");
		FileSystem testing = File3.filter("mySam");
		assertEquals(true, testing.nameTree.containsKey("mySample.txt"));
		assertEquals(true, testing.nameTree.containsKey("mySample1.txt"));
		assertEquals(true, testing.nameTree.containsKey("mySample2.txt"));
	}
	@Test
	public void testFilterWild2() {
		FileSystem File3 = new FileSystem();
		
		File3.add("mySample.txt", "/home", "2021/02/01");
		File3.add("mySample1.txt", "/root", "2021/02/03");
		File3.add("mySample2.txt", "/user", "2021/02/05");
		FileSystem testing = File3.filter("big");
		assertEquals(0, testing.nameTree.size());
	}
}