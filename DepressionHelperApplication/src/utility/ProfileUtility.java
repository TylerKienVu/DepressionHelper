package utility;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import models.JournalEntries;
import models.JournalEntry;
import models.UserProfile;

/**
 *
 * @author tyler
 */
public class ProfileUtility {
    public static void createProfile(File saveFile) throws JAXBException {
        UserProfile profile = new UserProfile();
        JournalEntries entries = new JournalEntries();

        profile.setJournalEntries(entries);
        
        //write empty profile to file in xml
        JAXBContext jaxbContext = JAXBContext.newInstance(UserProfile.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(profile, saveFile);
        
        //debug
        System.out.println("Creating Profile..");
        jaxbMarshaller.marshal(profile, System.out);
    }
    public static void addJournalEntry(JournalEntry newEntry, File saveFile) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(UserProfile.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        //extract contents of profile.xml and append entry
        UserProfile profile = (UserProfile) unmarshaller.unmarshal(saveFile);
        JournalEntries entries = profile.getJournalEntries();
        List<JournalEntry> entryList = entries.getEntries();
        
        if(entryList == null) {
            entryList = new ArrayList<>();
        }
        
        entryList.add(newEntry);
        entries.setEntries(entryList);
        profile.setJournalEntries(entries);

        //overwrite file with updated profile
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(profile, saveFile);
        
        //debug
        System.out.println("Updating Profile..");
        jaxbMarshaller.marshal(profile, System.out);        
    }
}
