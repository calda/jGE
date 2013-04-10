package com.cal;

import jge.world.Coordinates;

public enum HoleID{
	
	HAA(0, 31, 33), 
	HBA(1, 41, 43), 
	HBB(2, 42, 44), 
	HCA(3, 11, 51, 53, 33), 
	HCB(4, 52, 54), 
	HCC(5, 11, 53, 55, 31), 
	HDA(6, 21, 43), 
	HDB(7, 22, 44), 
	HDC(8, 21, 41),
	HDD(9, 22, 42), 
	HEA(10, 31, 53), 
	HEB(11, 32, 54), 
	HEC(12, 51, 55, 31, 33), 
	HED(13, 52, 32),
	HEE(14, 53, 33);

	final Coordinates pos;
	final HoleID[] possible;

	private HoleID(int positionInArray, int...possible){
		Coordinates[] holes = {
				Coordinates.make(234, 103),
				Coordinates.make(199, 160), Coordinates.make(266, 160),
				Coordinates.make(164, 210), Coordinates.make(234, 210), Coordinates.make(302, 210),
				Coordinates.make(125, 275), Coordinates.make(199, 275), Coordinates.make(266, 275), Coordinates.make(325, 275),
				Coordinates.make(93, 337), Coordinates.make(164, 337), Coordinates.make(234, 337), Coordinates.make(302, 337), Coordinates.make(365, 337)
		}; pos = holes[positionInArray];
		this.possible = new HoleID[possible.length];
		for(int i = 0; i < possible.length; i+=1){
			this.possible[i] = getFromInt(possible[i]);
		}
	}

	public static HoleID getIDFromPos(Coordinates pos){
		for(HoleID id : HoleID.values()){
			if(id.pos.equals(pos)) return id;
		}return null;
	}
	
	public static HoleID getFromInt(int i){
		char[] cs = (i + "").toCharArray();
		for(int j = 0; j < cs.length; j++){
			switch(cs[j]){
				case '1': cs[j] = 'A'; break;
				case '2': cs[j] = 'B'; break;
				case '3': cs[j] = 'C'; break;
				case '4': cs[j] = 'D'; break;
				case '5': cs[j] = 'E'; break;
			}
		}
		return HoleID.valueOf("H" + cs[0] + cs[1]);
	}

	public HoleID[] getPossibleJumps(){
		return possible;
	}



}
