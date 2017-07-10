package ro.teamnet.hello2;

import org.junit.Test;

/**
 * Created by Adrian.Purcaru on 10/07/2017.
 */
public class HelloWorldExtendTest {
    @Test
    public void test() throws Exception {
        new HelloWorldExtend().extendSayHello();
    }
    
    @Test
    public void testReturnHelloExtend(){
        HelloWorldExtend helloWorldExtend = new HelloWorldExtend();
        System.out.println("From Test: " + helloWorldExtend.extendSayHello());
        
        String res = helloWorldExtend.extendSayHello();
        assert res.equals("cgf");
    }
}
