package data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Test_data {
    
    DocPath docPath;
    AccredNumb accredNumb;
    @BeforeEach
    public void setUp(){
        this.docPath = new DocPath();
        this.accredNumb = new AccredNumb();
    }

    @Override
    @Test
    public void addPathTest() throws NullPointerException{
        String path = "/doc/vida_laboral.pdf";
        docPath.addDocPath(path);
        assertEquals(path, docPath.getPath());
    }

    @Override
    @Test
    public void addAccredNumbTest() throws IllegalArgumentException {
        String code = "75634816211";
        accredNumb.AddAccredNumb(code);
        assertEquals(code, accredNumb.getAccredNumber());
    }

}
