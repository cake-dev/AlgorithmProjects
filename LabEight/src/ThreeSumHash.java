import java.util.*;

public class ThreeSumHash {
    public int[] findTriple_3(int[] A) {
        Map<Integer, int[]> map = new HashMap<Integer, int[]>();
        for (int i = 0, l = A.length; i < l; i++) {
            map.clear();
            for (int j = i + 1; j < l; j++) {
                if (map.containsKey(A[j])) {
                    int[] pair = map.get(A[j]);
                    return new int[] { pair[0], pair[1], A[j] };
                } else
                    map.put(-A[i] - A[j], new int[] { A[i], A[j] });
            }
        }
        return null;
    }

    public static void main(String[] args) {
        ThreeSumHash tsh = new ThreeSumHash();
        int[] A = { 1, -1, 0, 3, 2, 5, -5 };
        int[] B = tsh.findTriple_3(A);
        for (int i = 0; i < B.length; i++)
            System.out.print(B[i] + " ");
    }
}
