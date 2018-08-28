/*
 * CREDIT SUISSE IS WILLING TO LICENSE THIS SPECIFICATION TO YOU ONLY UPON THE CONDITION THAT YOU
 * ACCEPT ALL OF THE TERMS CONTAINED IN THIS AGREEMENT. PLEASE READ THE TERMS AND CONDITIONS OF THIS
 * AGREEMENT CAREFULLY. BY DOWNLOADING THIS SPECIFICATION, YOU ACCEPT THE TERMS AND CONDITIONS OF
 * THE AGREEMENT. IF YOU ARE NOT WILLING TO BE BOUND BY IT, SELECT THE "DECLINE" BUTTON AT THE
 * BOTTOM OF THIS PAGE. Specification: JSR-354 Money and Currency API ("Specification") Copyright
 * (c) 2012-2013, Credit Suisse All rights reserved.
 */
package javax.money;

import org.testng.annotations.Test;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import static org.testng.Assert.*;

/**
 * Test class for {@link javax.money.AbstractQuery} and  {@link javax.money.AbstractQueryBuilder}.
 */
public class AbstractQueryTest {

    private AbstractQueryBuilder<AbstractQueryBuilder, AbstractQuery> createBuilder() {
        return new AbstractQueryBuilder() {

            @Override
            public AbstractQuery build() {
                return new AbstractQuery(this) {
                };
            }
        };
    }

    @Test
    public void testGetProviders() throws Exception {
        AbstractQueryBuilder b = createBuilder();
        b.setProviderNames("1", "2", "3");
        AbstractQuery query = b.build();
        assertNotNull(query);
        List<String> providers = query.getProviderNames();
        assertNotNull(providers);
        assertTrue(providers.size() == 3);
        assertTrue(providers.contains("1"));
        assertTrue(providers.contains("2"));
        assertTrue(providers.contains("3"));
    }

    @Test
    public void testGetTargetType() throws Exception {
        AbstractQueryBuilder b = createBuilder();
        b.setTargetType(String.class);
        AbstractQuery query = b.build();
        assertEquals(query.getTargetType(), String.class);
    }

    @Test
    public void testGetTimestampMillis() throws Exception {
        AbstractQueryBuilder b = createBuilder();
        b.setTimestampMillis(200L);
        AbstractQuery query = b.build();
        assertEquals(query.getTimestampMillis().longValue(), 200L);
        LocalDateTime localDateTime = LocalDateTime.parse("1969-12-31T21:00:00.200");
        assertEquals(query.getTimestamp(), localDateTime);
    }

    @Test
    public void testGetTimestamp() throws Exception {
        AbstractQueryBuilder b = createBuilder();
        LocalDateTime instant = LocalDateTime.now();
        b.setTimestamp(instant);
        AbstractQuery query = b.build();
        assertEquals(query.getTimestamp(), instant);
        assertEquals(query.getTimestamp(), instant);
    }
}
