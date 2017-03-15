package com.oracle.philbot.interfaces;

// expose a rest interface to facilitate a gui

public interface IRest  {

	public void joinRoom(String room);
	public void leaveRoom(String room);
	public List<String> joinedRoom(); // get list of joined rooms
	public void sendMessage(String dest, String msg) // dest is single user or room
	public List<ThinMessage> getMessagesByCriteria(String criteria, boolean blocking) // criteria includes messageto/from, date range, etc. blocking=true means wait until there are messages then return
	public void callBackOnEvent(String url, String eventcriteria) // request to be notified on url when eventcriteria occur, eg to facilitate message push to clients

}
