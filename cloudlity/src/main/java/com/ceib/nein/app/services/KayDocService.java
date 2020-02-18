package com.ceib.nein.app.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceib.nein.app.config.KayDocAppConfig;



@Service
public class KayDocService {

	@Autowired
	KayDocAppConfig kayDocAppConfig;
	
	
	public Object getkayDocDirectorList() {
		
		Object obj = kayDocAppConfig.callKayDocRestApi("http://180.179.206.28:8080/services/rest/folder/listChildren?folderId=4", "admin:admin");
		
		return obj;
	}
	
	public void createFolder() {
    System.out.println("create folder == ");
		kayDocAppConfig.callKayDocRestApiPost("http://180.179.206.28:8080/services/rest/folder/createFolder", "admin:admin");
		
		//return obj;
	}

	public Object getData() {
		Map<String ,String> map = new HashMap<>();
		map.put("he","hello");
		return map;
	}
	
}
