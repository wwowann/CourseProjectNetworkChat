package server;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ParserSettingTXTTest {
    File file = new File("settings.txt");
    ParserSettingTXT parserSettingTXT = new ParserSettingTXT(file);

    @Test
    void notGetPort() throws IOException {
       int result = (int)parserSettingTXT.getData("port");
       assertNotEquals(4444, result);
    }
    @Test
    void getPort() throws IOException {
        int result = (int)parserSettingTXT.getData("port");
        assertEquals(3345, result);
    }
}