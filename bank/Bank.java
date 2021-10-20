package bank;

import java.util.ArrayList;

public interface Bank<T> {
    Account findAccount(int id);
    boolean addAccount(Account account);
    T getTotalBalancePerCity();
    T getTotalCountPerCity();
    ArrayList<Integer> getTotalCountPerRange(ArrayList<Integer> ranges);
    ArrayList<Integer> getTotalCountPerRangeUsingSort(ArrayList<Integer> ranges, Account[] sortedAccounts);
    Account[] sortAccounts();
    void printAccounts();
}
