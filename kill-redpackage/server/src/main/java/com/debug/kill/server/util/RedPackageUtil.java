package com.debug.kill.server.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RedPackageUtil {
	/**
	 * @param totalMoney 总红包金额
	 * @param peopelNum  红包数量
	 * @return 每个红包Lsit
	 */
	public static List<Integer> divideRedPackage(Integer totalMoney, Integer peopelNum){
		List<Integer> redPackage = new ArrayList<>();
		if(totalMoney <= 0 || peopelNum <= 0){
			return redPackage;
		}
		int restMoney = totalMoney;
		int restPeople = peopelNum;
		Random random = new Random();
		for(int i = 0; i < peopelNum - 1; i++){
			int money = random.nextInt(restMoney / restPeople - 1) + 1;
			restMoney -= money;
			restPeople--;
			redPackage.add(money);
		}
		redPackage.add(restMoney);
		return redPackage;
	}
}
