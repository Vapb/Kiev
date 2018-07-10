package cin;

import java.text.ParseException;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;

public class Singleton {

   private static Singleton singleton;
   private static EPServiceProvider engine;
   private static EPStatement statement;
   
   public static void main(String[] args) {
		  try {
			Singleton.getInstance();
			Singleton.getStatement().addListener( (newData, oldData) -> {
				//if
				String unidade = (String) newData[0].get("unidade").toString();
				String codx = (String) newData[0].get("codx").toString();
				String cody = (String) newData[0].get("cody").toString();
				String tempo = (String) newData[0].get("tempo").toString();
				try {
					System.out.println(unidade);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}

   /* A private Constructor prevents any other
    * class from instantiating.
    */
   private Singleton() throws ParseException {
		engine = EPServiceProviderManager.getDefaultProvider();
		engine.getEPAdministrator().getConfiguration().addEventType(OnibusEvent.class);
		String epl = "select unidade,codx,cody,tempo from OnibusEvent where codx > 0";
		statement = engine.getEPAdministrator().createEPL(epl);
		
		Runnable r = new Csv(engine);
		new Thread(r).start();
   }

   /* Static 'instance' method */
   public static Singleton getInstance( ) throws ParseException {
	      if(singleton == null) {
	    	  singleton = new Singleton();
	       }
      return singleton;
   }

   /* Other methods protected by singleton-ness */
   public static EPStatement getStatement( ) {
      return statement;
   }
}
