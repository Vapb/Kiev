package endpoint;


import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import cin.Singleton;

@ServerEndpoint("/websocketendpoint")
public class MyServerEndpoint {
	
	public MyServerEndpoint(){
		System.out.println("\n \n\n \n\n \n\n \n\n \nasdasd");
		
	}
	
	@OnOpen
	public void onOpen(){
		System.out.println("Open Connection ...");
	}
	
	@OnClose
	public void onClose(){
		System.out.println("Close Connection ...");
	}
	
	@OnMessage
	public void onMessage(Session session, String message){
		//MyServletContextListener tmp = MyServletContextListener.getInstance( );
		System.out.println("Message from the client: " + message);
		//String echoMsg = "Echo from the server : " + message;
		Singleton.getStatement().addListener( (newData, oldData) -> {
			//if
			String unidade = (String) newData[0].get("unidade").toString();
			String codx = (String) newData[0].get("codx").toString();
			String cody = (String) newData[0].get("cody").toString();
			String tempo = (String) newData[0].get("tempo").toString();
			try {
				session.getBasicRemote().sendText("{"
						+
						"\"unidade\":" + unidade + "," +
						"\"codx\":" + codx + "," +
						"\"cody\":" + cody + "," +
						"\"tempo\":\"" + tempo + "\""
						+ "}");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

	@OnError
	public void onError(Throwable e){
		e.printStackTrace();
	}

}