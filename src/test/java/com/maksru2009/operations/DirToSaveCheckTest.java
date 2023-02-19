package com.maksru2009.operations;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DirToSaveCheckTest {

    private String pathCorrect;
    private String pathUnCorrect;
    @BeforeEach
    void setUp() {
        pathCorrect = "E:\\folder";
        pathUnCorrect ="Q-E:\\";
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void checkDirectoryCreateFolderCorrectFolder() {
        String expected = "E:\\folder\\RecipeFolder\\Recipe.pdf";

        DirToSaveCheck.path = pathCorrect;

        Assertions.assertThat(DirToSaveCheck.directoryCreateFolder()).isEqualTo(expected);
    }
    @Test
    void checkDirectoryCreateFolderUnCorrectFolder() {
        String expected = System.getProperty("user.dir")+"\\Recipe.pdf";

        DirToSaveCheck.path = pathUnCorrect;

        Assertions.assertThat(DirToSaveCheck.directoryCreateFolder()).isEqualTo(expected);
    }

    @Test
    void checkPathInParamsCorrectStr() {
        String expected = "C:\\folderCorrect";

        DirToSaveCheck.checkPathInParams(new String[]{"asd","pathToSave-C:\\folderCorrect"});

        Assertions.assertThat(DirToSaveCheck.path).isEqualTo(expected);
    }
    @Test
    void checkPathInParamsUnCorrectStr() {
        String expected = "C:\\folderCorrect";

        DirToSaveCheck.checkPathInParams(new String[]{"asd","pathToSave-C:--:A\\folderCorrect"});

        Assertions.assertThat(DirToSaveCheck.path).isNotEqualTo(expected);
    }
    @Test
    void checkPathInParamsVoidStr() {
        String expected = System.getProperty("user.dir");

        DirToSaveCheck.checkPathInParams(new String[]{});

        Assertions.assertThat(DirToSaveCheck.path).isEqualTo(expected);
    }
}