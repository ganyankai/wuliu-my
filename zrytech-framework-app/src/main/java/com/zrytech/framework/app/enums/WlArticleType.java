package com.zrytech.framework.app.enums;

public enum WlArticleType {
	INFORMATION("information",1),
	NOTICE("notice",2),
	RECEPTIONROOM("receptionroom",3),
    CRUDE_OIL("crude_oil",4),
	FTPURL("http://47.94.173.245:8089",5);
		 private WlArticleType(String name, int index) {
	            this.name = name;
			    this.index = index;
	        }
	    private String name;
	    private int index;
	    public String getInfo() {  
	        return this.name; 
	    }
	    public int getIndex(){return  this.index;}
	}