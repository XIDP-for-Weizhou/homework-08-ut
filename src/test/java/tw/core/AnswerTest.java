package tw.core;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tw.core.exception.AnswerFormatIncorrectException;
import tw.core.model.Record;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by jxzhong on 2017/9/23.
 */
class AnswerTest {
    private Answer actualAnswer;

    @BeforeEach
    public void setUp() {
        actualAnswer = Answer.createAnswer("1 2 3 4");
    }

    @Test
    void should_return_answer_object_with_num_equal_input_when_input_calling_createAnswer() {
        // given
        // when
        // then
        assertEquals(Answer.class, actualAnswer.getClass());
        assertThat(actualAnswer.toString(), is("1 2 3 4"));
    }

    @Test
    void should_throw_exception_when_input_invalid() {
        // given
        // when
        // then
        assertThrows(AnswerFormatIncorrectException.class, () -> Answer.createAnswer("1 1 10 2").validate());
    }

    @Test
    void should_return_0A0B_when_input_all_wrong() {
        // given
        Answer inputAnswer = Answer.createAnswer("5 6 7 8");
        // when
        Record record = actualAnswer.check(inputAnswer);
        // then
        assertEquals("0A0B", record.getValue());
    }

    @Test
    void should_return_1A2B_when_input_part_wrong() {
        // given
        Answer inputAnswer = Answer.createAnswer("0 3 2 4");
        // when
        Record record = actualAnswer.check(inputAnswer);
        // then
        assertEquals("1A2B", record.getValue());
    }

}