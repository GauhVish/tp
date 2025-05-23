package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_SCHEDULE_START_TIME_BEFORE_END_TIME;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_REMARK_TOO_LONG;
import static seedu.address.model.person.Remark.MAX_REMARK_LENGTH;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.JobRole;
import seedu.address.model.person.Label;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "@@@";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_JOB_ROLE = "S**tware Eng!n33r";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_LABEL = "Acceptnot";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_JOB_ROLE = "Software Engineer";
    private static final String VALID_JOB_ROLE_2 = "(Level 7)";
    private static final String VALID_JOB_ROLE_3 = "(Level 7 or Level 8)";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_REMARK = "Proficient in Java EE";
    private static final String VALID_FILE_EXTENSION = ".json";
    private static final String VALID_FILE_NAME = "test";
    private static final String VALID_FILE_PATH_RELATIVE = VALID_FILE_NAME + VALID_FILE_EXTENSION;
    private static final String VALID_FILE_PATH_ABSOLUTE = "/" + VALID_FILE_PATH_RELATIVE;

    private static final String VALID_LABEL = "Accepted";
    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAddress((String) null));
    }

    @Test
    public void parseAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAddress(INVALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(VALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(addressWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parseLabel_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseLabel((String) null));
    }

    @Test
    public void parseLabel_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseLabel(INVALID_LABEL));
    }

    @Test
    public void parseLabel_validValueWithoutWhitespace_returnsLabel() throws Exception {
        Label expectedLabel = new Label(VALID_LABEL);
        assertEquals(expectedLabel, ParserUtil.parseLabel(VALID_LABEL));
    }

    @Test
    public void parseLabel_validValueWithWhitespace_returnsTrimmedLabel() throws Exception {
        String labelWithWhitespace = WHITESPACE + VALID_LABEL + WHITESPACE;
        Label expectedLabel = new Label(VALID_LABEL);
        assertEquals(expectedLabel, ParserUtil.parseLabel(labelWithWhitespace));
    }

    @Test
    public void parseJobRole_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseJobRole((String) null));
    }

    @Test
    public void parseJobRole_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseJobRole(INVALID_JOB_ROLE));
    }

    @Test
    public void parseJobRole_overflowValue_throwsParseException() {
        String jobRoleWithWhitespace = WHITESPACE + VALID_JOB_ROLE + VALID_JOB_ROLE_3 + WHITESPACE;
        assertThrows(ParseException.class, () -> ParserUtil.parseJobRole(jobRoleWithWhitespace));
    }

    @Test
    public void parseJobRole_validValueWithoutWhitespace_returnsJobRole() throws Exception {
        JobRole expectedJobRole = new JobRole(VALID_JOB_ROLE);
        assertEquals(expectedJobRole, ParserUtil.parseJobRole(VALID_JOB_ROLE));
    }

    @Test
    public void parseJobRole_validValueWithWhitespaceSpecialCharacters_returnsJobRole() throws Exception {
        String jobRoleWithWhitespace = WHITESPACE + VALID_JOB_ROLE + VALID_JOB_ROLE_2 + WHITESPACE;
        JobRole expectedJobRole = new JobRole(VALID_JOB_ROLE + VALID_JOB_ROLE_2);
        assertEquals(expectedJobRole, ParserUtil.parseJobRole(jobRoleWithWhitespace));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }

    @Test
    public void parsePath_validRelativePathGiven_returnsPath() throws Exception {
        Path actualPath = ParserUtil.parsePath(VALID_FILE_PATH_RELATIVE);
        Path expectedPath = Path.of(VALID_FILE_PATH_RELATIVE);

        assertEquals(expectedPath, actualPath);
    }

    @Test
    public void parsePath_validAbsolutePathGiven_returnsPath() throws Exception {
        Path actualPath = ParserUtil.parsePath(VALID_FILE_PATH_ABSOLUTE);
        Path expectedPath = Path.of(VALID_FILE_PATH_ABSOLUTE);

        assertEquals(expectedPath, actualPath);
    }

    @Test
    public void parsePath_validPathWithoutJsonExtensionGiven_returnsPathWithJsonExtensionAppended() throws Exception {
        Path actualPath = ParserUtil.parsePath(VALID_FILE_NAME);
        Path expectedPath = Path.of(VALID_FILE_PATH_RELATIVE);

        assertEquals(expectedPath, actualPath);
    }

    @Test
    public void parseRemark_validRemarkGiven_returnsRemark() throws ParseException {
        Remark actualRemark = ParserUtil.parseRemark(VALID_REMARK);
        Remark expectedRemark = new Remark(VALID_REMARK);

        assertEquals(expectedRemark, actualRemark);
    }

    @Test
    public void parseRemark_veryLongRemarkGiven_throwsParseException() {
        String userInput = VALID_REMARK.repeat(MAX_REMARK_LENGTH);

        assertThrows(
                ParseException.class,
                String.format(MESSAGE_REMARK_TOO_LONG, MAX_REMARK_LENGTH, userInput.length()), () ->
                        ParserUtil.parseRemark(userInput));
    }

    @Test
    public void parseScheduleTiming_startNotBeforeEnd_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_SCHEDULE_START_TIME_BEFORE_END_TIME, () ->
                ParserUtil.parseStartEndTimeFromSchedulePrefix("2025-05-13 10:00 09:00"));
    }
}
