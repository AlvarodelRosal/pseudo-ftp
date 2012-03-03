package alvarodelrosal.ftp.modelo.FTPActions;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class FTPHelloTest {
    
    FTPHello hello = new FTPHello();
    
    @Test
    public void givesARightName() {
        assertEquals("Hello", hello.getName());
    }
    
    @Test
    public void forAnEmptyParametersInputOnlyAnwsersTheMessage() {
        assertEquals("Eh... What's up, doc?", hello.doAction(new ArrayList()));
    }
    
    @Test
    public void forAParameterReturnsItSeparatedWithTokens() {
        List<String> parameters = new ArrayList();
        parameters.add("Hi!");
        
        assertEquals("Eh... What's up, doc?<:@:>Hi!", hello.doAction(parameters));
    }
    
    @Test(expected= IllegalArgumentException.class)
    public void doesNotAllowTheTokenAsAParameter() {
        List<String> parameters = new ArrayList();
        parameters.add("<:@:>");
        
        hello.doAction(parameters);
    }
    
    @Test
    public void allTheParametersAreSeparated() {
        List<String> parameters = new ArrayList();
        parameters.add("Hi!");
        parameters.add("How");
        parameters.add("are");
        parameters.add("you");
        parameters.add("?");
        
        assertEquals("Eh... What's up, doc?<:@:>Hi!<:@:>How<:@:>are<:@:>you<:@:>?",
                hello.doAction(parameters));
    }
}
