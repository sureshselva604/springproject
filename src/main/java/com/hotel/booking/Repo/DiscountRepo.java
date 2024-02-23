package com.hotel.booking.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.booking.Model.Discount;

@Repository
public interface DiscountRepo extends JpaRepository<Discount, Long> {

	Discount findByDiscountId(Long discountId);

}
