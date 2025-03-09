package e1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GoldBankAccountTest{

    public static final int DEPOSIT = 150;
    public static final int WITHDRAW = 100;
    private BankAccount bankAccount;
    private CoreBankAccount core;
    private CoreBankAccount gold;

    @BeforeEach
    void init(){
        this.core = new CoreBankAccount(bankAccount);
        this.gold = new CoreBankAccount(new GoldBankAccount(core));
    }

    @Test
    void isInitiallyEmpty(){
        int expectedBalance = 0;
        assertEquals(expectedBalance, gold.getBalance());
    }

    @Test
    void tryToDeposit(){
        int expectedBalance = 150;
        gold.deposit(DEPOSIT);
        assertEquals(expectedBalance, gold.getBalance());
    }

    @Test
    void getFee(){
        int expectedFee = 0;
        assertEquals(expectedFee, gold.getFee(WITHDRAW));
    }

    @Test
    void getOverdraft(){
        int expectedOverdraft = -500;
        assertEquals(expectedOverdraft, gold.getOverdraft());
    }

    @Test
    void tryToWithdraw(){
        int expectedBalance = 50;
        tryToDeposit();
        gold.withdraw(WITHDRAW);
        assertEquals(expectedBalance, gold.getBalance());
    }

    @Test
    void notEnoughMoney(){
        int tooHighWithdraw = 1000;
        tryToDeposit();
        assertThrows(IllegalStateException.class, () -> gold.withdraw(tooHighWithdraw));
    }

    @Test
    void checkOverdraft(){
        int expectedBalance = -50;
        int withdraw = 200;
        tryToDeposit();                 //deposit 150
        gold.withdraw(withdraw);
        assertEquals(expectedBalance, gold.getBalance());
    }
}
