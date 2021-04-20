package Server;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class ParserSettingTXTTest {
    File file = new File("settings.txt");
    ParserSettingTXT parserSettingTXT = new ParserSettingTXT(file);

    @Test
    void notGetPort() {
       int result = parserSettingTXT.getPort();;
       assertNotEquals(4444, result);
    }
    @Test
    void getPort() {
        int result = parserSettingTXT.getPort();;
        assertEquals(3345, result);
    }
}