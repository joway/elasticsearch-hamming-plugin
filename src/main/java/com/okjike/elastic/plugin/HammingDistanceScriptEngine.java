//package com.okjike.elastic.plugin;
//
//import org.elasticsearch.common.inject.internal.Nullable;
//import org.elasticsearch.common.xcontent.support.XContentMapValues;
//import org.elasticsearch.script.CompiledScript;
//import org.elasticsearch.script.ExecutableScript;
//import org.elasticsearch.script.ScriptEngineService;
//import org.elasticsearch.script.SearchScript;
//import org.elasticsearch.search.lookup.SearchLookup;
//
//import java.util.Map;
//import java.util.function.Function;
//
//public class HammingDistanceScriptEngine implements ScriptEngineService {
//    private static final String NAME = "hamming_distance_script_engine";
//
//    @Override
//    public String getType() {
//        return NAME;
//    }
//
//    @Override
//    public String getExtension() {
//        return getType();
//    }
//
//    @Override
//    public Object compile(String scriptName, String scriptSource, Map<String, String> params) {
//        if (!scriptName.equals(NAME)){
//            throw new IllegalArgumentException("Unknown script name " + scriptSource);
//        }
//        String hash = params == null ? null :
//                XContentMapValues.nodeStringValue(params.get("hash"), null);
//        String field = params == null ? null :
//                XContentMapValues.nodeStringValue(params.get("field"), null);
//        if (hash == null) {
//            throw new IllegalArgumentException("Missing parameter [hash]");
//        }
//        if (field == null) {
//            throw new IllegalArgumentException("Missing parameter [field]");
//        }
//        return new HammingDistanceNativeScriptPlugin.HammingNativeScript(hash, field);
//    }
//
//
//    @Override
//    @SuppressWarnings("unchecked")
//    public SearchScript search(CompiledScript compiledScript, SearchLookup lookup, @Nullable Map<String, Object> params) {
//        Function<Map<String, Object>, SearchScript> scriptFactory = (Function<Map<String, Object>, SearchScript>) compiledScript.compiled();
//        return scriptFactory.apply(params);
//    }
//
//    @Override
//    public ExecutableScript executable(CompiledScript compiledScript, @Nullable Map<String, Object> params) {
//        throw new UnsupportedOperationException();
//    }
//
//    @Override
//    public boolean isInlineScriptEnabled() {
//        return true;
//    }
//
//    @Override
//    public void close() {
//    }
//}
