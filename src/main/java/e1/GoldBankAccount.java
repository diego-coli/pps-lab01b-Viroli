package e1;

public class GoldBankAccount implements BankAccount {

    /**
     * Gold =   fee 0,              overdraft -500
     * @return
     */

    private CoreBankAccount coreBankAccount;

    public GoldBankAccount(CoreBankAccount coreBankAccount){
        this.coreBankAccount = coreBankAccount;
    }

    @Override
    public int getFee(int amount){
        return 0;
    }

    @Override
    public int getOverdraft(){
        return -500;
    }
}

