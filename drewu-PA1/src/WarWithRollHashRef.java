import java.util.ArrayList;
import java.util.HashSet;

public class WarWithRollHashRef extends WarRef {
    HashSet<HashedString> set;
    int ae;

    public WarWithRollHashRef(String[] s_, int k_) {
        super(s_, k_);

        ae = 1;
        for (int i = 1; i <= k_ - 1; i++)
            ae *= 31;

        set = new HashSet<>();
        for (String input : s) {
            HashedString hs = new HashedString(input, 0, k_, ae);
            set.add(hs);
        }
    }

    @Override
    public ArrayList<String> compute2k() {
        ArrayList<String> output = new ArrayList<String>();

        for (String si : s) {
            for (String sj : s) {
                HashedString candidate = new HashedString(si + sj, 1, k, ae);

                boolean acceptCandidate = true;
                for (int i = 0; i < k - 1; i++) {
                    if (!set.contains(candidate)) {
                        acceptCandidate = false;
                        break;
                    }
                    candidate.slideWindow();
                }

                if (acceptCandidate) {
                    output.add(candidate.getString());
                }
            }
        }

        return output;
    }

    public boolean inputContains(String candidate, int i) {
        return false;
    }

    public class HashedString {
        String string;
        int index;
        int hash;
        int k;
        int alphaexp;

        public HashedString(String string_, int index_, int k_, int alphaexp_) {
            string = string_;
            index = index_;
            k = k_;
            alphaexp = alphaexp_;

            hash = 0;
        }

        @Override
        public int hashCode() {

            int h = hash;

            if (h == 0 && k > 0 && string.length() > 0) {
                for (int i = 0; i < k; i++) {
                    h = 31 * h + string.charAt(i + index);
                }
                hash = h;
            }

            return hash;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof HashedString) {
                String comp = ((HashedString) o).getString();
                int ii = ((HashedString) o).getIndex();
                if (!(comp.length() - ii >= k))
                    return false;
                for (int i = 0; i < k; i++) {
                    if (comp.charAt(i + ii) != string.charAt(i + index))
                        return false;
                }
                return true;
            } else {
                return false;
            }
        }

        public void slideWindow() {
            hash = 31 * (hash - alphaexp * string.charAt(index)) + string.charAt(index + k);
            index++;
        }

        public String getString() {
            return string;
        }

        public int getIndex() {
            return index;
        }

        public int getK() {
            return k;
        }
    }
}