import java.util.Scanner;
import java.net.URL;
import java.io.InputStream;
import java.lang.StringBuffer;

public class IdToName {

  /**
   * Request an Id, parse it into a url, fetch the url and then search for a name.
   */
  public static void main(String[] args) {

    // Get HTML
    String id = input("So, what's the ID of the peep you wanna find?");
    StringBuffer html = fetchHtml("https://www.ecs.soton.ac.uk/people/" + id);

    // Check if page moved, if so find url and refetch
    int titleInd = html.indexOf("title>") + 6;
    String titleMsg = readUntil(html, titleInd, '<');

    if (titleMsg.equals("301 Moved Permanently")) {
      int hrefInd = html.indexOf("href=\"") + 6;
      String newUrl = readUntil(html, hrefInd, '"');
      html = fetchHtml(newUrl);
    }

    // Search for name
    int nameInd = html.indexOf("property=\"name\">") + 16;
    String name = readUntil(html, nameInd, '<');

    // Concat result
    if (name.isBlank() || name.isEmpty())
      name = "Nothing";
    else
      name = "\"" + name + "\"";
    System.out.println("Found... " + name);
  }

  /**
   * A python-like input function, for taking values from the cmd line.
   * 
   * @param msg what to print
   * @return what is inputed
   */
  public static String input(String msg) {
    System.out.println(msg);

    Scanner scan = new Scanner(System.in);
    String input = scan.nextLine();
    scan.close();

    return input;
  }

  /**
   * Returns a StringBuffer contains the whole HTML page response.
   * 
   * @param url
   * @return StringBuffer containing html response
   */
  public static StringBuffer fetchHtml(String url) {
    StringBuffer buffer = new StringBuffer();

    // Check using https
    if (url.charAt(4) != 's') {
      url = url.substring(0, 4) + 's' + url.substring(4, url.length());
    }

    try {
      // loop through input stream
      InputStream stream = new URL(url).openStream();
      int ptr = 0;

      while ((ptr = stream.read()) != -1) {
        buffer.append((char) ptr);
      }
      stream.close();

    } catch (Exception e) {
      System.out.println(e);
    }
    return buffer;
  }

  /**
   * Return string of values up to the end char in a StringBuffer, starting from index.
   * 
   * @param html page to read through
   * @param index where to start
   * @param end what to end at
   * @return what was read
   */
  public static String readUntil(StringBuffer html, int index, char end) {
    String value = "";
    while (html.charAt(index) != end) {
      value += html.charAt(index);
      index++;
    }
    return value;
  }
}

/*
 * biblography : -
 * https://www.geeksforgeeks.org/ways-to-read-input-from-console-in-java/ -
 * https://stackoverflow.com/questions/31462/how-to-fetch-html-in-java -
 * https://www.javatpoint.com/URL-class -
 * https://www.geeksforgeeks.org/stringbuffer-indexof-method-in-java-with-
 * https://docs.oracle.com/javase/7/docs/api/java/lang/StringBuffer.html
 * https://stackoverflow.com/questions/5884353/how-to-insert-a-character-in-a-
 * https://www.baeldung.com/java-blank-empty-strings
 */