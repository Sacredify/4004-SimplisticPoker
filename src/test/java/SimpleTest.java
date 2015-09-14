import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Example of a junit test.
 *
 * Created by Mike on 14/09/2015.
 */
public class SimpleTest {

    @Test
    public void canTestNothing() throws Exception {
        assertThat(true, is(true));
    }
}
