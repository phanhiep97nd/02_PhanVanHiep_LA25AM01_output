/**
 * Copyright(C) 2020  Luvina Software
 * Bai1.java, Jun 8, 2020 Phan Van Hiep
 */
package bai1;

import java.util.Scanner;

/**
 * Bai 1 cua bai tap java core: Thực hiện phép chia a và b
 * 
 * @author Phan Van Hiep
 */
public class Bai1 {
	/**
	 * Lay ra so ma nguoi dung nhap
	 * @param number  gia tri muon lay
	 * @return tra ve gia tri kieu so nguyen ma nguoi dung nhap
	 */
	public double getNumber(String number) {
		Scanner sc = new Scanner(System.in);
		boolean flag = true;
		do {
			System.out.println("Giá trị [" + number + "] : ");
			String input = sc.nextLine();
			if ("".equals(validate(input, number))) {
				return Double.parseDouble(input);
			} else {
				System.out.println(validate(input, number));
				flag = false;
			}
		} while (!flag);
		return 0;
	}

	/**
	 * Description: validate du lieu nguoi dung nhap
	 * 
	 * @param input gia tri nguoi dung nhap tu ban phim
	 * @param bien muon kiem tra
	 * @return tra ve thong bao loi neu co
	 */
	private String validate(String input, String number) {
		if ("".equals(input)) {
			return "Hãy nhập giá trị cho " + number + " .";
		} else {
			try {
				double valueNumber = Double.parseDouble(input);
				if (valueNumber <= 0) {
					return "Giá trị [" + number + "] phải là giá trị số và > 0. Hãy nhập lại.";
				} else if (valueNumber > 99999) {
					return "Giá trị [" + number + "] không được lớn hơn 5 số. Hãy nhập lại.";
				}
			} catch (NumberFormatException e) {
				return "Giá trị [" + number + "] phải là giá trị số và > 0. Hãy nhập lại.";
			}
		}
		return "";
	}

	/**
	 *  thuc hien phep chia
	 * @param valueA gia tri cua A ma nguoi dung da nhap
	 * @param valueB gia tri cua B ma nguoi dung da nhap
	 * @return tra ve thuong cua phep chia
	 */
	public double divide(double valueA, double valueB) {
		return (double) (valueA) / (double) (valueB);
	}

	/**
	 * Description: thong bao ket qua ra man hinh
	 */
	public void printResult() {
		System.out.println("Thương của A/B bằng: " + divide(getNumber("A"), getNumber("B")));
	}
}
