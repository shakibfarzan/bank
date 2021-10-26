package bank;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface Banks<T> {
    Account findAccount(int id);
    boolean addAccount(Account account);
    T getTotalBalancePerCity();
    T getTotalCountPerCity();
    ArrayList<Integer> getTotalCountPerRange(ArrayList<Integer> ranges);
    double calcTotalBalance();
    void printAccounts();
    static Account[] convertListToArrayAccount(Collection<Account> accounts){
        Account[] accountsArray = new Account[accounts.size()];
        // Copy accounts to array
        int i = 0;
        for(Account account: accounts){
            accountsArray[i] = account;
            i++;
        }
        return accountsArray;
    }

    static Account[] convertListOfItemToArrayAccount(List<Item<Integer, Account>> items){
        Account[] accountsArray = new Account[items.size()];
        int i = 0;
        for(Item<Integer,Account> account: items){
            accountsArray[i] = account.data;
            i++;
        }
        return accountsArray;
    }

}
