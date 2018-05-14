import java.util.ArrayList;

public class TestStuff
{

	public static void main(String[] args) {
		
//		String[] arr = {"M","K","I","L","O","N","P"};
//		BinaryST bst = new BinaryST(arr);
//		System.out.println("(" + bst.rankOf("M") + ")");
//		System.out.println("-->(" + bst.rankOf("K") + ")");
//		System.out.println("-->-->(" + bst.rankOf("I") + ")");
//		System.out.println("-->(" + bst.rankOf("L") + ")");
//		System.out.println("-->(" + bst.rankOf("O") + ")");
//		System.out.println("-->-->(" + bst.rankOf("N") + ")");
//		System.out.println(bst.size());
//		System.out.println("-->-->(" + bst.rankOf("P") + ")");
//		System.out.println(bst.height());
		
//		String strand = "TACCATTACCAT";
//		System.out.println("BAC,CBA,BCB,ABC,AAB,AAA,BAA");
//		WarWithArray w = new WarWithArray(generateAllPossibleSubstrings(strand, 3), 3);
		String[] s2 = {"BAC","CBA","BCB","ABC","AAB","AAA","BAA"};
		WarWithArray w = new WarWithArray(s2, 3);
//		WarWithBST w = new WarWithBST(generateAllPossibleSubstrings(strand, 30), 30);
//		WarWithHash w = new WarWithHash(generateAllPossibleSubstrings(strand, 30), 30);
//		WarWithRollHash w = new WarWithRollHash(generateAllPossibleSubstrings(strand, 30), 30);
		ArrayList<String> s = w.compute2k();
		System.out.println(s);
//		System.out.print("{ ");
//		for(int i = 0; i < s.size(); i++)
//		{
//			System.out.print(s.get(i));	
//			if(i + 1 != s.size())
//			{
//				System.out.print(", ");
//			}
//		}
//		System.out.println(" }");
		
//		Hashtable<Integer, String> t = new Hashtable<Integer, String>();
//		t.put("HELLO".hashCode(), "HELLO");
//		t.put("HELLP".hashCode(), "HELLP");
//		System.out.println("HELLO".hashCode());
//		System.out.println("HELLP".hashCode());
//		System.out.println("O".hashCode());
//		System.out.println("P".hashCode());
//		System.out.println(t.get("HELLO".hashCode()));
//		System.out.println(t.get(("HELLO".hashCode() - 'O') + 'P'));
 		
	}

	@SuppressWarnings("unused")
	private static String[] generateAllPossibleSubstrings(String U, int k)
	{
		ArrayList<String> s = new ArrayList<String>();
		for(int i = 0; i < U.length() - k + 1; i++)
		{
			String temp = U.substring(i, i + k);
			if(!s.contains(temp))
			{
				s.add(temp);
			}
		}
		String[] S = new String[s.size()];
		for(int i = 0; i < S.length; i++)
		{
			S[i] = s.get(i);
		}
		return S;
	}
}
