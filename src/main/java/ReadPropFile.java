import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadPropFile {
	private Properties prop;
	private InputStream input;
	private final String fileName="config.properties";
	
	private String email;
	private String password;
	private String chromeDriverLocation;
	
	public ReadPropFile() {
		prop= new Properties();
		try {
			input=ReadPropFile.class.getClassLoader().getResourceAsStream(fileName);
			if(input==null) throw new FileNotFoundException();
			
			//load a prop file from classpath
			prop.load(input);
			
			//get prop values and assign the values
			this.email=prop.getProperty("email");
			this.password=prop.getProperty("password");
			this.chromeDriverLocation=prop.getProperty("chromeDriverLocation");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(input!=null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public String getEmail() {
		return this.email;
	}
	public String getPassword() {
		return this.password;
	}
	public String getChromeDriverLocation() {
		return this.chromeDriverLocation;
	}
	
}
