package e1;

public class BronzeBankAccount implements BankAccount{

    /**
     * BRONZE ACCOUNT -> fee = 0 if withdraw<100, otherwise fee = 1, overdraft = 0
     */

    private CoreBankAccount coreBankAccount;

    public BronzeBankAccount(CoreBankAccount coreBankAccount){
        this.coreBankAccount = coreBankAccount;
    }

    @Override
    public int getFee(int amount){
        if(amount < 100){
            return 0;
        }
        else{
            return 1;
        }
    }

    @Override
    public int getOverdraft(){
        return 0;
    }
}
