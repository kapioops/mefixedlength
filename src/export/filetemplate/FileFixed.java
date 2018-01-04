package export.filetemplate;

public class FileFixed {
	private String fileName;
	private int fileLength;
	private FileFixedHeader fileHeader;
	private FileFixedDetail fileDetail;
	private FileFixedFooter fileFooter;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getFileLength() {
		return fileLength;
	}
	public void setFileLength(int fileLength) {
		this.fileLength = fileLength;
	}
	public FileFixedHeader getFileHeader() {
		return fileHeader;
	}
	public void setFileHeader(FileFixedHeader fileHeader) {
		this.fileHeader = fileHeader;
	}
	public FileFixedDetail getFileDetail() {
		return fileDetail;
	}
	public void setFileDetail(FileFixedDetail fileDetail) {
		this.fileDetail = fileDetail;
	}
	public FileFixedFooter getFileFooter() {
		return fileFooter;
	}
	public void setFileFooter(FileFixedFooter fileFooter) {
		this.fileFooter = fileFooter;
	}
}
