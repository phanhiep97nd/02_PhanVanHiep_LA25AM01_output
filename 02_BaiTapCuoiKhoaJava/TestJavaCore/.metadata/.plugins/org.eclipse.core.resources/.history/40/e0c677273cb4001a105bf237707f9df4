/**
 * Copyright(C) 2018 Luvina Software Company
 * PositionLogic.java,20/11/2018, Luvina
 */
package logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import common.Constant;
import model.PositionModel;

/**
 * Class đọc các thế cờ từ file
 * @author Luvina
 */
public class PositionLogic {
	private BufferedReader bufferedReader; // thuộc tính dùng để đọc văn bản từ 1 input stream

	/**
	 * Đọc thế cờ từ file và đưa vào mảng
	 * @param fileName: file thế cờ
	 * @return danh sách thế cờ
	 * @throws IOException
	 */
	public ArrayList<PositionModel> getListPosition(String fileName) {
		// tạo list chứa các thế cờ
		ArrayList<PositionModel> listPosition = new ArrayList<PositionModel>(); 
		// thuộc tính chứa dòng hiện tại đang đọc trong file
		String readline = null; 										
		// tạo mảng chứa các thế cờ
		char[][] position = new char[Constant.ROW_MATR][Constant.COL_MATR]; 
		// biến duyệt từng hàng trong mảng thế cờ
		int r = 0; 														
		// thực hiện công việc có khả năng xảy ra lỗi
		try { 															
			// Đối tượng File để đọc
			FileReader fileReader = new FileReader(fileName); 			
			// lấy dữ liệu từ file lưu vào br
			bufferedReader = new BufferedReader(fileReader); 			
			// đọc từng dòng trong file text
			while ((readline = bufferedReader.readLine()) != null) {	
				// biến đếm các vị trí có giá trị trong matrix
				int countCell = 0;										
				// chạy từng vị trí của 1 dòng trong matrix thế cờ
				for (int c = 0; c < Constant.COL_MATR; c++) { 			
					// gán giá trị cho từng phần tử của matrix thế cờ		
					position[r][c] = readline.charAt(c); 								
					// kết thúc vòng for
				} 														
				// tăng biến r => chuyển đến dòng phía dưới		
				r++; 													
				// điều kiện so sánh, nếu đọc đủ 5 dòng thì thêm thế cờ mới vào listTheCo
				if (r == Constant.ROW_MATR) { 
					// duyệt từng dòng của matrix con
					for (int i = 0; i < position.length; i++) {	
						// duyệt từng cột của matrix con
						for (int j = 0; j < position.length; j++) {		
							// kiểm tra vị trí có giá trị
							if (position[i][j]!=' ') {					
								// tăng biến đếm
								countCell++;							
							//kết thúc if
							}											
						//kết thúc vòng for duyệt cột
						}												
					//kết thúc vòng for duyệt hàng
					}													
					//nếu số vị trí có giá trị đúng bằng kích thước matrix
					if (countCell==Constant.ROW_MATR*Constant.COL_MATR) {				
						//thêm thế cờ vào list thế cờ
						listPosition.add(new PositionModel(position));		
						// làm mới mảng thế cờ
						position = new char[Constant.ROW_MATR][Constant.COL_MATR]; 
						// gán lại giá trị ban đầu cho r
						r = 0; 												
						// gán lại biến đếm giá trị
						countCell=0;										
					// kết thúc xử lý điều kiện
					} 													
				// kết thúc xử lý điều kiện
				}														
			// kết thúc vòng while
			} 															
			//xử lý nếu file rỗng
			if (listPosition.size()==0) {								
				// in ra thông báo
				JOptionPane.showMessageDialog(null, "Hệ thống đang có lỗi"); 
				// kết thúc chương trình
				System.exit(0);											
				// kết thúc điều kiện
			}															
			// đóng bufferedReader
			bufferedReader.close(); 									
			// đóng fileReader
			fileReader.close(); 										
		// bắt lỗi luồng đọc ghi bị gián đoạn
		} catch(IOException e) {										
			// in ra thông báo
			JOptionPane.showMessageDialog(null, "Hệ thống đang có lỗi"); 
			// ghi log ra màn hình console
			System.out.println(e.getMessage()); 						
			//dừng chương trình
			System.exit(0);											
		// kết thúc việc xử lý lỗi
		}														
		// trả về list theCo
		return listPosition; 											
	// kết thúc phương thức
	} 			
// đóng class
}	
