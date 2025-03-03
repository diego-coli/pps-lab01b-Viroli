package e1;

public class BronzeBankAccount implements BankAccount{

    /**
     * Bronze = conditional fee,    overdraft 0
     * @return
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
