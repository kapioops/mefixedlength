package filetemplate;

import export.filetemplate.*;

public class FileFixed {
	private String fileName;
	private int fileSize;
	private FileFixedHeader fileHeader;
	private FileFixedDetail fileDetail;
	private FileFixedFooter fileFooter;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getFileSize() {
		return fileSize;
	}
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
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
	@Override
	public String toString() {
		return "FileFixed [fileName=" + fileName + ", fileSize=" + fileSize + ", fileHeader=" + fileHeader
				+ ", fileDetail=" + fileDetail + ", fileFooter=" + fileFooter + "]";
	}
	
	public FileFixed() {}
	
	public FileFixed(String fileName, int fileSize, FileFixedHeader fileHeader, FileFixedDetail fileDetail,
			FileFixedFooter fileFooter) {
		super();
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.fileHeader = fileHeader;
		this.fileDetail = fileDetail;
		this.fileFooter = fileFooter;
	}
	
	
}
