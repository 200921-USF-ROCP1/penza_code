package revature.com.serialization;

import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class Main {

	public static void main(String[] args) {
		
		/*int x = Integer.parseInt("100");
		int y = Integer.parseInt("stop");
		
		System.out.println( x );
		System.out.println( y );*/
		
		DemoClass democ1 = new DemoClass(), empty1;
		democ1.setI(67);
		democ1.setB(false);
		democ1.setS("Testing 1 2 3!");
		
		String s;
		
		s = marshalToXml(democ1);
		System.out.println( "s as marshalled from democ1: --" + s + "--");
		
		empty1 = unmarshalFromXml(s);
		System.out.println( "Empty s=" + empty1.getS() );
		System.out.println( "Empty i=" + empty1.getI() );
		
		//String marshalToJson(DemoClass demoClass);
		//DemoClass unmarshalFromJson(String json);

	}

	public static String marshalToXml(DemoClass d1) {
		StringWriter sw = new StringWriter();
		
		try {
			JAXBContext context = JAXBContext.newInstance(DemoClass.class);
			
			// Marshalling
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			
			marshaller.marshal(d1, sw );
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sw.toString();
		                             
	}
	
	// You can make this a file or whatever is easiest
	public static DemoClass unmarshalFromXml(String xml) {
		StringBuffer xmlStr = new StringBuffer( xml );
		DemoClass d1 = new DemoClass();

		JAXBContext context;
		try {
			context = JAXBContext.newInstance(DemoClass.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			d1 = (DemoClass) unmarshaller.unmarshal(new StringReader( xmlStr.toString() ) );
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d1;
	}

	/*String marshalToJson(DemoClass demoClass) {
		
	}
	
	// Change the parameter type if you want
	DemoClass unmarshalFromJson(String json) {
		
	}*/

}
