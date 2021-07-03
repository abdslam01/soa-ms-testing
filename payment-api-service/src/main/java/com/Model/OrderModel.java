package com.Model;

public class OrderModel {
    private Double total;
    private String currency;
    private String method;
    private String intent;
    private String description;

    public OrderModel() {}
    public OrderModel(Double total, String currency, String method, String intent, String description) {
        this.total = total;
        this.currency = currency;
        this.method = method;
        this.intent = intent;
        this.description = description;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "OrderModel{" +
                "total=" + total +
                ", currency='" + currency + '\'' +
                ", method='" + method + '\'' +
                ", intent='" + intent + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
