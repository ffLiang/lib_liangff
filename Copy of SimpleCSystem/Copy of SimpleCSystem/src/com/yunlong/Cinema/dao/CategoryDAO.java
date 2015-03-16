package com.yunlong.Cinema.dao;

import java.util.List;

import com.yunlong.Cinema.vo.Category;

public interface CategoryDAO {
		/**
		 * 查所有的电影类型
		 * @return
		 */
		public List<Category> list();
		/**
		 * 通过类型名查类型编号
		 * @param cat
		 * @return
		 */
		public int cate(String cat);
		/**
		 * 添加电影种类
		 * @param category
		 * @return
		 */
		public boolean insertCate(Category category);
		/**
		 * 修改电影种类
		 * @param category
		 * @return
		 */
		public boolean editCate(Category category);
}
