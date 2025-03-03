package e1;

public class SilverBankAccount implements BankAccount{

    /**
     * SILVER ACCOUNT -> fee = 1, overdraft = 0
     */

    private CoreBankAccount coreBankAccount;

    public SilverBankAccount(CoreBankAccount coreBankAccount){
        this.coreBankAccount = coreBankAccount;
    }

    @Override
    public int getFee(int amount){
        return 1;
    }

    @Override
    public int getOverdraft(){
        return 0;
    }
}
