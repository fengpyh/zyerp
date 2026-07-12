package com.cryptomind.wquote;


import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import javax.swing.tree.TreeNode;

@Slf4j
public class TrieTree extends TrieBase {
    private TrieTree() {
    }

    private static TrieTree instance = new TrieTree();

    public static TrieTree getInstance() {
        return instance;
    }

    //前缀树代码
    private TrieNode root = new TrieNode('#', null);

    private boolean validate(String str) {
        boolean v1 = str != null && !str.isEmpty();
        if(v1) {
        	return isValid(str);
        }else {
        	return v1;
        }
    }

    public boolean insertNode(String str, QuoteData quoteData) {
        if (!validate(str)) {
            return false;
        }

        char[] chs = str.toCharArray();
        TrieNode p = root;
        for (char c : chs) {
            TrieNode tmp = p.getChild(c);
            if (tmp == null) {
                tmp = new TrieNode(c, null, true);
                //tmp.setLeaf(false);
                //tmp.setTreeNodeData(treeNodeData);
                p.setChild(c, tmp);
            }
            p = tmp;
        }
        if (p != null && p != root) {
            p.setLeaf(true);
            if (p.getQuoteData() == null) {
                p.setQuoteData(quoteData);
            }else {
            	log.warn("insertNode.error.duplicateData: {}, {}", str, quoteData.getSymbol() );
            }
            return true;
        } else {
            return false;
        }
    }

    private TrieNode _dfs_find(String str) {
        if(str==null || str.isEmpty()) {
            TrieNode p = root;
            return p;
        }else if (!validate(str)) {
            return null;
        }

        char chs[] = str.toCharArray();
        TrieNode p = root;
        for (char c : chs) {
            TrieNode tmp = p.getChild(c);
            if (tmp != null) {
                p = tmp;
            } else {
                break;
            }
        }

        if (p != null && p != root) {
            return p;
        }
        return null;
    }

    public QuoteData dfs_find(String str) {
        if (!validate(str)) {
            return null;
        }

        char chs[] = str.toCharArray();
        TrieNode p = root;
        for (char c : chs) {
            TrieNode tmp = p.getChild(c);
            if (tmp != null) {
                p = tmp;
            } else {
                break;
            }
        }

        if (p != null && p != root && p.getQuoteData()!=null) {
            return p.getQuoteData();
        }
        return null;
    }

    public List<QuoteData> dfsListData(String type) {
        TrieNode rootNode = _dfs_find(type);
        List<QuoteData> retList = new ArrayList<>();
        dfs(rootNode, retList);
        return retList;
    }

    private void dfs(TrieNode p, List<QuoteData> retList) {
    	TrieNode[] children = p.getChildren();
        for (int i=0;i<children.length;i++) {
        	TrieNode child = children[i];
        	if(child!=null) {
        		if(child.getQuoteData() != null && child.getQuoteData().getSymbolData()!=null) {
    				retList.add(child.getQuoteData());
    			}
        		
        		if (!child.isLeaf()) {
        			dfs(child, retList);
        		}
        	}
        }
    }

}
