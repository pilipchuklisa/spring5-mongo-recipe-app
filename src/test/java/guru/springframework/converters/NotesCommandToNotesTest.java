package guru.springframework.converters;

import guru.springframework.commands.NotesCommand;
import guru.springframework.domain.Notes;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
public class NotesCommandToNotesTest {

    public static final String ID_VALUE = "1";
    public static final String RECIPE_NOTES = "Notes";
    NotesCommandToNotes converter;

    @Before
    public void setUp() throws Exception {
        converter = new NotesCommandToNotes();

    }

    @Test
    public void testNullParameter() throws Exception {
        Assert.assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        Assert.assertNotNull(converter.convert(new NotesCommand()));
    }

    @Test
    public void convert() throws Exception {
        //given
        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(ID_VALUE);
        notesCommand.setRecipeNotes(RECIPE_NOTES);

        //when
        Notes notes = converter.convert(notesCommand);

        //then
        Assert.assertNotNull(notes);
        Assert.assertEquals(ID_VALUE, notes.getId());
        Assert.assertEquals(RECIPE_NOTES, notes.getRecipeNotes());
    }

}