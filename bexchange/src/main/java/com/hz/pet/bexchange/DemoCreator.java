package com.hz.pet.bexchange;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.hz.pet.bexchange.domain.advt.Advt;
import com.hz.pet.bexchange.domain.advt.AdvtLocation;
import com.hz.pet.bexchange.domain.advt.AdvtRepository;
import com.hz.pet.bexchange.domain.user.User;
import com.hz.pet.bexchange.domain.user.UserRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.*;
import java.util.stream.Stream;

import static com.google.common.collect.Iterables.get;
import static com.google.common.collect.Sets.newHashSet;
import static com.hz.pet.bexchange.domain.Advt.AdvtStatus.ACTIVE;
import static com.hz.pet.bexchange.domain.Advt.AdvtStatus.DONE;
import static com.hz.pet.bexchange.domain.Advt.AdvtType.BUY;
import static com.hz.pet.bexchange.domain.Advt.AdvtType.SELL;
import static com.hz.pet.bexchange.domain.Advt.Currency.values;

/**
 * @author Herman Zamula
 */
@Component
public class DemoCreator {

    private static final ImmutableSet<String> COMMENTS = ImmutableSet.of(
            "Срочно",
            "Б*я касса горит",
            "Вася, срочно нужно нужно обменять",
            "Стою в здании СБУ",
            "Выручайте!",
            "Гонтаревой нужно, норм курс");

    static {

        final HashSet<String> builder = newHashSet();

        Stream.generate(() -> String.format("0%09d", new Random().nextInt(1_000_000_000)))
                .filter((n) -> !builder.contains(n))
                .limit(100)
                .forEach(builder::add);

        phoneNumbers = ImmutableSet.copyOf(builder);

    }

    private static final Set<String> phoneNumbers;
    private static final Map<String, Set<String>> locations = ImmutableMap.of(
            "Киев", ImmutableSet.of("Оболонский", "Деснянский", "Печерский", "Дарницкий", "Соломенский"),
            "Харьков", ImmutableSet.of("Киевский", "Дзержинсий", "Октябрьский", "ХТЗ", "Салтовка")
    );

    @Inject
    private UserRepository userRepository;

    @Inject
    private AdvtRepository advtRepository;


    @PostConstruct
    public void load() {

        Random random = new Random();

        phoneNumbers.stream()
                .map(number -> userRepository.save(new User(number, "pwd")))
                .forEach(user -> {

                    Advt advt = new Advt();
                    advt.setAdvtStatus(random.nextBoolean() ? ACTIVE : DONE);
                    advt.setAdvtType(random.nextBoolean() ? SELL : BUY);
                    advt.setCreationTime(new Date());
                    advt.setCurrency(values()[random.nextInt(4)]);
                    advt.setAmount((long) random.nextInt(100000));
                    advt.setCreator(user);
                    advt.setExchangeRate(random.nextBoolean() ? 25.05f : 28.9f);

                    AdvtLocation advtLocation = getAdvtLocation(random);
                    advt.setLocation(advtLocation);

                    advt.setComment(get(COMMENTS, random.nextInt(COMMENTS.size())));

                    advtRepository.save(advt);
                });


    }

    private AdvtLocation getAdvtLocation(Random random) {
        final String city = random.nextBoolean() ? "Киев" : "Харьков";
        AdvtLocation advtLocation = new AdvtLocation();
        advtLocation.setCity(city);
        final Set<String> areas = locations.get(city);
        advtLocation.setArea(get(areas, random.nextInt(areas.size())));
        return advtLocation;
    }

}
