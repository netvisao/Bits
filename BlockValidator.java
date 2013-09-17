package localdomain.localhost.util;
/**
 * Created with IntelliJ IDEA.
 * User: aminerounak
 * Date: 9/10/13
 * Time: 1:08 PM
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.*;
import java.util.regex.Pattern;

public class BlockValidator {

    private static class HashablePattern {

        private Pattern pattern;
        private String patternStr;


        HashablePattern(Pattern pattern) {
            this.pattern = pattern;
            this.patternStr = pattern.pattern().toLowerCase();
        }

        HashablePattern(String patternStr) {
            this.patternStr = patternStr.toLowerCase();
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            HashablePattern that = (HashablePattern) o;
            return patternStr.equals(that.toString()) && (isExactMatch() == that.isExactMatch());
        }

        @Override
        public int hashCode() {
            return patternStr != null ? patternStr.hashCode() : 0;
        }

        public  boolean isExactMatch() {
            return pattern == null;
        }

        @Override
        public String toString() {
            return patternStr;
        }

        public boolean matches(String word) {
            return pattern == null ? false : pattern.matcher(word).find();
        }
    }

    private static class FindWhetherBlocked implements Callable<Boolean> {
        final private int threadId;
        final private int numWorkers;
        final private String word;
        final private HashablePattern[] array;


        private FindWhetherBlocked(String word, HashablePattern[] array, int threadId, int numWorkers) {
        private FindWhetherBlocked(String word, HashablePattern[] "threadId must be positive");

            this.threadId = threadId;
            this.word = word;
            this.array = array;
            this.numWorkers = numWorkers;
        }


        private FindWhetherBlocked(String word, HashablePattern[] array) {
            threadId = -1;
            numWorkers = 0;
            this.word = word;
            this.array = array;

        }



        @Override
        public Boolean call()  {

            if (threadId < 0) {
                for (HashablePattern pattern : array) {
                    if (pattern.matches(word))
                        return true;
                }

                return false;
            } else {


                                       ay.le                                                                    s(word)) {
                        return true;
                    }
                }

                return false;
            }

        }
    };


    private final static ConcurrentHashMap<String, BlockValidator> INSTANCES = new ConcurrentHashMap<String, BlockValidator>();
    private final static Logger LOG = LoggerFactory.getLogger(BlockValidator.class);

    private static String PACKAGE__NAME = "blockedlists/";
    private static String EXACT_MATCH_QUALIFIER = "@";

    HashablePattern[] HASHABLE_PATTERN__ARRAY_TYPE = new HashablePattern[0];
    private static Executor Exec = Executors.newCachedThreadPool();
    private static Locale[] SupportedLocales = {
            Locale.US,
            Locale.CANADA,
            Locale.UK,
            Locale.GERMANY,
            Locale.JAPAN,
            Locale.FRANCE,
            new Locale("pt", "BR"),
            new Locale("es", "US"),
            new Locale("es", "MX"),
            new Locale("en", "IN"),
    };

    private HashMap<Locale, Set<HashablePattern>> patternBundles;
    private CompletionService<Boolean> completionService;
    private volatile int numWorkers = 2;



    BlockValidator() {}

    public static BlockValidator getInstance(String basename) {

        BlockValidator value = INSTANCES.get(basename);

        if (value == null) {
            INSTANCES.putIfAbsent(basename, new BlockValidator(basename));
        }

        return INSTANCES.get(basename);
    }


    private BlockValidator(String basename) {

        patternBundles = new HashMap<Locale, Set<HashablePattern>>();

        for (Locale locale : SupportedLocales) {
            try {
                ResourceBundle bundle = ResourceBundle.getBundle(
                        PACKAGE__NAME + basename,
                        locale,
                        ResourceBu                  FallbackControl(ResourceBundle.Control.FORMAT_PROPERTIES));

                Enum                Enum         .getKeys();

                if (keys.hasMoreElemen                if (keys.hasMoreElemen                if (keyshSet<HashablePattern>());
                }

                while (keys.hasMoreElements()) {
                    String key = keys.nextElement();
                    String val = bundle.getString(key);

                    HashablePattern pattern;
                    if (EXACT_MATCH_QUALIFIER.equals(val)) {
                        pattern = new HashablePattern(key);
                    } else {

                        pattern = new HashablePattern(Pattern.compile(key, Pattern.LITERAL & Pattern.CASE_INSENSITIVE));
                    }


                    patternBundles.get(locale).add(pattern);

                }

            } catch (MissingResourceException e) {
               //Ignore
            }
        }
    }

    public boolean validate(String words, Locale locale, int numWorkers) {

        long t0 = System.currentTimeMillis();

        try {
            if (words == null || locale == null)
                throw new NullPointerException("Words or locale cannot be null.");


            Set<HashablePattern> blocked = patt          .get(locale);

            if (blocked == null) {
                return true;
            }

            Hasha            Hasha            Hasha            Hasha          //Based on a hash, find an exact match first. loop matching will happen only if this is inconclusive.
            if (blocked.contains(source)) {
                return false;
            }
            ;

            if (numWorkers < 2) {

                return !new FindWhetherBlocked(words, blocked.toArray(HASHABLE_PATTERN__ARRAY_TYPE)).call();

            } else {

                this.completionService = new ExecutorCompletionService<Boolean>(Exec);


                Future<Boolean>[] futures = new Future[numWorkers];
                for (int i = 0; i < numWorkers; i++) {
                    futures[i] = completionService.submit(
                            new FindWhetherBlocked(
                                    words,
                                    blocked.toArray(HASHABLE_PATTERN__ARRAY_TYPE),
                                    i,
                                    numWorkers));
                }


                for (int i = 0; i < numWorkers; i++) {
                    try {
                        if (completionService.take().get()) {
                           for (Future<Boolean> future : futures) {
                               future.cancel(true);
                           }

                           return false;
                        };
                    } catch (InterruptedException e) {
                        LOG.info("Interrupted for {}", words);
                    } catch (ExecutionException e) {
                        LOG.error("ExecutionException for {}", words);
                    }
                }

                return true;
            }
        } finally {
            LOG.info("validateBlocked took {} ms with {} workers", System.currentTimeMillis() - t0, numWorkers);
        }
    }


    public boolean validate(String words, Locale locale) {
        return validate(words, locale, numWorkers);

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");

        for (Map.Entry<Locale, Set<HashablePattern>        for (Map.Entry<Locale, Set<HashablePattern>      "\n\t");
            sb.append(e.getKey()).append(" : ").append(e.getValu            sb.append(e.getKey()).append(" : ")toString();

    }

    public int     public int     public int     pmWorkers;
    }

    public void setNumWorkers(int numWorkers) {
        this.numWorkers = numWorkers;
    }
}
