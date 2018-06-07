package com.mkiisoft.builditbigger.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class Joke {

    private static final int DEFAULT_ID = 0;
    private static final String DEFAULT_TYPE = "joke";
    private static final String DEFAULT_SETUP = "init";
    private static final String DEFAULT_PUNCHLINE = "final";

    @SerializedName("id")
    public abstract int id();
    @SerializedName("type")
    public abstract String type();
    @SerializedName("setup")
    public abstract String setup();
    @SerializedName("punchline")
    public abstract String punchline();

    public static TypeAdapter<Joke> typeAdapter(Gson gson) {
        return new AutoValue_Joke.GsonTypeAdapter(gson);
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder id (int id);
        public abstract Builder type(String type);
        public abstract Builder setup(String setup);
        public abstract Builder punchline(String punchline);
        public abstract Joke build();
    }

    @SuppressWarnings("unused")
    public static Builder builder() {
        return new AutoValue_Joke.Builder()
                .id(DEFAULT_ID)
                .type(DEFAULT_TYPE)
                .setup(DEFAULT_SETUP)
                .punchline(DEFAULT_PUNCHLINE);
    }
}