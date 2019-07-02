package testclass;

import java.util.ArrayList;

/*佣金问题：
 * 电脑销售系统，
 * 主机（25￥单位价格，每月最多销售的数量为70），
 * 显示器（30￥单位价格，每月最多销售数量为80），
 * 外设（45￥单位价格，每月最多销售的数量为90）；
 * 每个销售员每月至少销售一台完整的机器，
 * 当系统的主机这个变量接受到-1值的时候，系统自动统计该销售员本月的销售总额。
 * 当销售额小于等于1000（包括1000）按照10%提佣金，
 * 当销售额在1000-1800之间（包括1800）的时候按照15%提佣金，
 * 当销售额大于1800时按照20%提佣金。用边界值法设计测试用例。
*/
public class SaleSystem {
	private static final int priceOfMaster = 25;
	private static final int priceOfScreen = 30;
	private static final int priceOfPeripheral = 45;
	private static int saleOfMaster =  0;
	private static int saleOfScreen =  0;
	private static int saleOfPeripheral =  0;
	public static double sell(ArrayList<Sale> saleList ) {
		double salary = 0;
		saleOfMaster =  0;
		saleOfScreen =  0;
		saleOfPeripheral =  0;
//		System.out.println(saleList.size());
		if (saleList.size() < 1) {
			System.out.println("无销售记录");
			return salary;
		}
		if(saleList.get(saleList.size()-1).mNum != -1) {
			System.out.println("输入缺少-1作为结束标志");
		}
		for (int i = 0; i < saleList.size(); i++) {
			Sale sale = saleList.get(i);
			if(sale.mNum == -1) {
				if (i != saleList.size()-1) {
					System.out.println("输入数据在结束后仍有输入");
					return salary;					
				}
				if (saleOfMaster>=1 && saleOfScreen>=1 && saleOfPeripheral>=1) {
					int totalSale = saleOfMaster*priceOfMaster + saleOfScreen*priceOfScreen + saleOfPeripheral*priceOfPeripheral;
					System.out.println("本月销售总额为："+totalSale);
					salary = 0;
					if (totalSale>=0 && totalSale<=1000) {
						salary = totalSale*0.1;
						System.out.println("本月佣金为："+salary);
					}else if (totalSale<=1800) {
						salary = totalSale*0.15;
						System.out.println("本月佣金为："+salary);
					}else if (totalSale>1800) {
						salary = totalSale*0.2;
						System.out.println("本月佣金为："+salary);
					}else {
						System.out.println("销售额不能为负");
					}
					return salary;
				}else {
					System.out.println("本月并未成功销售一台完整的机器");
					return salary;
				}
			}else {
				if (sale.mNum>=0 && sale.sNum>=0 && sale.pNum>=0) {
					if(saleOfMaster+sale.mNum>70 || saleOfScreen+sale.sNum>80 || saleOfPeripheral+sale.pNum>90) {
						System.out.println("超出每月销售数量");
						return salary;
					}else {
						saleOfMaster+=sale.mNum;
						saleOfScreen+=sale.sNum;
						saleOfPeripheral+=sale.pNum;
					}
				}else {
					System.out.println("销售数量不能为负");
					return salary;
				}
			}
		}
		return salary;
	}
}
