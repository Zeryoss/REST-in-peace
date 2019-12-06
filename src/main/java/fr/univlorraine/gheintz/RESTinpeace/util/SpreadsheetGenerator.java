package fr.univlorraine.gheintz.RESTinpeace.util;

import fr.univlorraine.gheintz.RESTinpeace.entity.Grave;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

public interface SpreadsheetGenerator {

    public static enum ExportType {
        Full,
        Light
    };

    public ByteArrayInputStream generate(List<Grave> graves, ExportType exportType) throws IOException;
}
