package filetemplate;

public class FileFixedLengthInfo {
	
	private String field_name;
	private int field_length;
	private String field_default;
	
	public String getField_name() {
		return field_name;
	}

	public void setField_name(String field_name) {
		this.field_name = field_name;
	}

	public int getField_length() {
		return field_length;
	}

	public void setField_length(int field_length) {
		this.field_length = field_length;
	}

	public String getField_default() {
		return field_default;
	}

	public void setField_default(String field_default) {
		this.field_default = field_default;
	}

	public FileFixedLengthInfo(String field_name, int field_length, String field_default) {
		this.field_name = field_name;
		this.field_length = field_length;
		this.field_default = field_default;
	}
	
	public FileFixedLengthInfo() {
		
	}
	
	

}
