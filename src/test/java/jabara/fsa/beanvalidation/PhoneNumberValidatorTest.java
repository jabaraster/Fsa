/**
 * 
 */
package jabara.fsa.beanvalidation;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

/**
 * @author jabaraster
 */
public class PhoneNumberValidatorTest {

    private static final boolean  VALID   = true;
    private static final boolean  INVALID = false;

    private PhoneNumber.Validator sut;

    /**
     * 
     */
    @SuppressWarnings({ "nls", "boxing" })
    @Test
    public void _ハイフンがなく数字以外の文字が混ざっている文字列はinvalid() {
        assertThat(this.sut.isValid("000aaaa0000", null), is(INVALID));
        assertThat(this.sut.isValid("000aaaー0000", null), is(INVALID));
    }

    /**
     * 
     */
    @SuppressWarnings({ "boxing", "nls" })
    @Test
    public void _ハイフンを除いて10桁あるいは11桁の文字列はvalid() {
        assertThat(this.sut.isValid("092-123-1234", null), is(VALID));
        assertThat(this.sut.isValid("092-1230-1234", null), is(VALID));
        assertThat(this.sut.isValid("092-12301234", null), is(VALID));
        assertThat(this.sut.isValid("092-1231234", null), is(VALID));
        assertThat(this.sut.isValid("0921231234", null), is(VALID));
        assertThat(this.sut.isValid("09212341234", null), is(VALID));
    }

    /**
     * 
     */
    @SuppressWarnings({ "nls", "boxing" })
    @Test
    public void _ハイフンを除いて12桁の文字列はinvalid() {
        assertThat(this.sut.isValid("092-123-124111", null), is(INVALID));
        assertThat(this.sut.isValid("092-131234111", null), is(INVALID));
        assertThat(this.sut.isValid("092131231141", null), is(INVALID));
    }

    /**
     * 
     */
    @SuppressWarnings({ "nls", "boxing" })
    @Test
    public void _ハイフンを除いて9桁の文字列はinvalid() {
        assertThat(this.sut.isValid("092-123-124", null), is(INVALID));
        assertThat(this.sut.isValid("092-131234", null), is(INVALID));
        assertThat(this.sut.isValid("092131234", null), is(INVALID));
    }

    /**
     * 
     */
    @Before
    public void setUp() {
        this.sut = new PhoneNumber.Validator();
    }
}
