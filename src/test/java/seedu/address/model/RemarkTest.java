package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Remark;

public class RemarkTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Remark(null));
    }

    @Test
    public void equals() {
        Remark remark = new Remark("Some remark");

        // same values -> returns true
        assertEquals(new Remark("Some remark"), remark);

        // same object -> returns true
        assertEquals(remark, remark);

        // null -> returns false
        assertNotEquals(null, remark);

        // different types -> returns false
        assertNotEquals(5.0f, remark);

        // different values -> returns false
        assertNotEquals(new Remark("Other remark"), remark);
    }
}
