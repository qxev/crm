package cn.finance.dao;

import cn.finance.model.Client;

public class TestClientDao  {

	private ClientDao clientDao;


	public void testSave() {
		Client client = clientDao.getClientBySv3Id(1);
		client.setName("xuang");
		clientDao.save(client);
		System.out.println(client.getName());
	}

	public ClientDao getClientDao() {
		return clientDao;
	}

	public void setClientDao(ClientDao clientDao) {
		this.clientDao = clientDao;
	}
}
