package export.outputformat.iclicense;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import export.FixedFileExport;
import export.filetemplate.FileFixed;
import export.filetemplate.FileFixedDetail;
import export.filetemplate.FileFixedFieldInfo;
import export.filetemplate.FileFixedFooter;
import export.filetemplate.FileFixedHeader;
import export.filetemplate.FileFixedProcessor;
import importfile.ReadHRCsv;
import importfile.inputformat.IcFromHr;

public class TestListIcData {
	private List<IcLicense> listIc = new ArrayList<IcLicense>();
	
	public List<IcLicense> getIcLicenseList(List<IcFromHr> icList){
		
		for(IcFromHr list: icList) {
			IcLicense icl = new IcLicense();
			icl.licenseCode = list.getIcNumber();
			icl.idNo = list.getIcIdCard().replaceAll(" ", "");
			icl.empNo = list.getEmpNo();
			icl.name = list.getIcEngName();
			icl.surname = list.getIcEngLast();
			icl.prefix = list.getIcEngTitle().replaceAll(" ", "");
			int expiredYear = 1999;
			if(list.getIcExpiredYear() != "") {
				expiredYear = Integer.parseInt(list.getIcExpiredYear());
			}
			Calendar expiredDate = Calendar.getInstance();
			expiredDate.set(expiredYear, 12, 31);
			Calendar today = Calendar.getInstance();

			if(expiredDate.before(today)) {
				icl.status = "I";
			} else {
				icl.status = "A";
			}
			
			
			listIc.add(icl);
		}
		
		return listIc;
	}
	public FileFixed testXML() {
		
		FileFixedProcessor ffp = new FileFixedProcessor();
		
		FileFixedFieldInfo[] list1 = ffp.getFileFormat("MSLI0100_TDMMYY_BCAP.TXT","header");
		FileFixedFieldInfo[] list2 = ffp.getFileFormat("MSLI0100_TDMMYY_BCAP.TXT","detail");
		FileFixedFieldInfo[] list3 = ffp.getFileFormat("MSLI0100_TDMMYY_BCAP.TXT","footer");
		
		FileFixedDetail fileDetail = new FileFixedDetail();
		fileDetail.setFieldInfo(list2);
		FileFixedHeader fileHeader = new FileFixedHeader();
		fileHeader.setFieldInfo(list1);
		FileFixedFooter fileFooter = new FileFixedFooter();
		fileFooter.setFieldInfo(list3);
		
		FileFixed ff = new FileFixed();
		ff.setFileHeader(fileHeader);
		ff.setFileDetail(fileDetail);
		ff.setFileFooter(fileFooter);
		ff.setFileName("MSLI0100_TDMMYY_BCAP.TXT");
		ff.setFileLength(400);
		
		return ff;
	}
	public List<String> getFormatOutputIcData(){
		List<String> list = new ArrayList<String>();
		ReadHRCsv readcsv = new ReadHRCsv();
		List<IcFromHr> ls = null;
		try {
			
			FixedFileExport ic = new FixedFileExport();
			FileFixed fileFormat = testXML();
			//header

			IcLicenseHeader icheader = new IcLicenseHeader();
			icheader.date = "04012561";
			if(fileFormat.getFileHeader().getFieldInfo() !=null) {
				String yeah = ic.getDataObjectDetail(icheader, fileFormat.getFileHeader().getFieldInfo());
				list.add(yeah);
			}
			
			//detail
			ls = readcsv.getIcListFromCsv();
			TestListIcData testL = new TestListIcData();
			List<IcLicense>  listIc = testL.getIcLicenseList(ls);		
			
			if(fileFormat.getFileDetail().getFieldInfo()!=null && listIc != null) {
				for(IcLicense icl:listIc) {
					String yeah = ic.getDataObjectDetail(icl, fileFormat.getFileDetail().getFieldInfo());
					list.add(yeah);
				}
			}
			//footer
			IcLicenseFooter icfotter = new IcLicenseFooter();
			icfotter.totalRecord = listIc.size() + "";
			if(fileFormat.getFileFooter().getFieldInfo()!=null) {
				String yeah = ic.getDataObjectDetail(icfotter, fileFormat.getFileFooter().getFieldInfo());
				list.add(yeah);
			}
			return list;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
