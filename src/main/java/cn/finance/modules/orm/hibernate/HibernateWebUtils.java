package cn.finance.modules.orm.hibernate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.util.WebUtils;

import cn.finance.model.base.PropertyFilter;

/**
 * Hibernate针对Web应用的Utils函数集合.
 */
public class HibernateWebUtils {
	private HibernateWebUtils() {
	}

	public static <T, ID> void mergeByCheckedIds(final Collection<T> srcObjects, final Collection<ID> checkedIds, final Class<T> clazz) {
	}

	/**
	 * 根据对象ID集合,整理合并集合.
	 * 
	 * 页面发送变更后的子对象id列表时,删除原来的子对象集合再根据页面id列表创建一个全新的集合这种看似最简单的做法是不行的.
	 * 因此采用如此的整合算法：在源集合中删除id不在目标集合中的对象,根据目标集合中的id创建对象并添加到源集合中. 因为新建对象只有ID被赋值,
	 * 因此本函数不适合于cascade-save-or-update的情形.
	 * 
	 * @param srcObjects
	 *            源集合,元素为对象.
	 * @param checkedIds
	 *            目标集合,元素为ID.
	 * @param clazz
	 *            集合中对象的类型
	 * @param idName
	 *            对象主键的名称
	 */
	public static <T, ID> void mergeByCheckedIds(final Collection<T> srcObjects, final Collection<ID> checkedIds, final Class<T> clazz,
			final String idName) {
	}

	/**
	 * 根据按PropertyFilter命名规则的Request参数,创建PropertyFilter列表.
	 * 默认Filter属性名前缀为filter_.
	 * 
	 */
	public static List<PropertyFilter> buildPropertyFilters(final HttpServletRequest request) {
		return buildPropertyFilters(request, "filter_");
	}

	/**
	 * 根据按PropertyFilter命名规则的Request参数,创建PropertyFilter列表.
	 * PropertyFilter命名规则为Filter属性前缀_比较类型_属性名.
	 * 
	 * eg. filter_EQUAL_name filter_LIKE_name_OR_email
	 */
	@SuppressWarnings("unchecked")
	public static List<PropertyFilter> buildPropertyFilters(final HttpServletRequest request, final String filterPrefix) {
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();

		// 从request中获取含属性前缀名的参数,构造去除前缀名后的参数Map.
		Map<String, Object> filterParamMap = WebUtils.getParametersStartingWith(request, filterPrefix);

		// 分析参数Map,构造PropertyFilter列表
		for (Map.Entry<String, Object> entry : filterParamMap.entrySet()) {
			String filterName = entry.getKey();
			String value = (String) entry.getValue();
			// 如果value值为空,则忽略此filter.
			boolean omit = StringUtils.isBlank(value);
			if (!omit) {
				PropertyFilter filter = new PropertyFilter(filterName, value);
				filterList.add(filter);
			}
		}
		return filterList;
	}
}
