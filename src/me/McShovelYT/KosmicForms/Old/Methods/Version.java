package me.McShovelYT.KosmicForms.Old.Methods;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import me.McShovelYT.KosmicForms.Old.Main;

public class Version {
    public static final String VERSION = Main.getPlugin(Main.class).getDescription().getVersion();
    public static final String REMOTE_VERSION_FILE = "https://raw.githubusercontent.com/Dbzfan1023/Open-SRC-Kosmic-Forms/master/Version.txt";
    public static final byte UNINITIALIZED = 0;
    public static final byte CURRENT = 1;
    public static final byte OUTDATED = 2;
    public static final byte CONNECTION_ERROR = 3;
    public static byte result = 0;
    public static String line = null;
    public static String ammv = null;
    public static String news = null;

    public Version() {
    }

    public static void checkVersion() {
        try {
            URL url = new URL("https://raw.githubusercontent.com/Dbzfan1023/Open-SRC-Kosmic-Forms/master/Version.txt");
            InputStreamReader isr = new InputStreamReader(url.openStream());
            BufferedReader reader = new BufferedReader(isr);

            while((line = reader.readLine()) != null) {
                if (line.equals(VERSION)) {
                    result = 1;
                    reader.close();
                    isr.close();
                    return;
                } else {
                    result = 2;
                    reader.close();
                    isr.close();
                }
            }
        } catch (Exception var3) {
            var3.printStackTrace(System.err);
            result = 3;
        }

    }

    public static String getResultMessage() {
        if (result == 0) {
            return "Version Check was Uninitialized!";
        } else if (result == 1) {
            return "Your Version Is Up to date!";
        } else if (result == 2) {
            return "There is a New Version Available!";
        } else {
            return result == 3 ? "Connection Error when trying to get latest version!" : null;
        }
    }
}
