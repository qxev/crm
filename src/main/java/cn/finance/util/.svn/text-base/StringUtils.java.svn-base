package cn.finance.util;

import java.io.IOException;

public class StringUtils {

	public static short[] getShouts(String str) {
		short shorts[] = null;
		if (str != null) {
			String strs[] = org.apache.commons.lang.StringUtils.split(str, ",");
			if (strs.length > 0) {
				shorts = new short[strs.length];
				for (int i = 0; i < strs.length; i++) {
					shorts[i] = Short.valueOf(strs[i]);
				}
			}
		}
		return shorts;
	}

	public static void main(String[] args) throws IOException {
//		String text = "";
//
//		short line[] = getShouts(text);
//		for (short a : line) {
//			System.out.println(a);
//		}
//		Date d = new Date("3000/01/01");
//		System.out.println(d.compareTo(new Date("1000/01/01")));
	}
}
