package com.example.mkiisoft.endpoints.backend;

/*
Copyright 2017 Google Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.mkiisoft.java.JokesLibrary;

import javax.inject.Named;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.endpoints.mkiisoft.example.com",
                ownerName = "backend.endpoints.mkiisoft.example.com"
        )
)
public class MyEndpoint {

    @ApiMethod(name = "getRandomJoke")
    public MyBean getRandomJoke() {
        JokesLibrary jokeTeller = new JokesLibrary();
        MyBean response = new MyBean();
        response.setData(jokeTeller.generateJoke());
        return response;
    }
}
