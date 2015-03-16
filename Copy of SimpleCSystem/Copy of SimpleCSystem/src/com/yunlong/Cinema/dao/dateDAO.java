package com.yunlong.Cinema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yunlong.Cinema.util.ConnClose;
import com.yunlong.Cinema.vo.Category;
import com.yunlong.Cinema.vo.Date;

public interface dateDAO {
		/**
		 * 通过帐号查记录日期
		 * @return
		 */
		public List<String> list(int accountId);
		/**
		 * 通过帐号和日期查找记录
		 * @param accountId
		 * @param date
		 * @return
		 */
		public List<String> note(int accountId, String date);
		/**
		 * 添加记录
		 * @param Date
		 * @return
		 */
		public boolean insertNote(Date date);

		
}
