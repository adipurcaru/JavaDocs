
package ro.teamnet.hello2;

import ro.teamnet.hello.HelloWorld;

/**
 * Created by Adrian.Purcaru on 10/07/2017.
 */
public class HelloWorldExtend {
    public HelloWorldExtend() {
    }
    
    public String extendSayHello(){
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.sayHello();
        //System.out.println("The new Hello World");
        
        return "The new Hello World";
    }
    

    
    public static void main(String[] args) {
        HelloWorldExtend hwe = new HelloWorldExtend();
        
        hwe.extendSayHello();
    }
    
}
