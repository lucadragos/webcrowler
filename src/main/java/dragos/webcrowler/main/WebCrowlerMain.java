package dragos.webcrowler.main;

import java.util.Optional;

public class WebCrowlerMain {
    private static final int NO_ARGS_CONSTANT = 0;
    private static final String DEFAULT_ROOT_LINK = "http://wiprodigital.com";
    private static final int LINK_PARAM_INDEX = 0;

    public static void main(String[] args) {
        Optional<String> rootLinkOptional = Optional.empty();
        if (args.length == NO_ARGS_CONSTANT) {
            rootLinkOptional = Optional.of(DEFAULT_ROOT_LINK);
        }
        rootLinkOptional = Optional.of(args[LINK_PARAM_INDEX]);

        //start processing
    }
}
