package com.tawsif.ecommerce.kafka;

import com.tawsif.ecommerce.models.Customer;

public class CustomerEvent {
    public String eventType;
    public String timestamp;
    public Customer data;

    public CustomerEvent(){}

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Customer getData() {
        return data;
    }

    public void setData(Customer data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CustomerEvent{" +
                "eventType='" + eventType + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", data=" + data +
                '}';
    }
}
