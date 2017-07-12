package ro.teamnet.zerotohero.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Hashtable;

/**
 * TODO
 * in order to resolve the exercises below, you will have to create
 * all the needed clasees, with their members and methods
 * (in ro.teamnet.zerotohero.reflection.demoobjects package)
 */
public class ClassReflectionDemoMain {
    public enum MyEnum {
        item1, item2, item3
    }
    
    static  public int i = 20;
    private int myPrivate = 10;
    static ClassReflectionDemoMain classReflectionDemoMain = new ClassReflectionDemoMain();
    
    public int getMyPrivate() {
        System.out.println(i);
        return myPrivate;
    }
    
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        //get the class for a String object, and print it
        Class stringClass = "string".getClass();
        System.out.println(stringClass.getName());
        
        //get the class of an Enum, and print it
        Class enumClass = MyEnum.item1.getClass();
        System.out.println(enumClass);
        
        // get the class of a collection, and print it
        Class colClass = (new Hashtable<>()).getClass();
        System.out.println(colClass);
        
        // get the class of a primitive type, and print it
        
        System.out.println(Integer.valueOf(i).getClass());
        
        // get and print the class for a field of primitive type
        try {
            Field field = ClassReflectionDemoMain.class.getDeclaredField("i");
            System.out.println(field.getType());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        
        // get and print the class for a primitive type, using the wrapper class
        Class wrapper = Integer.TYPE;
        System.out.println(wrapper);
        
        // get the class for a specified class name
        try {
            Class specified = Class.forName("ro.teamnet.zerotohero.reflection.ClassReflectionDemoMain");
            System.out.println(specified);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        Class superClass = ClassReflectionDemoMain.class.getSuperclass();
        System.out.println(superClass);
        // get the superclass of the superclass above, and print it
        
        Class super2 = superClass.getSuperclass();
        System.out.println(super2);
        
        // get and print the declared classes within some other class
        Class[] declClasses = ClassReflectionDemoMain.class.getDeclaredClasses();
        for (Class cl : declClasses) {
            System.out.println(cl);
        }
        
        
        // print the number of constructors of a class
        int constr = ClassReflectionDemoMain.class.getDeclaredConstructors().length;
        System.out.println(constr);
        
        // get and invoke a public constructor of a class
        try {
            Constructor ct = ClassReflectionDemoMain.class.getConstructor();
            ct.newInstance();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        
        // get and print the class of one private field
        
        Field f = ClassReflectionDemoMain.class.getDeclaredField("myPrivate");
        System.out.println(f.getClass());
        
        
        // set and print the value of one private field for an object
        f.setAccessible(true);
        f.setInt(classReflectionDemoMain, 5);
        System.out.println(classReflectionDemoMain.myPrivate);
        // get and print only the public fields class
        Field[] publicFields = ClassReflectionDemoMain.class.getFields();
        for(Field field : publicFields){
            System.out.println(field);
        }
        
        // get and invoke one public method of a class
        Method method = classReflectionDemoMain.getClass().getMethod("getMyPrivate");
        method.invoke(classReflectionDemoMain);
        
        // get and invoke one inherited method of a class
        Method method1 = classReflectionDemoMain.getClass().getMethod("toString");
       // method.invoke(new Object());
        
        // invoke a method of a class the classic way for ten times, and print the timestamp (System.currentTimeMillis())
        System.out.println(System.currentTimeMillis());
        for(int i=0; i<100; i++){
            classReflectionDemoMain.getMyPrivate();
        }
        System.out.println(System.currentTimeMillis());
        // invoke a method of a class by Reflection for 100 times, and print the timestamp
        //what do you observe?
        System.out.println("------");

        System.out.println(System.currentTimeMillis());
        for(int i=0; i<100; i++){
            classReflectionDemoMain.getMyPrivate();
        }
        System.out.println(System.currentTimeMillis());
        
    }
}
