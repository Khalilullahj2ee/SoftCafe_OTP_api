package com.softcafe.otp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softcafe.otp.model.OtpModel;

public interface OtpRepository extends JpaRepository<OtpModel, Long> {

}
