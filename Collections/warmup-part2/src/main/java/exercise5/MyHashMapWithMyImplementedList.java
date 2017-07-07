package exercise5;

import exercise4.MyHashMap;

import java.util.*;

/**
 * Create a HashMap that uses to store the buckets your implementation of MyImplementedList that you
 * created in the Collections I workshop.
 *
 * Created by Radu.Hoaghe on 7/6/2017.
 */
public class MyHashMapWithMyImplementedList {

    // TODO uncomment the following line and add your MyImplementedList implementation to the project
    private MyImplementedList<LinkedList<MyEntry>> buckets;

    private final int BUCKET_ARRAY_SIZE = 16;
    int size = 0 ;
    
    public MyHashMapWithMyImplementedList() {
        // T
        buckets = new MyImplementedList<LinkedList<MyEntry>>();
        for(int i=0; i<BUCKET_ARRAY_SIZE; i++){
            buckets.add(new LinkedList<MyEntry>());
        }
    }

    public String get(String key) {
        // TODO
        if(key == null)
            return null;
    
        int hashCode = Math.abs(key.hashCode()) % BUCKET_ARRAY_SIZE;
    
    
        if(BUCKET_ARRAY_SIZE < hashCode){
            return null;
        }
    
        LinkedList<MyEntry> list = buckets.get(hashCode);
        for(MyEntry e : list){
            if(e.getKey().equals(key)){
                return e.getValue();
            }
        }
    
        return null;
    }

    public void put(String key, String value) {
        //
        if(key == null) {
            size++;
            buckets.get(0).add(new MyEntry(key,value));
            return;
        }
    
        int hashCode = Math.abs(key.hashCode()) % BUCKET_ARRAY_SIZE;
        LinkedList<MyEntry> list = buckets.get(hashCode);
    
        for(MyEntry e : list){
            if(key.equals(e.getKey())){
                e.setValue(value);
                return;
            }
        }
        list.add(new MyEntry(key, value));
        size++;
    }

    public Set<String> keySet() {
        //
        Set<String>keySet = new HashSet<String>();
    
        //for(LinkedList<MyEntry> e : buckets){
        for(int i=0; i< buckets.size(); i++){
            LinkedList<MyEntry> e=buckets.get(i);
            for(MyEntry entry : e){
                keySet.add(entry.getKey());
            }
        }
        return keySet;
    }

    public Collection<String> values() {
        //
        Collection<String> valuesCol = new LinkedList<String>();
    
        //for(LinkedList<MyEntry> e : buckets){
        for(int i=0; i< buckets.size(); i++){
            LinkedList<MyEntry> e=buckets.get(i);
            for(MyEntry entry : e){
                valuesCol.add(entry.getValue());
            }
        }
    
        return valuesCol;
    }

    public String remove(String key) {
        //  Returns the value associated with the key removed from the map or null if the key wasn't found
        //for(LinkedList<MyEntry> entries : buckets){
        for(int i=0; i< buckets.size(); i++){
            LinkedList<MyEntry> entries=buckets.get(i);
            for(MyEntry e : entries) {
                if (e.getKey().equals(key)) {
                    size--;
                    String val = e.getValue();
                    entries.remove(e);
                
                    return val;
                }
            }
        }
        return null;
    }

    public boolean containsKey(String key) {
        //
        //for(LinkedList<MyEntry> entries : buckets){
        for(int i=0; i< buckets.size(); i++){
            LinkedList<MyEntry> entries=buckets.get(i);
            for(MyEntry e : entries) {
                if (e.getKey().equals(key)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean containsValue(String value) {
        //
        //for(LinkedList<MyEntry> entries : buckets){
        for(int i=0; i< buckets.size(); i++){
            LinkedList<MyEntry> entries=buckets.get(i);
            for(MyEntry e : entries) {
                if (e.getValue().equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int size() {
        //  Return the number of the Entry objects stored in all the buckets
        return size;
    }

    public void clear() {
        //  Remove all the Entry objects from the bucket list
        //for(LinkedList<MyEntry> myEntries : buckets){
        for(int i=0; i< buckets.size(); i++){
            LinkedList<MyEntry> e=buckets.get(i);
            e.clear();
        }
        size = 0;
    }

    public Set<MyHashMap.MyEntry> entrySet() {
        //  Return a Set containing all the Entry objects
        Set<LinkedList<MyEntry>> mySet = new HashSet<LinkedList<MyEntry>>();
    
        for(LinkedList<MyEntry> myEntries : buckets){
            mySet.add(myEntries);
        }
        return null;
    }

    public boolean isEmpty() {
        //
        return size == 0;
    }

    public static class MyEntry {
        private String key;
        private String value;

        public MyEntry(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
