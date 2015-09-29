package com.example.Guanqing.myapplication.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import java.util.logging.Logger;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "myJokesApi",
        version = "v1",
        resource = "myJokes",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.Guanqing.example.com",
                ownerName = "backend.myapplication.Guanqing.example.com",
                packagePath = ""
        )
)
public class MyJokesEndpoint {

    private static final Logger logger = Logger.getLogger(MyJokesEndpoint.class.getName());

    /**
     * This method gets the <code>MyJokes</code> object.
     *
     * @return The <code>MyJokes</code>.
     */
    @ApiMethod(name = "getMyJokes")
    public MyJokes getMyJokes() {
        logger.info("Calling getMyJokes method");
        return new MyJokes();
    }
}