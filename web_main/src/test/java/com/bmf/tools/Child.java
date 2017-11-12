package com.bmf.tools;

import org.springframework.core.io.Resource;

public class Child extends Parent {
    private Resource resource;

    public Resource getResource() {
        return resource;
    }

    public Child setResource(Resource resource) {
        this.resource = resource;
        return this;
    }

    @Override
    public void get() {
        System.out.println(this.getSingle());
        System.out.println(this.getTo());
    }
}
