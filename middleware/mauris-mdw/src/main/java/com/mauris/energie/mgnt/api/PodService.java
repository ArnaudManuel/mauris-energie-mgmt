package com.mauris.energie.mgnt.api;

import com.mauris.energie.mgnt.model.VirtualPod;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;

/**
 * Created by Arnaud on 10.11.2018.
 */
public class PodService implements PodApi {
    private static final HashMap<String, VirtualPod> cache = new HashMap<>();

    @Override
    public ResponseEntity<VirtualPod> getPodContent(@ApiParam(value = "the pod identification", required = true) @PathVariable("virtual-id") String virtualId) {
        VirtualPod virtualPod = cache.get(virtualId);
        if(virtualPod == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(virtualPod);
    }

    @Override
    public ResponseEntity<Void> podPost(@ApiParam(value = "the pod identification", required = true) @RequestParam(value = "virtual-id", required = true) @NotNull @Valid String virtualId, @ApiParam(value = "the pod contents, coma separated") @RequestParam(value = "contents", required = false) @Valid String contents) {

        VirtualPod virtualPod = new VirtualPod();
        for(String content : contents.split(","))
            virtualPod.add(content);

        cache.put(virtualId, virtualPod);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<VirtualPod> setPodContent(@ApiParam(value = "the pod identification", required = true) @PathVariable("virtual-id") String virtualId, @ApiParam(value = "the pod contents, coma separated", required = true) @RequestParam(value = "contents", required = true) @NotNull @Valid String contents) {
        VirtualPod virtualPod = cache.get(virtualId);
        if(virtualPod == null)
            return ResponseEntity.notFound().build();

        virtualPod = new VirtualPod();
        for(String content : contents.split(","))
            virtualPod.add(content);

        cache.put(virtualId, virtualPod);

        return ResponseEntity.ok().body(virtualPod);
    }



}
