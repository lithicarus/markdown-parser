//https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
/**
 *ON IENG6: javac -cp .:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar MarkdownParseTest.java
java -cp .:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore MarkdownParseTest

ON WINDOWS: javac -cp ".;lib\junit-4.13.2.jar;lib\hamcrest-core-1.3.jar" MarkdownParseTest.java
java -cp ".;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar" org.junit.runner.JUnitCore MarkdownParseTest

scp ieng6 -r; ssh ieng6 " cd markdown-parser;  /software/CSE/oracle-java-17/jdk-17.0.1/bin/javac -cp .:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar MarkdownParseTest.java; /software/CSE/oracle-java-17/jdk-17.0.1/bin/java -cp .:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore MarkdownParseTest"
 * Hello world Please work pleaseeee
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
            if (openBracket==-1||openParen==-1||closeBracket==-1||closeParen==-1) {
                //System.out.println("no brackets or parantheses");
                validMarkdownLink=false;
                break;
            }
            else if ((imageMarker==openBracket-1&& imageMarker!=-1) 
                    || markdown.contains("!")) {
                validMarkdownLink=false;
                //System.out.println("not an image");
            }
            else if(openParen-closeBracket!=1||closeParen-openParen==1) {
                validMarkdownLink=false;
                //System.out.println("incorrect link syntax");
                //second portion tests empty link
                currentIndex=openParen+1;
                continue;  
            }
            else if (validMarkdownLink==true) {
                //System.out.println("found");
                toReturn.add(markdown.substring(openParen + 1, closeParen));
            }
            currentIndex = closeParen + 1;
            validMarkdownLink=true;
        }
        return toReturn;
    }
    
    

    public static void main(String[] args) throws IOException {
        System.out.println("github");
        Path fileName = Path.of(args[0]);
        String content = Files.readString(fileName);
        ArrayList<String> links = getLinks(content);
	    System.out.println(links);
    }
}
