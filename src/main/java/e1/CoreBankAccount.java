package e1;

class CoreBankAccount implements BankAccount{

    private int balance;
    private BankAccount bankAccount;

    public CoreBankAccount(BankAccount bankAccount){
        this.bankAccount = bankAccount;
        this.balance = 0;
    }

    public int getBalance() {
        return this.balance;
    }

    public int getFee(int amount){
        if(bankAccount != null){
            return bankAccount.getFee(amount);
        }
        else{
            throw new IllegalStateException("\nBank Account type not set");
        }
    }

    public int getOverdraft(){
        if(bankAccount != null){
            return bankAccount.getOverdraft();
        }
        else{
            throw new IllegalStateException("\nBank Account type not set");
        }
    }

    public void withdraw(int amount) {
        if(bankAccount != null){
            if(this.balance >= (amount + this.getFee(amount))){
                this.balance = this.balance - amount - this.getFee(amount);
            }
            else{
                throw new IllegalStateException("\nNot enough money");
            }
        }
        else{
            throw new IllegalStateException("\nBank Account type not set");
        }
    }

    public void deposit(int amount) {
        this.balance = this.balance + amount;
    }
}
