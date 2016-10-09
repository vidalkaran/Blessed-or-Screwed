package domain;

import java.util.Arrays;

public class VariedParent {
		private String[] conquest;
		private String[] birthright;
		private String[] revelations;
		
		public String[] getConquest() {
			return conquest;
		}
		
		public String[] getBirthright() {
			return birthright;
		}
		
		public String[] getRevelations() {
			return revelations;
		}
		
		public String[] getVariedParentsList(String route)
		{
			String[] output = new String[0];
			switch(route.toLowerCase())
			{
				case "conquest":
				{
					output = conquest;
					break;
				}
				case "birthright":
				{
					output = birthright;
					break;
				}
				case "revelations":
				{
					output = revelations;
					break;
				}
			}
			return output;
		}
		
		public String toString() {
			return "\n   Conquest variedParents: " + Arrays.toString(conquest) + ",\n   Birthright variedParents: " + Arrays.toString(birthright) + ",\n   Revelations variedParents: " + Arrays.toString(revelations);
		}
}
