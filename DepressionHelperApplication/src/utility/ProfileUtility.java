package utility;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import models.ChecklistScore;
import models.ChecklistScores;
import models.JournalEntries;
import models.JournalEntry;
import models.UserProfile;

/**
 *
 * @author tyler
 */
public class ProfileUtility {
    public static void createProfile(File saveFile) throws JAXBException {
        
        //Initialize Objects
        UserProfile profile = new UserProfile();
        JournalEntries entries = new JournalEntries();
        ChecklistScores scores = new ChecklistScores();
        
        //Set objects
        profile.setChecklistScores(scores);
        profile.setJournalEntries(entries);
        
        updateProfile(profile,saveFile);
    }
    
    public static void addJournalEntry(JournalEntry newEntry, File saveFile) throws JAXBException {
        //extract contents of profile.xml and append entry
        UserProfile profile = extractProfile(saveFile);
        JournalEntries entries = profile.getJournalEntries();
        List<JournalEntry> entryList = entries.getEntries();
        
        if(entryList == null) {
            entryList = new ArrayList<>();
        }
        
        entryList.add(newEntry);
        entries.setEntries(entryList);
        profile.setJournalEntries(entries);

        updateProfile(profile,saveFile);
    }
    
    public static void deleteJournalEntry(JournalEntry entry, File saveFile) throws JAXBException {
        UserProfile profile = extractProfile(saveFile);
        JournalEntries entries = profile.getJournalEntries();
        List<JournalEntry> entryList = entries.getEntries();
        
        for(JournalEntry journalEntry : entryList) {
        	if(journalEntry.getDate().equals(entry.getDate())) {
        		entryList.remove(journalEntry);
        		break;
        	}
        }
        
        entries.setEntries(entryList);
        profile.setJournalEntries(entries);

        updateProfile(profile,saveFile);    	
    }
    
    public static void updateJournalEntry(JournalEntry entry, File saveFile) throws JAXBException {
        UserProfile profile = extractProfile(saveFile);
        JournalEntries entries = profile.getJournalEntries();
        List<JournalEntry> entryList = entries.getEntries();
        
        for(JournalEntry journalEntry : entryList) {
        	if(journalEntry.getDate().equals(entry.getDate())) {
        		copyJournal(journalEntry, entry);
        	}
        }
        
        entries.setEntries(entryList);
        profile.setJournalEntries(entries);

        updateProfile(profile,saveFile);
    }
    
    public static void addChecklistScore(ChecklistScore newScore, File saveFile) throws JAXBException {
        //extract contents of profile.xml and append entry
        UserProfile profile = extractProfile(saveFile);
        ChecklistScores scores = profile.getChecklistScores();
        List<ChecklistScore> scoreList = scores.getScores();
        
        if(scoreList == null) {
            scoreList = new ArrayList<>();
        }
        
        scoreList.add(newScore);
        scores.setScores(scoreList);
        profile.setChecklistScores(scores);

        updateProfile(profile, saveFile);
    }
    
    public static void createSavePath() {
        String path = System.getProperty("user.home") + "\\.depressionhelper\\";
        if (!new File(path).exists()){
            new File(path).mkdirs();
        }
    }
    
    public static File getSaveFile() throws JAXBException {
    	File saveFile = new File(System.getProperty("user.home") + "\\.depressionhelper\\profile.xml");
    	if(!saveFileExists()) {
    		createSavePath();
    		createProfile(saveFile);
    	}
        return saveFile;
    }
    
    public static List<ChecklistScore> getChecklistScores() throws JAXBException {
        UserProfile profile = extractProfile(getSaveFile());
        return profile.getChecklistScores().getScores();
    }
    
    public static List<JournalEntry> getJournalEntries() throws JAXBException {
        UserProfile profile = extractProfile(getSaveFile());
        return profile.getJournalEntries().getEntries();
    }
    
    private static boolean saveFileExists() {
    	String path = System.getProperty("user.home") + "\\.depressionhelper\\profile.xml";
    	if(new File(path).exists()) {
    		return true;
    	}
    	return false;
    }
    
    private static void updateProfile(UserProfile profile, File saveFile) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(UserProfile.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        
        //overwrite file with updated profile
        jaxbMarshaller.marshal(profile, saveFile);
        
        //debug
        System.out.println("Updating Profile..");
        jaxbMarshaller.marshal(profile, System.out);         
    }
    
    private static UserProfile extractProfile(File saveFile) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(UserProfile.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        UserProfile profile = (UserProfile) unmarshaller.unmarshal(saveFile);
        return profile;
    }
    
    private static JournalEntry copyJournal(JournalEntry original, JournalEntry toCopy) {
    	original.setName(toCopy.getName());
    	original.setAutoEntry(toCopy.getAutoEntry());
    	original.setDistortionEntry(toCopy.getDistortionEntry());
    	original.setRationalEntry(toCopy.getRationalEntry());
    	original.setDate(toCopy.getDate());
    	return original;
    }
}
