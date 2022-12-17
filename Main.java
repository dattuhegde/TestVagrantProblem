import java.util.ArrayList;
import java.util.HashSet;

class Main{
	//Hardcoded data for prices(which are sorted)
	//If not sorted we can sort this data in O(nlogn) and then store it for future use case
	private static String[] names = {"BM","HT","Hindu","TOI","ET"};
	private static float[] prices = {10.5f,18f,20.5f,26f,34f};
	//Static array and hashset declarations in case solve is called multiple times
	private static ArrayList<Integer> res=new ArrayList<Integer>();
	private static HashSet<Integer> visited=new HashSet<Integer>();
	private static int len;
	private static void solve(float budget){
		visited.clear();
		res.clear();
		for(int i=0;i<prices.length;i++){
			solveRec(i,budget);
		}
	}
	//Recursive function to check combinations
	//Optimized for the sorted order of prices and keeps track of visited indices
	//The values have been printed instead of returning a copy of arraylist each time
	private static boolean solveRec(int index,float budget){
		if(prices[index]<=budget){
			budget-=prices[index];
			visited.add(index);
			res.add(index);
			for(int i=index+1;i<prices.length;i++){
				if(!visited.contains(i)){
					if(!solveRec(i,budget)){
						break;
					}
				}
			}
			visited.remove(index);
			len=res.size();
			System.out.print("{");
			len--;
			for(int i=0;i<len;i++){
				System.out.print('"'+names[res.get(i)]+"\",");
			}
			System.out.println('"'+names[res.get(len)]+"\"}");
			len++;
			res.remove(len-1);
			return true;
		}else{
			return false;
		}
	}
	public static void main(String[] args){
		solve(400f);
	}
}
