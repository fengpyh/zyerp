package com.cryptomind.wquote;

public class TrieBase {

	protected final static int LEN = 46;
	protected final static String CHAR_STR="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ/-########";
    /*
     * 0-9 10
     * a-z 26 - case insensitive
     * A-Z 26
     * special char:/ -  10
     * 
     */
	protected int index(char c) {
		if(c>='0' && c<='9') {
			return c-'0';
		}else if(c>='A' && c<='Z') {
			return c-'A'+10;
		}else {
			int i = LEN;
			switch(c) {
			case '/': i= 36; break;
			case '-': i= 37; break;
			case '_': i= 38; break;
			default: break;
			}
			return i;	
		}
	}
	
	protected boolean isValid(char c) {
		int i = index(c);
		return i<LEN;
	}
	
	protected boolean isValid(String str) {
		for(int i=0;i<str.length();i++) {
			char c = str.charAt(i);
			if(!isValid(c)) {
				return false;
			}
		}
		return true;
	}
	
	
	protected char getChar(int i ) {
		if(i>=0 && i<CHAR_STR.length()) {
			return CHAR_STR.charAt(i);
		}else {
			return '#';
		}
	}
	
}
