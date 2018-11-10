package com.mauris.energie.mgnt.services;

import com.mauris.energie.mgnt.ambrosusTemplate.RestAccess;
import com.mauris.energie.mgnt.mappers.TemplateConverter;
import com.mauris.energie.mgnt.model.History;
import com.mauris.energie.mgnt.model.VirtualPod;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Arnaud on 10.11.2018.
 */
@RestController
public class PodService  {
    @Autowired
    RestAccess podsApi;

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

    @GetMapping("/pods/{virtual-id}")
    public ResponseEntity<VirtualPod> getPodContent(@ApiParam(value = "the pod identification", required = true) @PathVariable("virtual-id") String virtualId) {
        VirtualPod virtualPod = cache.get(virtualId);
        if (virtualPod == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(virtualPod);
    }

    @GetMapping("/pods/{virtual-id}/data")
    public ResponseEntity<String> getPodData(@ApiParam(value = "the pod identification", required = true) @PathVariable("virtual-id") String virtualId, @ApiParam(value = "the pod identification format dd-MM-yyyy", required = true) @RequestParam(value = "from", required = true) @NotNull @Valid String from, @ApiParam(value = "the pod identification format dd-MM-yyyy", required = true) @RequestParam(value = "to", required = true) @NotNull @Valid String to) {
        SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");

        Date start;
        Date end;
        try {
            start = formater.parse(from + "-00-00-00");
            end  = formater.parse(to + "-23-59-59");
        } catch (ParseException e){
            return ResponseEntity.badRequest().build();
        }



        History history = TemplateConverter.toHistory(podsApi.getAmbrosus(virtualId,  start,  end));
        return ResponseEntity.ok().body(history.toString());

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

        virtualPod = new VirtualPod();
        for (String content : contents.split(","))
            virtualPod.add(content);

        cache.put(virtualId, virtualPod);

        return ResponseEntity.ok().body(virtualPod);
    }

}