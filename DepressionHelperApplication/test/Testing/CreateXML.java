package Testing;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import models.JournalEntries;
import models.JournalEntry;
import models.UserProfile;


/**
 *
 * @author tyler
 */
public class CreateXML {
    public static void main(String[] args) {
        JournalEntry entry = new JournalEntry();
        entry.setDate(new Date());
        entry.setAutoEntry("This is a test auto entry");
        entry.setDistortionEntry("This is a test distortion entry");
        entry.setRationalEntry("This is a rational response");
        
        JournalEntries entries = new JournalEntries();
        List<JournalEntry> entryList = new ArrayList<>();
        entryList.add(entry);
        entries.setEntries(entryList);
        
        UserProfile profile = new UserProfile();
        profile.setJournalEntries(entries);
        
        try {
            File file = new File("test.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(UserProfile.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            
            //output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            
            jaxbMarshaller.marshal(profile, file);
            jaxbMarshaller.marshal(profile, System.out);
        }
        catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
