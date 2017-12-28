package export;

import java.lang.reflect.Field;

public class IcLicenseExport {
	public String getDataObjectDetail(Object object, String format) throws IllegalArgumentException, IllegalAccessException {
		String output = "";
		Field[] fields = object.getClass().getDeclaredFields();
		String[] formatList = format.split("%");
		if(object != null) {
			if(formatList.length-1 == fields.length) {
				int index = 1;
				for(Field field: fields) {
					String temp = String.format("%"+formatList[index++], field.get(object).toString());
					output += temp;
				}
			}
		}
		return output;
	}
}
