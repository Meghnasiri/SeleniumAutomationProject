package Practice.TestData;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

public class DataReader {

	//Json to String
	
	public List<HashMap<String, String>>  jsonDataToString() throws IOException {
		
	String jsonContent=	FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//java//Practice//TestData//PurchaseOrder.json"), StandardCharsets.UTF_8);
		
  //String to HashMap using Jackson DataBind Dependency 
	ObjectMapper mapper = new ObjectMapper();
	
	 List<HashMap<String, String>> data =mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {});
	
	return data;
	}
	
	
}
