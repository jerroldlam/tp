package seedu.duke.apps.academicplanner.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import seedu.duke.apps.moduleloader.ModuleLoader;
import java.util.ArrayList;

//@@author vanessa-kang
class SearchModulesCommandTest {
    private static final String PARTIAL_CODE_TEST = "cg40".toUpperCase();
    private static final int PARTIAL_CODE_RESULT = 3;
    private static final ArrayList<String> PARTIAL_CODE_RESULT_LIST = new ArrayList<>() {{
            add("CG4001");
            add("CG4002");
            add("CG4003");
        }};
    private static final String FULL_CODE_TEST = "cs1010".toUpperCase();
    private static final int FULL_CODE_RESULT = 7;
    private static final ArrayList<String> FULL_CODE_RESULT_LIST = new ArrayList<>() {{
            add("CS1010");
            add("CS1010E");
            add("CS1010J");
            add("CS1010R");
            add("CS1010S");
            add("CS1010X");
            add("CS1010XCP");
        }};

    ModuleLoader allModules;
    SearchModulesCommand searcher;
    String keyword;
    ArrayList<String> testList = new ArrayList<>();

    @BeforeEach
    void setup() {
        try {
            allModules = new ModuleLoader();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void searchmodules_partialcode_success() {
        keyword = PARTIAL_CODE_TEST;
        searcher = new SearchModulesCommand(allModules,keyword);
        searcher.execute();
        testList = searcher.matchList;
        assertEquals(PARTIAL_CODE_RESULT, testList.size());
        assertEquals(PARTIAL_CODE_RESULT_LIST, testList);
    }

    @Test
    void searchmodules_fullcode_success() {
        keyword = FULL_CODE_TEST;
        searcher = new SearchModulesCommand(allModules,keyword);
        searcher.execute();
        testList = searcher.matchList;
        assertEquals(FULL_CODE_RESULT, testList.size());
        assertEquals(FULL_CODE_RESULT_LIST, testList);
    }

    @Test
    void searchmodules_missingkey_exceptionThrown() {
        try {
            searcher = new SearchModulesCommand(allModules,keyword);
            searcher.execute();
            testList = searcher.matchList;
            assertEquals(999, testList.size());
            fail();
        } catch (NullPointerException e) {
            assertEquals(null, e.getMessage());
        }
    }

}
