package ReadFile.ReadFile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class ReadFile {

	public static void main(String[] args) throws IOException {

		BufferedReader bfr = new BufferedReader(new FileReader("test/src/test/java/input.txt"));
		String line = bfr.readLine();
		List<TableDTO>  dtoList = new ArrayList<>();
		while((line = bfr.readLine()) != null ){
			String[] arr = line.split("//|");
			TableDTO tableDTO = new TableDTO();
			tableDTO.setId(Integer.parseInt(arr[0].trim()));
			tableDTO.setName(arr[1].trim());
			tableDTO.setSurName(arr[2].trim());
			DateFormat format = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
			Date date = null;

			try {
				date = format.parse(arr[3]);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tableDTO.setTime(date);
			dtoList.add(tableDTO);
		}

		for (TableDTO dto: dtoList)
		{
			if(dto.getTime().after(new Date (119,4,9))) {
				System.out.println(dto.getName());// this line prints names on console
				
			}

	}

}

}

class TableDTO{
	int id;
	String name;
	String surName;
	Date time;
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurName() {
		return surName;
	}
	public void setSurName(String surName) {
		this.surName = surName;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public void setId(int id) {
		this.id = id;
	}

}
