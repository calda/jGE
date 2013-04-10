package com.cal;

import java.util.Arrays;
import jge.world.Coordinates;

public enum ID{
	
	H11(0, 31, 33), 
	H21(1, 41, 43), 
	H22(2, 42, 44), 
	H31(3, 11, 51, 53, 33), 
	H32(4, 52, 54), 
	H33(5, 11, 53, 55, 31), 
	H41(6, 21, 43), 
	H42(7, 22, 44), 
	H43(8, 21, 41),
	H44(9, 22, 42), 
	H51(10, 31, 53), 
	H52(11, 32, 54), 
	H53(12, 51, 55, 31, 33), 
	H54(13, 52, 32),
	H55(14, 53, 33);

	public static ID getBetween(ID i1, ID i2){
		if(!Arrays.asList(i1.getPossibleJumps()).contains(i2)){
			System.out.println("Jump impossible.");
			return null;
		}
		char[] chars1 = i1.toString().substring(1).toCharArray();
		char[] chars2 = i2.toString().substring(1).toCharArray();
		int[] ints1 = new int[2];
		ints1[0] = Integer.parseInt("" + chars1[0]);
		ints1[1] = Integer.parseInt("" + chars1[1]);
		int[] ints2 = new int[2];
		ints2[0] = Integer.parseInt("" + chars2[0]);
		ints2[1] = Integer.parseInt("" + chars2[1]);
		int difference1 = Math.abs(ints1[0] - ints2[0]);
		int difference2 = Math.abs(ints1[1] - ints2[1]);
		int two = 0;
		if(difference1 == 2) two++;
		if(difference2 == 2) two++;
		if(two == 2){
			int larger1 = (ints1[0] > ints2[0]) ? ints1[0] : ints2[0];
			int larger2 = (ints1[1] > ints2[1]) ? ints1[1] : ints2[1];
			return ID.valueOf("H" + (larger1 - 1) + (larger2 - 1));
		}else if(two == 1){
			int larger1 = (ints1[0] > ints2[0]) ? ints1[0] : ints2[0];
			int larger2 = (ints1[1] > ints2[1]) ? ints1[1] : ints2[1];
			boolean xStaysSame = ints1[0] == ints2[0];
			if(xStaysSame) return ID.valueOf("H" + larger1 + (larger2 - 1));
			else return ID.valueOf("H" + (larger1 - 1) + larger2);
		}else{
			return null;
		}
	}
	
	public final Coordinates pos;
	private final String[] possible;

	private ID(int positionInArray, int...possible){
		Coordinates[] holes = {
				Coordinates.make(234, 103),
				Coordinates.make(199, 160), Coordinates.make(266, 160),
				Coordinates.make(164, 210), Coordinates.make(234, 210), Coordinates.make(302, 210),
				Coordinates.make(125, 275), Coordinates.make(199, 275), Coordinates.make(266, 275), Coordinates.make(325, 275),
				Coordinates.make(93, 337), Coordinates.make(164, 337), Coordinates.make(234, 337), Coordinates.make(302, 337), Coordinates.make(365, 337)
		}; pos = holes[positionInArray];
		this.possible = new String[possible.length];
		for(int i = 0; i < possible.length; i+=1){
			this.possible[i] = "H" + possible[i];
		}
	}

	public static ID getIDFromPos(Coordinates pos){
		for(ID id : ID.values()){
			if(id.pos.getX() == pos.getX() && id.pos.getY() == pos.getY()) return id;
		}return null;
	}
	
	public static ID getFromInt(int i){
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
		return ID.valueOf("H" + cs[0] + cs[1]);
	}

	public ID[] getPossibleJumps(){
		ID[] ids = new ID[possible.length];
		for(int i = 0; i < possible.length; i+=1){
			ids[i] = ID.valueOf(possible[i]);
		}
		return ids;
	}



}
