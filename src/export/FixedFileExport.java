package export;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import export.filetemplate.FileFixedFieldInfo;
import export.filetemplate.FileFixed;


public class FixedFileExport {
	
	public String getDataObjectDetail(Object object, FileFixedFieldInfo[] formatList) {
		String output = "";
		Field[] fields = object.getClass().getDeclaredFields();
		if(object != null) {
			if(formatList.length == fields.length) {
				int index = 0;
				for(Field field: fields) {
					FileFixedFieldInfo info = formatList[index];
					String defaultVal = formatList[index].getField_default();
					String temp;
					if(defaultVal != "") {
						temp = String.format("%-"+info.getField_length()+"s", defaultVal);
					} else {
						try {
							temp = String.format("%-"+info.getField_length()+"s", field.get(object).toString());
						} catch (IllegalArgumentException | IllegalAccessException e) {
							temp = "";
						}
					}
					output += temp;
					index++;
				}
			}
		}
		return output;
	}
	public void writeFileFormatFile(FileFixed ff, List<String> list) {

		Path file = Paths.get(ff.getFileName());
		try {
			Files.write(file, list, Charset.forName("UTF-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
