package org.eluder.score.tables.rest.springmvc;

import org.eluder.jetty.server.EmbeddedJetty;
import org.eluder.jetty.server.ServerConfig;

public class ScoreTablesSpringMvcApp {
    public static void main(final String[] args) throws Exception {
        new EmbeddedJetty(new ServerConfig().setClassPath(true)).run();
    }
}
