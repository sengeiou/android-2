/*
 * Copyright © 2018 Zhenjie Yan.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zhuj.android.http.request;

import android.net.Uri;
import android.text.TextUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.FormBody;

/**
 * Created by Zhenjie Yan on 2018/2/23.
 */
public class Params {

    public static Builder newBuilder() {
        return new Builder();
    }

    private final Map<String, List<Object>> mMap;

    private Params(Builder builder) {
        this.mMap = builder.mMap;
    }

    /**
     * Get parameters by key.
     *
     * @param key key.
     *
     * @return if the key does not exist, it may be null.
     */
    public List<Object> get(String key) {
        return mMap.get(key);
    }

    /**
     * Get the first value of the key.
     *
     * @param key key.
     *
     * @return if the key does not exist, it may be null.
     */
    public Object getFirst(String key) {
        List<Object> values = mMap.get(key);
        if (values != null && values.size() > 0) return values.get(0);
        return null;
    }

    /**
     * Get {@link Set} view of the parameters.
     *
     * @return a set view of the mappings.
     *
     * @see Map#entrySet()
     */
    public Set<Map.Entry<String, List<Object>>> entrySet() {
        return mMap.entrySet();
    }

    /**
     * Get {@link Set} view of the keys.
     *
     * @return a set view of the keys.
     *
     * @see Map#keySet()
     */
    public Set<String> keySet() {
        return mMap.keySet();
    }

    /**
     * No parameters.
     *
     * @return true if there are no key-values pairs.
     *
     * @see Map#isEmpty()
     */
    public boolean isEmpty() {
        return mMap.isEmpty();
    }

    /**
     * Parameters contains the key.
     *
     * @param key key.
     *
     * @return true if there contains the key.
     */
    public boolean containsKey(String key) {
        return mMap.containsKey(key);
    }


    /**
     * ReBuilder.
     */
    public Builder builder() {
        Map<String, List<Object>> map = new LinkedHashMap<>();
        for (Map.Entry<String, List<Object>> entry : mMap.entrySet()) {
            map.put(entry.getKey(), new ArrayList<>(entry.getValue()));
        }
        return new Builder(map);
    }

    @Override
    public String toString() {
        return toString(false);
    }

    public String toString(boolean encode) {
        StringBuilder builder = new StringBuilder();
        Set<String> keySet = keySet();
        for (String key : keySet) {
            List<Object> values = get(key);
            for (Object value : values) {
                if (value instanceof CharSequence) {
                    String textValue = encode ? Uri.encode(value.toString()) : value.toString();
                    builder.append("&").append(key).append("=").append(textValue);
                }
            }
        }
        if (builder.length() > 0) builder.deleteCharAt(0);
        return builder.toString();
    }

    public static class Builder {

        private Map<String, List<Object>> mMap;

        private Builder() {
            this.mMap = new LinkedHashMap<>();
        }

        private Builder(Map<String, List<Object>> map) {
            this.mMap = map;
        }
        /**
         * Remove parameters by key.
         */
        public Builder remove(String key) {
            mMap.remove(key);
            return this;
        }

        /**
         * Remove all parameters.
         */
        public Builder clear() {
            mMap.clear();
            return this;
        }

        public Params build() {
            return new Params(this);
        }
    }
}