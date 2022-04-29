import static org.junit.Assert.*;
import org.junit.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
public class MarkdownParseTest {
    String content;
    @Test
    public void addition() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void TestLinksGiven()throws IOException{   
        content = Files.readString(Path.of("test-file.md"));
        ArrayList<String> links = MarkdownParse.getLinks(content);
        assertEquals("tests if the links are the same", "https://something.com", links.get(0));
        assertEquals("tests if the links are the same", "some-thing.html",links.get(1));
    }
    @Test
    public void TestLinksStandard()throws IOException{   
        content = Files.readString(Path.of("test.md"));
        ArrayList<String> links = MarkdownParse.getLinks(content);
        assertEquals("tests if the links are the same", "google.com", links.get(1));
    }
    @Test
    public void TestLinksEnd()throws IOException{   
        content = Files.readString(Path.of("testEnd.md"));
        ArrayList<String> links = MarkdownParse.getLinks(content);
        assertEquals("tests if the links are the same", "meow.com", links.get(0));
    }
        
    @Test
    public void TestLinksImage()throws IOException{   
        content = Files.readString(Path.of("testImage.md"));
        ArrayList<String> links = MarkdownParse.getLinks(content);
        assertEquals("tests if the links are the same", 0, links.size());
    }
    @Test
    public void TestLinksBundle()throws IOException{   
        content = Files.readString(Path.of("testImagesSpacedBrackets.md"));
        ArrayList<String> links = MarkdownParse.getLinks(content);
        assertEquals("tests if the links are the same", 0, links.size());
    }
    @Test
    public void TestLinksMiddle()throws IOException{   
        content = Files.readString(Path.of("testMiddle.md"));
        ArrayList<String> links = MarkdownParse.getLinks(content);
        assertEquals("tests if the links are the same", "meow.com", links.get(0));
    }
    @Test
    public void TestLinksNoParantheses()throws IOException{   
        content = Files.readString(Path.of("testNoParantheses.md"));
        ArrayList<String> links = MarkdownParse.getLinks(content);
        assertEquals("tests if the links are the same", 0, links.size());
    }
    @Test
    public void TestLinksNoBrackets()throws IOException{   
        content = Files.readString(Path.of("testNoBracket.md"));
        ArrayList<String> links = MarkdownParse.getLinks(content);
        assertEquals("tests if the links are the same", 0, links.size());
    }
    @Test
    public void TestLinksNoLink()throws IOException{   
        content = Files.readString(Path.of("testNoLink.md"));
        ArrayList<String> links = MarkdownParse.getLinks(content);
        assertEquals("tests if the links are the same", 0, links.size());
    }

}

