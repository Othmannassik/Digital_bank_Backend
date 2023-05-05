package ma.emsi.digitalbankbackend;

import ma.emsi.digitalbankbackend.entities.AccountOperation;
import ma.emsi.digitalbankbackend.entities.CurrentAccount;
import ma.emsi.digitalbankbackend.entities.Customer;
import ma.emsi.digitalbankbackend.entities.SavingAccount;
import ma.emsi.digitalbankbackend.enums.AccountStatus;
import ma.emsi.digitalbankbackend.enums.OperationType;
import ma.emsi.digitalbankbackend.repositories.AccountOperationRepository;
import ma.emsi.digitalbankbackend.repositories.BankAccountRepository;
import ma.emsi.digitalbankbackend.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class DigitalBankBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigitalBankBackendApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CustomerRepository customerRepository,
                            BankAccountRepository bankAccountRepository,
                            AccountOperationRepository accountOperationRepository){
        return args -> {
            Stream.of("Othman","Hiba","Mouad")
                    .forEach(name->{
                        Customer customer = new Customer();
                        customer.setName(name);
                        customer.setEmail(name+"@gmail.com");
                        customerRepository.save(customer);
                    });

            customerRepository.findAll()
                    .forEach(customer -> {
                        CurrentAccount currentAccount = new CurrentAccount();
                        currentAccount.setId(UUID.randomUUID().toString());
                        currentAccount.setCreatedAt(new Date());
                        currentAccount.setBalance(Math.random()*85000);
                        currentAccount.setStatus(Math.random()>0.5 ? AccountStatus.CREATED : AccountStatus.ACTIVATED);
                        currentAccount.setCustomer(customer);
                        currentAccount.setOverDraft(7000);
                        bankAccountRepository.save(currentAccount);

                        SavingAccount savingAccount = new SavingAccount();
                        savingAccount.setId(UUID.randomUUID().toString());
                        savingAccount.setCreatedAt(new Date());
                        savingAccount.setBalance(Math.random()*85000);
                        savingAccount.setStatus(Math.random()>0.5 ? AccountStatus.CREATED : AccountStatus.ACTIVATED);
                        savingAccount.setCustomer(customer);
                        savingAccount.setInterestRate(3.2);
                        bankAccountRepository.save(savingAccount);
                    });

            bankAccountRepository.findAll()
                    .forEach(bankAccount -> {
                        for (int i=0; i<3; i++){
                            AccountOperation accountOperation = new AccountOperation();
                            accountOperation.setOperationDate(new Date());
                            accountOperation.setBankAccount(bankAccount);
                            accountOperation.setAmount(Math.random()*40000);
                            accountOperation.setType(Math.random()>0.5 ? OperationType.DEBIT : OperationType.CREDIT);
                            accountOperationRepository.save(accountOperation);
                        }
                    });
        };
    }
}
