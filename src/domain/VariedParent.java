package domain;

import java.io.Serializable;
import java.util.Arrays;

public class VariedParent implements Serializable{
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
		
		public int getVariedParents(String route, String variedParent)
		{
			int output = 0;
			switch(route.toLowerCase())
			{
				case "conquest":
				{
					output = Arrays.asList(conquest).indexOf(variedParent);
					break;
				}
				case "birthright":
				{
					output = Arrays.asList(birthright).indexOf(variedParent);
					break;
				}
				case "revelations":
				{
					output = Arrays.asList(revelations).indexOf(variedParent);
					break;
				}
			}
			return output;
		}
		
		public String toString() {
			return "\n   Conquest variedParents: " + Arrays.toString(conquest) + ",\n   Birthright variedParents: " + Arrays.toString(birthright) + ",\n   Revelations variedParents: " + Arrays.toString(revelations);
		}
}
