package com.yunlong.Cinema.dao;

import java.util.Vector;

import com.yunlong.Cinema.vo.Account;

public interface AccountADO {
	/**
	 * ��½��Ϣ
	 * @return �˻�����
	 */
	public Account enter(Account account);
	/**
	 * ����˺�
	 * @param account
	 * @return
	 */
	public boolean insert(Account account);
	/**
	 * �޸��˺���Ϣ
	 * @param account
	 * @return
	 */
	public boolean edit(Account account);
	/**
	 * ɾ���˺���Ϣ
	 * @param id
	 * @return
	 */
	public boolean delete(int id);
	/**
	 * ���������˻�����
	 * @return
	 */
    public Vector<Vector<Object>> aselect();
    /**
     * ͨ��id���˻�����
     * @return
     */
    public Account idSelect(int id);
    /**
     * �޸��˺���Ϣ���������롿
     * @param account
     * @return
     */
    public boolean editNP(Account account);
    /**
     * ���������û�����
     * @return
     */
    public Vector<String> aName();
    /**
     * ͨ��accountId�޸�����
     * @return
     */
    public boolean epassword(int id,String pw);
}
