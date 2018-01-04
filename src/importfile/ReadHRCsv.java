package importfile;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import importfile.inputformat.IcFromHr;

public class ReadHRCsv {

	public List<IcFromHr> getIcListFromCsv() throws IOException {
		// open file input stream
		BufferedReader reader = new BufferedReader(new FileReader(
				"C:\\Users\\nb1964\\Desktop\\IC_FromHR.csv"));

		// read file line by line
		String line = null;
		Scanner scanner = null;
		int index = 0;
		List<IcFromHr> empList = new ArrayList<>();

		while ((line = reader.readLine()) != null) {
			IcFromHr emp = new IcFromHr();
			scanner = new Scanner(line);
			scanner.useDelimiter(",");
			while (scanner.hasNext()) {
				String data = scanner.next();
				if (index == 0)
					emp.setEmpNo(data);
				else if (index == 1)
					emp.setIcTitle(data);
				else if (index == 2)
					emp.setIcName(data);
				else if (index == 3)
					emp.setIcLastName(data);
				else if (index == 4)
					emp.setIcEngTitle(data);
				else if (index == 5)
					emp.setIcEngName(data);
				else if (index == 6)
					emp.setIcEngLast(data);
				else if (index == 7)
					emp.setIcIdCard(data);
				else if (index == 8)
					emp.setIcNumber(data);
				else if (index == 9)
					emp.setIcExpiredYear(data);
				else
					System.out.println("invalid data::" + data);
				index++;
			}
			index = 0;
			empList.add(emp);
		}
		
		//close reader
		reader.close();
		
		return empList;
	}
	
}
