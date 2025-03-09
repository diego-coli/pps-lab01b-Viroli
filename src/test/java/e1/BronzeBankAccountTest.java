package e1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BronzeBankAccountTest {

    public static final int DEPOSIT = 150;
    public static final int WITHDRAW = 100;
    private BankAccount bankAccount;
    private CoreBankAccount core;
    private CoreBankAccount bronze;

    @BeforeEach
    void init(){
        this.core = new CoreBankAccount(bankAccount);
        this.bronze = new CoreBankAccount(new BronzeBankAccount(core));
    }

    @Test
    void isInitiallyEmpty(){
        int expectedBalance = 0;
        assertEquals(expectedBalance, bronze.getBalance());
    }

    @Test
    void tryToDeposit(){
        int expectedBalance = 150;
        bronze.deposit(DEPOSIT);
        assertEquals(expectedBalance, bronze.getBalance());
    }

    @Test
    void getFeeLowWithdraw(){
        int expectedFee = 0;
        assertEquals(expectedFee, bronze.getFee(WITHDRAW - 1));
    }

    @Test
    void getFeeHighWithdraw(){
        int expectedFee = 1;
        assertEquals(expectedFee, bronze.getFee(WITHDRAW));
    }

    @Test
    void getOverdraft(){
        int expectedOverdraft = 0;
        assertEquals(expectedOverdraft, bronze.getOverdraft());
    }

    @Test
    void tryToWithdraw(){
        int expectedBalance = 49;
        tryToDeposit();
        bronze.withdraw(WITHDRAW);
        assertEquals(expectedBalance, bronze.getBalance());
    }

    @Test
    void notEnoughMoney(){
        int tooHighWithdraw = 1000;
        tryToDeposit();
        assertThrows(IllegalStateException.class, () -> bronze.withdraw(tooHighWithdraw));
    }
}
