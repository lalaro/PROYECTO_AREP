package com.eci.arep.redis_service.model;

public class RequestPayload {
    private final Boolean paied;
    private final String data;
    private final Boolean isPremium;

    public RequestPayload(Boolean paied, String data, Boolean isPremium) {
        this.data = data;
        this.paied = paied;
        this.isPremium = isPremium;
    }

    public Boolean getPaied() {
        return paied;
    }
    public String getData() {
        return data;
    }
    public Boolean getIsPremium() {
        return isPremium;
    }

    
}
