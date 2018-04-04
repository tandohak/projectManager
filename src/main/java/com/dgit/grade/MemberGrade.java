package com.dgit.grade;

public enum MemberGrade {
	NORMAL(1),STANDBY(2),DELETE(3),ADMIN(99);
	
	private int value;
	
    private MemberGrade(int value){
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
}
