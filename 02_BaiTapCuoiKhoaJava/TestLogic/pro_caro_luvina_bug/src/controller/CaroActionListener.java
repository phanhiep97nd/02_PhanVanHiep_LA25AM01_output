/**
 * Copyright(C) 2018 Luvina Software Company
 * CaroActionListener.java,21/11/2018, Luvina
 */
package controller;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import common.Constant;
import logic.CaroLogic;
import model.CaroButton;
import view.CaroView;

/**
 * Class CaroActionListener implements ActionListener để bắt các sự kiện click vào các button
 * @author Luvina
 */
public class CaroActionListener implements ActionListener {
	// arrayButton lưu ma trận bàn cờ
	private CaroButton[][] arrayButton = Constant.BUTT_MATR;
	// lưu giá trị số nước cờ đã di chuyển
	private static int countMove = 0; 						
	// khởi tạo đối tượng caroView
	private CaroView caroView = new CaroView();	
	// khai báo đối tượng caroLogic
	private CaroLogic caroLogic;
	
	/**
	 * Phương thức khởi tạo CaroActionListener
	 */
	public CaroActionListener() {
		//khởi tạo caroLogic
		caroLogic = new CaroLogic();		
	}
	/**
	 * Ghi đè phương thức actionPerformed của ActionListener
	 * @param: act: hành động
	 */
	@Override
	public void actionPerformed(ActionEvent act) {
		// trả về nguồn của sự kiện và ép kiểu thành CaroButton
		CaroButton buttClick = (CaroButton) act.getSource(); 
		// kiểm tra vị trí click là 1 ô trống
		if ("".equals(buttClick.getText())) { 				
			// set giá trị cho ô vừa chọn là "O"
			caroView.setFlagButt(buttClick, "O"); 			
			// Lấy tọa độ điểm máy cần đánh
			Point point = caroLogic.getXPointComputer(); 	
			// Máy đánh X
			caroView.setFlagButt(arrayButton[(int) point.getX()][(int) point.getY()], "X"); 
			// tăng biến đếm số nước đã đi thêm 1
			countMove++; 									
		// Kết thúc if kiểm tra button 
		}
	// kết thúc phương thức
	}
// kết thúc class
}	
