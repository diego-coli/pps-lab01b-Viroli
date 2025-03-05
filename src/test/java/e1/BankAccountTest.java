package e1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest {

    public static final int DEPOSIT = 150;
    public static final int WITHDRAW = 100;
    private BankAccount bankAccount;
    private CoreBankAccount core;
    private CoreBankAccount gold;
    private CoreBankAccount silver;
    private CoreBankAccount bronze;

    @BeforeEach
    void init(){
        this.core = new CoreBankAccount(bankAccount);
        this.gold = new CoreBankAccount(new GoldBankAccount(core));
        this.silver = new CoreBankAccount(new SilverBankAccount(core));
        this.bronze = new CoreBankAccount(new BronzeBankAccount(core));
    }

    @Test
    void isInitiallyEmpty(){
        int expectedBalance = 0;
        assertAll(
                () -> assertEquals(expectedBalance, gold.getBalance()),
                () -> assertEquals(expectedBalance, silver.getBalance()),
                () -> assertEquals(expectedBalance, bronze.getBalance())
                );
    }

    @Test
    void tryToDeposit(){
        int expectedBalance = 150;
        gold.deposit(DEPOSIT);
        silver.deposit(DEPOSIT);
        bronze.deposit(DEPOSIT);
        assertAll(
                () -> assertEquals(expectedBalance, gold.getBalance()),
                () -> assertEquals(expectedBalance, silver.getBalance()),
                () -> assertEquals(expectedBalance, bronze.getBalance())
        );
    }

    @Test
    void getGoldFee(){
        int expectedFee = 0;
        assertEquals(expectedFee, gold.getFee(WITHDRAW));
    }

    @Test
    void getSilverFee(){
        int expectedFee = 1;
        assertEquals(expectedFee, silver.getFee(WITHDRAW));
    }

    @Test
    void getBronzeFeeLowWithdraw(){
        int expectedFee = 0;
        int lowWithdraw = 99;
        assertEquals(expectedFee, bronze.getFee(lowWithdraw));
    }

    @Test
    void getBronzeFeeHighWithdraw(){
        int expectedFee = 1;
        int highWithdrow = 100;
        assertEquals(expectedFee, bronze.getFee(highWithdrow));
    }

    @Test
    void goldWithdraw(){
        int expectedBalance = 50;
        gold.deposit(DEPOSIT);
        gold.withdraw(WITHDRAW);
        assertEquals(expectedBalance, gold.getBalance());
    }

    @Test
    void silverWithdraw(){
        int expectedBalance = 49;
        silver.deposit(DEPOSIT);
        silver.withdraw(WITHDRAW);
        assertEquals(expectedBalance, silver.getBalance());
    }

    @Test
    void bronzeLowWithdraw(){
        int amount = 90;
        int expectedBalance = 60;
        bronze.deposit(DEPOSIT);
        bronze.withdraw(amount);
        assertEquals(expectedBalance, bronze.getBalance());
    }

    @Test
    void bronzeHighWithdraw(){
        int amount = 140;
        int expectedBalance = 9;
        bronze.deposit(DEPOSIT);
        bronze.withdraw(amount);
        assertEquals(expectedBalance, bronze.getBalance());
    }

    @Test
    void checkGoldOverdraft(){
        int expectedBalance = -350;
        int amount = 500;
        tryToDeposit();             //deposit 150
        gold.withdraw(amount);
        assertEquals(expectedBalance, gold.getBalance());
    }

    @Test
    void notEnoughMoney(){
        int expectedBalance = 150;
        int amount = 1000;
        tryToDeposit();             //deposit 150
        assertAll(
                () -> assertThrows(IllegalStateException.class, () -> gold.withdraw(amount)),
                () -> assertThrows(IllegalStateException.class, () -> silver.withdraw(amount)),
                () -> assertThrows(IllegalStateException.class, () -> bronze.withdraw(amount)),
                () -> assertEquals(expectedBalance, gold.getBalance()),
                () -> assertEquals(expectedBalance, silver.getBalance()),
                () -> assertEquals(expectedBalance, bronze.getBalance())
                );
    }
}
