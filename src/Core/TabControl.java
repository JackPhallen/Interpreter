package Core;

public class TabControl {
	/* TabControl-
	 * 
	 * Purpose: Static class to keep track of the amount of tabs that should be printed
	 * in order to make prettyprint possible */
	private static int tabCount = 0;

	public static int getTabCount() {
		return tabCount;
	}

	public static void increaseTabCount() {
		TabControl.tabCount += 1;
	}
	
	public static void decreaseTabCount() {
		TabControl.tabCount -= 1;
	}
	 
	public static void printTabs() {
		int i = tabCount;
		while(i>0)
		{
			System.out.print("\t");
			i--;
		}
	}
	
	
	

}
