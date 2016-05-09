package common.helpers;

import java.util.Comparator;

public class StringHelper implements Comparator<String> {

	@Override
	public int compare(String s1, String s2) {
		final Integer size1 = s1.length();
		final Integer size2 = s2.length();
		return size1.compareTo(size2);
	}
}
