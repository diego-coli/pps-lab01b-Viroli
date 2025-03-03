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
        int initialBalance = 0;
        assertAll(
                () -> assertEquals(initialBalance, gold.getBalance()),
                () -> assertEquals(initialBalance, silver.getBalance()),
                () -> assertEquals(initialBalance, bronze.getBalance())
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
        assertEquals(expectedFee, bronze.getFee(WITHDRAW - 1));
    }

    @Test
    void getBronzeFeeHighWithdraw(){
        int expectedFee = 1;
        assertEquals(expectedFee, bronze.getFee(WITHDRAW));
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
    void notEnoughMoney(){
        int expectedBalanceGold = -350;
        int expectedBalanceSilver = 150;
        int expectedBalanceBronze = 150;
        int amount = 500;
        tryToDeposit();
        gold.withdraw(amount);
        assertAll(
                () -> assertThrows(IllegalStateException.class, () -> silver.withdraw(amount)),
                () -> assertThrows(IllegalStateException.class, () -> bronze.withdraw(amount)),
                () -> assertEquals(expectedBalanceGold, gold.getBalance()),
                () -> assertEquals(expectedBalanceSilver, silver.getBalance()),
                () -> assertEquals(expectedBalanceBronze, bronze.getBalance())
                );
    }
}
