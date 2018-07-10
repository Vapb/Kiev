package cin;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.espertech.esper.client.EPServiceProvider;

public class Csv implements Runnable{
	EPServiceProvider engine;
	
	public Csv(EPServiceProvider engine) {
		this.engine = engine;
	}

	@Override
	public void run() {

		// Pegar CSV
		String arquivoCSV = "C:\\Users\\vapb7\\Downloads\\dados.csv";
		BufferedReader br = null;
		String linha = "";
		String csvDivisor = ";";
		int i = 0;
		try {
			
			br = new BufferedReader(new FileReader(arquivoCSV));

			while ((linha = br.readLine()) != null) {
				String[] lista = linha.split(csvDivisor);
				SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date data = formato.parse(lista[3]);
				int codx = Integer.parseInt(lista[5]);
				int cody = Integer.parseInt(lista[4]);
				engine.getEPRuntime().sendEvent(new OnibusEvent(lista[0],lista[1],lista[2],data,codx,cody));
				Thread.sleep(100);

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

