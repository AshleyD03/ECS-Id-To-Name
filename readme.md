# ECS Id To Name

HELLO!!! Somebodies been going through the wiki, haven't you 😇 <br>
This is my answer to week 1's challenge, that being : <br>
*Write a Java program which converts an ECS email name into a person's full name*
My answer is a little long, but this is my first proper java program, so please be nice 😊. *Please be nice, im a JS programmer* 

## How it works 

1. First get there ID from the command line. To do this I used a Scanner Instance on the System.in. It's not that uggly, but for the sake of simplicity on the main instance, I put it into a nice input function, acting as a python input. 
2. Then we attach the ID to the end of "https://www.ecs.soton.ac.uk/people/" to make our url target.
3. Fetch the HTML with the url. I used an input stream from an instance of a URL object, that then it's strean into a StringBuffer.
4. Check that we got the right page. With ID's there are some funny edge cases, where putting "dan" will re-direct you to the actual id "dan1". To check find read the title of the page to see if it's **301 Moved Permanently**. If so, find the href on the page and Fetch the HTML again with the new url.
5. Search through the html StringBuffer for the tag with **property="name"** and print the value. If the value is empty or there is no propert="name" print nothing was found.

## Bibliography 

Just incase anyone would like further references on the specific methods use in my code: 
- https://www.geeksforgeeks.org/ways-to-read-input-from-console-in-java/ -
- https://stackoverflow.com/questions/31462/how-to-fetch-html-in-java -
- https://www.javatpoint.com/URL-class -
- https://www.geeksforgeeks.org/stringbuffer-indexof-method-in-java-with-
- https://docs.oracle.com/javase/7/docs/api/java/lang/StringBuffer.html
- https://stackoverflow.com/questions/5884353/how-to-insert-a-character-in-a-
- https://www.baeldung.com/java-blank-empty-strings
This can also be found at the bottom of the IdToName.java file