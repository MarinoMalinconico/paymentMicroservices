package com.companyName.paymentMicroservices;

import com.companyName.paymentMicroservices.repository.PaymentRepository;
import com.companyName.paymentMicroservices.repository.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

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
                                                                    //YYYY-mm-dd 2025-09-30
        paymentRepository.save(new Payment("cn4563df3", LocalDate.parse("2024-09-22"),"gulp",    "RGNRDV87H13D761R", new BigDecimal("3000.01"), "EUR"));
        paymentRepository.save(new Payment("cn7256su9", LocalDate.parse("2024-09-22"),"pluto",   "RGNLSN87H13D761R", new BigDecimal("3000.02"), "JPY"));
        paymentRepository.save(new Payment("cn6396dr7", LocalDate.parse("2024-09-22"),"paperino","FRNFBA85M08D761M", new BigDecimal("3000.03"), "USD"));
        paymentRepository.save(new Payment("cn2759ds4", LocalDate.parse("2024-09-22"),"topolino","DSTLCU89R52D761R", new BigDecimal("3000.04"), "GBP"));
        paymentRepository.save(new Payment("cn2874da2", LocalDate.parse("2024-09-22"),"sora",    "DSTLCU89R52D761R", new BigDecimal("3000.05"), "RUB"));
    }
}
