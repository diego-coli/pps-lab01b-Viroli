package e1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest {

    public static final int INITIAL_BALANCE = 0;
    public static final int DEPOSIT = 150;
    public static final int EXPECTED_BALANCE = 150;
    public static final int AMOUNT = 100;
    public static final int BRONZE_HIGH_WITHDRAW = 140;
    public static final int BRONZE_LOW_WITHDRAW = 90;
    public static final int GOLD_FEE = 0;
    public static final int SILVER_FEE = 1;
    public static final int BRONZE_FEE_LOW_WITHDRAW = 0;
    public static final int BRONZE_FEE_HIGH_WITHDRAW = 1;
    public static final int EXPECTED_BRONZE_BALANCE_HIGH_WITHDRAW = 9;
    public static final int EXPECTED_BRONZE_BALANCE_LOW_WITHDRAW = 60;
    public static final int EXPECTED_SILVER_BALANCE = 49;
    public static final int EXPECTED_GOLD_BALANCE = 50;
    public static final int TOO_HIGH_WITHDRAW = 500;
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
        assertAll(
                () -> assertEquals(INITIAL_BALANCE, gold.getBalance()),
                () -> assertEquals(INITIAL_BALANCE, silver.getBalance()),
                () -> assertEquals(INITIAL_BALANCE, bronze.getBalance())
                );
    }

    @Test
    void tryToDeposit(){
        gold.deposit(DEPOSIT);
        silver.deposit(DEPOSIT);
        bronze.deposit(DEPOSIT);
        assertAll(
                () -> assertEquals(EXPECTED_BALANCE, gold.getBalance()),
                () -> assertEquals(EXPECTED_BALANCE, silver.getBalance()),
                () -> assertEquals(EXPECTED_BALANCE, bronze.getBalance())
        );
    }

    @Test
    void getGoldFee(){
        assertEquals(GOLD_FEE, gold.getFee(AMOUNT));
    }

    @Test
    void getSilverFee(){
        assertEquals(SILVER_FEE, silver.getFee(AMOUNT));
    }

    @Test
    void getBronzeFeeLowWithdraw(){
        assertEquals(BRONZE_FEE_LOW_WITHDRAW, bronze.getFee(AMOUNT - 1));
    }

    @Test
    void getBronzeFeeHighWithdraw(){
        assertEquals(BRONZE_FEE_HIGH_WITHDRAW, bronze.getFee(AMOUNT));
    }

    @Test
    void goldWithdraw(){
        gold.deposit(DEPOSIT);
        gold.withdraw(AMOUNT);
        assertEquals(EXPECTED_GOLD_BALANCE, gold.getBalance());
    }

    @Test
    void silverWithdraw(){
        silver.deposit(DEPOSIT);
        silver.withdraw(AMOUNT);
        assertEquals(EXPECTED_SILVER_BALANCE, silver.getBalance());
    }

    @Test
    void bronzeLowWithdraw(){
        bronze.deposit(DEPOSIT);
        bronze.withdraw(BRONZE_LOW_WITHDRAW);
        assertEquals(EXPECTED_BRONZE_BALANCE_LOW_WITHDRAW, bronze.getBalance());
    }

    @Test
    void bronzeHighWithdraw(){
        bronze.deposit(DEPOSIT);
        bronze.withdraw(BRONZE_HIGH_WITHDRAW);
        assertEquals(EXPECTED_BRONZE_BALANCE_HIGH_WITHDRAW, bronze.getBalance());
    }

    @Test
    void notEnoughMoney(){
        tryToDeposit();
        assertAll(
                () -> assertThrows(IllegalStateException.class, () -> gold.withdraw(TOO_HIGH_WITHDRAW)),
                () -> assertThrows(IllegalStateException.class, () -> silver.withdraw(TOO_HIGH_WITHDRAW)),
                () -> assertThrows(IllegalStateException.class, () -> bronze.withdraw(TOO_HIGH_WITHDRAW)),
                () -> assertEquals(EXPECTED_BALANCE, gold.getBalance()),
                () -> assertEquals(EXPECTED_BALANCE, silver.getBalance()),
                () -> assertEquals(EXPECTED_BALANCE, bronze.getBalance())
                );
    }
}
