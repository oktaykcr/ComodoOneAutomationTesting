import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import io.qameta.allure.Attachment;

public class AllureAttachmentsFactory {
	
	/**
	 * This method attach the text file into the test function.
	 * @param attachName the name of the attach file
	 * @param displayed message of the text file.
	 * @return the message as a formatted string.
	 */
	@Attachment(value = "{attachName}", type = "text/plain")
	  public static String saveTextLog(String attachName, String message) {
	      return message;
	}
	
	/**
	 * This method attach desired image file into the test function.
	 * @param attachName the name of the attach file.
	 * @param fileName the name of the image file with extension. 
	 * @return the image as a formatted ByteArray.
	 */
	@Attachment(value = "{attachName}", type = "image/png")
	public static byte[] saveImageAttach(String attachName,String fileName) {
	    try {
	        URL defaultImage = AllureAttachmentsFactory.class.getResource(fileName);
	        File imageFile = new File(defaultImage.toURI());
	        return toByteArray(imageFile);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return new byte[0];
	}
	
	/**
	 * This method attach downloadable file into the test function.
	 * @param attachName the name of the downloadable content
	 * @param fileName the name of the downloadable file with extension
	 * @return the file as a formatted ByteArray.
	 */
	@Attachment(value = "{attachName}", type="downloadable")
	public static byte[] saveDownloadableContent(String attachName,String fileName) {
	    try {
	        URL contentURL = AllureAttachmentsFactory.class.getResource(fileName);
	        File contentFile = new File(contentURL.toURI());
	        return toByteArray(contentFile);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return new byte[0];
	}

	private static byte[] toByteArray(File file) throws IOException {
	    return Files.readAllBytes(Paths.get(file.getPath()));
	}
}
