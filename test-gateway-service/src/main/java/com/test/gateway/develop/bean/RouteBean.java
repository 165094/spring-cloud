package com.test.gateway.develop.bean;

import lombok.Data;

@Data
public class RouteBean {

    private Integer id;
    /**
     * spring.application.name
     */
    private String routesId;
    private String uri;
    private String predicates;
    private String filters;
    private String alias;
    private String serverName;
    private boolean isAddSwagger = true;

    public RouteBean() {
    }

    public RouteBean(String routesId, String alias, String filters) {
        this.routesId = routesId;
        this.uri = "lb://" + routesId.toUpperCase();
        this.predicates = "Path=/" + alias + "/**";
        this.alias = alias;
        this.filters = filters;
        this.serverName = alias;
    }

    public RouteBean(String routesId, String alias, String filters, String serverName) {
        this.routesId = routesId;
        this.uri = "lb://" + routesId.toUpperCase();
        this.predicates = "Path=/" + alias + "/**";
        this.alias = alias;
        this.filters = filters;
        this.serverName = serverName;
    }
}
