package ma.emsi.digitalbankbackend.web;

import lombok.AllArgsConstructor;
import ma.emsi.digitalbankbackend.dtos.AccountOperationDTO;
import ma.emsi.digitalbankbackend.dtos.BankAccountDTO;
import ma.emsi.digitalbankbackend.exceptions.BankAccountNotFoundException;
import ma.emsi.digitalbankbackend.services.BankAccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class BankAccountRestController {
    private BankAccountService bankAccountService;

    @GetMapping("/accounts/{accountId}")
    public BankAccountDTO getBankAccount(@PathVariable String accountId) throws BankAccountNotFoundException {
        return bankAccountService.getBankAccount(accountId);
    }

    @GetMapping("/accounts")
    public List<BankAccountDTO> listAccounts(){
        return bankAccountService.bankAccountList();
    }

    @GetMapping("/accounts/{accountId}/operation")
    public List<AccountOperationDTO> getHistory(@PathVariable String accountId){
        return bankAccountService.accountHistory(accountId);
    }
}
