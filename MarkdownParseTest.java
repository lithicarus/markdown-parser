import static org.junit.Assert.*;
import org.junit.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
// HELLO WORLD
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
        assertEquals("tests if the links are the same", "google.com", links.get(0));
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

    @Test
    public void TestLinkMultipleLines()throws IOException{   
        content = Files.readString(Path.of("testLinkMultipleLines.md"));
        ArrayList<String> links = MarkdownParse.getLinks(content);
        assertEquals("tests if the links are the same", 1, links.size());
    }
    @Test
    public void TestLinkCurlyBraces()throws IOException{   
        content = Files.readString(Path.of("testLinkCurlyBraces.md"));
        ArrayList<String> links = MarkdownParse.getLinks(content);
        assertEquals("tests if the links are the same", 0, links.size());
    }

    @Test
    public void TestIncorrectImage()throws IOException{   
        content = Files.readString(Path.of("testIncorrectImage.md"));
        ArrayList<String> links = MarkdownParse.getLinks(content);
        assertEquals("tests if the links are the same", 0, links.size());
    }

    @Test
    public void TestLinksWrongFormat()throws IOException{   
        content = Files.readString(Path.of("test-file2.md"));
        ArrayList<String> links = MarkdownParse.getLinks(content);
        assertEquals("tests if the links are the same", 0, links.size());
    }

    @Test
    public void TestNoLinks()throws IOException{   
        content = Files.readString(Path.of("test-file3.md"));
        ArrayList<String> links = MarkdownParse.getLinks(content);
        assertEquals("tests if the links are the same", 0, links.size());
    }

    @Test
    public void TestNoLinks2()throws IOException{   
        content = Files.readString(Path.of("test-file4.md"));
        ArrayList<String> links = MarkdownParse.getLinks(content);
        assertEquals("tests if the links are the same", 0, links.size());
    }

    @Test
    public void TestLinksWrongPlace()throws IOException{   
        content = Files.readString(Path.of("test-file5.md"));
        ArrayList<String> links = MarkdownParse.getLinks(content);
        assertEquals("tests if the links are the same", 0, links.size());
    }

    @Test
    public void TestWrongFormat()throws IOException{   
        content = Files.readString(Path.of("test-file6.md"));
        ArrayList<String> links = MarkdownParse.getLinks(content);
        assertEquals("tests if the links are the same", 0, links.size());
    }

    @Test
    public void testSnippet1()throws IOException{   
        content = Files.readString(Path.of("snippet1.md"));
        ArrayList<String> links = MarkdownParse.getLinks(content);
        
        ArrayList<String> expected = new ArrayList<>();

        
        expected.add("`google.com");
        expected.add("google.com");
        expected.add("ucsd.edu");
        
        assertEquals(expected, links);
        assertEquals(3, links.size());
        
    }
    @Test
    public void testSnippet2()throws IOException{   
        content = Files.readString(Path.of("snippet2.md"));
        ArrayList<String> links = MarkdownParse.getLinks(content);
        ArrayList<String> expected = new ArrayList<>();

        expected.add("a.com");
        expected.add("a.com(())");
        expected.add("example.com");
        
        assertEquals(expected, links);
        assertEquals(3, links.size());
    }
    @Test
    public void testSnippet3()throws IOException{   
        content = Files.readString(Path.of("snippet3.md"));
        ArrayList<String> links = MarkdownParse.getLinks(content);

        //https://sites.google.com/eng.ucsd.edu/cse-15l-spring-2022/schedule
        ArrayList<String> expected = new ArrayList<>();

        expected.add("https://sites.google.com/eng.ucsd.edu/cse-15l-spring-2022/schedule");

        
        assertEquals(expected, links);
        assertEquals(1, links.size());
    }

}

