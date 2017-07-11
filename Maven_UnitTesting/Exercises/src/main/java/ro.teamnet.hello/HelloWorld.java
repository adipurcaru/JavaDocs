package ro.teamnet.hello;


import org.apache.log4j.Logger;

/**
 * Created by Adrian.Purcaru on 10/07/2017.
 */
public class HelloWorld {
    
    static final Logger logger = Logger.getLogger(HelloWorld.class.getName());
    
    /**
     * method for saying hello
     */
    public void sayHello(){
        
        System.out.println("Hello World!");
    }
    
    /**
     * method for returning a key
     * @return - The HelloWorld key
     */
    public String returnHelloKey(){
        return "HelloKey";
    }
    
    public static void main(String[] args) {
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.sayHello();
    }
    
}
