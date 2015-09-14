import ca.carleton.poker.service.input.InputService;
import ca.carleton.poker.service.input.impl.DefaultInputService;
import ca.carleton.poker.service.input.impl.InputServiceFactory;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Pretty pointless, but we'll test this nonetheless.
 * <p>
 * Created by Mike on 14/09/2015.
 */
public class InputServiceTest {

    @Test
    public void canGetDefaultInput() {
        final InputService service = InputServiceFactory.getInputService();
        assertThat(service, is(not(nullValue())));
        assertThat(service, is(instanceOf(DefaultInputService.class)));
    }
}
