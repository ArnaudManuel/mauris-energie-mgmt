package com.mauris.energie.mgnt.model;

/**
 * Created by Arnaud on 10.11.2018.
 */
public class VirtualPodListEleement {

    private String name;
    private  VirtualPod virtualPod;

    public VirtualPodListEleement(String name, VirtualPod virtualPod) {
        this.name = name;
        this.virtualPod = virtualPod;
    }

    public String getName() {
        return name;
    }

    public VirtualPod getVirtualPod() {
        return virtualPod;
    }

}
