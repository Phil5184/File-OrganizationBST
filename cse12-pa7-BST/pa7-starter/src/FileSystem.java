import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Comparator;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class FileSystem {

    BST<String, FileData> nameTree;
    BST<String, ArrayList<FileData>> dateTree;
    
    // TODO
    public FileSystem() {
    	this.nameTree = new BST<>();
    	this.dateTree = new BST<>();
    }


    // TODO
    public FileSystem(String inputFile) {
    	// Add your code here
    	this.nameTree = new BST<>();
    	this.dateTree = new BST<>();
        try {
            File f = new File(inputFile);
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String[] data = sc.nextLine().split(", ");
                // Add your code here
                //FileData f1 = new FileData(data[0], data[1], data[2]);
                this.add(data[0], data[1], data[2]);
                
                
                /*if (nameTree.containsKey(f1.name) == false) {
                	nameTree.put(f1.name, f1);
                }
                if (dateTree.containsKey(f1.lastModifiedDate) == false) {
                	ArrayList<FileData> toDate = new ArrayList<>();
                	toDate.add(f1);
                	dateTree.put(f1.lastModifiedDate, toDate);
                }
                else {
                	ArrayList<FileData> toDate2 = dateTree.get(f1.lastModifiedDate);
                	toDate2.add(f1);
                	dateTree.replace(f1.lastModifiedDate, toDate2);
                }*/
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);

        }
    }


    // TODO
    public void add(String name, String dir, String date) {
    	if (name == null || dir == null || date == null) {
    		return;
    	}
    	//for nameTree
    	FileData add = new FileData(name, dir, date);
    	if (nameTree.containsKey(add.name)) {
    		FileData compare = nameTree.get(name);
    		if (compare.lastModifiedDate.compareTo(date) < 0) {
    			nameTree.replace(name, add);
    			
    		}
    		
    	}
    	else {
    		nameTree.put(name, add);
    		
    	}
    	//for dateTree
    	System.out.println("here");
    	ArrayList<String> keysLoop = (ArrayList<String>) dateTree.keys();
    	if (dateTree.size() == 0) {
    		ArrayList<FileData> Newadd = new ArrayList<>();
			Newadd.add(add);
			dateTree.put(date, Newadd);
			return;
    	}
		for (int k = 0; k < keysLoop.size(); k++) {
			ArrayList<FileData> looping = dateTree.get(keysLoop.get(k));
			for (int n = looping.size() - 1; n >= 0; n--) {
				System.out.println(looping.get(n));
				if (looping.get(n).name.equals(name) && looping.get(n).lastModifiedDate.compareTo(date) < 0) {
					looping.remove(n);
					if (dateTree.containsKey(date)) {
						ArrayList<FileData> update = dateTree.get(date);
						update.add(add);
						dateTree.replace(date, update);
						//dateTree.size +=1;
						return;
					}
					else {
						ArrayList<FileData> New2 = new ArrayList<>();
						New2.add(add);
						dateTree.put(date, New2);
						return;
					}
				}
				else if (looping.get(n).name.equals(name) && looping.get(n).lastModifiedDate.equals(date)) {
					continue;
				}
				else if (looping.get(n).name.equals(name) && looping.get(n).lastModifiedDate.compareTo(date) > 0) {
					continue;
				}
				else {
					//System.out.println("here1");
					if (dateTree.containsKey(date)) {
						ArrayList<FileData> updating = dateTree.get(date);
						updating.add(add);
						dateTree.replace(date, updating);
						dateTree.size += 1;
						return;
					}
					else {
						//System.out.println("here2");
						ArrayList<FileData> New3 = new ArrayList<>();
						New3.add(add);
    					dateTree.put(date, New3);
    					return;
					}
				}
			}
		}
    	//for dateTree
    	/*if (dateTree.containsKey(date)) {
    		ArrayList<FileData> looped = dateTree.get(date);
    		boolean alltrue = true;
    		for (int i = 0; i < looped.size(); i++) {
    			if (looped.get(i).name.equals(name)) {
    				alltrue = alltrue && false;
    			}
    		}
    		if (alltrue == true) {
    			ArrayList<FileData> New = new ArrayList<>();
    			New.add(add);
    			dateTree.put(date, New);
    		}
    	}*/
    	
    		
    }
    		
   


    // TODO
    public ArrayList<String> findFileNamesByDate(String date) {
    	if (dateTree.containsKey(date) == false) {
    		return null;
    	}
    	if (date == null) {
    		return null;
    	}
    	ArrayList<FileData> Check = dateTree.get(date);
    	ArrayList<String> return1 = new ArrayList<>();
    	for (int n = 0; n < Check.size(); n++) {
    		return1.add(Check.get(n).name);
    	}
    	return return1;
    }


    // TODO
    public FileSystem filter(String startDate, String endDate) {
    	FileSystem giveBack = new FileSystem();
    	ArrayList<String> keys1 = (ArrayList<String>) dateTree.keys();
    	for (int k = 0; k < keys1.size(); k++) {
    		if (keys1.get(k).compareTo(startDate) >= 0 && keys1.get(k).compareTo(endDate) < 0) {
    			ArrayList<FileData> loop1 = dateTree.get(keys1.get(k));
    			for (int n = 0; n < loop1.size(); n++) {
    				FileData FD = loop1.get(n);
    				giveBack.add(FD.name, FD.dir, FD.lastModifiedDate);
    			}
			}
    	}
    	return giveBack;
    }
    
    
    // TODO
    public FileSystem filter(String wildCard) {
    	FileSystem giveBack2 = new FileSystem();
    	ArrayList<String> keys2 = (ArrayList<String>) nameTree.keys();
    	for (int k = 0; k < keys2.size(); k++) {
    		if (keys2.get(k).contains(wildCard)) {
    			FileData FD2 = nameTree.get(keys2.get(k));
    			giveBack2.add(FD2.name, FD2.dir, FD2.lastModifiedDate);
    			
			}
    	}
    	return giveBack2;
    }
    
    
   
    	
    
    // TODO
    public List<String> outputNameTree() {
    	ArrayList<String> list = (ArrayList<String>) nameTree.keys();
    	ArrayList<String> output = new ArrayList<>();
    	for (int n = 0; n < list.size(); n++) {
    		String concat = nameTree.get(list.get(n)).toString();
    		output.add(list.get(n) + ": " + concat);
    	}
    	return output;
    }
    
    
    // TODO
    public List<String> outputDateTree() {
    	ArrayList<String> list1 = (ArrayList<String>) dateTree.keys();
    	ArrayList<String> output1 = new ArrayList<>();
    	for (int n = list1.size() - 1; n >= 0; n--) {
    		ArrayList<FileData> concat = dateTree.get(list1.get(n));
    		//System.out.println(concat.size());
    		for (int z = concat.size() - 1; z >= 0; z--) {
    			//System.out.println(z);
    			String str = concat.get(z).toString();
    			output1.add(list1.get(n) + ": " + str);
    		}
    	}
    	return output1;
    	
    }
}

