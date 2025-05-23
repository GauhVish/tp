package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteJCommand;
import seedu.address.model.person.JobRole;

public class DeleteJCommandParserTest {
    private DeleteJCommandParser parser = new DeleteJCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteJCommandParser() {
        String jobRole = "Software Engineer";
        assertParseSuccess(parser, "Software Engineer", new DeleteJCommand(new JobRole(jobRole)));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "S0ftwar3 3ng!n33r", String.format(JobRole.MESSAGE_NEW_CONSTRAINTS));
    }

    @Test
    public void parse_noArgs_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteJCommand.MESSAGE_USAGE));
    }
}
