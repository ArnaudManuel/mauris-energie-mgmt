package com.mauris.energie.mgnt.services;

import com.mauris.energie.mgnt.api.PodApi;
import com.mauris.energie.mgnt.model.VirtualPod;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by Arnaud on 10.11.2018.
 */
public class PodService implements PodApi {
    @Override
    public ResponseEntity<VirtualPod> getPodContent(@ApiParam(value = "the pod identification", required = true) @PathVariable("virtual-id") String virtualId) {
        return null;
    }

    @Override
    public ResponseEntity<Void> podPost(@ApiParam(value = "the pod identification", required = true) @RequestParam(value = "virtual-id", required = true) @NotNull @Valid String virtualId, @ApiParam(value = "the pod contents, coma separated") @RequestParam(value = "contents", required = false) @Valid String contents) {
        return null;
    }

    @Override
    public ResponseEntity<VirtualPod> setPodContent(@ApiParam(value = "the pod identification", required = true) @PathVariable("virtual-id") String virtualId, @ApiParam(value = "the pod contents, coma separated", required = true) @RequestParam(value = "contents", required = true) @NotNull @Valid String contents) {
        return null;
    }
}
