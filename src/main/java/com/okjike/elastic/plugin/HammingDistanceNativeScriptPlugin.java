package com.okjike.elastic.plugin;

import com.okjike.elastic.plugin.hamming.HammingDistanceService;
import org.elasticsearch.common.inject.internal.Nullable;
import org.elasticsearch.common.xcontent.support.XContentMapValues;
import org.elasticsearch.plugins.Plugin;
import org.elasticsearch.plugins.ScriptPlugin;
import org.elasticsearch.script.AbstractDoubleSearchScript;
import org.elasticsearch.script.ExecutableScript;
import org.elasticsearch.script.NativeScriptFactory;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class HammingDistanceNativeScriptPlugin extends Plugin implements ScriptPlugin {
    private static final String NAME = "hamming_distance_native";

    @Override
    public List<NativeScriptFactory> getNativeScripts() {
        return Collections.singletonList(new HammingNativeScriptFactory());
    }

    public static class HammingNativeScriptFactory implements NativeScriptFactory {
        @Override
        public ExecutableScript newScript(@Nullable Map<String, Object> params) {
            String hash = params == null ? null :
                    XContentMapValues.nodeStringValue(params.get("hash"), null);
            String field = params == null ? null :
                    XContentMapValues.nodeStringValue(params.get("field"), null);
            if (hash == null) {
                throw new IllegalArgumentException("Missing parameter [hash]");
            }
            if (field == null) {
                throw new IllegalArgumentException("Missing parameter [field]");
            }
            return new HammingNativeScript(hash, field);
        }

        @Override
        public boolean needsScores() {
            return false;
        }

        @Override
        public String getName() {
            return NAME;
        }
    }

    public static class HammingNativeScript extends AbstractDoubleSearchScript {

        private final String hash;
        private final String field;

        HammingNativeScript(String hash, String field) {
            this.hash = hash;
            this.field = field;
        }

        @Override
        public double runAsDouble() {
            String sourceHash = (String) source().get(field);
            int distance = HammingDistanceService.hammingDistanceScore(hash, sourceHash);
            return distance;
        }
    }
}
