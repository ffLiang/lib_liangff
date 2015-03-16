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
		 * ͨ���ʺŲ��¼����
		 * @return
		 */
		public List<String> list(int accountId);
		/**
		 * ͨ���ʺź����ڲ��Ҽ�¼
		 * @param accountId
		 * @param date
		 * @return
		 */
		public List<String> note(int accountId, String date);
		/**
		 * ��Ӽ�¼
		 * @param Date
		 * @return
		 */
		public boolean insertNote(Date date);

		
}
