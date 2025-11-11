package com.companyName.paymentMicroservices;

import com.companyName.paymentMicroservices.repository.PaymentRepository;
import com.companyName.paymentMicroservices.repository.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
@Slf4j
public class PaymentMicroservicesApplication implements CommandLineRunner {

    @Autowired
    PaymentRepository paymentRepository;

    public static void main(String[] args) {
		SpringApplication.run(PaymentMicroservicesApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        log.info("Metodo run");
        paymentRepository.save(new Payment("cn4563df3","pippo","gulp","pippo@topolinia.top", "RGNLSN87H13D761R", new BigDecimal("3000.01")));
        paymentRepository.save(new Payment("cn7256su9","pluto","bau","pluto@topolinia.top", "RGNLSN87H13D761R", new BigDecimal("3000.02")));
        paymentRepository.save(new Payment("cn6396dr7","paperino","quack","paperino@paperopoli.top", "FRNFBA85M08D761M", new BigDecimal("3000.03")));
        paymentRepository.save(new Payment("cn2759ds4","topolino","squit","topolino@topolinia.top", "DSTLCU89R52D761R", new BigDecimal("3000.04")));
        paymentRepository.save(new Payment("cn2874da2","sora","roxas","sora@gummiship.kh", "DSTLCU89R52D761R", new BigDecimal("3000.05")));
    }
}
