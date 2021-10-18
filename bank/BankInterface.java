package bank;

import java.util.ArrayList;

public interface BankInterface<T> {
    Account findAccount(int id);
    boolean addAccount(Account account);
    T getTotalBalancePerCity();
    T getTotalCountPerCity();
    T getTotalCountPerRange(ArrayList<Integer> ranges);
    void printAccounts();
}
