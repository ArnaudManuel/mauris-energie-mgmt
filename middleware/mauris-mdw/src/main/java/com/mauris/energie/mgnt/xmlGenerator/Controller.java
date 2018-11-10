package com.mauris.energie.mgnt.xmlGenerator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
@RestController
public class Controller {
	
	String path = "E:\\workspacBlueArk\\file.xml" ;
	@GetMapping("/test")
	public Device test() {
		List<MeteredValue> listH = new LinkedList<>();
		MeteredValue h = new MeteredValue();
		h.setObis("1-1:1.8.1");
		h.setReadingDate("2018-11-02");
		h.setTime("2018-11-01T00:01:28");
		MeteredValue h1 = new MeteredValue();
		h1.setObis("1-1:2.8.1");
		h1.setReadingDate("2018-11-02");
		h1.setTime("2018-11-01T00:01:28");
		MeteredValue h2 = new MeteredValue();
		h2.setObis("1-1:0.1.0");
		h2.setReadingDate("2018-11-02");
		h2.setTime("2018-11-01T00:01:28");
		listH.add(h);
		listH.add(h1);
		listH.add(h2);
		Device test = new Device();
		test.setMeteredValue(listH);
		test.setMeteringcode("CH1014001234500000000000000016802");
		return test;
		
	}
	@GetMapping("/file")
	public void generateFile(HttpServletResponse response) throws IOException {
		List<MeteredValue> listH = new LinkedList<>();
		MeteredValue h = new MeteredValue();
		h.setObis("1-1:1.8.1");
		h.setReadingDate("2018-11-02");
		h.setTime("2018-11-01T00:01:28");
		MeteredValue h1 = new MeteredValue();
		h1.setObis("1-1:2.8.1");
		h1.setReadingDate("2018-11-02");
		h1.setTime("2018-11-01T00:01:28");
		MeteredValue h2 = new MeteredValue();
		h2.setObis("1-1:0.1.0");
		h2.setReadingDate("2018-11-02");
		h2.setTime("2018-11-01T00:01:28");
		listH.add(h);
		listH.add(h1);
		listH.add(h2);
		Device test = new Device();
		test.setMeteredValue(listH);
		test.setMeteringcode("CH1014001234500000000000000016802");
		
	
		 try {

		        File file = new File(path);
		        JAXBContext jaxbContext = JAXBContext.newInstance(Device.class);
		        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		        // output pretty printed
		        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		        jaxbMarshaller.marshal(test, file);
		        jaxbMarshaller.marshal(test, System.out);

		      } catch (JAXBException e) {
		        e.printStackTrace();
		      }
		 Path filePath = Paths.get(path);
			byte[] contents = Files.readAllBytes(filePath);

			
			streamReport(response, contents, "Report");

	}
	protected void streamReport(HttpServletResponse response, byte[] data, String name) throws IOException {

		response.setContentType("application/xml");
		response.setHeader("Content-disposition", "attachment; filename=" + name);
		response.setContentLength(data.length);

		response.getOutputStream().write(data);
		response.getOutputStream().flush();
		Path fileToDeletePath = Paths.get(path);
		Files.delete(fileToDeletePath);
	}


}
