import static org.junit.Assert.*;
import org.junit.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
public class MarkdownParseTest {
    MarkdownParse md = new MarkdownParse();
    String content;
    @Test
    public void addition() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void TestLinks()throws IOException{   
        content = Files.readString(Path.of("test-file.md"));
        ArrayList<String> links = md.getLinks(content);
        assertEquals("tests if the links are the same", "https://something.com", links.get(0));
        assertEquals("tests if the links are the same", "some-thing.html",links.get(1));
    }

}

