package SimpleATM;

public class ATM {
    int [] denominations = {100, 200, 500, 2000};
    int [] currentNotes = {0, 0, 0, 0};

    private boolean dfs(int amount, int idx, int [] res) {
        if(amount == 0)
            return true;
        if(amount<0 || idx == res.length)
            return false;

        int currNoteValue = denominations[idx];
        int maxNoteCount = Math.min(currentNotes[idx], amount/currNoteValue);

        for(int count=maxNoteCount; count>=0; count--) {
            res[idx] = count;
            if(dfs(amount-currNoteValue*count, idx+1, res))
                return true;
        }

        res[idx]=0;
        return false;
    }

    public ATM(int[] seedVals) {
        for(int i=0;i< seedVals.length;i++)
            currentNotes[i] += seedVals[i];
    }

    public void depositMoney(int[] deposit) {
        for(int i=0;i< deposit.length;i++)
            currentNotes[i] += deposit[i];
    }

    //Return maximum amount of notes possible
    public int [] withdrawMoney(int amount) {
        int [] res = new int[denominations.length];
        if(!dfs(amount,0, res)) {
            res = new int[]{-1};
            return res;
        }
        for(int i=0;i< currentNotes.length;i++)
            currentNotes[i] -= res[i];
        return res;
    }


}
