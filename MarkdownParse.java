//https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
/**
 * javac -cp .:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar MarkdownParseTest.java
java -cp .:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore MarkdownParseTest
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {

    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then read link upto next )
        int currentIndex = 0;
        boolean validMarkdownLink = true;
        while(currentIndex < markdown.length()) {
            //System.out.println(validMarkdownLink);
            int imageMarker = markdown.indexOf("!",currentIndex);
            int openBracket = markdown.indexOf("[", currentIndex);
            int closeBracket = markdown.indexOf("]", openBracket);
            int openParen = markdown.indexOf("(", closeBracket);
            int closeParen = markdown.indexOf(")", openParen);
            if(openBracket==-1||openParen==-1||closeBracket==-1||closeParen==-1)
            {
                //System.out.println("no brackets or parantheses");
                validMarkdownLink=false;
                break;
            }
            else if(imageMarker==openBracket-1&& imageMarker!=-1)
            {
                validMarkdownLink=false;
                //System.out.println("not an image");
            }
            else if (markdown.contains("!")) {
                validMarkdownLink=false;
            }
            else if(openParen-closeBracket!=1||closeParen-openParen==1){
                validMarkdownLink=false;
                //System.out.println("incorrect link syntax");
                //second portion tests empty link
                currentIndex=openParen+1;
                continue;  
            }
            else if(validMarkdownLink==true)
            {
                //System.out.println("found");
                toReturn.add(markdown.substring(openParen + 1, closeParen));
            }
            currentIndex = closeParen + 1;
            validMarkdownLink=true;
        }
        return toReturn;
    }
    
    

    public static void main(String[] args) throws IOException {
        System.out.println("github test");
        Path fileName = Path.of(args[0]);
        String content = Files.readString(fileName);
        ArrayList<String> links = getLinks(content);
	    System.out.println(links);
    }
}
