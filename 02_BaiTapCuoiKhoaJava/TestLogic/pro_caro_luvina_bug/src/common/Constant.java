/**
 * Copyright(C) 2018 Luvina Software Company

 * Constant.java, 20/11/2018, Luvina
 */
package common;

import java.util.ArrayList;

import model.CaroButton;
import model.PositionModel;

/**
 * Xây dựng hàm chứa các hằng số trong PJ
 * 
 * @author Luvina
 */
public class Constant {
	// số hàng của bàn cờ
	public static final int ROW_BOARD = 20; 
	// số cột của bàn cờ
	public static final int COL_BOARD = 20; 
	// số hàng của matrix thế cờ
	public static final int ROW_MATR = 5; 
	// số cột của matrix thế cờ
	public static final int COL_MATR = 5; 
	// độ lớn của 1 ô
	public static final int SIZE_BUTT = 40; 
	// file thế cờ
	public static final String POSITION = "TheCo.txt"; 
	// tạo mảng chứa danh sách các thế cờ
	public static final ArrayList<PositionModel> LIST_POSITION = new ArrayList<PositionModel>();
	// tạo mảng ma trận 20x20
	public static final CaroButton[][] BUTT_MATR = new CaroButton[ROW_BOARD][COL_BOARD];
//đóng class
} 
