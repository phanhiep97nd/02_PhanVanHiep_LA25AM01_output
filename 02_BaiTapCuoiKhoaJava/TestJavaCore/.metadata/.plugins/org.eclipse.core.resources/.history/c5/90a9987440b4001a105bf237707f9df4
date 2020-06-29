/**
 * Copyright(C) 2018 Luvina Software Company
 * CaroActionListener.java,21/11/2018, Luvina
 */
package main;

import common.Constant;
import logic.CaroLogic;
import view.CaroView;

/**
 * Class để chạy chương trình
 * @author Luvina
 */
public class CaroMain {
	/**
		 * Hàm main chạy chương trình
		 * @param args
		 * 
	 */
	public static void main(String[] args) {
		//bắt lỗi
		try {								
			//đọc tất cả các thế cờ lưu vào list
			CaroLogic.listPosition = Constant.LIST_POSITION; 
			//tạo màn hình giao diện chơi cờ
			new CaroView().createGUI(); 	
			//xử lý lỗi
		} catch (Exception e) {				
			//In thông báo
			System.out.println("Chương trình bị lỗi"); 
		// kết thúc catch	
		}										
	//kết thúc hàm main
	} 										
//kết thúc chương trình 
}											
