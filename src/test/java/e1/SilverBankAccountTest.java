package e1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SilverBankAccountTest {

    public static final int DEPOSIT = 150;
    public static final int WITHDRAW = 100;
    private BankAccount bankAccount;
    private CoreBankAccount core;
    private CoreBankAccount silver;

    @BeforeEach
    void init(){
        this.core = new CoreBankAccount(bankAccount);
        this.silver = new CoreBankAccount(new SilverBankAccount(core));
    }

    @Test
    void isInitiallyEmpty(){
        int expectedBalance = 0;
        assertEquals(expectedBalance, silver.getBalance());
    }

    @Test
    void tryToDeposit(){
        int expectedBalance = 150;
        silver.deposit(DEPOSIT);
        assertEquals(expectedBalance, silver.getBalance());
    }

    @Test
    void getFee(){
        int expectedFee = 1;
        assertEquals(expectedFee, silver.getFee(WITHDRAW));
    }

    @Test
    void getOverdraft(){
        int expectedOverdraft = 0;
        assertEquals(expectedOverdraft, silver.getOverdraft());
    }

    @Test
    void tryToWithdraw(){
        int expectedBalance = 49;
        tryToDeposit();
        silver.withdraw(WITHDRAW);
        assertEquals(expectedBalance, silver.getBalance());
    }

    @Test
    void notEnoughMoney(){
        int tooHighWithdraw = 1000;
        tryToDeposit();
        assertThrows(IllegalStateException.class, () -> silver.withdraw(tooHighWithdraw));
    }
}
