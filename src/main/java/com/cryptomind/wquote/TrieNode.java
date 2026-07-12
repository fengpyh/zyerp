package com.cryptomind.wquote;



public class TrieNode extends TrieBase{

    
    private char the_char;
    private QuoteData quoteData;
    private boolean is_leaf;


    public TrieNode(char c, QuoteData data) {
    	this.the_char = c;
    	this.quoteData = data;
    	this.is_leaf = true;
    	
    	 children = new TrieNode[LEN];
    }
    
    public TrieNode(char c, QuoteData data, boolean isLeaf) {
    	this.the_char = c;
    	this.quoteData = data;
    	this.is_leaf = isLeaf;
    	
    	 children = new TrieNode[LEN];
    }
    
	
    //private HashMap<Character, TrieNode> children=new HashMap<Character, TrieNode>();
    private final TrieNode[] children;

    public TrieNode[] getChildren() {
    	return children;
    }
    
    public TrieNode getChild(char c) {
    	int i = this.index(c);
    	if(i>LEN) {
    		return null;
    	}else {
    		return children[i];
    	}
    }
    
    public void setChild(char c, TrieNode node) {
    	int i = this.index(c);
    	children[i]=node;
    	if(node!=null) {
    		is_leaf=false;
    	}
    }
    
    public void setChild(int i, TrieNode node) {
    	children[i]=node;
    	if(node!=null) {
    		is_leaf=false;
    	}
    }

    public boolean isLeaf() {
    	return is_leaf;
    }
    
    public void setLeaf(boolean is_leaf) {
    	this.is_leaf = is_leaf;
    }
    
    public QuoteData getQuoteData() {
    	return quoteData;
    }
    
    public void setQuoteData(QuoteData data) {
    	this.quoteData = data;
    }
    
    public char getC() {
    	return the_char;
    }
}
