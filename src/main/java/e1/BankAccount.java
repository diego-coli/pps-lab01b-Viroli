package e1;

public interface BankAccount {

    /**
     * Gold =   fee 0,              overdraft -500
     * Silver = fee 1,              overdraft 0
     * Bronze = conditional fee,    overdraft 0
     *
     * @return
     */

    int getFee(int amount);

    int getOverdraft();

}