package com.mauris.energie.mgnt.services;

import com.mauris.energie.mgnt.api.PodApi;
import com.mauris.energie.mgnt.model.VirtualPod;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by Arnaud on 10.11.2018.
 */
public class PodService implements PodApi {
    @Override
    public ResponseEntity<VirtualPod> getPodContent(@ApiParam(value = "the pod identification", required = true) @PathVariable("virtual-id") String virtualId) {
        return null;
    }
}
