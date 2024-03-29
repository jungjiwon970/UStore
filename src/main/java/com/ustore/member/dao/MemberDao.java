package com.ustore.member.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.ustore.member.dto.MemberDto;

@Mapper
public interface MemberDao {

	int joinnum(MemberDto dto);

	int joinbis(MemberDto dto);

	int cusnum();

	ArrayList<HashMap<String, String>> listall(int pageState);

	HashMap<String, String> detail(int idx);

	ArrayList<HashMap<String, String>> cussearch(String keyword, int state);

	HashMap<String, String> update(int idx);

	int updatesave(HashMap<String, String> params);

	int del(int idx);

	int newdate(int idx, int gradeidx);

	HashMap<String, String> filefind(int idx);

	ArrayList<HashMap<String, String>> productlistcall(int memberidx, String startdate, String enddate);

	int pointinsert(int idx2);

	int cuscount();
	
	int delcuscount();

	int findnum(int idx);

	ArrayList<HashMap<String, String>> pointlistcall(int memberidx, String startdate, String enddate);

	String sumpoint(int idx);

	ArrayList<HashMap<String, String>> detailbill(String paymentidx);

	HashMap<String, String> billlist(String paymentidx);

	int bischeck(String bisnum);

	void delupdate(int idx);

	String cusex(int idx);

	int newexdate(int idx, int gradeidx, String exdate);

	

}
