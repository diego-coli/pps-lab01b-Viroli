package e1;

public interface BankAccount {

    /**
     * GOLD ACCOUNT -> fee = 0, overdraft = -500
     * SILVER ACCOUNT -> fee = 1, overdraft = 0
     * BRONZE ACCOUNT -> fee = 0 if withdraw<100, otherwise fee = 1, overdraft = 0
     */

    int getFee(int amount);

    int getOverdraft();

}