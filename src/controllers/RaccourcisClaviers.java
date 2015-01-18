package controllers;

import java.awt.event.KeyEvent;

public enum RaccourcisClaviers {
	
	R_1(KeyEvent.VK_AMPERSAND),
	R_2(16777449),
	R_3(152),
	R_4(222),
	R_5(519),
	R_6(45),
	R_7(16777448),
	R_8(523),
	R_9(16777415),
	R_0(16777440);
	
	int keyCode;
	RaccourcisClaviers(int keyCode){
		this.keyCode= keyCode;
	}

}
