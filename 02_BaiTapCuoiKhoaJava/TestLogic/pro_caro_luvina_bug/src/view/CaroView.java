/**
 * Copyright(C) 2018 Luvina Software Company
 * CaroView.java, 20/11/2018 , Luvina
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;

import javax.swing.JFrame;

import common.Constant;
import controller.CaroActionListener;
import model.CaroButton;
/**
 * Tạo giao diện
 * @author Luvina
 */
public class CaroView extends JFrame {
	private static final long serialVersionUID = 1L; 	// để chắc chắn trước và sau khi chuyển đổi, lớp CaroUI là một
	private static final String TITLE = "GAME: CỜ CARO";// khai báo thuộc tính title với tên là "GAME: CỜ CARO"
	private CaroActionListener cellEvent; // Khai báo một EventListenser
	private CaroButton[][] arrButt = Constant.BUTT_MATR; // tạo thuộc tính arrButt để chứa ma trận bàn cờ
	/**
	 * Vẽ bàn cờ kích thước 20x20
	 */
	public void createGUI() {
		initGUI();// Khởi tạo giao diện
		initComponent();// Khởi tạo các component trong giao diện
		setVisible(true); 							// đặt chế độ hiển thị cho frame
	}												// kết thúc hàm

	/**
	 * Phương thức khởi tạo giao diện với các thuộc tính.
	 */
	private void initGUI() {
		setTitle(TITLE);								// Tạo tên tiêu đề
		setSize(Constant.ROW_BOARD * Constant.SIZE_BUTT, Constant.COL_BOARD * Constant.SIZE_BUTT); //thiết lập kích thước
		setLocationRelativeTo(null); 					// thiết lập cửa sổ xuất hiện ở giữa màn hình desktop
		setResizable(false); 							// không cho người dùng thay đổi kích thước cửa sổ		
		setDefaultCloseOperation(EXIT_ON_CLOSE); 		// thiết lập hoạt động khi nhấn tắt cửa sổ
	}

	/**
	 * Phương thức khởi tạo component
	 */
	private void initComponent() {
		cellEvent = new CaroActionListener();							// Khởi tạo một EventListener
		setLayout(new GridLayout(Constant.ROW_BOARD, Constant.COL_BOARD)); // Tạo 1 GridLayout kích thước 20x20 rồi set giá trị vào Frame
		// Tạo ra các button trên bàn cờ
		for (int row = 0; row < Constant.ROW_BOARD; row++) { 			// duyệt từng dòng của bàn cờ
			for (int col = 0; col < Constant.COL_BOARD; col++) { 		// duyệt từng cột của bàn cờ
				arrButt[row][col] = new CaroButton();		// Tạo button mới ở vị trí hàng i cột j
				// thiết lập kích thước cho các component
				arrButt[row][col].setPreferredSize(new Dimension(Constant.SIZE_BUTT, Constant.SIZE_BUTT));
				arrButt[row][col].setBackground(Color.WHITE); 				// set background cho button
				arrButt[row][col].setFont(new Font("Forte", Font.BOLD, 22));// set font chữ trên button
				arrButt[row][col].setMargin(new Insets(1, 1, 1, 1)); 		// Căn trái phải trên dưới cho giá trị trong button
				arrButt[row][col].setPoint(new Point(row, col)); 			// Lưu giá trị tọa độ của một button
				arrButt[row][col].addActionListener(cellEvent); // Hành động xảy ra khi tác động vào button					
				add(arrButt[row][col]); 	// Thêm button vào Frame với bố cục đã được chia nhờ GridLayout
			} 										// kết thúc vòng for duyệt cột
		} 											// kết thúc vòng for duyệt dòng			
		pack(); 									// giúp frame có kích thước vừa đủ với nội dung của frame
	} //kết thúc phương thức
	/**
	 * Thiết lập giá trị cho 1 button
	 * 
	 * @param butt: 1 button có kiểu là Butt
	 * @param name: giá trị của button ( là X hoặc O)
	 * 
	 */
	public void setFlagButt(CaroButton caroButton, String name) {
		caroButton.setText(name); 					// set tên cho một button
		if ("X".equals(name)) { 					// Nếu giá trị muốn set là "X" là quân cờ của máy
			caroButton.setForeground(Color.RED); 	// Thì cho giá trị đó màu đỏ
		} else { 									// Nếu giá trị muốn set là "O" là quân cờ của người
			caroButton.setForeground(Color.BLUE); 	// Thì cho giá trị đó màu xanh
		} 											// Kết thúc else
	} 												// Kết thúc hàm
}// đóng class

