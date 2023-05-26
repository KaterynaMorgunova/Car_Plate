package com.f1dot5.data.repository;

import com.f1dot5.data.Customer;
import com.f1dot5.data.CustomerDelivery;

public interface CustomerDeliveryRepository {
    CustomerDelivery save(CustomerDelivery customerDelivery);
}
