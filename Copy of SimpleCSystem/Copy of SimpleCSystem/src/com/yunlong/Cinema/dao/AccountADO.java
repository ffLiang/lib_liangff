package com.yunlong.Cinema.dao;

import java.util.Vector;

import com.yunlong.Cinema.vo.Account;

public interface AccountADO {
	/**
	 * 登陆信息
	 * @return 账户资料
	 */
	public Account enter(Account account);
	/**
	 * 添加账号
	 * @param account
	 * @return
	 */
	public boolean insert(Account account);
	/**
	 * 修改账号信息
	 * @param account
	 * @return
	 */
	public boolean edit(Account account);
	/**
	 * 删除账号信息
	 * @param id
	 * @return
	 */
	public boolean delete(int id);
	/**
	 * 查找所有账户资料
	 * @return
	 */
    public Vector<Vector<Object>> aselect();
    /**
     * 通过id查账户资料
     * @return
     */
    public Account idSelect(int id);
    /**
     * 修改账号信息【不改密码】
     * @param account
     * @return
     */
    public boolean editNP(Account account);
    /**
     * 查找所有用户姓名
     * @return
     */
    public Vector<String> aName();
    /**
     * 通过accountId修改密码
     * @return
     */
    public boolean epassword(int id,String pw);
}
