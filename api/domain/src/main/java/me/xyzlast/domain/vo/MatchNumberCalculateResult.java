package me.xyzlast.domain.vo;

/**
 * Created by ykyoon on 14. 11. 7.
 */
public class MatchNumberCalculateResult {
    private final String number;
    private int count;

    public MatchNumberCalculateResult(String number) {
        this.number = number;
    }

    public void increase() {
        this.count++;
    }

    public String getNumber() {
        return number;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
