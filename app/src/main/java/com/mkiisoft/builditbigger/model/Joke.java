package com.mkiisoft.builditbigger.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class Joke {

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
    abstract static class Builder {
        abstract Builder id (int id);
        abstract Builder type(String type);
        abstract Builder setup(String setup);
        abstract Builder punchline(String punchline);
        abstract Joke build();
    }
}
