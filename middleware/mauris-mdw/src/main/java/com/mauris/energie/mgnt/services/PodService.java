package com.mauris.energie.mgnt.services;

import com.mauris.energie.mgnt.ambrosusTemplate.RestAccess;
import com.mauris.energie.mgnt.helper.HistoryAgregator;
import com.mauris.energie.mgnt.mappers.TemplateConverter;
import com.mauris.energie.mgnt.model.History;
import com.mauris.energie.mgnt.model.VirtualPod;
import com.mauris.energie.mgnt.model.VirtualPodListEleement;
import com.mauris.energie.mgnt.xmlGenerator.Device;
import com.mauris.energie.mgnt.xmlGenerator.MeteredValue;

import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Arnaud on 10.11.2018.
 */
@RestController
public class PodService  {
    @Autowired
    RestAccess podsApi;
	String path = "E:\\workspacBlueArk\\file.xml" ;

    private static final HashMap<String, VirtualPod> cache = new HashMap<>();
    static{
        VirtualPod test = new VirtualPod();
        test.add("CH1014001234500000000000000006860_test");
        cache.put("test", test);
    }

    @GetMapping("/")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("api is working");
    }

    @GetMapping("/pods")
    public ResponseEntity<List<VirtualPodListEleement>> getAl(){
        List<VirtualPodListEleement> content = new LinkedList<>();
        for (Map.Entry<String, VirtualPod> value : cache.entrySet())
            content.add(new VirtualPodListEleement(value.getKey(), value.getValue()));
        return ResponseEntity.ok(content);
    }

    @GetMapping("/pods/{virtual-id}")
    public ResponseEntity<VirtualPod> getPodContent(@ApiParam(value = "the pod identification", required = true) @PathVariable("virtual-id") String virtualId) {
        VirtualPod virtualPod = cache.get(virtualId);
        if (virtualPod == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(virtualPod);
    }

    @GetMapping("/pods/{virtual-id}/data")
    public ResponseEntity<History> getPodData(@ApiParam(value = "the pod identification", required = true) @PathVariable("virtual-id") String virtualId, @ApiParam(value = "the pod identification format dd-MM-yyyy", required = true) @RequestParam(value = "from", required = true) @NotNull @Valid String from, @ApiParam(value = "the pod identification format dd-MM-yyyy", required = true) @RequestParam(value = "to", required = true) @NotNull @Valid String to) {


        SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");

        Date start;
        Date end;
        try {
            start = formater.parse(from + "-00-00-00");
            end  = formater.parse(to + "-23-59-59");
        } catch (ParseException e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(getData(virtualId, start, end));

    }



    @PostMapping("/pods/{virtual-id}")
    public ResponseEntity<Void> podPost(@ApiParam(value = "the pod identification", required = true) @RequestParam(value = "virtual-id", required = true) @NotNull @Valid String virtualId, @ApiParam(value = "the pod contents, coma separated") @RequestParam(value = "contents", required = false) @Valid String contents) {

        VirtualPod virtualPod = new VirtualPod();
        for (String content : contents.split(","))
            virtualPod.add(content);

        cache.put(virtualId, virtualPod);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/pods/{virtual-id}")
    public ResponseEntity<VirtualPod> setPodContent(@ApiParam(value = "the pod identification", required = true) @PathVariable("virtual-id") String virtualId, @ApiParam(value = "the pod contents, coma separated", required = true) @RequestParam(value = "contents", required = true) @NotNull @Valid String contents) {
        VirtualPod virtualPod = cache.get(virtualId);
        if (virtualPod == null)
            return ResponseEntity.notFound().build();

        virtualPod.clear();
        for (String content : contents.split(","))
            virtualPod.add(content);

        cache.put(virtualId, virtualPod);

        return ResponseEntity.ok().body(virtualPod);
    }
    @PutMapping("/pods/{virtual-id}/fees")
    public ResponseEntity<VirtualPod> setSimpleTarification(@ApiParam(value = "the pod identification", required = true) @PathVariable("virtual-id") String virtualId, @ApiParam(value = "the pod contents, coma separated", required = true) @RequestParam(value = "contents", required = true) @NotNull @Valid String contents) {
        boolean _value = contents.equalsIgnoreCase("true");

        VirtualPod virtualPod = cache.get(virtualId);
        if (virtualPod == null)
            return ResponseEntity.notFound().build();

        virtualPod.setOnlySimpleFee(_value);

        return ResponseEntity.ok().body(virtualPod);
    }
	@GetMapping("/pods/file")
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

    private History getData(String key, Date from, Date to){
        VirtualPod vPod = cache.get(key);
        if(vPod != null){
            History base = TemplateConverter.toHistory(podsApi.getAmbrosus(vPod.get(0),  from,  to));
            ArrayList<History> reamining = new ArrayList<>();
            if(vPod.size() == 1)
                return base;

            for(int cpt = 1 ; cpt < vPod.size() ; ++ cpt)
                reamining.add(TemplateConverter.toHistory(podsApi.getAmbrosus(vPod.get(cpt),  from,  to)));

            return HistoryAgregator.aggragate(base, (History[]) reamining.toArray());
        }else{
            return TemplateConverter.toHistory(podsApi.getAmbrosus(key,  from,  to));
        }
    }
}