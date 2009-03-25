package java_quetico_trip_planner;

import java.io.File;

/**
 * Filter JDialog Write Trip
 * 
 * Overides methods in the FileFilter class in order to restrict selection of
 * certain file types. In this instance we only want .xls files
 *
 * @author cleblanc
 */
public class FilterJDialogWriteTrip extends javax.swing.filechooser.FileFilter {

    @Override
    public boolean accept(File f)
    {
        String filename = f.getName();
        return filename.endsWith(".xls");
    }

    @Override
    public String getDescription() {
        String fileType = "*.xls";
        return fileType;
    }


}
