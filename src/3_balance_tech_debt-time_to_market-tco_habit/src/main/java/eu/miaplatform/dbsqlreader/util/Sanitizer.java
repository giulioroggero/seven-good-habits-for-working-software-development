package eu.miaplatform.dbsqlreader.util;

public final class Sanitizer {


	public static Integer ndFormatter(Integer value){
		return value;
	}
	
	public static  Double percentageFormatter(Integer value, Integer total){
		Double res = safeRatioFormatter(value, total);
		return  (res == -1d) ? res : res * 100;
	}
	
	public static  Double safeRatioFormatter(Integer numerator, Integer denominator){
		if (numerator != null && denominator != null && denominator != 0 && numerator != -1 && denominator != -1){
			return (((double)numerator)/denominator);
		}else{
			return -1d;
		}
	}
	
	public static  Integer safeSum(Integer add1, Integer add2){
		if (add1 != null && add2 != null && add1 >= 0 && add2 >= 0){
			return add1 + add2;
		}else if (add1 == null || add1 < 0){
			return add2;
		}else if (add2 == null || add2 < 0){
			return add1;
		}else{
			return 0;
		}
	}
}
