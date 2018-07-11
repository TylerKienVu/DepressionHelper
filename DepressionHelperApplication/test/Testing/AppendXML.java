package Testing;


import java.io.File;
import java.util.Date;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import models.JournalEntry;
import models.JournalEntries;
import models.UserProfile;
/**
 *
 * @author tyler
 */
public class AppendXML {
    public static void main(String[] args) throws JAXBException {
        JournalEntry newEntry = new JournalEntry();
        newEntry.setDate(new Date());
        newEntry.setAutoEntry("second test");
        newEntry.setDistortionEntry("second test");
        newEntry.setRationalEntry("second test");
        
        JAXBContext jc = JAXBContext.newInstance(UserProfile.class);

        Unmarshaller unmarshaller = jc.createUnmarshaller();
        File file = new File("test.xml");
        if(file.exists() && !file.isDirectory()) {
            UserProfile profile = (UserProfile) unmarshaller.unmarshal(file);

            JournalEntries entries = profile.getJournalEntries();
            List<JournalEntry> entryList = entries.getEntries();
            entryList.add(newEntry);
            entries.setEntries(entryList);
            profile.setJournalEntries(entries);

            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(profile, file);
            marshaller.marshal(profile, System.out);
        }
    }
}
