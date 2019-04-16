package com.tapi.tcs.vc.common.util;

import java.util.Collection;
import java.util.Map;

import net.sf.cglib.beans.BeanMap;

/**
 * Bean数据操作工具类
 * @author 
 *
 */
public class Beans {
	/**
	 * 拷贝两个Bean的属性（弱校验，增强容错性，但同名字段必须具有可直接赋值的相同类型或者父类型）
	 * @param t
	 * @param s
	 * @param copyNull 当目标对象相关属性非空时，是否拷贝源对象的null值覆盖掉
	 * @param copyEmpty 当目标对象相关属性非空时，是否拷贝源对象的空值覆盖掉
	 */
	public static void copy(Object t,Object s,boolean copyNull,boolean copyEmpty){
		if(s == null || t == null){
			return;
		}
		Map<String,Object> sm = BeanMap.create(s);//cglib的实现效率更高
		Map<String,Object> tm = BeanMap.create(t);
		for(String sk:sm.keySet()){
			if(!tm.containsKey(sk)){//对于不相干的属性不作处理
				continue;
			}
			Object sv = sm.get(sk);
			Object tv = tm.get(sk);
			if(tv==null){
				tm.put(sk, sv);
				continue;
			}
			if(sv == null){
				if(copyNull){
					tm.put(sk, sv);
				}
			}
			if(isEmpty(sv)){
				if(copyEmpty){
					tm.put(sk, sv);
				}
			}
		}
	}
	
	/**
	 * 默认拷贝方法，不拷贝null属性和空属性
	 * @param t
	 * @param s
	 */
	public static void copy(Object t,Object s){
		copy(t,s,false,false);
	}

	/**
	 * 判断一个对象是否为空
	 * @param sv
	 * @return
	 */
	public static boolean isEmpty(Object sv) {
		if(sv == null){
			return true;
		}
		if(sv.getClass().isArray()){
			return ((Object[])sv).length == 0;
		}
		if(sv instanceof Collection){
			return ((Collection)sv).isEmpty();
		}
		if(sv instanceof Map){
			return ((Map)sv).isEmpty();
		}
		if( sv instanceof String){
			return sv.toString().trim().equals("");
		}
		return sv.getClass().equals(Object.class);
	}
	
	public static boolean isNotEmpty(Object o) {
		return !isEmpty(o);
	}
}
