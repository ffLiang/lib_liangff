package com.yunlong.Cinema.dao;

import java.util.List;

import com.yunlong.Cinema.vo.Category;

public interface CategoryDAO {
		/**
		 * �����еĵ�Ӱ����
		 * @return
		 */
		public List<Category> list();
		/**
		 * ͨ�������������ͱ��
		 * @param cat
		 * @return
		 */
		public int cate(String cat);
		/**
		 * ��ӵ�Ӱ����
		 * @param category
		 * @return
		 */
		public boolean insertCate(Category category);
		/**
		 * �޸ĵ�Ӱ����
		 * @param category
		 * @return
		 */
		public boolean editCate(Category category);
}
