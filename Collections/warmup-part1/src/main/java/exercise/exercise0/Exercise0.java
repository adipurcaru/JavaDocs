package exercise.exercise0;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Radu.Hoaghe on 4/20/2015.
 *
 * Exercise 0: Create a List (ArrayList or LinkedList), add elements to it and print all of them using ListIterator
 *             for loop and foreach loop.
 *
 */
public class Exercise0 {

    public Exercise0(){

    }

    public void iterateThroughList(){

        // TODO Exercise #0 a) Create a list (ArrayList or LinkedList) and add elements to it
        List<Integer> myList = new ArrayList<Integer>();
        myList.add(2);
        myList.add(3);
        myList.add(4);
        myList.add(5);
        myList.add(6);
        // TODO Exercise #0 a) Don't forget to specify the type of the list (Integer, String etc.)

        // TODO Exercise #0 b) Iterate through the list using ListIterator and print all its elements
        Iterator<Integer> it = myList.listIterator();
        while(it.hasNext()){
            Integer integ = it.next();
            System.out.println(integ);
        }
        // TODO Exercise #0 c) Iterate through the list using classic for loop and print all its elements
        for(int i=0; i<myList.size(); i++){
            System.out.println(myList.get(i));
        }
        // TODO Exercise #0 d) Iterate through the list using foreach loop and print all its elements
        for(Integer item:myList){
            System.out.println(item);
        }
    }

    public static void main(String[] args) {
        // TODO Exercise #0 e) Create a new instance of Exercise0 class and call the iterateThroughList() method
        Exercise0 e0 = new Exercise0();
        e0.iterateThroughList();
    }
}
