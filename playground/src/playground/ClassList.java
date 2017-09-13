package playground;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;


//code by Clayton Newmiller, following example from Mona Ramini
public class ClassList {

	List<String> classListList = new LinkedList<String>();
	Map<String, String> classListMap = new HashMap<String, String>();
	
	public ClassList() {
		classListList.add("Arya");
		classListList.add("Cersei");
		classListList.add("Eddard");
		classListList.add("Sansa");
		classListList.add("Tyrion");
		classListMap.put("Arya", "B");
		classListMap.put("Cersei", "F");
		classListMap.put("Eddard", "A");
		classListMap.put("Sansa", "C");
		classListMap.put("Tyrion", "A");
	}
	public void printClassList() {
		Iterator<String> iterator = classListList.iterator();
		while (iterator.hasNext()) {
			String name = iterator.next();
			System.out.println(name);
		}
	}
	
	public void printClassList2() {
		for (String name: classListList) {
			System.out.println(name);
		}
	}
	
	public void printClassListMap() {
		Iterator<Entry<String, String>> entries = classListMap.entrySet().iterator();
		while (entries.hasNext()) {
			Entry<String, String> entry = entries.next();
			System.out.println("Key: "+entry.getKey() + ", Value: "+entry.getValue());
		}
	}
	
	public void printClassListMap2() {
		for (Entry<String, String> entry: classListMap.entrySet()) {
			System.out.println("Key: "+entry.getKey() + ", Value: "+entry.getValue());
		}
		for (String key : classListMap.keySet()) {
			System.out.println("Key: "+ key);
		}
		for (String value : classListMap.values()) {
			System.out.println("Key: "+ value);
		}
		
	}
	
	
	public static void main(String[] args) {
		ClassList myClassList = new ClassList();
		myClassList.printClassList();
		myClassList.printClassListMap();
	}
	
	
}
