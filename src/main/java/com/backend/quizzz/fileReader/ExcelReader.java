package com.backend.quizzz.fileReader;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ExcelReader {

    Map<Integer, List<String>> getFileContent(String path) throws IOException;
}
