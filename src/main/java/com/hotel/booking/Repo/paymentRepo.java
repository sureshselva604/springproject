package com.hotel.booking.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.booking.Model.Bill;
import com.hotel.booking.Model.Payment;


@Repository
public interface paymentRepo extends JpaRepository<Payment, Long> {

	boolean existsByBillId(Bill bill);

}
