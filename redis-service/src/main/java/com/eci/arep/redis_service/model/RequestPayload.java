package com.eci.arep.redis_service.model;

public class RequestPayload {
    private final Boolean paied;
    private final String data;

    public RequestPayload(Boolean paied, String data) {
        this.data = data;
        this.paied = paied;
    }

    public Boolean getPaied() {
        return paied;
    }
    public String getData() {
        return data;
    }

    
}
