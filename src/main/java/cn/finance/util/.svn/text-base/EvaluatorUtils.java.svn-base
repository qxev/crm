package cn.finance.util;

import java.util.Map;

import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;

import org.apache.commons.lang.math.NumberUtils;

public abstract class EvaluatorUtils {
	public static String evaluator(String expression, Map<String, String> map)
			throws EvaluationException {
		Evaluator e = new Evaluator();
		e.setVariables(map);
		String number = e.evaluate(expression);
		if(NumberUtils.isNumber(number)) {
			return e.evaluate(expression);
		} else {
			System.out.println(number);
			throw new NumberFormatException("NaN");
		}
	}

	public static void main(String[] args) {
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("a", "5");
//		map.put("b", "0");
//		try {
////			java.lang.Double
//			// new
//			// Double(Math.floor(NumberUtils.toDouble(EvaluatorUtils.evaluator(REMAIN_DAYS,
//			// map)))).intValue();
//			Double dd = NumberUtils.createDouble(Math.floor(NumberUtils.toDouble(EvaluatorUtils
//					.evaluator("#{a} / #{b}", map))) + "");
//			System.out.println(NumberUtils.isDigits(dd.toString()));
//		} catch (EvaluationException e) {
//			e.printStackTrace();
//		}
		System.out.println(9/0);
	}
}
